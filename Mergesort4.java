public class Mergesort4 {
    static Node listFront;

    public static void main(String[] args) {
        // Creating a linked list with some sample values
        listFront = new Node(16);
        listFront.next = new Node(14);
        listFront.next.next = new Node(5);
        listFront.next.next.next = new Node(7);
        listFront.next.next.next.next = new Node(1);
        listFront.next.next.next.next.next = new Node(8);
        listFront.next.next.next.next.next.next = new Node(12);
        listFront.next.next.next.next.next.next.next = new Node(10);
        
        // Perform the merge sort
        listFront = mergesort4(listFront);
        
        // Print the sorted linked list
        printList(listFront);
    }

    public static Node mergesort4(Node head) {
        // Base case: if head is null or there is only one element in the list
        if (head == null || head.next == null) {
            return head;
        }

        // Split the list into two halves
        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;
        middle.next = null;

        // Recursively sort the two halves
        Node left = mergesort4(head);
        Node right = mergesort4(nextOfMiddle);

        // Merge the sorted halves
        return merge4(left, right);
    }

    public static Node merge4(Node list1, Node list2) {
        Node result;
        
        // Base cases
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        // Pick either list1 or list2 and recur
        if (list1.key <= list2.key) {
            result = list1;
            result.next = merge4(list1.next, list2);
        } else {
            result = list2;
            result.next = merge4(list1, list2.next);
        }
        return result;
    }

    // Utility function to get the middle of the linked list
    public static Node getMiddle(Node head) {
        if (head == null) {
            return head;
        }

        Node slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Utility function to print the linked list
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.key + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}