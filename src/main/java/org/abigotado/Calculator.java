package org.abigotado;

import java.util.Arrays;
import java.util.Scanner;

public class Calculator {


    public static void run() {
        final Scanner scanner = new Scanner(System.in);
        Integer currentValue = null;

        System.out.println("Calculator has started");

        while (true) {
            try {
                if (currentValue == null) {
                    currentValue = setOperand(scanner);
                }

                char operation = setOperation(scanner);

                currentValue = calculate(currentValue, operation, setOperand(scanner));
            } catch (Exception e) {
                if (e.getMessage().equals("c")) {
                    System.out.println("Calculator has been reset. You can start again");
                    currentValue = null;
                    continue;
                }
                else if (e.getMessage().equals("s")) {
                    System.out.println("Calculator has stopped");
                    break;
                }

                System.out.println("Calculator will be restarted as an error has occurred: "
                                   + e.getMessage()
                                   + "\n"
                                   + Arrays.toString(e.getStackTrace()));
                currentValue = null;
                System.out.println("Calculator has been restarted");
            }
        }

        scanner.close();
    }

    private static int setOperand(Scanner scanner) {
        int operand;
        while (true) {
            System.out.println("Enter number: ");
            if (scanner.hasNextInt()) {
                operand = scanner.nextInt();
                break;
            }
            String result = scanner.next();
            throwStopOrResetException(result);
            printError();
        }

        return operand;
    }

    private static int calculate(int operandOne, char operation, int operandTwo) {
        int result = switch (operation) {
            case '+' -> operandOne + operandTwo;
            case '-' -> operandOne - operandTwo;
            case '*' -> operandOne * operandTwo;
            case '/' -> operandOne / operandTwo;
            default -> 0;
        };
        printResult(result);
        return result;
    }

    private static char setOperation(Scanner scanner) {
        String input = getOperationString(scanner);
        while (true) {
            char operation = input.charAt(0);
            if (input.length() == 1 && (operation == '+' || operation == '-' || operation == '*' || operation == '/')) {
                break;
            }
            throwStopOrResetException(input);

            printError();
            input = getOperationString(scanner);
        }

        return input.charAt(0);
    }

    private static String getOperationString(Scanner scanner) {
        System.out.println("Enter operation symbol (+, -, *, /): ");
        return scanner.next();
    }

    private static void printResult(int result) {
        System.out.println("Result: " + result);
    }

    private static void printError() {
        System.out.println("Invalid input. Try again, please.");
    }

    private static void throwStopOrResetException(String input) {
        if (input.length() != 1)
            return;
        if (Character.toLowerCase(input.charAt(0)) == 'c') {
            throw new RuntimeException("c");
        }
        else if (Character.toLowerCase(input.charAt(0)) == 's') {
            throw new RuntimeException("s");
        }
    }
}


