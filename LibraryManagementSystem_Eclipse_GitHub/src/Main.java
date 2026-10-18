import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();

    public static void main(String[] args) {
        library.loadFromFile();
        System.out.println("==================================");
        System.out.println("     LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==================================");
        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1: addBook(); break;
                case 2: viewBooks(); break;
                case 3: searchBook(); break;
                case 4: issueBook(); break;
                case 5: returnBook(); break;
                case 6: showStatistics(); break;
                case 0:
                    library.saveToFile();
                    System.out.println("Records saved. Goodbye!");
                    running = false;
                    break;
                default: System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n------------- MENU --------------");
        System.out.println("1. Add a book\n2. View all books\n3. Search book by ID");
        System.out.println("4. Issue a book\n5. Return a book\n6. Library statistics\n0. Save and exit");
        System.out.println("---------------------------------");
    }

    private static void addBook() {
        int id = readInt("Book ID: ");
        if (library.findBook(id) != null) {
            System.out.println("A book with this ID already exists.");
            return;
        }
        String title = readNonEmpty("Title: ");
        String author = readNonEmpty("Author: ");
        String category = readNonEmpty("Category: ");
        library.addBook(new Book(id, title, author, category));
        library.saveToFile();
        System.out.println("Book added successfully.");
    }

    private static void viewBooks() {
        List<Book> books = library.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found. Add a book first.");
            return;
        }
        System.out.printf("%-8s %-28s %-22s %-16s %-12s%n", "ID", "TITLE", "AUTHOR", "CATEGORY", "STATUS");
        System.out.println("----------------------------------------------------------------------------------------");
        for (Book book : books) System.out.println(book);
    }

    private static void searchBook() {
        int id = readInt("Enter book ID: ");
        Book book = library.findBook(id);
        if (book == null) System.out.println("No book found with ID " + id + ".");
        else System.out.println(book);
    }

    private static void issueBook() {
        int id = readInt("Enter the ID of the book to issue: ");
        Book book = library.findBook(id);
        if (book == null) System.out.println("Book not found.");
        else if (!book.isAvailable()) System.out.println("This book is already issued.");
        else {
            String borrower = readNonEmpty("Borrower's name: ");
            book.issueTo(borrower);
            library.saveToFile();
            System.out.println("Book issued to " + borrower + ".");
        }
    }

    private static void returnBook() {
        int id = readInt("Enter the ID of the book to return: ");
        Book book = library.findBook(id);
        if (book == null) System.out.println("Book not found.");
        else if (book.isAvailable()) System.out.println("This book is already available.");
        else {
            String borrower = book.getBorrower();
            book.returnBook();
            library.saveToFile();
            System.out.println("Book returned successfully. Previous borrower: " + borrower);
        }
    }

    private static void showStatistics() {
        int available = 0, issued = 0;
        for (Book book : library.getAllBooks()) {
            if (book.isAvailable()) available++; else issued++;
        }
        System.out.println("Total books: " + (available + issued));
        System.out.println("Available books: " + available);
        System.out.println("Issued books: " + issued);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Please enter a valid whole number."); }
        }
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty() && !value.contains("|")) return value;
            System.out.println("Value cannot be empty or contain the | character.");
        }
    }
}
