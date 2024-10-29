package org.abigotado.array_sort;

import java.util.Arrays;

public class ArraySorter {

    public static void main(String[] args) {
        int[] numbers = new int[(int) (Math.random() * 100)];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * 100);
        }

        int max = numbers[0];
        int min = numbers[0];

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
            if (numbers[i] < min) {
                min = numbers[i];
            }

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

        System.out.println("Min value: " + min);
        System.out.println("Max value: " + max);
        System.out.println("Sorted array: " + Arrays.toString(numbers));

    }
}
