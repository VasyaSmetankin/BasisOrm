package core.querybuilder;

import com.sun.source.tree.BreakTree;
import core.statements.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@SuppressWarnings("checkstyle:Indentation")
public class ManualQueryBuilder {
    private final List<Statement> query = new ArrayList<>();

    public ManualQueryBuilder insert() {
        Insert insert = new Insert();
        query.add(insert);
        return this;
    }

    public ManualQueryBuilder into(String table, String columns) {
        StringBuilder builder = new StringBuilder();
        builder.append(table);
        builder.append(" ");
        builder.append(columns);
        Into into = new Into(builder.toString());
        query.add(into);
        return this;
    }

    public ManualQueryBuilder values(String values) {
        Values value = new Values(values);
        query.add(value);
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
    }

    public String assembly() {
        sort();
        QueryValidator.instance.validate(query);
        StringBuilder builder = new StringBuilder();

        String result = query.stream()
                .map(item -> item.getStatement() + " " + item.getValue())
                .collect(Collectors.joining(" "));

        builder.append(result);
        System.out.println(builder.toString());
        return builder.toString();
    }
}
