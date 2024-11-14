public class Mergesort2 {
    static int[] S;

    public static void main(String[] args) {
        S = new int[]{16, 14, 5, 7, 1, 8, 12, 10};
        int n = S.length;

        mergesort2(S, 0, n - 1);

        System.out.println("Sorted array: ");
        for (int num : S) {
            System.out.print(num + " ");
        }
        System.out.println("\n");

        // Additional code for performance testing
        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};

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
                    mergesort2(arrayCopy, 0, arrayCopy.length - 1);
                    long endTime = System.nanoTime();
                    totalTime += (endTime - startTime);
                }

                long averageTimeNano = totalTime / 100;
                double averageTimeSeconds = averageTimeNano / 1.0e9; // Convert nanoseconds to seconds

                long minutes = (long) (averageTimeSeconds / 60);
                double seconds = averageTimeSeconds % 60;

                System.out.println("Average execution time for " + arrayTypes[t] + " array of size " + size + ":");
                System.out.println("    " + averageTimeNano + " nanoseconds");
                System.out.println("    " + averageTimeSeconds + " seconds");
                System.out.println("    " + minutes + " minutes and " + seconds + " seconds");
            }
        }
    }

    // Updated mergesort2 method to accept the array as a parameter
    public static void mergesort2(int[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;

            // Recursively sort the two halves
            mergesort2(array, low, mid);
            mergesort2(array, mid + 1, high);

            // Merge the sorted halves
            merge2(array, low, mid, high);
        }
    }

    // Updated merge2 method to accept the array as a parameter
    public static void merge2(int[] array, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; ++i)
            leftArray[i] = array[low + i];
        for (int j = 0; j < n2; ++j)
            rightArray[j] = array[mid + 1 + j];

        // Merge the temporary arrays
        int i = 0, j = 0;
        int k = low;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray if any
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArray if any
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
