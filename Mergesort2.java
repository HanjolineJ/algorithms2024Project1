 public class Mergesort2 {
    private static int[] S;  // Array to be sorted

    public static void main(String[] args) {
        S = new int[]{16, 14, 5, 7, 1, 8, 12, 10};
        int n = S.length;

        mergesort2(1, n);  // Sort the array from index 1 to n (following textbook convention)

        System.out.println("Sorted array: ");
        for (int num : S) {
            System.out.print(num + " ");
        }
        System.out.println("\n");

        // Additional code for performance testing with different sizes
        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
        long grandTotalTimeMillis = 0; // Variable to track grand total time in milliseconds
        long grandTotalMemoryBytes = 0; // Variable to track grand total memory usage in bytes
        long grandTotalMemoryBits = 0;  // Variable to track grand total memory usage in bits

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
                long totalMemoryUsed = 0; // Accumulate memory usage

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
                double averageTimeSeconds = averageTimeMillis / 1000.0; // Convert milliseconds to seconds
                long averageMemoryUsed = totalMemoryUsed / 100; // Average memory used in bytes

                // Add to the grand totals
                grandTotalTimeMillis += totalTime;
                grandTotalMemoryBytes += totalMemoryUsed;

                long minutes = averageTimeMillis / (60 * 1000);
                double seconds = (averageTimeMillis % (60 * 1000)) / 1000.0;

                System.out.println("Average execution time for " + arrayTypes[t] + " array of size " + size + ":");
                System.out.println("    " + averageTimeMillis + " milliseconds");
                System.out.println("    " + averageTimeSeconds + " seconds");
                System.out.println("    " + minutes + " minutes and " + seconds + " seconds");
                System.out.println("    " + averageMemoryUsed + " bytes of memory used");
                System.out.println("    " + (averageMemoryUsed * 8) + " bits of memory used");
            }
        }

        // Calculate grand total memory in bits
        grandTotalMemoryBits = grandTotalMemoryBytes * 8;

        // Display grand total time and memory across all sorts
        double grandTotalTimeSecondsFinal = grandTotalTimeMillis / 1000.0; // Convert milliseconds to seconds for grand total
        long grandTotalMinutes = grandTotalTimeMillis / (60 * 1000);
        double grandTotalSeconds = (grandTotalTimeMillis % (60 * 1000)) / 1000.0;

        System.out.println("\nGrand Total Execution Time for All Arrays and Sizes:");
        System.out.println("    " + grandTotalTimeMillis + " milliseconds");
        System.out.println("    " + grandTotalTimeSecondsFinal + " seconds");
        System.out.println("    " + grandTotalMinutes + " minutes and " + grandTotalSeconds + " seconds");
        System.out.println("    " + grandTotalMemoryBytes + " bytes of total memory used");
        System.out.println("    " + grandTotalMemoryBits + " bits of total memory used");
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
