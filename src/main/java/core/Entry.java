package core;

import core.queryBuilder.ManualQueryBuilder;

public class Entry {

    public static void main(String[] args) {
        ManualQueryBuilder builder = new ManualQueryBuilder();
        String query = builder
                .select(new String[]{"name", "age"})
                .from(new String[]{"users"})
                .assembly();
        System.out.println(query);
    }
}

