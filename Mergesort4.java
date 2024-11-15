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
        int[] array = {16, 14, 5, 7, 1, 8, 12, 10, 3 , 19, 56, 134, 444, 1, 12, 123, 561, 10000, 863};
        head = arrayToList(array);

        head = mergesort4(head);

        System.out.println("Sorted linked list: ");
        printList(head);
        System.out.println("\n");

        // Additional code for performance testing
        int[] sizes = {10, 100, 1000, 10000, 100000};
        long grandTotalTimeMillis = 0; // Initialize variable to keep track of the grand total time in milliseconds

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
                    long startTime = System.currentTimeMillis();
                    list = mergesort4(list);
                    long endTime = System.currentTimeMillis();
                    totalTime += (endTime - startTime);
                }

                long averageTimeMillis = totalTime / 100;
                double averageTimeSeconds = averageTimeMillis / 1000.0; // Convert milliseconds to seconds

                // Add to the grand total counter in milliseconds
                grandTotalTimeMillis += totalTime;

                long minutes = averageTimeMillis / (60 * 1000);
                double seconds = (averageTimeMillis % (60 * 1000)) / 1000.0;

                System.out.println("Average execution time for " + arrayTypes[t] + " array of size " + size + ":");
                System.out.println("    " + averageTimeMillis + " milliseconds");
                System.out.println("    " + averageTimeSeconds + " seconds");
                System.out.println("    " + minutes + " minutes and " + seconds + " seconds");
            }
        }

        // Display grand total time across all sorts
        double grandTotalTimeSecondsFinal = grandTotalTimeMillis / 1000.0; // Convert milliseconds to seconds for grand total
        long grandTotalMinutes = grandTotalTimeMillis / (60 * 1000);
        double grandTotalSeconds = (grandTotalTimeMillis % (60 * 1000)) / 1000.0;

        System.out.println("\nGrand Total Execution Time for All Arrays and Sizes:");
        System.out.println("    " + grandTotalTimeMillis + " milliseconds");
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