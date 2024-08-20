package core;

import core.annotations.AnnotationAnalyzer;
import core.connection.Session;
import core.entity.User;
import core.querybuilder.QueryBuilder;

public class Entry {

    public static void main(String[] args) {
        User user = new User("PIZDA", 1488);
        Session session = new Session();
        session.add(user);
    }
}

