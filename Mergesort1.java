// import java.util.ArrayList;
// import java.util.List;
// import java.util.Random;

// public class Mergesort1 {
//     static long totalComparisons = 0; // Counter for comparisons in merge
//     static long totalMemoryAllocated = 0; // Counter for memory allocated in merge (bytes)

//     // To store detailed results for summary at the end
//     static List<String> detailedResults = new ArrayList<>();

//     public static void main(String[] args) {
//         System.out.println("Merge Sort Algorithm Used: Mergesort1\n");

//         // Allow setting an initial custom array for demonstration
//         int[] customArray = {2679, 7481, 2801, 5558, 5353, 1605, 214, 4653, 3213, 7961};

//         System.out.println("Initial Custom Array (size " + customArray.length + "):");
//         printArray(customArray);

//         // Sort the custom array using Mergesort1
//         mergeSort(customArray, 0, customArray.length - 1);

//         System.out.println("Sorted Custom Array (size " + customArray.length + "):");
//         printArray(customArray);
//         System.out.println();

//         // Performance testing with different array sizes and input types
//         int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
//         String[] arrayTypes = {"Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array"};

//         long grandTotalTimeMillis = 0; // Total time in milliseconds
//         long grandTotalMemoryBytes = 0; // Total memory used in bytes
//         long grandTotalComparisons = 0; // Total comparisons across all runs

//         for (int size : sizes) {
//             for (String arrayType : arrayTypes) {
//                 int[] testArray = generateArray(arrayType, size);
//                 long totalTime = 0;
//                 long totalMemoryUsed = 0;

//                 // Reset counters for this size and array type
//                 totalComparisons = 0;
//                 totalMemoryAllocated = 0;

//                 System.out.println("\nInitial " + arrayType + " (size " + size + "):");
//                 printArray(testArray);

//                 for (int i = 0; i < 100; i++) {
//                     int[] arrayCopy = testArray.clone();

//                     // Measure memory usage before sorting
//                     Runtime runtime = Runtime.getRuntime();
//                     runtime.gc(); // Request garbage collection
//                     long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

//                     long startTime = System.currentTimeMillis();
//                     mergeSort(arrayCopy, 0, arrayCopy.length - 1);
//                     long endTime = System.currentTimeMillis();

//                     // Measure memory usage after sorting
//                     long afterMemory = runtime.totalMemory() - runtime.freeMemory();
//                     long memoryUsed = afterMemory - beforeMemory;

//                     totalMemoryUsed += memoryUsed;
//                     totalTime += (endTime - startTime);
//                 }

//                 long averageTimeMillis = totalTime / 100;
//                 double averageTimeSeconds = averageTimeMillis / 1000.0;
//                 long averageMemoryUsed = totalMemoryUsed / 100;

//                 // Add to grand totals
//                 grandTotalTimeMillis += totalTime;
//                 grandTotalMemoryBytes += totalMemoryUsed;
//                 grandTotalComparisons += totalComparisons;

//                 // Add to detailed summary with memory usage
//                 detailedResults.add("Array Type: " + arrayType +
//                         ", Size: " + size +
//                         ", Average Time: " + averageTimeMillis + " ms" +
//                         ", Average Memory Used: " + averageMemoryUsed + " bytes");

//                 System.out.println("\nSorted " + arrayType + " (size " + size + "):");
//                 printArray(testArray);

//                 System.out.println("\nAverage execution time for " + arrayType + " of size " + size + ":");
//                 System.out.println("    " + averageTimeMillis + " milliseconds");
//                 System.out.println("    " + averageTimeSeconds + " seconds");
//                 System.out.println("    Average Memory Used: " + averageMemoryUsed + " bytes");
//                 System.out.println("    Total Comparisons for size " + size + ": " + totalComparisons);
//                 System.out.println("    Total Memory Allocated for size " + size + ": " + totalMemoryAllocated + " bytes");
//             }
//         }

//         double grandTotalTimeSecondsFinal = grandTotalTimeMillis / 1000.0;
//         long grandTotalMinutes = grandTotalTimeMillis / (60 * 1000);
//         double grandTotalSeconds = (grandTotalTimeMillis % (60 * 1000)) / 1000.0;

//         System.out.println("\nGrand Total Execution Time for All Sizes:");
//         System.out.println("    " + grandTotalTimeMillis + " milliseconds");
//         System.out.println("    " + grandTotalTimeSecondsFinal + " seconds");
//         System.out.println("    " + grandTotalMinutes + " minutes and " + grandTotalSeconds + " seconds");
//         System.out.println("    Grand Total Comparisons: " + grandTotalComparisons);
//         System.out.println("    Grand Total Memory Used: " + grandTotalMemoryBytes + " bytes");

