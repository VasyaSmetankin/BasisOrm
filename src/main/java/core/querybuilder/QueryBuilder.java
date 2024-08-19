package core.querybuilder;

import core.annotations.AnnotationAnalyzer;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class QueryBuilder {
    private List<String> query;
    private AnnotationAnalyzer annotationAnalyzer = new AnnotationAnalyzer();
    private ManualQueryBuilder manualQueryBuilder = new ManualQueryBuilder();

    public void Add(Object obj) {
        HashMap<String, String> columnData = annotationAnalyzer.getFieldsInfo(obj);
        HashMap<String, String> tableData = annotationAnalyzer.getTableInfo(obj);
    }

    public void Remove() {

    }

    public void Update() {

    }

    public void Select() {

    }
}
