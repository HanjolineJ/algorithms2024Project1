// public class Mergesort3 {
//     static int[] S;
//     static int[] U;
//     static long totalComparisons = 0; // Counter for comparisons
//     static long grandTotalComparisons = 0; // Counter for grand total comparisons

//     public static void main(String[] args) {
//         System.out.println("Merge Sort Algorithm Used: MergeSort3\n");

//         S = new int[]{16, 14, 5, 7, 1, 8, 12, 10, 1, 9};
//         int n = S.length;
//         U = new int[n];

//         mergesort3(S, n);

//         System.out.println("Sorted array: ");
//         for (int num : S) {
//             System.out.print(num + " ");
//         }
//         System.out.println("\n");

//         // Display initial analysis
//         System.out.println("Mergesort Analysis:");
//         System.out.println("    Algorithm: Bottom-Up Iterative Merge Sort");
//         System.out.println("    Total Comparisons Made: " + totalComparisons);
//         System.out.println("    Time Complexity: O(n log n)");
//         System.out.println("    Space Complexity: O(n)");

//         // Performance testing
//         int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
//         long grandTotalTimeMillis = 0; // Variable to track grand total time in milliseconds
//         long grandTotalMemoryBytes = 0; // Variable to track grand total memory usage in bytes

//         for (int size : sizes) {
//             int[][] testArrays = {
//                 ArrayGenerator.generateRandomArray(size),
//                 ArrayGenerator.generateSortedArray(size),
//                 ArrayGenerator.generateReversedArray(size),
//                 ArrayGenerator.generateNearlySortedArray(size, 0.05)
//             };

//             String[] arrayTypes = {"Random", "Sorted", "Reversed", "Nearly Sorted"};

//             for (int t = 0; t < testArrays.length; t++) {
//                 int[] generatedArray = testArrays[t];
//                 long totalTime = 0;
//                 long totalMemoryUsed = 0;

//                 // Reset local counters
//                 totalComparisons = 0;

//                 for (int i = 0; i < 100; i++) {
//                     int[] arrayCopy = generatedArray.clone();

//                     // Measure memory usage before sorting
//                     Runtime runtime = Runtime.getRuntime();
//                     runtime.gc(); // Request garbage collection
//                     long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

//                     long startTime = System.currentTimeMillis();
//                     mergesort3(arrayCopy, arrayCopy.length);
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

//                 long minutes = averageTimeMillis / (60 * 1000);
//                 double seconds = (averageTimeMillis % (60 * 1000)) / 1000.0;

//                 System.out.println("Average execution time for " + arrayTypes[t] + " array of size " + size + ":");
//                 System.out.println("    " + averageTimeMillis + " milliseconds");
//                 System.out.println("    " + averageTimeSeconds + " seconds");
//                 System.out.println("    " + minutes + " minutes and " + seconds + " seconds");
//                 System.out.println("    " + averageMemoryUsed + " bytes of memory used");
//                 System.out.println("    Total Comparisons: " + totalComparisons);
//             }
//         }

//         double grandTotalTimeSecondsFinal = grandTotalTimeMillis / 1000.0;
//         long grandTotalMinutes = grandTotalTimeMillis / (60 * 1000);
//         double grandTotalSeconds = (grandTotalTimeMillis % (60 * 1000)) / 1000.0;

//         // Display grand totals
//         System.out.println("\nGrand Total Execution Time for All Arrays and Sizes:");
//         System.out.println("    " + grandTotalTimeMillis + " milliseconds");
//         System.out.println("    " + grandTotalTimeSecondsFinal + " seconds");
//         System.out.println("    " + grandTotalMinutes + " minutes and " + grandTotalSeconds + " seconds");
//         System.out.println("    " + grandTotalMemoryBytes + " bytes of total memory used");
//         System.out.println("    " + grandTotalComparisons + " total comparisons made");
//         System.out.println("Merge Sort Algorithm Used: MergeSort3\n");
//     }

//     public static void mergesort3(int[] array, int n) {
//         U = new int[n]; // Auxiliary array for merging

//         // Start with single element arrays and merge iteratively
//         for (int width = 1; width < n; width = 2 * width) {
//             for (int i = 0; i < n; i = i + 2 * width) {
//                 int low = i;
//                 int mid = Math.min(i + width - 1, n - 1);
//                 int high = Math.min(i + 2 * width - 1, n - 1);

