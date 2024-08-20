package core;

import core.annotations.AnnotationAnalyzer;
import core.connection.Session;
import core.entity.User;
import core.querybuilder.QueryBuilder;

public class Entry {

    public static void main(String[] args) {
        User user = new User("John1", 30);
        Session session = new Session();
        User user2 = new User(31,"John2", 33232);
        session.delete(user2);





    }
}

