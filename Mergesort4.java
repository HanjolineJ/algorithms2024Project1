//class Node {
//    int key;
//    Node next;
//
//    public Node(int key) {
//        this.key = key;
//        this.next = null;
//    }
//}

// public class Mergesort4 {
//     static Node head;
//     static long totalComparisons = 0; // Counter for comparisons in merge
//     static long grandTotalComparisons = 0; // Counter for grand total comparisons

//     public static void main(String[] args) {
//         System.out.println("Merge Sort Algorithm Used: MergeSort4\n");

//         int[] array = {16, 14, 8, 134, 444, 123, 561, 10000, 863, 22};
//         head = arrayToList(array);

//         head = mergesort4(head);

//         System.out.println("Sorted linked list: ");
//         printList(head);
//         System.out.println("\n");

//         System.out.println("Mergesort Analysis:");
//         System.out.println("    Algorithm: Top-Down Recursive Merge Sort for Linked Lists");
//         System.out.println("    Total Comparisons Made: " + totalComparisons);
//         System.out.println("    Time Complexity: O(n log n)");
//         System.out.println("    Space Complexity: O(log n) due to recursion stack\n");

//         // Performance testing
//         int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
//         long grandTotalTimeMillis = 0; // Total time in milliseconds
//         long grandTotalMemoryBytes = 0; // Total memory in bytes

//         for (int size : sizes) {
//             int[][] testArrays = {
//                 ArrayGenerator.generateRandomArray(size),
//                 ArrayGenerator.generateSortedArray(size),
//                 ArrayGenerator.generateReversedArray(size),
//                 ArrayGenerator.generateNearlySortedArray(size, 0.05)
//             };

//             String[] arrayTypes = {"Random", "Sorted", "Reversed", "Nearly Sorted"};
//             for (int t = 0; t < testArrays.length; t++) {
//                 int[] generatedArray = testArrays[t];
//                 long totalTime = 0;
//                 long totalMemoryUsed = 0;

//                 // Reset the local counter for each test
//                 totalComparisons = 0;

//                 for (int i = 0; i < 100; i++) {
//                     Node list = arrayToList(generatedArray.clone());

//                     // Measure memory usage before sorting
//                     Runtime runtime = Runtime.getRuntime();
//                     runtime.gc(); // Request garbage collection
//                     long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

//                     long startTime = System.currentTimeMillis();
//                     list = mergesort4(list);
//                     long endTime = System.currentTimeMillis();

//                     // Measure memory usage after sorting
//                     long afterMemory = runtime.totalMemory() - runtime.freeMemory();
//                     long memoryUsed = afterMemory - beforeMemory;

//                     totalMemoryUsed += memoryUsed;
//                     totalTime += (endTime - startTime);
//                 }

//                 long averageTimeMillis = totalTime / 100;
//                 double averageTimeSeconds = averageTimeMillis / 1000.0;
//                 long averageMemoryUsed = totalMemoryUsed / 100;

//                 // Add to grand totals
//                 grandTotalTimeMillis += totalTime;
//                 grandTotalMemoryBytes += totalMemoryUsed;
//                 grandTotalComparisons += totalComparisons;

//                 long minutes = averageTimeMillis / (60 * 1000);
//                 double seconds = (averageTimeMillis % (60 * 1000)) / 1000.0;

//                 System.out.println("Average execution time for " + arrayTypes[t] + " array of size " + size + ":");
//                 System.out.println("    " + averageTimeMillis + " milliseconds");
//                 System.out.println("    " + averageTimeSeconds + " seconds");
//                 System.out.println("    " + minutes + " minutes and " + seconds + " seconds");
//                 System.out.println("    " + averageMemoryUsed + " bytes of memory used");
//                 System.out.println("    Total Comparisons: " + totalComparisons);
//             }
//         }

//         double grandTotalTimeSecondsFinal = grandTotalTimeMillis / 1000.0;
//         long grandTotalMinutes = grandTotalTimeMillis / (60 * 1000);
//         double grandTotalSeconds = (grandTotalTimeMillis % (60 * 1000)) / 1000.0;

