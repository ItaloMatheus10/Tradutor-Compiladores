package com.compiladores;

public class Main {
    public static void main(String[] args) {
        // Entrada do programa em linguagem de alto nível
        String input = 
            "let a = 42 + 2;\n" +
            "let b = 15 + 3;\n" +
            "print a + b;\n";

        // Fase de análise sintática e geração de código intermediário
        Parser parser = new Parser(input.getBytes());
        parser.parse();

        // Fase de interpretação do código intermediário
        Interpretador interpretador = new Interpretador(parser.output());
        interpretador.run();
    }
}