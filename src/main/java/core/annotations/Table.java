package core.annotations;

public @interface Table {
    String schema() default "postgres";
    String tableName();
}
