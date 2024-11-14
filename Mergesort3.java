public class Mergesort3 {
    static int[] S;
    static int[] U;

    public static void main(String[] args) {
        S = new int[]{16, 14, 5, 7, 1, 8, 12, 10};
        int n = S.length;
        U = new int[n];

        mergesort3(S, n);

        System.out.println("Sorted array: ");
        for (int num : S) {
            System.out.print(num + " ");
        }
        System.out.println("\n");

        // Additional code for performance testing
        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
        long grandTotalTimeNano = 0; // Initialize variable to keep track of the grand total time in nanoseconds

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

                // Run the sort 100 times and calculate the average execution time
                for (int i = 0; i < 100; i++) {
                    int[] arrayCopy = generatedArray.clone();
                    long startTime = System.nanoTime();
                    mergesort3(arrayCopy, arrayCopy.length);
                    long endTime = System.nanoTime();
                    totalTime += (endTime - startTime);
                }

                long averageTimeNano = totalTime / 100;
                double averageTimeSeconds = averageTimeNano / 1.0e9; // Convert nanoseconds to seconds

                // Add to the grand total counter in nanoseconds
                grandTotalTimeNano += totalTime;

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

    // Updated mergesort3 method to accept an array and size as parameters
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
}
