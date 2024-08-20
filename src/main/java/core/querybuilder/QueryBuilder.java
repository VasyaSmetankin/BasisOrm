package core.querybuilder;

import core.annotations.AnnotationAnalyzer;
import core.connection.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.SplittableRandom;

public class QueryBuilder {
    private List<String> query;
    private AnnotationAnalyzer annotationAnalyzer = new AnnotationAnalyzer();
    private ManualQueryBuilder manualQueryBuilder = new ManualQueryBuilder();

    public QueryBuilder() {
    }

    public String Add(Object object) {
        String table = annotationAnalyzer.getTableInfo(object);
        String columns = annotationAnalyzer.getColumns(object);
        String values = annotationAnalyzer.getColumnValues(object);
        String result = manualQueryBuilder.insert().into(table, columns).values(values).assembly();
        return result;
    }

    public void Remove() {

    }

    public void Update() {

    }

    public void Select() {

    }
}
