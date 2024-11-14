import java.util.Random;

public class ArrayGenerator {
    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
        for (int size : sizes) {
            int[] array = generateRandomArray(size);
            System.out.println("Array of size " + size + " generated.");
            System.out.println("Contents of the array: " + java.util.Arrays.toString(array));
        }
    }

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(10000); // Generate random numbers between 0 and 9999
        }
        return array;
    }
}