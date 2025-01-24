 import java.util.ArrayList;
 import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    // Constructor
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author +
               ", Available: " + (isAvailable ? "Yes" : "No");
    }
}

public class LibraryManagementSystem {
    private static ArrayList<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add New Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search for a Book");
            System.out.println("4. Borrow a Book");
            System.out.println("5. Return a Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Book Author: ");
        String author = scanner.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }

        System.out.println("\nList of Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void searchBook() {
        System.out.print("Enter Book ID or Title to search: ");
        scanner.nextLine(); // Consume newline
        String searchQuery = scanner.nextLine();

        boolean found = false;
        for (Book book : books) {
            if (String.valueOf(book.getId()).equals(searchQuery) || book.getTitle().equalsIgnoreCase(searchQuery)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    private static void borrowBook() {
        System.out.print("Enter Book ID to borrow: ");
        int id = scanner.nextInt();

        for (Book book : books) {
            if (book.getId() == id) {
                if (book.isAvailable()) {
                    book.borrowBook();
                    System.out.println("You borrowed the book: " + book.getTitle());
                } else {
                    System.out.println("Sorry, the book is already borrowed.");
                }
                return;
            }
        }

        System.out.println("Book not found.");
    }

    private static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = scanner.nextInt();

        for (Book book : books) {
            if (book.getId() == id) {
                if (!book.isAvailable()) {
                    book.returnBook();
                    System.out.println("Thank you for returning the book: " + book.getTitle());
                } else {
                    System.out.println("This book was not borrowed.");
                }
                return;
            }
        }

        System.out.println("Book not found.");
    }

}
    

