package core.annotations;


import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings("checkstyle:Indentation")
public class AnnotationAnalyzer {
    public HashMap<String, String> getTableInfo(Object object) {
        HashMap<String, String> tableInfo = new HashMap<>();
        if (object.getClass().isAnnotationPresent(Table.class)) {
            Table table = object.getClass().getAnnotation(Table.class);
            tableInfo.put("schema", table.schema());
            System.out.println("schema: " + table.schema());
            tableInfo.put("tableName", table.tableName());
            System.out.println("tableName: " + table.tableName());
            return tableInfo;
        }
        throw new IllegalArgumentException("Table annotation is missing");
    }

    public HashMap<String, String> getFieldsInfo(Object object) {
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

                System.out.println(fieldName + ": " + fieldValue);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        return fieldsInfo;
    }
}