//         // Print detailed summary
//         System.out.println("\nDetailed Results Summary:");
//         for (String result : detailedResults) {
//             System.out.println(result);
//         }
//     }

//     public static void mergeSort(int[] array, int left, int right) {
//         if (left < right) {
//             int mid = (left + right) / 2;
//             mergeSort(array, left, mid);
//             mergeSort(array, mid + 1, right);
//             merge(array, left, mid, right);
//         }
//     }

//     public static void merge(int[] array, int left, int mid, int right) {
//         int n1 = mid - left + 1;
//         int n2 = right - mid;

//         int[] L = new int[n1];
//         int[] R = new int[n2];

//         totalMemoryAllocated += (n1 + n2) * Integer.BYTES; // Memory allocated for L and R arrays

//         for (int i = 0; i < n1; i++) {
//             L[i] = array[left + i];
//         }
//         for (int j = 0; j < n2; j++) {
//             R[j] = array[mid + 1 + j];
//         }

//         int i = 0, j = 0, k = left;
//         while (i < n1 && j < n2) {
//             totalComparisons++; // Count comparisons
//             if (L[i] <= R[j]) {
//                 array[k] = L[i];
//                 i++;
//             } else {
//                 array[k] = R[j];
//                 j++;
//             }
//             k++;
//         }

//         while (i < n1) {
//             array[k] = L[i];
//             i++;
//             k++;
//         }

//         while (j < n2) {
//             array[k] = R[j];
//             j++;
//             k++;
//         }
//     }

//     public static void printArray(int[] array) {
//         int limit = Math.min(array.length, 25); // Limit output to 25 elements for readability
//         for (int i = 0; i < limit; i++) {
//             System.out.print(array[i] + " ");
//         }
//         if (array.length > 25) {
//             System.out.println("... (output limited to 25 elements)");
//         } else {
//             System.out.println();
//         }
//     }

