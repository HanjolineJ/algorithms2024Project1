// public class Mergesort2 {
//     private static int[] S;  // Array to be sorted
//     private static long totalComparisons = 0; // Counter for comparisons
//     private static long grandTotalComparisons = 0; // Counter for grand total comparisons

//     public static void main(String[] args) {
//         System.out.println("Merge Sort Algorithm Used: MergeSort2\n");

//         S = new int[]{16, 14, 5, 7, 1, 8, 12, 10, 6, 8};
//         int n = S.length;

//         mergesort2(1, n);  // Sort the array from index 1 to n (following textbook convention)

//         System.out.println("Sorted array: ");
//         for (int num : S) {
//             System.out.print(num + " ");
//         }
//         System.out.println("\n");

//         // Display initial analysis
//         System.out.println("Mergesort Analysis:");
//         System.out.println("    Algorithm: Top-Down Recursive Merge Sort");
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
//                     S = arrayCopy;

//                     // Measure memory usage before sorting
//                     Runtime runtime = Runtime.getRuntime();
//                     runtime.gc(); // Request garbage collection
//                     long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

//                     long startTime = System.currentTimeMillis();
//                     mergesort2(1, S.length);
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
//         System.out.println("Merge Sort Algorithm Used: MergeSort2\n");
//     }

//     // Mergesort2 method, following textbook convention of indexing from 1
//     public static void mergesort2(int low, int high) {
//         if (low < high) {
//             int mid = (low + high) / 2;
//             mergesort2(low, mid);
//             mergesort2(mid + 1, high);
//             merge2(low, mid, high);
//         }
//     }

//     // Merge method to merge the sorted subarrays (Algorithm 2.5)
//     public static void merge2(int low, int mid, int high) {
//         int n1 = mid - low + 1;
//         int n2 = high - mid;

//         // Create temporary arrays
//         int[] L = new int[n1];
//         int[] R = new int[n2];

//         // Copy data to temporary arrays
//         for (int i = 0; i < n1; i++) {
//             L[i] = S[low - 1 + i];  // Adjust for indexing from 1 (low - 1)
//         }
//         for (int j = 0; j < n2; j++) {
//             R[j] = S[mid + j];
//         }

//         // Merge the temp arrays back into S
//         int i = 0, j = 0, k = low - 1;
//         while (i < n1 && j < n2) {
//             totalComparisons++; // Count each comparison
//             if (L[i] <= R[j]) {
//                 S[k] = L[i];
//                 i++;
//             } else {
//                 S[k] = R[j];
//                 j++;
//             }
//             k++;
//         }

//         // Copy remaining elements of L, if any
//         while (i < n1) {
//             S[k] = L[i];
//             i++;
//             k++;
//         }

//         // Copy remaining elements of R, if any
//         while (j < n2) {
//             S[k] = R[j];
//             j++;
//             k++;
//         }
//     }
// }

// import java.util.ArrayList;
// import java.util.List;

// public class Mergesort2 {
//     static long totalComparisons = 0; // Counter for comparisons in merge
//     static long totalMemoryAllocated = 0; // Counter for memory allocated in merge (bytes)
//     static List<String> detailedResults = new ArrayList<>(); // For detailed results summary
//     private static int[] S;  // Array to be sorted

//     public static void main(String[] args) {
//         System.out.println("Merge Sort Algorithm Used: Mergesort2 (Textbook Reference)\n");

//         int[] sizes = {10, 100, 1000, 10000, 100000, 1000000}; // Array sizes to test
//         String[] arrayTypes = {"Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array"};

//         long grandTotalTimeMillis = 0; // Grand total time in milliseconds
//         long grandTotalMemoryBytes = 0; // Grand total memory usage in bytes
//         long grandTotalComparisons = 0; // Grand total comparisons

//         for (int size : sizes) {
//             int[] randomArray = ArrayGenerator.generateRandomArray(size);
//             int[] sortedArray = ArrayGenerator.generateSortedArray(size);
//             int[] reversedArray = ArrayGenerator.generateReversedArray(size);
//             int[] nearlySortedArray = ArrayGenerator.generateNearlySortedArray(size, 0.05);

