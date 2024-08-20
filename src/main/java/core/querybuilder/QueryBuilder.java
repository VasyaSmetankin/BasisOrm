package core.querybuilder;

import core.annotations.AnnotationAnalyzer;

import java.util.List;

public class QueryBuilder {
    private List<String> query;
    private AnnotationAnalyzer annotationAnalyzer = new AnnotationAnalyzer();
    private ManualQueryBuilder manualQueryBuilder = new ManualQueryBuilder();

    public QueryBuilder() {
    }

    public String create(Object object) {
        String table = annotationAnalyzer.getTableInfo(object);
        String columns = annotationAnalyzer.getColumns(object);
        String values = annotationAnalyzer.getColumnValues(object);
        String result = manualQueryBuilder.insert().into(table, columns).values(values).assembly();
        return result;
    }

    public String delete(Object object) {
        String table = annotationAnalyzer.getTableInfo(object);
        String columns = annotationAnalyzer.getColumns(object);
        String values = annotationAnalyzer.getColumnValues(object);
        String result = manualQueryBuilder.delete().from(table).where(columns + " = " + values).assembly();
        return result;
    }

    public String update(Object object) {
        String table = annotationAnalyzer.getTableInfo(object);
        String columns = annotationAnalyzer.getColumns(object);
        String values = annotationAnalyzer.getColumnValues(object);
        Object id = annotationAnalyzer.getPrimaryKeyValue(object);
        String result = manualQueryBuilder.update(table).set(columns + " = " + values).where("id = " + id).assembly();
        System.out.println(result);

        return result;
    }

    public String read(Object object) {

        return "";
    }

    public void clearQuery() {
        query.clear();
    }
}
