package core.annotations;


import java.util.Arrays;
import java.util.HashMap;
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

    public String getColumnValues(Object object) {
        HashMap<String, String> fieldsInfo = getColumnsInfo(object);

        String columns = fieldsInfo.values()
                .stream()
                .map(value -> {
                    if (value != null && value.matches(".*\\D.*")) {
                        return "'" + value + "'";
                    } else {
                        return value;
                    }
                })
                .collect(Collectors.joining(", ", "(", ")"));

        return columns;
    }

    public String getColumns(Object object) {
        HashMap<String, String> fieldsInfo = getColumnsInfo(object);
        String columns = fieldsInfo.keySet()
                .stream()
                .collect(Collectors.joining(", ", "(", ")"));

        return columns;
    }
}

