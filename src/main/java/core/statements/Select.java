package core.statements;

public class Select implements Statement {
    private int PRIORITY = 1;
    private String STATEMENT = "SELECT";
    private String value;

    public Select(String value) {
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
