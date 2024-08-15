package core.querybuilder;

import core.statements.From;
import core.statements.Select;
import core.statements.Statement;
import core.statements.Where;
import java.util.ArrayList;
import java.util.List;



@SuppressWarnings("checkstyle:Indentation")
public class ManualQueryBuilder {
    private final List<Statement> query = new ArrayList<>();

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
        query.forEach(item -> System.out.println(item.getStatement() + " " + item.getValue() + " "));
    }

    public String assembly() {
        QueryValidator.instance.validate(query);
        sort();
        StringBuilder result = new StringBuilder();
        return "";
    }
}
