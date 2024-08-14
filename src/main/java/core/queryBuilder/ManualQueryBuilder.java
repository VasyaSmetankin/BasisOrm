package core.queryBuilder;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Getter
@Setter
public class ManualQueryBuilder {
    LinkedHashMap<String, List<String>> insertionQuery;

    public ManualQueryBuilder() {
        this.insertionQuery = new LinkedHashMap<>();
        insertionQuery.put("SELECT", null);
        insertionQuery.put("FROM", null);
    }

    public ManualQueryBuilder select(String[] columns) {
        List<String> select = new ArrayList<>();
        for (String c : columns) {
            select.add(c);
        }
        insertionQuery.put("SELECT", select);
        return this;
    }

    public ManualQueryBuilder from(String[] table) {
        List<String> from = new ArrayList<>();
        for (String t : table) {
            from.add(t);
        }
        insertionQuery.put("FROM", from);
        return this;
    }

    public String assembly() {
        StringBuilder queryBuilder = new StringBuilder();
        for(String key : insertionQuery.keySet()) {
            if (insertionQuery.get(key) != null) {
                queryBuilder.append(key).append(" ");
                for (String value : insertionQuery.get(key)) {
                    queryBuilder.append(value).append(", ");
                }
            }
        }
        return queryBuilder.toString().trim();
    }
}