//             int[][] testArrays = {randomArray, sortedArray, reversedArray, nearlySortedArray};

//             for (int t = 0; t < testArrays.length; t++) {
//                 long totalTime = 0;
//                 long totalMemoryUsed = 0;

//                 // Reset counters for the current array type and size
//                 totalComparisons = 0;
//                 totalMemoryAllocated = 0;

//                 System.out.println("\nInitial " + arrayTypes[t] + " (size " + size + "):");
//                 printArray(testArrays[t]); // Print the initial array

//                 for (int i = 0; i < 100; i++) { // Run 100 times for averaging
//                     int[] arrayCopy = testArrays[t].clone();
//                     S = new int[arrayCopy.length + 1]; // Prepare 1-based array
//                     System.arraycopy(arrayCopy, 0, S, 1, arrayCopy.length); // Copy elements to 1-based index

//                     // Measure memory usage before sorting
//                     Runtime runtime = Runtime.getRuntime();
//                     runtime.gc(); // Request garbage collection
//                     long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

//                     long startTime = System.currentTimeMillis();
//                     mergesort2(1, arrayCopy.length); // Sort using textbook-based mergesort
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

//                 // Add to detailed summary
//                 detailedResults.add("Array Type: " + arrayTypes[t] +
//                         ", Size: " + size +
//                         ", Average Time: " + averageTimeMillis + " ms");

//                 System.out.println("\nSorted " + arrayTypes[t] + " (size " + size + "):");
//                 printArray(S); // Print the sorted array (convert to 0-based for display)

//                 System.out.println("\nAverage execution time for " + arrayTypes[t] + " of size " + size + ":");
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

//     public static void mergesort2(int low, int high) {
//         if (low < high) {
//             int mid = (low + high) / 2;
//             mergesort2(low, mid);
//             mergesort2(mid + 1, high);
//             merge2(low, mid, high);
//         }
//     }

//     public static void merge2(int low, int mid, int high) {
//         int n1 = mid - low + 1;
//         int n2 = high - mid;

//         // Create temporary arrays
//         int[] L = new int[n1];
//         int[] R = new int[n2];

//         // Copy data to temporary arrays
//         for (int i = 0; i < n1; i++) {
//             L[i] = S[low + i - 1]; // Adjust for 1-based indexing
//         }
//         for (int j = 0; j < n2; j++) {
//             R[j] = S[mid + j];
//         }

//         // Merge the temporary arrays
//         int i = 0, j = 0, k = low - 1;
//         while (i < n1 && j < n2) {
//             totalComparisons++;
//             if (L[i] <= R[j]) {
//                 S[k] = L[i];
//                 i++;
//             } else {
//                 S[k] = R[j];
//                 j++;
//             }
//             k++;
//         }

//         // Copy remaining elements of L[], if any
//         while (i < n1) {
//             S[k] = L[i];
//             i++;
//             k++;
//         }

//         // Copy remaining elements of R[], if any
//         while (j < n2) {
//             S[k] = R[j];
//             j++;
//             k++;
//         }
//     }

//     public static void printArray(int[] array) {
//         int limit = Math.min(array.length, 100); // Limit output to 100 elements for readability
//         for (int i = 1; i < limit; i++) { // Adjust for 1-based indexing
//             System.out.print(array[i] + " ");
//         }
//         if (array.length > 100) {
//             System.out.println("... (output limited to 100 elements)");
//         } else {
//             System.out.println();
//         }
//     }
// }

// import java.util.ArrayList;
// import java.util.List;

// public class Mergesort2 {
//     static long totalMemoryAllocated = 0; // Counter for memory allocated during merge
//     static List<String> detailedResults = new ArrayList<>(); // For detailed results summary
//     private static int[] S;  // Array to be sorted

//     public static void main(String[] args) {
//         System.out.println("Merge Sort Algorithm Used: Mergesort2 (Textbook Reference)\n");

