package core.statements;

public class Values implements Statement {




    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getStatement() {
        return "";
    }

    @Override
    public String getValue() {
        return "";
    }

    @Override
    public void setValue(String value) {

    }
}
