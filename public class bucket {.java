public class bucket {
    public static void main(String[] args) {
    my_list = LinkedList()

// Get user input
while True:
    data = input("Enter data (or type 'quit' to stop): ")
    if data.lower() == 'quit':
        break
    my_list.append(data)

// Print the list in both directions
print("Data from front to back:")
my_list.print_list()
print("Data from back to front:")
my_list.reverse_print()
    
}
}