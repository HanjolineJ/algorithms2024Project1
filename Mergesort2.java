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
        long grandTotalTimeNano = 0; // Variable to track grand total time in nanoseconds
        double grandTotalTimeSeconds = 0.0; // Variable to track grand total time in seconds

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

                for (int i = 0; i < 100; i++) {
                    int[] arrayCopy = generatedArray.clone();
                    S = arrayCopy;
                    long startTime = System.nanoTime();
                    mergesort2(1, S.length);
                    long endTime = System.nanoTime();
                    totalTime += (endTime - startTime);
                }

                long averageTimeNano = totalTime / 100;
                double averageTimeSeconds = averageTimeNano / 1.0e9; // Convert nanoseconds to seconds
                
                // Add to the grand total counters
                grandTotalTimeNano += totalTime;
                grandTotalTimeSeconds += averageTimeSeconds;

                long minutes = (long) (averageTimeSeconds / 60);
                double seconds = averageTimeSeconds % 60;

                System.out.println("Average execution time for " + arrayTypes[t] + " array of size " + size + ":");
                System.out.println("    " + averageTimeNano + " nanoseconds");
                System.out.println("    " + averageTimeSeconds + " seconds");
                System.out.println("    " + minutes + " minutes and " + seconds + " seconds");
            }
        }

        // Display grand total time across all sorts
        double grandTotalTimeSecondsFinal = grandTotalTimeNano / 1.0e9; // Convert nanoseconds to seconds for grand total
        long grandTotalMinutes = (long) (grandTotalTimeSecondsFinal / 60);
        double grandTotalSeconds = grandTotalTimeSecondsFinal % 60;

        System.out.println("\nGrand Total Execution Time for All Arrays and Sizes:");
        System.out.println("    " + grandTotalTimeNano + " nanoseconds");
        System.out.println("    " + grandTotalTimeSecondsFinal + " seconds");
        System.out.println("    " + grandTotalMinutes + " minutes and " + grandTotalSeconds + " seconds");
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
