package core;

import core.querybuilder.ManualQueryBuilder;

public class Entry {

    public static void main(String[] args) {

        ManualQueryBuilder builder = new ManualQueryBuilder();
        builder.from("2").select("1").where("3");
        builder.assembly();
    }
}