//         // Display grand totals
//         System.out.println("\nGrand Total Execution Time for All Arrays and Sizes:");
//         System.out.println("    " + grandTotalTimeMillis + " milliseconds");
//         System.out.println("    " + grandTotalTimeSecondsFinal + " seconds");
//         System.out.println("    " + grandTotalMinutes + " minutes and " + grandTotalSeconds + " seconds");
//         System.out.println("    " + grandTotalMemoryBytes + " bytes of total memory used");
//         System.out.println("    " + grandTotalComparisons + " total comparisons made");
//         System.out.println("Merge Sort Algorithm Used: MergeSort4\n");
//     }

//     // Converts an array to a linked list
//     public static Node arrayToList(int[] array) {
//         if (array.length == 0) {
//             return null;
//         }
//         Node head = new Node(array[0]);
//         Node current = head;
//         for (int i = 1; i < array.length; i++) {
//             current.next = new Node(array[i]);
//             current = current.next;
//         }
//         return head;
//     }

//     // Prints the linked list
//     public static void printList(Node head) {
//         Node current = head;
//         while (current != null) {
//             System.out.print(current.key + " ");
//             current = current.next;
//         }
//     }

//     // Mergesort4 to sort a linked list
//     public static Node mergesort4(Node head) {
//         if (head == null || head.next == null) {
//             return head;
//         }

//         // Split the list into two halves
//         Node middle = getMiddle(head);
//         Node nextToMiddle = middle.next;
//         middle.next = null;

//         // Recursively sort the sublists
//         Node left = mergesort4(head);
//         Node right = mergesort4(nextToMiddle);

//         // Merge the sorted sublists
//         return merge4(left, right);
//     }

//     // Merge two sorted linked lists
//     public static Node merge4(Node left, Node right) {
//         Node dummy = new Node(0);
//         Node current = dummy;

//         // Merge the two subarrays iteratively
//         while (left != null && right != null) {
//             totalComparisons++; // Count each comparison
//             if (left.key <= right.key) {
//                 current.next = left;
//                 left = left.next;
//             } else {
//                 current.next = right;
//                 right = right.next;
//             }
//             current = current.next;
//         }

//         // Attach the remaining nodes, if any
//         if (left != null) {
//             current.next = left;
//         } else {
//             current.next = right;
//         }

//         return dummy.next;
//     }

//     // Utility function to find the middle of the linked list
//     public static Node getMiddle(Node head) {
//         if (head == null) {
//             return head;
//         }

//         Node slow = head, fast = head;

//         // Move `fast` two nodes and `slow` one node at a time
//         while (fast.next != null && fast.next.next != null) {
//             slow = slow.next;
//             fast = fast.next.next;
//         }

//         return slow;
//     }
// }

// import java.util.List;
// import java.util.ArrayList;

// public class Mergesort4 {
//     static long totalComparisons = 0; // Counter for comparisons in merge
//     static long grandTotalComparisons = 0; // Counter for grand total comparisons
//     static List<String> detailedResults = new ArrayList<>(); // Store results for summary

//     public static void main(String[] args) {
//         System.out.println("Merge Sort Algorithm Used: MergeSort4 (Linked List Version)\n");

//         // Test with a small array
//         int[] array = {16, 14, 8, 134, 444, 123, 561, 10000, 863, 22};
//         Node head = arrayToList(array);

//         System.out.println("Initial linked list:");
//         printList(head);
//         System.out.println();

//         head = mergesort4(head);

//         System.out.println("\nSorted linked list:");
//         printList(head);
//         System.out.println("\n");

//         System.out.println("Mergesort Analysis:");
//         System.out.println("    Algorithm: Top-Down Recursive Merge Sort for Linked Lists");
//         System.out.println("    Total Comparisons Made: " + totalComparisons);
//         System.out.println("    Time Complexity: O(n log n)");
//         System.out.println("    Space Complexity: O(log n) due to recursion stack\n");

//         // Performance testing
//         int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
//         long grandTotalTimeMillis = 0;
//         long grandTotalMemoryBytes = 0;
//         String[] arrayTypes = {"Random", "Sorted", "Reversed", "Nearly Sorted"};

