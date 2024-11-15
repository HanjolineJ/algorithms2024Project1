public class Mergesort2 {
    private static int[] S;  // Array to be sorted
    private static long totalComparisons = 0; // Counter for comparisons
    private static long grandTotalComparisons = 0; // Counter for grand total comparisons

    public static void main(String[] args) {
        System.out.println("Merge Sort Algorithm Used: MergeSort2\n");

        S = new int[]{16, 14, 5, 7, 1, 8, 12, 10, 6, 8};
        int n = S.length;

        mergesort2(1, n);  // Sort the array from index 1 to n (following textbook convention)

        System.out.println("Sorted array: ");
        for (int num : S) {
            System.out.print(num + " ");
        }
        System.out.println("\n");

        // Display initial analysis
        System.out.println("Mergesort Analysis:");
        System.out.println("    Algorithm: Top-Down Recursive Merge Sort");
        System.out.println("    Total Comparisons Made: " + totalComparisons);
        System.out.println("    Time Complexity: O(n log n)");
        System.out.println("    Space Complexity: O(n)");

        // Performance testing
        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
        long grandTotalTimeMillis = 0; // Variable to track grand total time in milliseconds
        long grandTotalMemoryBytes = 0; // Variable to track grand total memory usage in bytes

        for (int size : sizes) {
            int[][] testArrays = {
                ArrayGenerator.generateRandomArray(size),
                ArrayGenerator.generateSortedArray(size),
                ArrayGenerator.generateReversedArray(size),
                ArrayGenerator.generateNearlySortedArray(size, 0.05)
            };

            String[] arrayTypes = {"Random", "Sorted", "Reversed", "Nearly Sorted"};
            for (int t = 0; t < testArrays.length; t++) {
                int[] generatedArray = testArrays[t];
                long totalTime = 0;
                long totalMemoryUsed = 0;

                // Reset local counters
                totalComparisons = 0;

                for (int i = 0; i < 100; i++) {
                    int[] arrayCopy = generatedArray.clone();
                    S = arrayCopy;

                    // Measure memory usage before sorting
                    Runtime runtime = Runtime.getRuntime();
                    runtime.gc(); // Request garbage collection
                    long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

                    long startTime = System.currentTimeMillis();
                    mergesort2(1, S.length);
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

                long minutes = averageTimeMillis / (60 * 1000);
                double seconds = (averageTimeMillis % (60 * 1000)) / 1000.0;

                System.out.println("Average execution time for " + arrayTypes[t] + " array of size " + size + ":");
                System.out.println("    " + averageTimeMillis + " milliseconds");
                System.out.println("    " + averageTimeSeconds + " seconds");
                System.out.println("    " + minutes + " minutes and " + seconds + " seconds");
                System.out.println("    " + averageMemoryUsed + " bytes of memory used");
                System.out.println("    Total Comparisons: " + totalComparisons);
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
        System.out.println("Merge Sort Algorithm Used: MergeSort2\n");
    }

    // Mergesort2 method, following textbook convention of indexing from 1
    public static void mergesort2(int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergesort2(low, mid);
            mergesort2(mid + 1, high);
            merge2(low, mid, high);
        }
    }

    // Merge method to merge the sorted subarrays (Algorithm 2.5)
    public static void merge2(int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        // Create temporary arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            L[i] = S[low - 1 + i];  // Adjust for indexing from 1 (low - 1)
        }
        for (int j = 0; j < n2; j++) {
            R[j] = S[mid + j];
        }

        // Merge the temp arrays back into S
        int i = 0, j = 0, k = low - 1;
        while (i < n1 && j < n2) {
            totalComparisons++; // Count each comparison
            if (L[i] <= R[j]) {
                S[k] = L[i];
                i++;
            } else {
                S[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L, if any
        while (i < n1) {
            S[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R, if any
        while (j < n2) {
            S[k] = R[j];
            j++;
            k++;
        }
    }
}
