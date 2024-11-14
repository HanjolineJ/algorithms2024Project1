public class Mergesort1 {
    public static void main(String[] args) {
        // Original main method for testing small hardcoded array
        int[] array = {16, 14, 5, 7, 1, 8, 12, 10};
        mergeSort(array, 0, array.length - 1);
        
        System.out.println("Sorted array: ");
        for (int num : array) {
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
                    mergeSort(arrayCopy, 0, arrayCopy.length - 1);
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

    // Standard Mergesort method
    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    // Merge function
    public static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
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
