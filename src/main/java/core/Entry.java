package core;

import core.annotations.AnnotationAnalyzer;
import core.connection.Session;
import core.entity.User;

import java.util.HashMap;

public class Entry {

    public static void main(String[] args) {
        User user = new User();
        AnnotationAnalyzer analyzer = new AnnotationAnalyzer(user);
        HashMap<String, String> fields = analyzer.getFieldsInfo();
        System.out.println("=====================================");

        fields.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}