//     public static int[] generateArray(String type, int size) {
//         Random random = new Random();
//         int[] array = new int[size];
//         switch (type) {
//             case "Random Array":
//                 for (int i = 0; i < size; i++) {
//                     array[i] = random.nextInt(10000);
//                 }
//                 break;
//             case "Sorted Array":
//                 for (int i = 0; i < size; i++) {
//                     array[i] = i;
//                 }
//                 break;
//             case "Reversed Array":
//                 for (int i = 0; i < size; i++) {
//                     array[i] = size - i;
//                 }
//                 break;
//             case "Nearly Sorted Array":
//                 for (int i = 0; i < size; i++) {
//                     array[i] = i;
//                 }
//                 for (int i = 0; i < size * 0.05; i++) {
//                     int idx = random.nextInt(size);
//                     array[idx] = random.nextInt(10000);
//                 }
//                 break;
//         }
//         return array;
//     }
// }


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mergesort1 {
    static long totalComparisons = 0; // Counter for comparisons in merge
    static long totalMemoryAllocated = 0; // Counter for memory allocated in merge (bytes)
    static List<String> detailedResults = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Merge Sort Algorithm Used: Mergesort1\n"); // Print the algorithm name

        // Custom array for demonstration
        int[] customArray = {2679, 44, 01, 5558, 22, 1605, 99, 46, 15, 79}; // Custom array
        System.out.println("Initial Array (size " + customArray.length + "):"); // Print initial array
        printArray(customArray);

        mergeSort(customArray, 0, customArray.length - 1); // Sort the custom array using Mergesort1
        System.out.println("Sorted Array (size " + customArray.length + "):");  // Print sorted array
        printArray(customArray);
        System.out.println();

        // Test sizes and input types
        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};  // Array sizes
        String[] arrayTypes = {"Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array"};    // Array types

        long grandTotalTimeMillis = 0;  // Total time in milliseconds
        long grandTotalMemoryBytes = 0; // Total memory used in bytes
        long grandTotalComparisons = 0; // Total comparisons across all runs

        for (int size : sizes) {    // For each size
            for (String arrayType : arrayTypes) {
                int[] testArray = ArrayGenerator.generateArray(arrayType, size);
                long totalTime = 0;
                long totalMemoryUsed = 0;

                totalComparisons = 0;
                totalMemoryAllocated = 0;

                System.out.println("\nInitial " + arrayType + " (size " + size + "):");
                printArray(testArray);

                for (int i = 0; i < 100; i++) { // Run 100 average cases
                    int[] arrayCopy = testArray.clone();

                    // Measure memory before sorting
                    Runtime runtime = Runtime.getRuntime();
                    runtime.gc(); // Force garbage collection
                    long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

                    long startTime = System.nanoTime();
                    mergeSort(arrayCopy, 0, arrayCopy.length - 1);
                    long endTime = System.nanoTime();

                    // Measure memory after sorting
                    long afterMemory = runtime.totalMemory() - runtime.freeMemory();
                    long memoryUsed = afterMemory - beforeMemory;

                    totalMemoryUsed += memoryUsed;
                    totalTime += (endTime - startTime);
                }

                long averageTimeNanos = totalTime / 10;
                double averageTimeMillis = averageTimeNanos / 1_000_000.0;
                double averageTimeSeconds = averageTimeMillis / 1000.0;
                long averageMemoryUsed = totalMemoryUsed / 10;

                grandTotalTimeMillis += totalTime / 1_000_000;
                grandTotalMemoryBytes += totalMemoryUsed;
                grandTotalComparisons += totalComparisons;

                detailedResults.add("Array Type: " + arrayType +    
                        ", Size: " + size +
                        ", Average Time: " + averageTimeMillis + " ms (" + averageTimeSeconds + " s)" +
                        ", Average Memory Used: " + averageMemoryUsed + " bytes");

                System.out.println("\nSorted " + arrayType + " (size " + size + "):");
                printArray(testArray);

                System.out.println("Average Time: " + averageTimeMillis + " ms (" + averageTimeSeconds + " s)");
                System.out.println("Average Memory Used: " + averageMemoryUsed + " bytes");
                System.out.println("Total Comparisons: " + totalComparisons);
            }
        }

        System.out.println("\nGrand Totals:");
        System.out.println("Total Execution Time: " + grandTotalTimeMillis + " ms");
        System.out.println("Total Memory Allocated: " + grandTotalMemoryBytes + " bytes");
        System.out.println("Total Comparisons: " + grandTotalComparisons);

        System.out.println("\nDetailed Results:");
        for (String result : detailedResults) {
            System.out.println(result);
        }
    }

    public static void mergeSort(int[] array, int left, int right) {    // Merge sort
        if (left < right) { // If left is less than right
            int mid = (left + right) / 2;   // Calculate mid
            mergeSort(array, left, mid);    // Recursively sort left half
            mergeSort(array, mid + 1, right);   // Recursively sort right half
            merge(array, left, mid, right); // Merge the sorted halves
        }
    }

    public static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;    // Length of left subarray
        int n2 = right - mid;   // Length of right subarray

        int[] L = new int[n1];  // Create left subarray
        int[] R = new int[n2];  // Create right subarray

        totalMemoryAllocated += (n1 + n2) * Integer.BYTES;  // Memory allocated for L and R arrays

        for (int i = 0; i < n1; i++) {  // Copy elements to left subarray
            L[i] = array[left + i]; 
        }
        for (int j = 0; j < n2; j++) {  // Copy elements to right subarray
            R[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0, k = left; // Initialize indices
        while (i < n1 && j < n2) {  // Merge the subarrays
            totalComparisons++;
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {    // Copy remaining elements of L
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {    // Copy remaining elements of R
            array[k] = R[j];
            j++;
            k++;
        }
    }

    public static void printArray(int[] array) {    // Print array
        int limit = Math.min(array.length, 25); // Limit output to 25 elements
        for (int i = 0; i < limit; i++) {   
            System.out.print(array[i] + " ");   
        }
        if (array.length > 25) {
            System.out.println("... (limit to 25 elements)");
        } else {
            System.out.println();
        }
    }
}

class ArrayGenerator {  // Array generator
    public static int[] generateArray(String type, int size) {
        Random random = new Random();   // Random object
        int[] array = new int[size];    // Create array
        switch (type) {
            case "Random Array":
                for (int i = 0; i < size; i++) {
                    array[i] = random.nextInt(10000);   // Random values between 0 and 9999
                }
                break;
            case "Sorted Array":
                for (int i = 0; i < size; i++) {    // Sorted array
                    array[i] = i;
                }
                break;
            case "Reversed Array":
                for (int i = 0; i < size; i++) {    // Reversed array
                    array[i] = size - i;
                }
                break;
            case "Nearly Sorted Array":
                for (int i = 0; i < size; i++) {    // Nearly sorted array
                    array[i] = i;
                }
                for (int i = 0; i < size * 0.05; i++) {
                    int idx = random.nextInt(size);
                    array[idx] = random.nextInt(10000);  // Random values between 0 and 9999
                }
                break;
        }
        return array;   // Return array
    }
}
