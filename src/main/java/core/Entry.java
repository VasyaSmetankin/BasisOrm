package core;

import core.querybuilder.ManualQueryBuilder;

public class Entry {

    public static void main(String[] args) {

        ManualQueryBuilder builder = new ManualQueryBuilder();
        builder.select("").where("");
        builder.assembly();
    }
}