//                 // Merge the sorted subarrays
//                 merge3(array, low, mid, high);
//             }

//             // Copy merged result from U back to array
//             System.arraycopy(U, 0, array, 0, n);
//         }
//     }

//     public static void merge3(int[] array, int low, int mid, int high) {
//         int i = low;
//         int j = mid + 1;
//         int k = low;

//         // Merge the two subarrays into U
//         while (i <= mid && j <= high) {
//             totalComparisons++; // Count each comparison
//             if (array[i] <= array[j]) {
//                 U[k] = array[i];
//                 i++;
//             } else {
//                 U[k] = array[j];
//                 j++;
//             }
//             k++;
//         }

//         // Copy remaining elements of the left subarray if any
//         while (i <= mid) {
//             U[k] = array[i];
//             i++;
//             k++;
//         }

//         // Copy remaining elements of the right subarray if any
//         while (j <= high) {
//             U[k] = array[j];
//             j++;
//             k++;
//         }
//     }
// }

// import java.util.ArrayList;
// import java.util.List;

// public class Mergesort3 {
//     static long totalComparisons = 0; // Counter for comparisons
//     static long totalMemoryAllocated = 0; // Counter for memory allocated
//     static long grandTotalMemoryBytes = 0; // Grand total memory used
//     static long grandTotalComparisons = 0; // Grand total comparisons
//     static List<String> detailedResults = new ArrayList<>(); // Detailed summary
//     static int[] S; // Primary array
//     static int[] U; // Auxiliary array for merging

//     public static void main(String[] args) {
//         System.out.println("Merge Sort Algorithm Used: MergeSort3 (Dynamic Programming Version)\n");

//         // Custom array for demonstration
//         int[] customArray = {2679, 7481, 2801, 5558, 5353, 1605, 214, 4653, 3213, 7961};

//         System.out.println("Initial Custom Array (size " + customArray.length + "):");
//         printArray(customArray);

//         // Prepare for sorting
//         S = customArray.clone();
//         U = new int[S.length]; // Auxiliary array

//         mergesort3(S, S.length);

//         System.out.println("Sorted Custom Array (size " + customArray.length + "):");
//         printArray(S);

//         // Performance testing with various sizes and array types
//         int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
//         String[] arrayTypes = {"Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array"};

//         long grandTotalTimeMillis = 0;

//         for (int size : sizes) {
//             for (String arrayType : arrayTypes) {
//                 int[] testArray = generateArray(arrayType, size);
//                 long totalTime = 0;
//                 long totalMemoryUsed = 0;

//                 totalComparisons = 0;

//                 System.out.println("\nInitial " + arrayType + " (size " + size + "):");
//                 printArray(testArray);

//                 for (int i = 0; i < 100; i++) {
//                     int[] arrayCopy = testArray.clone();
//                     S = arrayCopy.clone();
//                     U = new int[S.length]; // Reset auxiliary array

//                     // Measure memory usage
//                     Runtime runtime = Runtime.getRuntime();
//                     long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

//                     long startTime = System.currentTimeMillis();
//                     mergesort3(S, S.length);
//                     long endTime = System.currentTimeMillis();

//                     long afterMemory = runtime.totalMemory() - runtime.freeMemory();
//                     long memoryUsed = Math.max(0, afterMemory - beforeMemory); // Ensure non-negative values

//                     totalMemoryUsed += memoryUsed;
//                     totalTime += (endTime - startTime);
//                 }

//                 long averageTimeMillis = totalTime / 100;
//                 double averageTimeSeconds = averageTimeMillis / 1000.0;
//                 long averageMemoryUsed = totalMemoryUsed / 100;

//                 grandTotalTimeMillis += totalTime;
//                 grandTotalMemoryBytes += totalMemoryUsed;
//                 grandTotalComparisons += totalComparisons;

//                 System.out.println("\nSorted " + arrayType + " (size " + size + "):");
//                 printArray(S);

//                 System.out.println("\nAverage execution time for " + arrayType + " of size " + size + ":");
//                 System.out.println("    " + averageTimeMillis + " milliseconds");
//                 System.out.println("    " + averageTimeSeconds + " seconds");
//                 System.out.println("    Average Memory Used: " + averageMemoryUsed + " bytes");
//                 System.out.println("    Total Comparisons for size " + size + ": " + totalComparisons);

