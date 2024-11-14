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
                    mergeSort(arrayCopy, 0, arrayCopy.length - 1);
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

    // Standard Mergesort method
    public static void mergeSort(int[] array, int left, int right) { // left is the index of the first element and right is the index of the last element
        if (left < right) { // Base case: if left is not smaller than right
            int mid = (left + right) / 2; // Find the middle point
            mergeSort(array, left, mid); // Sort the left half
            mergeSort(array, mid + 1, right); // Sort the right half
            merge(array, left, mid, right); // Merge the sorted halves
        }
    }

    // Merge function
    public static void merge(int[] array, int left, int mid, int right) { // left is the index of the first element, mid is the index of the middle element, right is the index of the last element
        int n1 = mid - left + 1; // Size of the left subarray
        int n2 = right - mid; // Size of the right subarray

        int[] L = new int[n1]; // Create temporary arrays
        int[] R = new int[n2]; // Create temporary arrays

        for (int i = 0; i < n1; i++) { // Copy data to temporary arrays L[] and R[]
            L[i] = array[left + i]; // Copy the left subarray
        }
        for (int j = 0; j < n2; j++) { // Copy data to temporary arrays L[] and R[]
            R[j] = array[mid + 1 + j]; // Copy the right subarray
        }

        int i = 0, j = 0, k = left; // Initial indexes of first and second subarrays
        while (i < n1 && j < n2) { // Merge the temporary arrays back into array[left..right]
            if (L[i] <= R[j]) { // Compare elements of the two subarrays
                array[k] = L[i]; // Copy the smaller element to array
                i++; // Increment index of the left subarray
            } else {
                array[k] = R[j]; // Copy the smaller element to array
                j++; // Increment index of the right subarray
            }
            k++; // Increment index of the merged array
        }

        while (i < n1) { // Copy the remaining elements of L[], if there are any
            array[k] = L[i]; // Copy the remaining elements of the left subarray
            i++; // Increment index of the left subarray
            k++; // Increment index of the merged array
        }

        while (j < n2) { // Copy the remaining elements of R[], if there are any
            array[k] = R[j]; // Copy the remaining elements of the right subarray
            j++; // Increment index of the right subarray
            k++; // Increment index of the merged array
        }
    }
}
