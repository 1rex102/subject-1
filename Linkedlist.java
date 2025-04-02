
class Item {
    String data;
    Item next;
    Item previous;

    public Item(String data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }
}

public class Linkedlist {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Item head = null;
            Item tail = null;

            System.out.println("Enter data items (enter 'quit' to stop):");

            while (true) {
                System.out.print("Enter data: ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("quit")) {
                    break;
                }

                Item newItem = new Item(input);

                if (head == null) {
                    head = newItem;
                    tail = newItem;
                } else {
                    tail.next = newItem;
                    newItem.previous = tail;
                    tail = newItem;
                }
            }

            System.out.println("\nData from front to back:");
            Item current = head;
            while (current != null) {
                System.out.println(current.data);
                current = current.next;
            }

            System.out.println("\nData from back to front:");
            current = tail;
            while (current != null) {
                System.out.println(current.data);
                current = current.previous;
            }
        }
    }
}