//         for (int size : sizes) {
//             int[][] testArrays = {
//                 ArrayGenerator.generateRandomArray(size),
//                 ArrayGenerator.generateSortedArray(size),
//                 ArrayGenerator.generateReversedArray(size),
//                 ArrayGenerator.generateNearlySortedArray(size, 0.05)
//             };

//             for (int t = 0; t < testArrays.length; t++) {
//                 int[] generatedArray = testArrays[t];
//                 long totalTime = 0;
//                 long totalMemoryUsed = 0;

//                 totalComparisons = 0; // Reset comparisons for this test

//                 System.out.println("\nInitial " + arrayTypes[t] + " Array (size " + size + "):");
//                 printList(arrayToList(generatedArray));

//                 for (int i = 0; i < 100; i++) {
//                     Node list = arrayToList(generatedArray.clone());

//                     Runtime runtime = Runtime.getRuntime();
//                     runtime.gc();
//                     long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

//                     long startTime = System.currentTimeMillis();
//                     list = mergesort4(list);
//                     long endTime = System.currentTimeMillis();

//                     long afterMemory = runtime.totalMemory() - runtime.freeMemory();
//                     long memoryUsed = Math.max(0, afterMemory - beforeMemory); // Ensure non-negative values

//                     totalMemoryUsed += memoryUsed;
//                     totalTime += (endTime - startTime);
//                 }

//                 long averageTimeMillis = totalTime / 100;
//                 double averageTimeSeconds = averageTimeMillis / 1000.0;
//                 long averageMemoryUsed = totalMemoryUsed / 100;

//                 grandTotalTimeMillis += totalTime;
//                 grandTotalMemoryBytes += totalMemoryUsed;
//                 grandTotalComparisons += totalComparisons;

//                 System.out.println("\nSorted " + arrayTypes[t] + " Array (size " + size + "):");
//                 printList(arrayToList(generatedArray));

//                 System.out.println("\nAverage execution time for " + arrayTypes[t] + " Array of size " + size + ":");
//                 System.out.println("    " + averageTimeMillis + " milliseconds");
//                 System.out.println("    " + averageTimeSeconds + " seconds");
//                 System.out.println("    Average Memory Used: " + averageMemoryUsed + " bytes");
//                 System.out.println("    Total Comparisons: " + totalComparisons);

//                 // Add to detailed summary
//                 detailedResults.add("Array Type: " + arrayTypes[t] + ", Size: " + size +
//                         ", Average Time: " + averageTimeMillis + " ms, Average Memory Used: " +
//                         averageMemoryUsed + " bytes");
//             }
//         }

//         // Grand Total Summary
//         double grandTotalTimeSecondsFinal = grandTotalTimeMillis / 1000.0;
//         long grandTotalMinutes = grandTotalTimeMillis / (60 * 1000);
//         double grandTotalSeconds = (grandTotalTimeMillis % (60 * 1000)) / 1000.0;

//         System.out.println("\nGrand Total Execution Time for All Arrays and Sizes:");
//         System.out.println("    " + grandTotalTimeMillis + " milliseconds");
//         System.out.println("    " + grandTotalTimeSecondsFinal + " seconds");
//         System.out.println("    " + grandTotalMinutes + " minutes and " + grandTotalSeconds + " seconds");
//         System.out.println("    Grand Total Memory Used: " + grandTotalMemoryBytes + " bytes");
//         System.out.println("    Grand Total Comparisons: " + grandTotalComparisons);

//         // Print detailed results summary
//         System.out.println("\nDetailed Results Summary:");
//         for (String result : detailedResults) {
//             System.out.println(result);
//         }
//     }

//     // Convert array to linked list
//     public static Node arrayToList(int[] array) {
//         if (array.length == 0) return null;
//         Node head = new Node(array[0]);
//         Node current = head;
//         for (int i = 1; i < array.length; i++) {
//             current.next = new Node(array[i]);
//             current = current.next;
//         }
//         return head;
//     }

//     // Print linked list
//     public static void printList(Node head) {
//         Node current = head;
//         int count = 0;
//         while (current != null && count < 25) {
//             System.out.print(current.key + " ");
//             current = current.next;
//             count++;
//         }
//         if (current != null) System.out.print(25 + "... (output limited to 25 elements)");
//         System.out.println();
//     }