//                 detailedResults.add("Array Type: " + arrayType +
//                         ", Size: " + size +
//                         ", Average Time: " + averageTimeMillis + " ms" +
//                         ", Average Memory Used: " + averageMemoryUsed + " bytes");
//             }
//         }

//         System.out.println("\nInitial Custom Array (Printed Again for Reference):");
//         printArray(customArray);

//         double grandTotalTimeSecondsFinal = grandTotalTimeMillis / 1000.0;
//         long grandTotalMinutes = grandTotalTimeMillis / (60 * 1000);
//         double grandTotalSeconds = (grandTotalTimeMillis % (60 * 1000)) / 1000.0;

//         System.out.println("\nGrand Total Execution Time for All Sizes:");
//         System.out.println("    " + grandTotalTimeMillis + " milliseconds");
//         System.out.println("    " + grandTotalTimeSecondsFinal + " seconds");
//         System.out.println("    " + grandTotalMinutes + " minutes and " + grandTotalSeconds + " seconds");
//         System.out.println("    Grand Total Memory Used: " + grandTotalMemoryBytes + " bytes");
//         System.out.println("    Grand Total Comparisons: " + grandTotalComparisons);

//         System.out.println("\nDetailed Results Summary:");
//         for (String result : detailedResults) {
//             System.out.println(result);
//         }
//     }

//     public static void mergesort3(int[] array, int n) {
//         boolean useAux = true;

//         for (int width = 1; width < n; width *= 2) {
//             for (int i = 0; i < n; i += 2 * width) {
//                 int low = i;
//                 int mid = Math.min(i + width - 1, n - 1);
//                 int high = Math.min(i + 2 * width - 1, n - 1);

//                 if (useAux) {
//                     merge(array, U, low, mid, high);
//                 } else {
//                     merge(U, array, low, mid, high);
//                 }
//             }
//             useAux = !useAux; // Alternate between U and S
//         }

//         if (!useAux) {
//             System.arraycopy(U, 0, array, 0, n); // Final copy if necessary
//         }
//     }

//     public static void merge(int[] source, int[] dest, int low, int mid, int high) {
//         int i = low, j = mid + 1, k = low;
//         while (i <= mid && j <= high) {
//             totalComparisons++;
//             if (source[i] <= source[j]) {
//                 dest[k++] = source[i++];
//             } else {
//                 dest[k++] = source[j++];
//             }
//         }
//         while (i <= mid) {
//             dest[k++] = source[i++];
//         }
//         while (j <= high) {
//             dest[k++] = source[j++];
//         }
//     }

//     public static void printArray(int[] array) {
//         int limit = Math.min(array.length, 25);
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
//         int[] array = new int[size];
//         switch (type) {
//             case "Random Array":
//                 for (int i = 0; i < size; i++) array[i] = (int) (Math.random() * 10000);
//                 break;
//             case "Sorted Array":
//                 for (int i = 0; i < size; i++) array[i] = i;
//                 break;
//             case "Reversed Array":
//                 for (int i = 0; i < size; i++) array[i] = size - i;
//                 break;
//             case "Nearly Sorted Array":
//                 for (int i = 0; i < size; i++) array[i] = i;
//                 int swaps = (int) (size * 0.05);
//                 for (int i = 0; i < swaps; i++) {
//                     int index1 = (int) (Math.random() * size);
//                     int index2 = (int) (Math.random() * size);
//                     int temp = array[index1];
//                     array[index1] = array[index2];
//                     array[index2] = temp;
//                 }
//                 break;
//         }
//         return array;
//     }
// }

// import java.util.ArrayList;
// import java.util.List;

// public class Mergesort3 {
//     static int[] S; // Primary array
//     static int[] U; // Auxiliary array
//     static long totalComparisons = 0; // Counter for comparisons
//     static long grandTotalComparisons = 0; // Grand total comparisons
//     static long grandTotalMemoryBytes = 0; // Grand total memory usage
//     static List<String> detailedResults = new ArrayList<>(); // Summary of results

//     public static void main(String[] args) {
//         System.out.println("Merge Sort Algorithm Used: MergeSort3\n");

//         // Custom array for demonstration
//         S = new int[]{16, 14, 5, 7, 1, 8, 12, 10, 1, 9};
//         int n = S.length;
//         U = new int[n];

