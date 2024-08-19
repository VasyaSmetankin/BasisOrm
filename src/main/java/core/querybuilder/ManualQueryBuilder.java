package core.querybuilder;

import core.statements.*;

import java.util.ArrayList;
import java.util.List;



@SuppressWarnings("checkstyle:Indentation")
public class ManualQueryBuilder {
    private final List<Statement> query = new ArrayList<>();

    public ManualQueryBuilder insert(String value) {
        Insert insert = new Insert(value);
        query.add(insert);
        return this;
    }

    public ManualQueryBuilder into(String value) {
        Into into = new Into(value);
        query.add(into);
        return this;
    }

    public ManualQueryBuilder select(String value) {
        Select select = new Select(value);
        query.add(select);

        return this;
    }

    public ManualQueryBuilder from(String value) {
        From from = new From(value);
        query.add(from);

        return this;
    }

    public ManualQueryBuilder where(String value) {
        Where where = new Where(value);
        query.add(where);

        return this;
    }

    private void sort() {
        query.sort((s1, s2) -> Integer.compare(s1.getPriority(), s2.getPriority()));
        //query.forEach(item -> System.out.println(item.getStatement() + " " + item.getValue() + " "));
    }

    public String assembly() {
        QueryValidator.instance.validate(query);
        sort();
        StringBuilder result = new StringBuilder();
        return "";
    }
}
