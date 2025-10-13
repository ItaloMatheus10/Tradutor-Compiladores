package com.compiladores;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = "8*9";
        Parser p = new Parser (input.getBytes());
        p.parse();

    }
}
