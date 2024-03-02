package com.example.demo;

public class TestUtils {

    private static String graph = """
                       graph LR;
                       A--> B & C & D;
                       B--> A & E;
                       C--> A & E;
                       D--> A & E;
                       E--> B & C & D;
                       """;

    public static String getGraph(){
        return graph;
    }
}
