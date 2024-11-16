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
import java.util.ArrayList;
import java.util.List;

public class Mergesort3 {
    static int[] S;
    static int[] U; // Auxiliary array for merging
    static long totalComparisons = 0; // Counter for comparisons
    static long grandTotalComparisons = 0; // Counter for grand total comparisons
    static List<String> detailedResults = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Merge Sort Algorithm Used: MergeSort3 (Iterative Bottom-Up)\n");

        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
        String[] arrayTypes = {"Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array"};

        long grandTotalTimeMillis = 0; // Variable to track grand total time in milliseconds
        long grandTotalMemoryBytes = 0; // Variable to track grand total memory usage in bytes

        for (int size : sizes) {
            int[] randomArray = ArrayGenerator.generateRandomArray(size);
            int[] sortedArray = ArrayGenerator.generateSortedArray(size);
            int[] reversedArray = ArrayGenerator.generateReversedArray(size);
            int[] nearlySortedArray = ArrayGenerator.generateNearlySortedArray(size, 0.05);

            int[][] testArrays = {randomArray, sortedArray, reversedArray, nearlySortedArray};

            for (int t = 0; t < testArrays.length; t++) {
                long totalTime = 0;
                long totalMemoryUsed = 0;

                // Reset counters for this size and array type
                totalComparisons = 0;

                System.out.println("\nInitial " + arrayTypes[t] + " (size " + size + "):");
                printArray(testArrays[t]); // Print the initial array

                for (int i = 0; i < 100; i++) {
                    int[] arrayCopy = testArrays[t].clone();

                    // Measure memory usage before sorting
                    Runtime runtime = Runtime.getRuntime();
                    runtime.gc(); // Request garbage collection
                    long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

                    long startTime = System.currentTimeMillis();
                    mergesort3(arrayCopy, arrayCopy.length);
                    long endTime = System.currentTimeMillis();

                    // Measure memory usage after sorting
                    long afterMemory = runtime.totalMemory() - runtime.freeMemory();
                    long memoryUsed = afterMemory - beforeMemory;

                    totalMemoryUsed += memoryUsed;
                    totalTime += (endTime - startTime);
                }

                long averageTimeMillis = totalTime / 100;
                double averageTimeSeconds = averageTimeMillis / 1000.0;
                long averageMemoryUsed = totalMemoryUsed / 100;

                // Add to grand totals
                grandTotalTimeMillis += totalTime;
                grandTotalMemoryBytes += totalMemoryUsed;
                grandTotalComparisons += totalComparisons;

                System.out.println("\nSorted " + arrayTypes[t] + " (size " + size + "):");
                printArray(testArrays[t]); // Print the sorted array

                System.out.println("\nAverage execution time for " + arrayTypes[t] + " of size " + size + ":");
                System.out.println("    " + averageTimeMillis + " milliseconds");
                System.out.println("    " + averageTimeSeconds + " seconds");
                System.out.println("    Average Memory Used: " + averageMemoryUsed + " bytes");
                System.out.println("    Total Comparisons for size " + size + ": " + totalComparisons);

                // Record detailed results
                detailedResults.add("Array Type: " + arrayTypes[t] + 
                                    ", Size: " + size + 
                                    ", Average Time: " + averageTimeMillis + " ms");
            }
        }

        double grandTotalTimeSecondsFinal = grandTotalTimeMillis / 1000.0;
        long grandTotalMinutes = grandTotalTimeMillis / (60 * 1000);
        double grandTotalSeconds = (grandTotalTimeMillis % (60 * 1000)) / 1000.0;

        // Display grand totals
        System.out.println("\nGrand Total Execution Time for All Arrays and Sizes:");
        System.out.println("    " + grandTotalTimeMillis + " milliseconds");
        System.out.println("    " + grandTotalTimeSecondsFinal + " seconds");
        System.out.println("    " + grandTotalMinutes + " minutes and " + grandTotalSeconds + " seconds");
        System.out.println("    " + grandTotalMemoryBytes + " bytes of total memory used");
        System.out.println("    " + grandTotalComparisons + " total comparisons made");

        // Print detailed results
        System.out.println("\nDetailed Results Summary:");
        for (String result : detailedResults) {
            System.out.println(result);
        }
    }

    public static void mergesort3(int[] array, int n) {
        U = new int[n]; // Auxiliary array for merging

        // Start with single element arrays and merge iteratively
        for (int width = 1; width < n; width = 2 * width) {
            for (int i = 0; i < n; i = i + 2 * width) {
                int low = i;
                int mid = Math.min(i + width - 1, n - 1);
                int high = Math.min(i + 2 * width - 1, n - 1);

                // Merge the sorted subarrays
                merge3(array, low, mid, high);
            }

            // Copy merged result from U back to array
            System.arraycopy(U, 0, array, 0, n);
        }
    }

    public static void merge3(int[] array, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int k = low;

        // Merge the two subarrays into U
        while (i <= mid && j <= high) {
            totalComparisons++; // Count each comparison
            if (array[i] <= array[j]) {
                U[k] = array[i];
                i++;
            } else {
                U[k] = array[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of the left subarray if any
        while (i <= mid) {
            U[k] = array[i];
            i++;
            k++;
        }

        // Copy remaining elements of the right subarray if any
        while (j <= high) {
            U[k] = array[j];
            j++;
            k++;
        }
    }

    public static void printArray(int[] array) {
        int limit = Math.min(array.length, 100); // Limit output to 100 elements for readability
        for (int i = 0; i < limit; i++) {
            System.out.print(array[i] + " ");
        }
        if (array.length > 100) {
            System.out.println("... (output limited to 100 elements)");
        } else {
            System.out.println();
        }
    }
}
