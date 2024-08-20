package core.statements;

public class Delete implements Statement {
    private int PRIORITY = 1;
    private String STATEMENT = "DELETE";
    private String value;

    public Delete() {
        this.value = "";
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
        this.value = "";
    }
}
