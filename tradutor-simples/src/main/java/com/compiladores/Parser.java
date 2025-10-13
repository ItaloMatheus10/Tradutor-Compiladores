package com.compiladores;

public class Parser {
    
    private Scanner scan;
    private char currentToken;

    public Parser(byte[] input) {
        scan = new Scanner(input);
        currentToken = scan.nextToken();

    } 

    private void nextToken () {
    currentToken = scan.nextToken();
    }
    
    private void match(char t) {
        if (currentToken == t) {
            nextToken();
        }else {
            throw new Error("syntax error");
        }
    }






}    