//     // Top-Down Merge Sort for Linked List
//     public static Node mergesort4(Node head) {
//         if (head == null || head.next == null) return head;

//         Node middle = getMiddle(head);
//         Node nextToMiddle = middle.next;
//         middle.next = null;

//         Node left = mergesort4(head);
//         Node right = mergesort4(nextToMiddle);

//         return merge4(left, right);
//     }

//     // Merge two sorted linked lists
//     public static Node merge4(Node left, Node right) {
//         Node dummy = new Node(0);
//         Node current = dummy;

//         while (left != null && right != null) {
//             totalComparisons++;
//             if (left.key <= right.key) {
//                 current.next = left;
//                 left = left.next;
//             } else {
//                 current.next = right;
//                 right = right.next;
//             }
//             current = current.next;
//         }

//         if (left != null) current.next = left;
//         else current.next = right;

//         return dummy.next;
//     }

//     // Find the middle of the linked list
//     public static Node getMiddle(Node head) {
//         if (head == null) return head;

//         Node slow = head, fast = head;
//         while (fast.next != null && fast.next.next != null) {
//             slow = slow.next;
//             fast = fast.next.next;
//         }
//         return slow;
//     }
// }

// // Node class definition
// class Node {
//     int key;
//     Node next;

//     Node(int key) {
//         this.key = key;
//         this.next = null;
//     }
// }

import java.util.List;
import java.util.ArrayList;

public class Mergesort4 {
    static long totalComparisons = 0; // Counter for comparisons in merge
    static long grandTotalComparisons = 0; // Grand total comparisons
    static long grandTotalMemoryBytes = 0; // Grand total memory usage
    static List<String> detailedResults = new ArrayList<>(); // Store results for summary

    public static void main(String[] args) {
        System.out.println("Merge Sort Algorithm Used: MergeSort4 (Linked List Version)\n");

        // Test with a small array for demonstration
        int[] array = {16, 14, 55, 134, 444, 123, 561, 10000, 863, 22};
        Node head = arrayToList(array);

        System.out.println("Initial linked list:");
        printList(head);
        System.out.println();

        head = mergesort4(head);

        System.out.println("\nSorted linked list:");
        printList(head);
        System.out.println("\n");

        System.out.println("Mergesort Analysis:");
        System.out.println("    Algorithm: Top-Down Recursive Merge Sort for Linked Lists");
        System.out.println("    Total Comparisons Made: " + totalComparisons);
        System.out.println("    Time Complexity: O(n log n)");
        System.out.println("    Space Complexity: O(log n) due to recursion stack\n");

        // Performance testing
        int[] sizes = {0, 1, 2, 4, 8, 10, 100, 1000, 10000, 100000, 1000000};
        String[] arrayTypes = {"Random", "Sorted", "Reversed", "Nearly Sorted"};
        long grandTotalTimeMillis = 0;

        for (int size : sizes) {
            for (String arrayType : arrayTypes) {
                int[] generatedArray = generateArray(arrayType, size);
                long totalTime = 0;
                long totalMemoryUsed = 0;

                // Reset counters
                totalComparisons = 0;

                for (int i = 0; i < 100; i++) {
                    Node list = arrayToList(generatedArray.clone());

                    // Memory profiling
                    Runtime runtime = Runtime.getRuntime();
                    runtime.gc();
                    long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

                    // Execution time profiling
                    long startTime = System.currentTimeMillis();
                    list = mergesort4(list);
                    long endTime = System.currentTimeMillis();

                    long afterMemory = runtime.totalMemory() - runtime.freeMemory();
                    long memoryUsed = Math.max(0, afterMemory - beforeMemory);

                    totalMemoryUsed += memoryUsed;
                    totalTime += (endTime - startTime);
                }

                long averageTimeMillis = totalTime / 100;
                double averageTimeSeconds = averageTimeMillis / 1000.0;
                long averageMemoryUsed = totalMemoryUsed / 100;

                grandTotalTimeMillis += totalTime;
                grandTotalMemoryBytes += totalMemoryUsed;
                grandTotalComparisons += totalComparisons;

                // Output results
                System.out.println("\nAverage execution time for " + arrayType + " array of size " + size + ":");
                System.out.println("    " + averageTimeMillis + " milliseconds");
                System.out.println("    " + averageTimeSeconds + " seconds");
                System.out.println("    Average Memory Used: " + averageMemoryUsed + " bytes");
                System.out.println("    Total Comparisons: " + totalComparisons);

                detailedResults.add("Array Type: " + arrayType + ", Size: " + size +
                        ", Average Time: " + averageTimeMillis + " ms, Average Memory Used: " +
                        averageMemoryUsed + " bytes");
            }
        }

        // Grand Total Summary
        double grandTotalTimeSecondsFinal = grandTotalTimeMillis / 1000.0;
        long grandTotalMinutes = grandTotalTimeMillis / (60 * 1000);
        double grandTotalSeconds = (grandTotalTimeMillis % (60 * 1000)) / 1000.0;

        System.out.println("\nGrand Total Execution Time for All Arrays and Sizes:");
        System.out.println("    " + grandTotalTimeMillis + " milliseconds");
        System.out.println("    " + grandTotalTimeSecondsFinal + " seconds");
        System.out.println("    " + grandTotalMinutes + " minutes and " + grandTotalSeconds + " seconds");
        System.out.println("    Grand Total Memory Used: " + grandTotalMemoryBytes + " bytes");
        System.out.println("    Grand Total Comparisons: " + grandTotalComparisons);

        // Detailed Results Summary
        System.out.println("\nDetailed Results Summary:");
        for (String result : detailedResults) {
            System.out.println(result);
        }
    }

