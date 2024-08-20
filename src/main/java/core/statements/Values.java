package core.statements;

public class Values implements Statement {
    private int PRIORITY = 3;
    private String STATEMENT = "VALUES";
    private String value;

    public Values(String value) {
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

    }
}
