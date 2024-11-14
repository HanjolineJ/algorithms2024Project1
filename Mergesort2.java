public class Mergesort2 {
    static int[] S;

    public static void main(String[] args) {
        S = new int[]{16, 14, 5, 7, 1, 8, 12, 10};
        int n = S.length;
        
        mergesort2(0, n - 1);
        
        System.out.println("Sorted array: ");
        for (int num : S) {
            System.out.print(num + " ");
        }
    }

    public static void mergesort2(int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            
            // Recursively sort the two halves
            mergesort2(low, mid);
            mergesort2(mid + 1, high);
            
            // Merge the sorted halves
            merge2(low, mid, high);
        }
    }

    public static void merge2(int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;
        
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        
        // Copy data to temporary arrays
        for (int i = 0; i < n1; ++i)
            leftArray[i] = S[low + i];
        for (int j = 0; j < n2; ++j)
            rightArray[j] = S[mid + 1 + j];
        
        // Merge the temporary arrays
        int i = 0, j = 0;
        int k = low;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                S[k] = leftArray[i];
                i++;
            } else {
                S[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements of leftArray if any
        while (i < n1) {
            S[k] = leftArray[i];
            i++;
            k++;
        }
        
        // Copy remaining elements of rightArray if any
        while (j < n2) {
            S[k] = rightArray[j];
            j++;
            k++;
        }
    }
}