//         mergesort3(S, n);

//         System.out.println("Sorted array:");
//         // printArray(S);

//         // Display initial analysis
//         System.out.println("\nMergesort Analysis:");
//         System.out.println("    Algorithm: Bottom-Up Iterative Merge Sort");
//         System.out.println("    Total Comparisons Made: " + totalComparisons);
//         System.out.println("    Time Complexity: O(n log n)");
//         System.out.println("    Space Complexity: O(n)");

//         // Performance testing
//         int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
//         String[] arrayTypes = {"Random", "Sorted", "Reversed", "Nearly Sorted"};
//         long grandTotalTimeMillis = 0; // Variable to track total time

//         for (int size : sizes) {
//             for (String arrayType : arrayTypes) {
//                 int[] generatedArray = generateArray(arrayType, size);
//                 long totalTime = 0;
//                 long totalMemoryUsed = 0;

//                 // Reset local counters
//                 totalComparisons = 0;

//                 for (int i = 0; i < 100; i++) {
//                     int[] arrayCopy = generatedArray.clone();
//                     U = new int[arrayCopy.length]; // Reset auxiliary array

//                     // Measure memory usage
//                     Runtime runtime = Runtime.getRuntime();
//                     runtime.gc(); // Request garbage collection
//                     long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

//                     long startTime = System.currentTimeMillis();
//                     mergesort3(arrayCopy, arrayCopy.length);
//                     long endTime = System.currentTimeMillis();

//                     long afterMemory = runtime.totalMemory() - runtime.freeMemory();
//                     long memoryUsed = Math.max(0, afterMemory - beforeMemory); // Ensure non-negative

//                     totalMemoryUsed += memoryUsed;
//                     totalTime += (endTime - startTime);
//                 }

//                 long averageTimeMillis = totalTime / 100;
//                 double averageTimeSeconds = averageTimeMillis / 1000.0;
//                 long averageMemoryUsed = totalMemoryUsed / 100;

//                 // Update grand totals
//                 grandTotalTimeMillis += totalTime;
//                 grandTotalMemoryBytes += totalMemoryUsed;
//                 grandTotalComparisons += totalComparisons;

//                 System.out.println("\nAverage execution time for " + arrayType + " array of size " + size + ":");
//                 System.out.println("    " + averageTimeMillis + " milliseconds");
//                 System.out.println("    " + averageTimeSeconds + " seconds");
//                 System.out.println("    Average Memory Used: " + averageMemoryUsed + " bytes");
//                 System.out.println("    Total Comparisons: " + totalComparisons);

//                 detailedResults.add("Array Type: " + arrayType +
//                         ", Size: " + size +
//                         ", Average Time: " + averageTimeMillis + " ms" +
//                         ", Average Memory Used: " + averageMemoryUsed + " bytes");
//             }
//         }

//         double grandTotalTimeSecondsFinal = grandTotalTimeMillis / 1000.0;
//         long grandTotalMinutes = grandTotalTimeMillis / (60 * 1000);
//         double grandTotalSeconds = (grandTotalTimeMillis % (60 * 1000)) / 1000.0;

//         // Display grand totals
//         System.out.println("\nGrand Total Execution Time for All Arrays and Sizes:");
//         System.out.println("    " + grandTotalTimeMillis + " milliseconds");
//         System.out.println("    " + grandTotalTimeSecondsFinal + " seconds");
//         System.out.println("    " + grandTotalMinutes + " minutes and " + grandTotalSeconds + " seconds");
//         System.out.println("    Grand Total Memory Used: " + grandTotalMemoryBytes + " bytes");
//         System.out.println("    Grand Total Comparisons: " + grandTotalComparisons);

//         System.out.println("\nDetailed Results Summary:");
//         for (String result : detailedResults) {
//             System.out.println(result);
//         }
//     }

//     public static void mergesort3(int[] array, int n) {
//         U = new int[n]; // Auxiliary array for merging

//         for (int width = 1; width < n; width *= 2) {
//             for (int i = 0; i < n; i += 2 * width) {
//                 int low = i;
//                 int mid = Math.min(i + width - 1, n - 1);
//                 int high = Math.min(i + 2 * width - 1, n - 1);
//                 merge(array, low, mid, high);
//             }
//             System.arraycopy(U, 0, array, 0, n);
//         }
//     }

