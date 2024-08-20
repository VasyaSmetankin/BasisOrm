package core.entity;


import core.annotations.Column;
import core.annotations.PrimaryKey;
import core.annotations.Table;

import java.util.Optional;

@Table(tableName = "user", schema = "test")
public class User {
    @PrimaryKey(autoIncrement = true)
    Optional<Integer> id;

    @Column(name = "name", type = "VARCHAR")
    String username;
    int age;

    public User(int id, String username, int age) {
        this.id = id == 0 ? null : Optional.of(id);
        this.username = "John";
        this.age = 30;
    }

    public User(String username, int age) {
        this.id = null;
        this.username = username;
        this.age = age;
    }
}
