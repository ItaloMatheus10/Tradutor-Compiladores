package com.compiladores;

public class Scanner {

    private byte[] input;
    private int current; 

	public Scanner (byte[] input) {
        this.input = input;
    }

    private char peek () {
        if (current < input.length)
           return (char)input[current];
       return '\0';
    }

    private void advance()  {
        char ch = peek();
        if (ch != '\0') {
            current++;
        }
    }

    public String nextToken () {
        char ch = peek();
        if (ch == '0') {
            advance();
            return Character.toString(ch);
        }  else if (Character.isDigit(ch))
            return number();

        switch (ch) {
            case '+':
            case '-':
                advance();
                return Character.toString(ch);
            default:
                break;
        }

        throw new Error("lexical error");
    }

    private String number() {
        int start = current ;
        while (Character.isDigit(peek())) {
            advance();
        }
        
        String n = new String(input, start, current-start)  ;
        return n;
  }

}