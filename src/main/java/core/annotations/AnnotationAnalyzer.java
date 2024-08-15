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
            if (field.isAnnotationPresent(Field.class)) {
                Field fieldAnnotation = field.getAnnotation(Field.class);
                fieldsInfo.put(field.getName(), fieldAnnotation.name());
                System.out.println("field name by annotation: " + fieldAnnotation.name());
            } else {
                fieldsInfo.put(field.getName(), field.getName());
                System.out.println("field name: " + field.getName());
            }
        });
        return fieldsInfo;
    }
}
