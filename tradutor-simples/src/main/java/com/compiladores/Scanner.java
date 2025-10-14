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

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
               (c >= 'A' && c <= 'Z') ||
                c == '_';
    }
    
    private boolean isAlphaNumeric(char c) {
            return isAlpha(c) || Character.isDigit((c));
    }

    private Token number() {
        int start = current ;
        while (Character.isDigit(peek())) {
            advance();
        }
        
        String n = new String(input, start, current-start)  ;
        return new Token(TokenType.NUMBER, n);
    }

    
    public enum TokenType {
        PLUS,MINUS,

        // Literals.
        NUMBER,
        IDENT,
	
		EOF
    }

    public class Token {

        final TokenType type;
        final String lexeme;

        public Token (TokenType type, String lexeme) {
            this.type = type;
            this.lexeme = lexeme;
    }

    public String toString() {
        return "<"+ type +">" + lexeme + "</"+ type + ">";
    }
    
    }

    private void skipWhitespace() {
        char ch = peek();
        while (ch == ' ' || ch == '\r' || ch == '\t' || ch == '\n') {
            advance();
            ch = peek();
        }
    }


    public Token nextToken () {

        skipWhitespace();
        char ch = peek();
        if (ch == '0') {
            advance();
            return new Token (TokenType.NUMBER, Character.toString(ch));
        }  else if (Character.isDigit(ch))
            return number();
           
        switch (ch) {
                case '+':
                    advance();
                    return new Token (TokenType.PLUS,"+");
                case '-':
                    advance();
                    return new Token (TokenType.MINUS,"-");
                case '\0':
                    return new Token (TokenType.EOF,"EOF");
                default:
                     throw new Error("lexical error at " + ch);
        }
    }
    

}