//     public static void merge(int[] array, int low, int mid, int high) {
//         int i = low, j = mid + 1, k = low;

//         while (i <= mid && j <= high) {
//             totalComparisons++;
//             if (array[i] <= array[j]) {
//                 U[k++] = array[i++];
//             } else {
//                 U[k++] = array[j++];
//             }
//         }

//         while (i <= mid) {
//             U[k++] = array[i++];
//         }

//         while (j <= high) {
//             U[k++] = array[j++];
//         }
//     }

//     // public static void printArray(int[] array) {
//     //     int limit = Math.min(array.length, 25);
//     //     for (int i = 0; i < limit; i++) {
//     //         System.out.print(array[i] + " ");
//     //     }
//     //     if (array.length > 25) {
//     //         System.out.println("... (output limited to 25 elements)");
//     //     } else {
//     //         System.out.println();
//     //     }
//     // }

//     public static int[] generateArray(String type, int size) {
//         int[] array = new int[size];
//         switch (type) {
//             case "Random":
//                 for (int i = 0; i < size; i++) array[i] = (int) (Math.random() * 10000);
//                 break;
//             case "Sorted":
//                 for (int i = 0; i < size; i++) array[i] = i;
//                 break;
//             case "Reversed":
//                 for (int i = 0; i < size; i++) array[i] = size - i;
//                 break;
//             case "Nearly Sorted":
//                 for (int i = 0; i < size; i++) array[i] = i;
//                 int swaps = (int) (size * 0.05);
//                 for (int i = 0; i < swaps; i++) {
//                     int index1 = (int) (Math.random() * size);
//                     int index2 = (int) (Math.random() * size);
//                     int temp = array[index1];
//                     array[index1] = array[index2];
//                     array[index2] = temp;
//                 }
//                 break;
//         }
//         return array;
//     }
// }

import java.util.ArrayList;
import java.util.List;

public class Mergesort3 {
    static int[] S; // Primary array
    static int[] U; // Auxiliary array
    static long totalComparisons = 0; // Counter for comparisons
    static long grandTotalComparisons = 0; // Grand total comparisons
    static long grandTotalMemoryBytes = 0; // Grand total memory usage
    static List<String> detailedResults = new ArrayList<>(); // Summary of results

    public static void main(String[] args) {
        System.out.println("Merge Sort Algorithm Used: MergeSort3\n");  // Display the algorithm used

        // Test a custom array for demonstration
        S = new int[]{16, 14, 5, 7, 1, 8, 12, 10, 1, 9};    // Custom array
        int n = S.length;   // Length of the custom array
        U = new int[n]; // array for merging

        System.out.println("Initial Custom Array:");
        printArray(S);

        mergesort3(S, n);

        System.out.println("Sorted Custom Array:");
        printArray(S);

        // Performance testing
        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};  // Array sizes to test
        String[] arrayTypes = {"Random", "Sorted", "Reversed", "Nearly Sorted"};    // Array types to test
        long grandTotalTimeMillis = 0;

        for (int size : sizes) {    // Loop through each size
            for (String arrayType : arrayTypes) {   // Loop through each array type
                int[] generatedArray = generateArray(arrayType, size);  // Generate the array
                long totalTime = 0;
                long totalMemoryUsed = 0;

                // Reset counters
                totalComparisons = 0;

                for (int i = 0; i < 100; i++) { // Run 100 times
                    int[] arrayCopy = generatedArray.clone();   // Copy the array
                    U = new int[arrayCopy.length]; // Reset array

                    // Measure memory usage
                    Runtime runtime = Runtime.getRuntime();
                    runtime.gc(); // Request garbage collection
                    long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

                    long startTime = System.nanoTime();
                    mergesort3(arrayCopy, arrayCopy.length);    // Sort the array
                    long endTime = System.nanoTime();

                    long afterMemory = runtime.totalMemory() - runtime.freeMemory();    // Memory after sorting
                    long memoryUsed = Math.max(0, afterMemory - beforeMemory);

                    totalMemoryUsed += memoryUsed;
                    totalTime += (endTime - startTime);
                }

                long averageTimeNanos = totalTime / 100;
                long averageTimeMillis = averageTimeNanos / 1_000_000;
                double averageTimeSeconds = averageTimeNanos / 1_000_000_000.0;
                long averageMemoryUsed = totalMemoryUsed / 100;

                grandTotalTimeMillis += totalTime / 1_000_000;
                grandTotalMemoryBytes += totalMemoryUsed;
                grandTotalComparisons += totalComparisons;

                detailedResults.add("Array Type: " + arrayType +
                        ", Size: " + size +
                        ", Average Time: " + averageTimeMillis + " ms" +
                        " (" + averageTimeSeconds + " s)" +
                        ", Average Memory Used: " + averageMemoryUsed + " bytes");

                System.out.println("\nAverage execution time for " + arrayType + " array of size " + size + ":");
                System.out.println("    " + averageTimeMillis + " milliseconds");
                System.out.println("    " + averageTimeSeconds + " seconds");
                System.out.println("    Average Memory Used: " + averageMemoryUsed + " bytes");
                System.out.println("    Total Comparisons: " + totalComparisons);
            }
        }

