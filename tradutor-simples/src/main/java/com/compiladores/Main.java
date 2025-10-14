package com.compiladores;

import com.compiladores.Scanner.Token;
import com.compiladores.Scanner.TokenType;

public class Main {
   public static void main(String[] args) {
        String input = "45  + preco - 876";
        Scanner scan = new Scanner (input.getBytes());
        for (Token tk = scan.nextToken(); tk.type != TokenType.EOF; tk = scan.nextToken()) {
        System.out.println(tk);
    }
    
    }
}
