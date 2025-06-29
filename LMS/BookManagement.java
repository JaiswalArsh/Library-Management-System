import java.sql.*;
import java.util.Scanner;

public class BookManagement {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "Virus@12";

    public static void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(
                         "INSERT INTO Books (title, author) VALUES (?, ?)")) {

                statement.setString(1, title);
                statement.setString(2, author);
                statement.executeUpdate();
                System.out.println("Book added successfully!");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        }
    }

    public static void viewBooks() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM Books")) {

                System.out.println("Book ID\tTitle\tAuthor\tAvailable");
                while (resultSet.next()) {
                    int bookId = resultSet.getInt("book_id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    boolean available = resultSet.getBoolean("available");

                    System.out.println(bookId + "\t" + title + "\t" + author + "\t" + available);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        }
    }

    public static void searchBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search keyword (title or author): ");
        String keyword = "%" + scanner.nextLine() + "%";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(
                         "SELECT * FROM Books WHERE title LIKE ? OR author LIKE ?")) {

                statement.setString(1, keyword);
                statement.setString(2, keyword);
                ResultSet resultSet = statement.executeQuery();

                System.out.println("Search Results:");
                while (resultSet.next()) {
                    int bookId = resultSet.getInt("book_id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    boolean available = resultSet.getBoolean("available");

                    System.out.println("ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Available: " + available);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        }
    }
}