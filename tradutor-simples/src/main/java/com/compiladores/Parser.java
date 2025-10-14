package com.compiladores;

import com.compiladores.Scanner.Token;
import com.compiladores.Scanner.TokenType;

public class Parser {
    
    private Scanner scan;
    private Token currentToken;
    private StringBuilder output = new StringBuilder();

    public Parser(byte[] input) {
        scan = new Scanner(input);
        currentToken = scan.nextToken();

    } 

    private void nextToken () {
    currentToken = scan.nextToken();
    }

    public void parse () {
        letStatement();
    }
    
    private void match(TokenType t) {
        if (currentToken.type == t) {
            nextToken();
        }else {
            throw new Error("syntax error");
        }
    }

    void expr() {
        term ();
        oper();
    }

    void number () {
        System.out.println("push " + currentToken.lexeme);
        match(TokenType.NUMBER);
    }

    void oper () {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            term();
            System.out.println("add");
            oper();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            term();
            System.out.println("sub");
            oper();
        } 
    }

    void term () {
        if (currentToken.type == TokenType.NUMBER)
            number();
        else if (currentToken.type == TokenType.IDENT) {
            System.out.println("push "+currentToken.lexeme);
            match(TokenType.IDENT);
        }
        else
            throw new Error("syntax error");
    }

   void letStatement() {
        match(TokenType.LET);
        String id = currentToken.lexeme;
        match(TokenType.IDENT);
        match(TokenType.EQ);
        expr();
        output.append("pop ").append(id).append("\n");
        match(TokenType.SEMICOLON);
    }

    void printStatement() {
        match(TokenType.PRINT);
        expr();
        output.append("print\n");
        match(TokenType.SEMICOLON);
    }

    void statement() {
        if (currentToken.type == TokenType.PRINT) {
            printStatement();
        } else if (currentToken.type == TokenType.LET) {
            letStatement();
        } else {
            throw new Error("syntax error");
        }
    }

    void statements() {
        while (currentToken.type != TokenType.EOF) {
            statement();
        }
    }

    public String output() {
        return output.toString();
    }

}    