//         // Allow setting an initial custom array for demonstration
//         int[] customArray = {2679, 7481, 2801, 5558, 5353, 1605, 214, 4653, 3213, 7961};

//         // Print the initial custom array
//         System.out.println("Initial Custom Array (size " + customArray.length + "):");
//         printCustomArray(customArray);

//         // Prepare for 1-based indexing
//         S = new int[customArray.length + 1];
//         System.arraycopy(customArray, 0, S, 1, customArray.length);

//         // Sort the custom array using Mergesort2
//         mergesort2(1, customArray.length);

//         // Print the sorted custom array
//         System.out.println("Sorted Custom Array (size " + customArray.length + "):");
//         printArray(S);

//         // Performance testing with different array sizes and input types
//         int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
//         String[] arrayTypes = {"Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array"};

//         long grandTotalTimeMillis = 0; // Grand total time in milliseconds
//         long grandTotalMemoryBytes = 0; // Grand total memory used in bytes

//         for (int size : sizes) {
//             for (String arrayType : arrayTypes) {
//                 int[] testArray = generateArray(arrayType, size);
//                 long totalTime = 0;
//                 long totalMemoryUsed = 0;

//                 System.out.println("\nInitial " + arrayType + " (size " + size + "):");
//                 printCustomArray(testArray);

//                 for (int i = 0; i < 100; i++) { // Run 100 times for averaging
//                     int[] arrayCopy = testArray.clone();
//                     S = new int[arrayCopy.length + 1]; // Prepare 1-based array
//                     System.arraycopy(arrayCopy, 0, S, 1, arrayCopy.length); // Copy elements to 1-based index

//                     // Measure memory usage before sorting
//                     Runtime runtime = Runtime.getRuntime();
//                     runtime.gc(); // Request garbage collection
//                     long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

//                     long startTime = System.currentTimeMillis();
//                     mergesort2(1, arrayCopy.length); // Sort using textbook-based mergesort
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

//                 // Add to detailed summary
//                 detailedResults.add("Array Type: " + arrayType +
//                         ", Size: " + size +
//                         ", Average Time: " + averageTimeMillis + " ms" +
//                         ", Average Memory Used: " + averageMemoryUsed + " bytes");

//                 System.out.println("\nSorted " + arrayType + " (size " + size + "):");
//                 printArray(S);

//                 System.out.println("\nAverage execution time for " + arrayType + " of size " + size + ":");
//                 System.out.println("    " + averageTimeMillis + " milliseconds");
//                 System.out.println("    " + averageTimeSeconds + " seconds");
//                 System.out.println("    Average Memory Used: " + averageMemoryUsed + " bytes");
//             }
//         }

//         // Print the custom array again at the end for reference
        

//         System.out.println("\nGrand Total Execution Time for All Sizes:");
//         System.out.println("    " + grandTotalTimeMillis + " milliseconds");
//         System.out.println("    " + (grandTotalTimeMillis / 1000.0) + " seconds");
//         System.out.println("    Grand Total Memory Used: " + grandTotalMemoryBytes + " bytes");

//         // Print detailed summary
//         System.out.println("\nDetailed Results Summary:");
//         for (String result : detailedResults) {
//             System.out.println(result);
//         }
//     }

//     public static void mergesort2(int low, int high) {
//         if (low < high) {
//             int mid = (low + high) / 2;
//             mergesort2(low, mid);
//             mergesort2(mid + 1, high);
//             merge2(low, mid, high);
//         }
//     }

//     public static void merge2(int low, int mid, int high) {
//         int[] U = new int[high - low + 1 + 1]; // Temporary array for merging (1-based indexing)
//         totalMemoryAllocated += U.length * Integer.BYTES; // Memory allocated for temporary array
//         int i = low, j = mid + 1, k = 1;

//         // Merge the two subarrays
//         while (i <= mid && j <= high) {
//             if (S[i] <= S[j]) {
//                 U[k++] = S[i++];
//             } else {
//                 U[k++] = S[j++];
//             }
//         }

