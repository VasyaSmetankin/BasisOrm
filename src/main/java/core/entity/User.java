package core.entity;


import core.annotations.Field;
import core.annotations.Table;

@Table(tableName = "user", schema = "test")
public class User {
    int id;

    @Field(name = "user_name", type = "VARCHAR")
    String username;
    int age;

    public User() {
        this.id = 1;
        this.username = "John";
        this.age = 30;
    }
}
