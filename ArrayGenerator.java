// import java.util.Random;
// import java.util.Arrays;

// public class ArrayGenerator {
//     public static void main(String[] args) {
//         int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};

//         for (int size : sizes) {
//             // Generate different types of arrays
//             int[] randomArray = generateRandomArray(size);
//             int[] sortedArray = generateSortedArray(size);
//             int[] reversedArray = generateReversedArray(size);
//             int[] nearlySortedArray = generateNearlySortedArray(size, 0.05); // 5% random swaps

//             System.out.println("Generated arrays of size " + size);
//             System.out.println("Random Array: " + Arrays.toString(randomArray));
//             System.out.println("Sorted Array: " + Arrays.toString(sortedArray));
//             System.out.println("Reversed Array: " + Arrays.toString(reversedArray));
//             System.out.println("Nearly Sorted Array: " + Arrays.toString(nearlySortedArray));
//         }
//     }

//     // Method to generate random arrays
//     public static int[] generateRandomArray(int size) {
//         Random rand = new Random();
//         int[] array = new int[size];
//         for (int i = 0; i < size; i++) {
//             array[i] = rand.nextInt(10000); // Random numbers between 0 and 9999
//         }
//         return array;
//     }

//     // Method to generate sorted arrays
//     public static int[] generateSortedArray(int size) {
//         int[] array = generateRandomArray(size);
//         Arrays.sort(array);
//         return array;
//     }

//     // Method to generate reversely sorted arrays
//     public static int[] generateReversedArray(int size) {
//         int[] array = generateSortedArray(size);
//         int[] reversedArray = new int[size];
//         for (int i = 0; i < size; i++) {
//             reversedArray[i] = array[size - 1 - i];
//         }
//         return reversedArray;
//     }

//     // Method to generate nearly sorted arrays (with a specified percentage of elements being swapped)
//     public static int[] generateNearlySortedArray(int size, double swapPercentage) {
//         int[] array = generateSortedArray(size);
//         int numberOfSwaps = (int) (size * swapPercentage);
//         Random rand = new Random();

//         for (int i = 0; i < numberOfSwaps; i++) {
//             int index1 = rand.nextInt(size);
//             int index2 = rand.nextInt(size);
//             // Swap elements at index1 and index2
//             int temp = array[index1];
//             array[index1] = array[index2];
//             array[index2] = temp;
//         }

//         return array;
//     }
// }

import java.util.Random;
import java.util.Arrays;

public class ArrayGenerator {

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(10000); // Random values between 0 and 9999
        }
        return array;
    }

    public static int[] generateSortedArray(int size) {
        int[] array = generateRandomArray(size);
        Arrays.sort(array);
        return array;
    }

    public static int[] generateReversedArray(int size) {
        int[] array = generateSortedArray(size);
        int[] reversedArray = new int[size];
        for (int i = 0; i < size; i++) {
            reversedArray[i] = array[size - 1 - i];
        }
        return reversedArray;
    }

    public static int[] generateNearlySortedArray(int size, double swapPercentage) {
        int[] array = generateSortedArray(size);
        int numberOfSwaps = (int) (size * swapPercentage);
        Random rand = new Random();
        for (int i = 0; i < numberOfSwaps; i++) {
            int index1 = rand.nextInt(size);
            int index2 = rand.nextInt(size);
            // Swap elements at index1 and index2
            int temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }
        return array;
    }

    // Helper method to print a sample of arrays for debugging purposes
    public static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    // Test the generator
    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};

        for (int size : sizes) {
            System.out.println("\nArray of size " + size + ":");
            System.out.println("Random Array:");
            printArray(generateRandomArray(size));

            System.out.println("Sorted Array:");
            printArray(generateSortedArray(size));

            System.out.println("Reversed Array:");
            printArray(generateReversedArray(size));

            System.out.println("Nearly Sorted Array (5% shuffled):");
            printArray(generateNearlySortedArray(size, 0.05));
        }
    }
}
