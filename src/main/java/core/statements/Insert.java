package core.statements;

public class Insert implements Statement {
    private int PRIORITY = 1;
    private String STATEMENT = "INSERT";
    private String value;

    public Insert() {
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
        return "";
    }

    @Override
    public void setValue(String value) {
    }
}
