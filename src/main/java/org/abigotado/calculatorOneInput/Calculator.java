package org.abigotado.calculatorOneInput;

import java.util.Scanner;

public class Calculator {

    // Сложение
    public static double add(double a, double b) {
        return a + b;
    }

    // Вычитание
    public static double subtract(double a, double b) {
        return a - b;
    }

    // Умножение
    public static double multiply(double a, double b) {
        return a * b;
    }

    // Деление
    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Ошибка - деление на 0.");
        }
        return a / b;
    }

    // Возведение в степень
    public static double power(double a, double b) {
        return Math.pow(a, b);
    }

    // Получение остатка
    public static double mod(double a, double b) {
        return a % b;
    }

    // Проверка корректности выражения
    public static boolean isValidExpression(String[] tokens) {
        if (tokens.length != 3) return false;
        try {
            Double.parseDouble(tokens[0]);
            Double.parseDouble(tokens[2]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Калькулятор поддерживает операции: +, -, *, /, ^, %");
        System.out.println("Введите 'exit' для выхода.");

        while (true) {
            System.out.print("Введите выражение: ");
            String input = scanner.nextLine().trim();

            // Проверка выхода
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Выход...");
                break;
            }

            // Разделение строки на операнды и оператор
            String[] tokens = input.split(" ");
            if (!isValidExpression(tokens)) {
                System.out.println("Неверное выражение. Попробуйте еще раз.");
                continue;
            }

            double num1 = Double.parseDouble(tokens[0]);
            double num2 = Double.parseDouble(tokens[2]);
            String operator = tokens[1];

            // Выбор операции
            try {
                double result;
                switch (operator) {
                    case "+":
                        result = add(num1, num2);
                        break;
                    case "-":
                        result = subtract(num1, num2);
                        break;
                    case "*":
                        result = multiply(num1, num2);
                        break;
                    case "/":
                        result = divide(num1, num2);
                        break;
                    case "^":
                        result = power(num1, num2);
                        break;
                    case "%":
                        result = mod(num1, num2);
                        break;
                    default:
                        System.out.println("Неизвестный оператор. Попробуйте еще раз.");
                        continue;
                }
                System.out.println("Результат: " + result);
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}