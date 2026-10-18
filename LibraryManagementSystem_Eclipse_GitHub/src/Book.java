public class Book {
    private final int id;
    private final String title;
    private final String author;
    private final String category;
    private boolean available;
    private String borrower;

    public Book(int id, String title, String author, String category) {
        this(id, title, author, category, true, "");
    }

    public Book(int id, String title, String author, String category, boolean available, String borrower) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = available;
        this.borrower = borrower;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return available; }
    public String getBorrower() { return borrower; }

    public void issueTo(String borrowerName) {
        if (available) {
            available = false;
            borrower = borrowerName;
        }
    }

    public void returnBook() {
        available = true;
        borrower = "";
    }

    @Override
    public String toString() {
        return String.format("%-8d %-28s %-22s %-16s %-12s", id, title, author, category,
                available ? "Available" : "Issued");
    }

    public String toFileLine() {
        return id + "|" + title + "|" + author + "|" + category + "|" + available + "|" + borrower;
    }

    public static Book fromFileLine(String line) {
        String[] parts = line.split("\\|", -1);
        if (parts.length != 6) return null;
        try {
            return new Book(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3],
                    Boolean.parseBoolean(parts[4]), parts[5]);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
