package com.compiladores;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

class Command {

    public enum Type {
        ADD, 
        SUB,
        PUSH,
        POP,
        PRINT
    }

    public Command.Type type;
    public String arg = "";

    public Command(String[] command) {
        type = Command.Type.valueOf(command[0].toUpperCase());
        if (command.length > 1) {
            arg = command[1];
        }
    }

    public String toString() {
        return type.name() + " " + arg;
    }
}

public class Interpretador {

    List<String[]> commands;
    Stack<Integer> stack = new Stack<>();
    Map<String, Integer> variables = new HashMap<>();

    Interpretador(String input) {
        // Aceita tanto \n quanto \r\n
        String[] output = input.split("\\r?\\n");
        commands = Arrays.stream(output)
            .map(String::strip)
            .filter(s -> !s.isEmpty())
            .filter(s -> !s.startsWith("//"))
            .map(s -> s.split(" "))
            .collect(Collectors.toList());
    }

    public boolean hasMoreCommands() {
        return !commands.isEmpty();
    }

    public Command nextCommand() {
        return new Command(commands.remove(0));
    }

    public void run() {
        while (hasMoreCommands()) {
            Command command = nextCommand();
            switch (command.type) {
                case ADD: {
                    if (stack.size() < 2) throw new RuntimeException("Stack underflow on ADD");
                    int arg2 = stack.pop();
                    int arg1 = stack.pop();
                    stack.push(arg1 + arg2);
                    break;
                }
                case SUB: {
                    if (stack.size() < 2) throw new RuntimeException("Stack underflow on SUB");
                    int arg2 = stack.pop();
                    int arg1 = stack.pop();
                    stack.push(arg1 - arg2);
                    break;
                }
                case PUSH: {
                    Integer value = variables.get(command.arg);
                    if (value != null) {
                        stack.push(value);
                    } else {
                        stack.push(Integer.parseInt(command.arg));
                    }
                    break;
                }
                case POP: {
                    if (stack.isEmpty()) throw new RuntimeException("Stack underflow on POP");
                    int value = stack.pop();
                    variables.put(command.arg, value);
                    break;
                }
                case PRINT: {
                    if (stack.isEmpty()) throw new RuntimeException("Stack underflow on PRINT");
                    int arg = stack.pop();
                    System.out.println(arg);
                    break;
                }
            }
        }
    }
}