//         // Copy the remaining elements of the left subarray, if any
//         while (i <= mid) {
//             U[k++] = S[i++];
//         }

//         // Copy the remaining elements of the right subarray, if any
//         while (j <= high) {
//             U[k++] = S[j++];
//         }

//         // Copy the merged array back to the original array
//         for (k = 1; k <= (high - low + 1); k++) {
//             S[low + k - 1] = U[k];
//         }
//     }

//     public static void printArray(int[] array) {
//         int limit = Math.min(array.length - 1, 25); // Adjust for 1-based indexing
//         for (int i = 1; i <= limit; i++) { // Adjust for 1-based indexing
//             System.out.print(array[i] + " ");
//         }
//         if (array.length > 26) {
//             System.out.println("... (output limited to 25 elements)");
//         } else {
//             System.out.println();
//         }
//     }

//     public static void printCustomArray(int[] array) {
//         int limit = Math.min(array.length, 25); // For 0-based indexing
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
//                 for (int i = 0; i < size * 0.05; i++) array[(int) (Math.random() * size)] = (int) (Math.random() * 10000);
//                 break;
//         }
//         return array;
//     }
// }

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mergesort2 {
    static long totalMemoryAllocated = 0; // Counter for memory allocated during merge
    static List<String> detailedResults = new ArrayList<>(); // Detailed results for summary
    private static int[] S; // Array to be sorted

    public static void main(String[] args) {
        System.out.println("Merge Sort Algorithm Used: Mergesort2 \n");

        // Custom array for demonstration
        int[] customArray = {44, 7481, 23, 1111111, 0000004, 0005005, 214, 43, 13, 7961};

        System.out.println("Initial Custom Array (size " + customArray.length + "):");
        printCustomArray(customArray);

        // Prepare for 1-based indexing
        S = new int[customArray.length + 1];
        System.arraycopy(customArray, 0, S, 1, customArray.length);

        // Sort the custom array using Mergesort2
        mergesort2(1, customArray.length);

        System.out.println("Sorted Custom Array (size " + customArray.length + "):");
        printArray(S);

        // Performance testing with different array sizes and input types
        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
        String[] arrayTypes = {"Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array"};

        long grandTotalTimeMillis = 0; // Grand total time in milliseconds
        long grandTotalMemoryBytes = 0; // Grand total memory used in bytes

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Mergesort2_Results.txt"))) {
            writer.write("Detailed Results:\n");

            for (int size : sizes) {
                for (String arrayType : arrayTypes) {
                    int[] testArray = generateArray(arrayType, size);
                    long totalTime = 0;
                    long totalMemoryUsed = 0;

                    System.out.println("\nInitial " + arrayType + " (size " + size + "):");
                    printCustomArray(testArray);

                    for (int i = 0; i < 100; i++) { // Run 100 times for averaging
                        int[] arrayCopy = testArray.clone();
                        S = new int[arrayCopy.length + 1]; // Prepare 1-based array
                        System.arraycopy(arrayCopy, 0, S, 1, arrayCopy.length);

                        // Measure memory usage before sorting
                        Runtime runtime = Runtime.getRuntime();
                        runtime.gc(); // Request garbage collection
                        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

                        long startTime = System.nanoTime();
                        mergesort2(1, arrayCopy.length); // Sort using textbook-based mergesort
                        long endTime = System.nanoTime();

                        // Measure memory usage after sorting
                        long afterMemory = runtime.totalMemory() - runtime.freeMemory();
                        long memoryUsed = afterMemory - beforeMemory;

                        totalMemoryUsed += memoryUsed;
                        totalTime += (endTime - startTime);
                    }

                    long averageTimeNanos = totalTime / 100;
                    double averageTimeMillis = averageTimeNanos / 1_000_000.0;
                    double averageTimeSeconds = averageTimeMillis / 1000.0;
                    long averageMemoryUsed = totalMemoryUsed / 100;

                    // Add to grand totals
                    grandTotalTimeMillis += totalTime / 1_000_000;
                    grandTotalMemoryBytes += totalMemoryUsed;

                    // Add to detailed summary
                    String result = "Array Type: " + arrayType +
                            ", Size: " + size +
                            ", Average Time: " + averageTimeMillis + " ms (" + averageTimeSeconds + " s)" +
                            ", Average Memory Used: " + averageMemoryUsed + " bytes";
                    detailedResults.add(result);

                    // Write to file
                    writer.write(result + "\n");

                    System.out.println("\nSorted " + arrayType + " (size " + size + "):");
                    printArray(S);

                    System.out.println("\nAverage execution time for " + arrayType + " of size " + size + ":");
                    System.out.println("    " + averageTimeMillis + " milliseconds");
                    System.out.println("    " + averageTimeSeconds + " seconds");
                    System.out.println("    Average Memory Used: " + averageMemoryUsed + " bytes");
                }
            }

            writer.write("\nGrand Totals:\n");
            writer.write("Total Execution Time: " + grandTotalTimeMillis + " ms\n");
            writer.write("Total Memory Used: " + grandTotalMemoryBytes + " bytes\n");
        } catch (IOException e) {
            System.err.println("Error writing to results file: " + e.getMessage());
        }

        System.out.println("\nGrand Total Execution Time for All Sizes:");
        System.out.println("    " + grandTotalTimeMillis + " milliseconds");
        System.out.println("    " + (grandTotalTimeMillis / 1000.0) + " seconds");
        System.out.println("    Grand Total Memory Used: " + grandTotalMemoryBytes + " bytes");

        System.out.println("\nDetailed Results Summary:");
        for (String result : detailedResults) {
            System.out.println(result);
        }
    }

    public static void mergesort2(int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergesort2(low, mid);
            mergesort2(mid + 1, high);
            merge2(low, mid, high);
        }
    }

    public static void merge2(int low, int mid, int high) {
        int[] U = new int[high - low + 2]; // Temporary array for merging (1-based indexing)
        totalMemoryAllocated += U.length * Integer.BYTES; // Memory allocated for temporary array
        int i = low, j = mid + 1, k = 1;

        // Merge the two subarrays
        while (i <= mid && j <= high) {
            if (S[i] <= S[j]) {
                U[k++] = S[i++];
            } else {
                U[k++] = S[j++];
            }
        }

        // Copy the remaining elements of the left subarray, if any
        while (i <= mid) {
            U[k++] = S[i++];
        }

        // Copy the remaining elements of the right subarray, if any
        while (j <= high) {
            U[k++] = S[j++];
        }

        // Copy the merged array back to the original array
        for (k = 1; k <= (high - low + 1); k++) {
            S[low + k - 1] = U[k];
        }
    }

    public static void printArray(int[] array) {
        int limit = Math.min(array.length - 1, 25); // Adjust for 1-based indexing
        for (int i = 1; i <= limit; i++) {
            System.out.print(array[i] + " ");
        }
        if (array.length > 26) {
            System.out.println("... (output limited to 25 elements)");
        } else {
            System.out.println();
        }
    }

    public static void printCustomArray(int[] array) {
        int limit = Math.min(array.length, 25); // For 0-based indexing
        for (int i = 0; i < limit; i++) {
            System.out.print(array[i] + " ");
        }
        if (array.length > 25) {
            System.out.println("... (output limited to 25 elements)");
        } else {
            System.out.println();
        }
    }

    public static int[] generateArray(String type, int size) {
        int[] array = new int[size];
        switch (type) {
            case "Random Array":
                for (int i = 0; i < size; i++) array[i] = (int) (Math.random() * 10000);
                break;
            case "Sorted Array":
                for (int i = 0; i < size; i++) array[i] = i;
                break;
            case "Reversed Array":
                for (int i = 0; i < size; i++) array[i] = size - i;
                break;
            case "Nearly Sorted Array":
                for (int i = 0; i < size; i++) array[i] = i;
                for (int i = 0; i < size * 0.05; i++) array[(int) (Math.random() * size)] = (int) (Math.random() * 10000);
                break;
        }
        return array;
    }
}
