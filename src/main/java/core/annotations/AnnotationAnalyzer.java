package core.annotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("checkstyle:Indentation")
public class AnnotationAnalyzer {
    Object object;

    public AnnotationAnalyzer(Object object) {
        this.object = object;
    }

    public HashMap<String, String> getTableInfo() {
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

    public HashMap<String, String> getFieldsInfo() {
        HashMap<String, String> fieldsInfo = new HashMap<>();
        Arrays.stream(object.getClass().getDeclaredFields()).forEach(field -> {
            try {
                field.setAccessible(true);

                String fieldName = field.isAnnotationPresent(Field.class) ?
                        field.getAnnotation(Field.class).name() :
                        field.getName();

                Object fieldValue = field.get(object);
                fieldsInfo.put(fieldName, fieldValue != null ? fieldValue.toString() : "null");

                System.out.println("Field name: " + fieldName + ", value: " + fieldValue);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        return fieldsInfo;
    }
}