    // Convert array to linked list
    public static Node arrayToList(int[] array) {
        if (array.length == 0) return null;
        Node head = new Node(array[0]);
        Node current = head;
        for (int i = 1; i < array.length; i++) {
            current.next = new Node(array[i]);
            current = current.next;
        }
        return head;
    }

    // Print linked list
    public static void printList(Node head) {
        Node current = head;
        int count = 0;
        while (current != null && count < 25) {
            System.out.print(current.key + " ");
            current = current.next;
            count++;
        }
        if (current != null) System.out.print("... (output limited to 25 elements)");
        System.out.println();
    }

    // Top-Down Merge Sort for Linked List
    public static Node mergesort4(Node head) {
        if (head == null || head.next == null) return head;

        Node middle = getMiddle(head);
        Node nextToMiddle = middle.next;
        middle.next = null;

        Node left = mergesort4(head);
        Node right = mergesort4(nextToMiddle);

        return merge4(left, right);
    }

    // Merge two sorted linked lists
    public static Node merge4(Node left, Node right) {
        Node dummy = new Node(0);
        Node current = dummy;

        while (left != null && right != null) {
            totalComparisons++;
            if (left.key <= right.key) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }

        if (left != null) current.next = left;
        else current.next = right;

        return dummy.next;
    }

    // Find the middle of the linked list
    public static Node getMiddle(Node head) {
        if (head == null) return head;

        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Generate arrays for testing
    public static int[] generateArray(String type, int size) {
        int[] array = new int[size];
        switch (type) {
            case "Random":
                for (int i = 0; i < size; i++) array[i] = (int) (Math.random() * 10000);
                break;
            case "Sorted":
                for (int i = 0; i < size; i++) array[i] = i;
                break;
            case "Reversed":
                for (int i = 0; i < size; i++) array[i] = size - i;
                break;
            case "Nearly Sorted":
                for (int i = 0; i < size; i++) array[i] = i;
                int swaps = (int) (size * 0.05);
                for (int i = 0; i < swaps; i++) {
                    int index1 = (int) (Math.random() * size);
                    int index2 = (int) (Math.random() * size);
                    int temp = array[index1];
                    array[index1] = array[index2];
                    array[index2] = temp;
                }
                break;
        }
        return array;
    }
}

// Node class definition
class Node {
    int key;
    Node next;

    Node(int key) {
        this.key = key;
        this.next = null;
    }
}