        // Print grand totals
        double grandTotalTimeSecondsFinal = grandTotalTimeMillis / 1000.0;
        long grandTotalMinutes = grandTotalTimeMillis / (60 * 1000);
        double grandTotalSeconds = (grandTotalTimeMillis % (60 * 1000)) / 1000.0;

        System.out.println("\nGrand Total Execution Time for All Arrays and Sizes:");
        System.out.println("    " + grandTotalTimeMillis + " milliseconds");
        System.out.println("    " + grandTotalTimeSecondsFinal + " seconds");
        System.out.println("    " + grandTotalMinutes + " minutes and " + grandTotalSeconds + " seconds");
        System.out.println("    Grand Total Memory Used: " + grandTotalMemoryBytes + " bytes");
        System.out.println("    Grand Total Comparisons: " + grandTotalComparisons);

        System.out.println("\nDetailed Results Summary:");
        for (String result : detailedResults) {
            System.out.println(result);
        }
    }

    public static void mergesort3(int[] array, int n) {
        U = new int[n]; // Auxiliary array for merging

        for (int width = 1; width < n; width *= 2) {
            for (int i = 0; i < n; i += 2 * width) {
                int low = i;
                int mid = Math.min(i + width - 1, n - 1);
                int high = Math.min(i + 2 * width - 1, n - 1);
                merge(array, low, mid, high);
            }
            System.arraycopy(U, 0, array, 0, n);
        }
    }

    public static void merge(int[] array, int low, int mid, int high) {
        int i = low, j = mid + 1, k = low;

        while (i <= mid && j <= high) { // Merge the two subarrays into U
            totalComparisons++;
            if (array[i] <= array[j]) { // Compare the elements
                U[k++] = array[i++];    // Copy the smaller element
            } else {
                U[k++] = array[j++];    // Copy the smaller element
            }
        }

        while (i <= mid) {  // Copy remaining elements of the left subarray if any
            U[k++] = array[i++];    // Copy the remaining elements
        }

        while (j <= high) { // Copy remaining elements of the right subarray if any
            U[k++] = array[j++];    // Copy the remaining elements
        }
    }

    public static void printArray(int[] array) {
        int limit = Math.min(array.length, 25);   // Limit the output to 25 elements
        for (int i = 0; i < limit; i++) {   // Loop through the array
            System.out.print(array[i] + " ");   // Print the element
        }
        if (array.length > 25) {    // Check if the array is longer than 25 elements
            System.out.println("... (output limited to 25 elements)");   // Print the message
        } else {
            System.out.println();
        }
    }

    public static int[] generateArray(String type, int size) {  // Generate an array based on the type
        int[] array = new int[size];
        switch (type) {
            case "Random":
                for (int i = 0; i < size; i++) array[i] = (int) (Math.random() * 10000);
                break;
            case "Sorted":
                for (int i = 0; i < size; i++) array[i] = i;
                break;
            case "Reversed":
                for (int i = 0; i < size; i++) array[i] = size - i;
                break;
            case "Nearly Sorted":
                for (int i = 0; i < size; i++) array[i] = i;
                int swaps = (int) (size * 0.05);
                for (int i = 0; i < swaps; i++) {
                    int index1 = (int) (Math.random() * size);
                    int index2 = (int) (Math.random() * size);
                    int temp = array[index1];
                    array[index1] = array[index2];
                    array[index2] = temp;
                }
                break;
        }
        return array;
    }
}
