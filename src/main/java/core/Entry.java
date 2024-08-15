package core;

import core.annotations.AnnotationAnalyzer;
import core.entity.User;
import core.querybuilder.ManualQueryBuilder;
import core.querybuilder.QueryBuilder;

public class Entry {

    public static void main(String[] args) {
        User user = new User();

        AnnotationAnalyzer analyzer = new AnnotationAnalyzer(user);
        analyzer.getTableInfo();
        analyzer.getFieldsInfo();
    }
}

