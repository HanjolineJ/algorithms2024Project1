//class Node {
//    int key;
//    Node next;
//
//    public Node(int key) {
//        this.key = key;
//        this.next = null;
//    }
//}

public class Mergesort4 {
    static Node head;

    public static void main(String[] args) {
        int[] array = {16, 14, 5, 7, 1, 8, 12, 10};
        head = arrayToList(array);

        head = mergesort4(head);

        System.out.println("Sorted linked list: ");
        printList(head);
        System.out.println("\n");

        // Additional code for performance testing
        int[] sizes = {10, 100, 1000, 10000, 100000};
        long grandTotalTimeNano = 0; // Initialize variable to keep track of the grand total time in nanoseconds
        double grandTotalTimeSeconds = 0.0; // Initialize variable to keep track of the grand total time in seconds

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
                    Node list = arrayToList(generatedArray.clone());
                    long startTime = System.nanoTime();
                    list = mergesort4(list);
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

    // Converts an array to a linked list
    public static Node arrayToList(int[] array) {
        if (array.length == 0) {
            return null;
        }
        Node head = new Node(array[0]);
        Node current = head;
        for (int i = 1; i < array.length; i++) {
            current.next = new Node(array[i]);
            current = current.next;
        }
        return head;
    }

    // Prints the linked list
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.key + " ");
            current = current.next;
        }
    }

    // Mergesort4 to sort a linked list
    public static Node mergesort4(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Split the list into two halves
        Node middle = getMiddle(head);
        Node nextToMiddle = middle.next;
        middle.next = null;

        // Recursively sort the sublists
        Node left = mergesort4(head);
        Node right = mergesort4(nextToMiddle);

        // Merge the sorted sublists
        return merge4(left, right);
    }

    // Merge two sorted linked lists
    public static Node merge4(Node left, Node right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        Node dummy = new Node(0);
        Node current = dummy;

        // Merge the two subarrays iteratively
        while (left != null && right != null) {
            if (left.key <= right.key) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }

        // Attach the remaining nodes, if any
        if (left != null) {
            current.next = left;
        } else {
            current.next = right;
        }

        return dummy.next;
    }

    // Utility function to find the middle of the linked list
    public static Node getMiddle(Node head) {
        if (head == null) {
            return head;
        }

        Node slow = head, fast = head;

        // Move `fast` two nodes and `slow` one node at a time
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
