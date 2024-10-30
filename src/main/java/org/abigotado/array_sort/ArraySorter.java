package org.abigotado.array_sort;

import java.util.Arrays;
import java.util.Scanner;

public class ArraySorter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int length = promptForArrayLength(scanner);
        boolean withLimits = promptForLimitsOption(scanner);

        if (withLimits) {
            double lowerBound = promptForDouble(scanner, "Enter lower bound: ", null);
            double upperBound = promptForDouble(scanner, "Enter upper bound: ", lowerBound);

            if (isIntegerBounds(lowerBound, upperBound)) {
                int[] numbers = generateIntArray(length, (int) lowerBound, (int) upperBound);
                displayArrayStatistics(numbers);
            }
            else {
                double[] numbers = generateDoubleArray(length, lowerBound, upperBound);
                displayArrayStatistics(numbers);
            }
        }
        else {
            int[] numbers = generateIntArray(length, 0, 100);
            displayArrayStatistics(numbers);
        }

        scanner.close();
    }

    private static int promptForArrayLength(Scanner scanner) {
        int length;
        do {
            System.out.print("Enter array length: ");
            length = parsePositiveInt(scanner.nextLine());
        } while (length <= 0);
        return length;
    }

    private static boolean promptForLimitsOption(Scanner scanner) {
        System.out.print("Do you want to enter limits for random numbers? (Y/n): ");
        String line = scanner.nextLine().trim().toLowerCase();
        while (!line.matches("[yn]?")) {
            System.out.print("Invalid input. Try again: ");
            line = scanner.nextLine().trim().toLowerCase();
        }
        return line.equals("y");
    }

    private static double promptForDouble(Scanner scanner, String prompt, Double min) {
        double value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Double.parseDouble(scanner.nextLine());
                if (min == null || value >= min) break;
                System.out.println("Value must be more or equal to " + min + ". Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return value;
    }

    private static int parsePositiveInt(String input) {
        try {
            int value = Integer.parseInt(input);
            if (value > 0) return value;
        } catch (NumberFormatException ignored) {}
        System.out.println("Invalid input. Try again.");
        return -1;
    }

    private static boolean isIntegerBounds(double lowerBound, double upperBound) {
        return lowerBound % 1 == 0 && upperBound % 1 == 0;
    }

    private static double[] generateDoubleArray(int length, double min, double max) {
        double[] numbers = new double[length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Math.random() * (max - min) + min;
        }
        return numbers;
    }

    private static int[] generateIntArray(int length, int min, int max) {
        int[] numbers = new int[length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * (max - min) + min);
        }
        return numbers;
    }

    private static void displayArrayStatistics(int[] numbers) {
        System.out.println("Array: " + Arrays.toString(numbers));
        System.out.println("Min: " + findMin(numbers));
        System.out.println("Max: " + findMax(numbers));
        System.out.println("Sorted array: " + Arrays.toString(sort(numbers)));
    }

    private static void displayArrayStatistics(double[] numbers) {
        System.out.println("Array: " + Arrays.toString(numbers));
        System.out.println("Min: " + findMin(numbers));
        System.out.println("Max: " + findMax(numbers));
        System.out.println("Sorted array: " + Arrays.toString(sort(numbers)));
    }

    // Поиск максимума вручную
    public static int findMax(int[] numbers) {
        int max = numbers[0];
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        return max;
    }

    public static double findMax(double[] numbers) {
        double max = numbers[0];
        for (double number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        return max;
    }

    // Поиск минимума вручную
    public static int findMin(int[] numbers) {
        int min = numbers[0];
        for (int number : numbers) {
            if (number < min) {
                min = number;
            }
        }
        return min;
    }

    public static double findMin(double[] numbers) {
        double min = numbers[0];
        for (double number : numbers) {
            if (number < min) {
                min = number;
            }
        }
        return min;
    }

    // Сортировка вручную (сортировка выбором)
    public static int[] sort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = numbers[minIndex];
            numbers[minIndex] = numbers[i];
            numbers[i] = temp;
        }
        return numbers;
    }

    public static double[] sort(double[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[minIndex]) {
                    minIndex = j;
                }
            }
            double temp = numbers[minIndex];
            numbers[minIndex] = numbers[i];
            numbers[i] = temp;
        }
        return numbers;
    }
}