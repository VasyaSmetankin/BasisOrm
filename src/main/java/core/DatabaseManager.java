package core;


import lombok.*;

@Getter
@Setter
public class DatabaseManager {
    String url;
    String user;
    String password;

    public DatabaseManager() {
        this.url = "jdbc:postgresql://localhost:5432/basis_orm_test";
        this.user = "postgres";
        this.password = "root";
    }

}
