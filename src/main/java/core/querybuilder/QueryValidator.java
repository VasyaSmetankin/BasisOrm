package core.querybuilder;

import core.statements.Statement;
import java.util.HashMap;
import java.util.List;


@SuppressWarnings("checkstyle:Indentation")
public class QueryValidator {
    static QueryValidator instance = new QueryValidator();

    private QueryValidator() {
    }

    private boolean hasDuplicates(List<Statement> query) {
        HashMap<Integer, Statement> map = new HashMap<>();
        for (Statement obj : query) {
            if (map.containsKey(obj.getPriority())) {
                return true;
            }
            map.put(obj.getPriority(), obj);
        }

        return false;
    }

    private boolean hasMissingPriority(List<Statement> query) {
        query.sort((s1, s2) -> Integer.compare(s1.getPriority(), s2.getPriority()));
        for (int i = 0; i < query.size(); i++) {
            if (query.get(i).getPriority() != i + 1) {
                return true;
            }
        }
        return false;
    }

    public void validate(List<Statement> query) {
        if (query.isEmpty()) {
            throw new IllegalArgumentException("Query is empty");
        } else if (this.hasDuplicates(query)) {
            throw new IllegalArgumentException("Query has duplicate priorities");
        } else if (this.hasMissingPriority(query)) {
            throw new IllegalArgumentException("Query has missing priority");
        }
    }
}
