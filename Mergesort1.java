public class Mergesort1 {
    public static void main(String[] args) {
        int[] array = {16, 14, 5, 7, 1, 8, 12, 10};
        mergeSort(array, 0, array.length - 1);
        
        System.out.println("Sorted array: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            
            // Recursively sort the two halves
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            
            // Merge the sorted halves
            merge(array, left, middle, right);
        }
    }

    public static void merge(int[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;
        
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        
        // Copy data to temporary arrays
        for (int i = 0; i < n1; ++i)
            leftArray[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            rightArray[j] = array[middle + 1 + j];
        
        // Merge the temporary arrays
        int i = 0, j = 0;
        int k = left;
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