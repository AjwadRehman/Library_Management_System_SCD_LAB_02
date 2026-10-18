import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Library {
    private final List<Book> books = new ArrayList<>();
    private final Path dataFile = Paths.get("data", "books.txt");

    public void addBook(Book book) {
        if (findBook(book.getId()) == null) {
            books.add(book);
            books.sort(Comparator.comparingInt(Book::getId));
        }
    }

    public List<Book> getAllBooks() { return new ArrayList<>(books); }

    public Book findBook(int id) {
        for (Book book : books) if (book.getId() == id) return book;
        return null;
    }

    public void saveToFile() {
        try {
            Path parent = dataFile.getParent();
            if (parent != null) Files.createDirectories(parent);
            try (BufferedWriter writer = Files.newBufferedWriter(dataFile, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)) {
                for (Book book : books) {
                    writer.write(book.toFileLine());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Warning: Could not save library data: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        if (!Files.exists(dataFile)) return;
        try (BufferedReader reader = Files.newBufferedReader(dataFile, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Book book = Book.fromFileLine(line);
                if (book != null && findBook(book.getId()) == null) books.add(book);
            }
            books.sort(Comparator.comparingInt(Book::getId));
        } catch (IOException e) {
            System.out.println("Warning: Could not load library data: " + e.getMessage());
        }
    }
}
