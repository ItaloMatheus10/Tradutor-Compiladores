package com.compiladores;

public class Parser {
    
    private Scanner scan;
    private char currentToken;

    public Parser(byte[] input) {
        scan = new Scanner(input);
        currentToken = scan.nextToken();

    } 
}    