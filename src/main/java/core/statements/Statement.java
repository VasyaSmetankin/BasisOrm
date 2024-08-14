package core.statements;

public interface Statement {
    int getPriority();
    String getStatement();
    String getValue();
    void setValue(String value);

}
