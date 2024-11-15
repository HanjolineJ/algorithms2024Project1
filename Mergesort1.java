public class Mergesort1 {
    static long totalComparisons = 0; // Counter for comparisons in merge
    static long totalMemoryAllocated = 0; // Counter for memory allocated in merge (bytes)
    static long grandTotalComparisons = 0; // Counter for total comparisons across all tests

    public static void main(String[] args) {
        System.out.println("Merge Sort Algorithm Used: MergeSort1\n");

        int[] array = {16, 14, 5, 7, 1, 8, 12, 10, 4, 5};
        mergeSort(array, 0, array.length - 1);

        System.out.println("Sorted array: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println("\n");

        // Display time and space complexity analysis
        System.out.println("Mergesort Analysis:");
        System.out.println("    Algorithm: Top-Down Recursive Merge Sort");
        System.out.println("    Total Comparisons Made: " + totalComparisons);
        System.out.println("    Total Memory Allocated: " + totalMemoryAllocated + " bytes");
        System.out.println("    Time Complexity: O(n log n)");
        System.out.println("    Space Complexity: O(n)");

        // Performance testing
        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
        long grandTotalTimeMillis = 0; // Total time in milliseconds
        long grandTotalMemoryBytes = 0; // Total memory in bytes

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

                // Reset counters
                totalComparisons = 0;
                totalMemoryAllocated = 0;

                for (int i = 0; i < 100; i++) {
                    int[] arrayCopy = generatedArray.clone();

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
                grandTotalComparisons += totalComparisons; // Accumulate total comparisons

                long minutes = averageTimeMillis / (60 * 1000);
                double seconds = (averageTimeMillis % (60 * 1000)) / 1000.0;

                System.out.println("Average execution time for " + arrayTypes[t] + " array of size " + size + ":");
                System.out.println("    " + averageTimeMillis + " milliseconds");
                System.out.println("    " + averageTimeSeconds + " seconds");
                System.out.println("    " + minutes + " minutes and " + seconds + " seconds");
                System.out.println("    " + averageMemoryUsed + " bytes of memory used");
                System.out.println("    Total Comparisons: " + totalComparisons);
                System.out.println("    Total Memory Allocated: " + totalMemoryAllocated + " bytes");
            }
        }

        double grandTotalTimeSecondsFinal = grandTotalTimeMillis / 1000.0;
        long grandTotalMinutes = grandTotalTimeMillis / (60 * 1000);
        double grandTotalSeconds = (grandTotalTimeMillis % (60 * 1000)) / 1000.0;

        System.out.println("\nGrand Total Execution Time for All Arrays and Sizes:");
        System.out.println("    " + grandTotalTimeMillis + " milliseconds");
        System.out.println("    " + grandTotalTimeSecondsFinal + " seconds");
        System.out.println("    " + grandTotalMinutes + " minutes and " + grandTotalSeconds + " seconds");
        System.out.println("    " + grandTotalMemoryBytes + " bytes of total memory used");
        System.out.println("    " + grandTotalComparisons + " total comparisons made");
        System.out.println("Merge Sort Algorithm Used: MergeSort1\n");
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

        totalMemoryAllocated += (n1 + n2) * Integer.BYTES; // Memory allocated for L and R

        for (int i = 0; i < n1; i++) {
            L[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            totalComparisons++; // Count each comparison
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
}
