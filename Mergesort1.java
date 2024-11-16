import java.util.ArrayList;
import java.util.List;

public class Mergesort1 {
    static long totalComparisons = 0; // Counter for comparisons in merge
    static long totalMemoryAllocated = 0; // Counter for memory allocated in merge (bytes)

    // To store detailed results for summary at the end
    static List<String> detailedResults = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Merge Sort Algorithm Used: Mergesort1\n");

        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
        String[] arrayTypes = {"Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array"};

        long grandTotalTimeMillis = 0; // Total time in milliseconds
        long grandTotalMemoryBytes = 0; // Total memory used in bytes
        long grandTotalComparisons = 0; // Total comparisons across all runs

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
                totalMemoryAllocated = 0;

                System.out.println("\nInitial " + arrayTypes[t] + " (size " + size + "):");
                printArray(testArrays[t]); // Print the initial array

                for (int i = 0; i < 100; i++) {
                    int[] arrayCopy = testArrays[t].clone();

                    // Measure memory usage before sorting
                    Runtime runtime = Runtime.getRuntime();
                    runtime.gc(); // Request garbage collection
                    long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

                    long startTime = System.currentTimeMillis();
                    mergeSort(arrayCopy, 0, arrayCopy.length - 1);
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

                // Add to detailed summary
                detailedResults.add("Array Type: " + arrayTypes[t] +
                        ", Size: " + size +
                        ", Average Time: " + averageTimeMillis + " ms");

                System.out.println("\nSorted " + arrayTypes[t] + " (size " + size + "):");
                printArray(testArrays[t]); // Print the sorted array

                System.out.println("\nAverage execution time for " + arrayTypes[t] + " of size " + size + ":");
                System.out.println("    " + averageTimeMillis + " milliseconds");
                System.out.println("    " + averageTimeSeconds + " seconds");
                System.out.println("    Average Memory Used: " + averageMemoryUsed + " bytes");
                System.out.println("    Total Comparisons for size " + size + ": " + totalComparisons);
                System.out.println("    Total Memory Allocated for size " + size + ": " + totalMemoryAllocated + " bytes");
            }
        }

        double grandTotalTimeSecondsFinal = grandTotalTimeMillis / 1000.0;
        long grandTotalMinutes = grandTotalTimeMillis / (60 * 1000);
        double grandTotalSeconds = (grandTotalTimeMillis % (60 * 1000)) / 1000.0;

        System.out.println("\nGrand Total Execution Time for All Sizes:");
        System.out.println("    " + grandTotalTimeMillis + " milliseconds");
        System.out.println("    " + grandTotalTimeSecondsFinal + " seconds");
        System.out.println("    " + grandTotalMinutes + " minutes and " + grandTotalSeconds + " seconds");
        System.out.println("    Grand Total Comparisons: " + grandTotalComparisons);
        System.out.println("    Grand Total Memory Used: " + grandTotalMemoryBytes + " bytes");

        // Print detailed summary
        System.out.println("\nDetailed Results Summary:");
        for (String result : detailedResults) {
            System.out.println(result);
        }
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    public static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        totalMemoryAllocated += (n1 + n2) * Integer.BYTES; // Memory allocated for L and R arrays

        for (int i = 0; i < n1; i++) {
            L[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            totalComparisons++; // Count comparisons
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
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
            System.out.println("... (only for 100 instances for large arrays)");
        } else {
            System.out.println();
        }
    }
}
