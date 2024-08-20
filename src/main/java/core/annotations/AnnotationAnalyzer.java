package core.annotations;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("checkstyle:Indentation")
public class AnnotationAnalyzer {
    public String getTableInfo(Object object) {
        HashMap<String, String> tableInfo = new HashMap<>();
        StringBuilder result = new StringBuilder();
        if (object.getClass().isAnnotationPresent(Table.class)) {
            Table table = object.getClass().getAnnotation(Table.class);
            result.append(table.schema());
            result.append(".");
            result.append(table.tableName());
            return result.toString();
        }
        throw new IllegalArgumentException("Table annotation is missing");
    }

    public HashMap<String, String> getColumnsInfo(Object object) {
        HashMap<String, String> fieldsInfo = new HashMap<>();

        Arrays.stream(object.getClass().getDeclaredFields()).forEach(field -> {
            try {
                field.setAccessible(true);
                Object fieldValue = field.get(object);

                if (fieldValue == null) {
                    return;
                }

                if (fieldValue instanceof Optional) {
                    fieldValue = ((Optional<?>) fieldValue).orElse(null);
                    if (fieldValue == null) {
                        return;
                    }
                }

                String fieldName = field.isAnnotationPresent(Column.class)
                        ? field.getAnnotation(Column.class).name()
                        : field.getName();

                fieldsInfo.put(fieldName, fieldValue.toString());

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return fieldsInfo;
    }

    public static String getPrimaryKeyValue(Object obj) {
        try {
            // Получаем все поля класса
            Field[] fields = obj.getClass().getDeclaredFields();

            // Ищем поле с аннотацией PrimaryKey
            for (Field field : fields) {
                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    // Делаем поле доступным для чтения, если оно private
                    field.setAccessible(true);

                    // Получаем значение поля
                    Object value = field.get(obj);

                    // Если значение является Optional, разворачиваем его
                    if (value instanceof Optional) {
                        Optional<?> optionalValue = (Optional<?>) value;
                        if (optionalValue.isPresent()) {
                            return optionalValue.get().toString();
                        } else {
                            return "null"; // Optional пуст
                        }
                    }

                    // Если значение не Optional, возвращаем его строковое представление
                    return value != null ? value.toString() : "null";
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // Если поле с аннотацией PrimaryKey не найдено
        return null;
    }

    public String getColumnValues(Object object) {
        HashMap<String, String> fieldsInfo = getColumnsInfo(object);

        return fieldsInfo.values()
                .stream()
                .map(value -> {
                    if (value != null && value.matches(".*\\D.*")) {
                        return "'" + value + "'";
                    } else {
                        return value;
                    }
                })
                .collect(Collectors.joining(", ", "(", ")"));
    }

    public String getColumns(Object object) {
        HashMap<String, String> fieldsInfo = getColumnsInfo(object);
        return fieldsInfo.keySet()
                .stream()
                .collect(Collectors.joining(", ", "(", ")"));
    }
}

