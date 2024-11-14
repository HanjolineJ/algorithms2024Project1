public class Mergesort3 {
    static int[] S;
    static int[] U;

    public static void main(String[] args) {
        S = new int[]{16, 14, 5, 7, 1, 8, 12, 10};
        int n = S.length;
        U = new int[n];

        mergesort3(n);
        
        System.out.println("Sorted array: ");
        for (int num : S) {
            System.out.print(num + " ");
        }
    }

    public static void mergesort3(int n) {
        // Start with single element arrays and merge iteratively
        for (int width = 1; width < n; width = 2 * width) {
            for (int i = 0; i < n; i = i + 2 * width) {
                int low = i;
                int mid = Math.min(i + width - 1, n - 1);
                int high = Math.min(i + 2 * width - 1, n - 1);
                
                // Merge the sorted subarrays
                merge3(low, mid, high);
            }
            
            // Swap roles of U and S
            int[] temp = S;
            S = U;
            U = temp;
        }
    }

    public static void merge3(int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int k = low;
        
        // Merge the two subarrays into U
        while (i <= mid && j <= high) {
            if (S[i] <= S[j]) {
                U[k] = S[i];
                i++;
            } else {
                U[k] = S[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements of the left subarray if any
        while (i <= mid) {
            U[k] = S[i];
            i++;
            k++;
        }
        
        // Copy remaining elements of the right subarray if any
        while (j <= high) {
            U[k] = S[j];
            j++;
            k++;
        }
    }
}