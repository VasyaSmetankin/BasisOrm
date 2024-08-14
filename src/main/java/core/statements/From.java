package core.statements;

public class From implements Statement {
    private int PRIORITY = 2;
    private String STATEMENT = "FROM";
    private String value;

    public From(String value) {
        this.value = value;
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public String getStatement() {
        return STATEMENT;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
