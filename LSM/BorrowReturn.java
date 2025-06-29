import java.sql.*;
import java.util.Scanner;

public class BorrowReturn {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "Virus@12";

    public static void borrowBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book ID to borrow: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter borrower name: ");
        String borrowerName = scanner.nextLine();

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                // Check if book is available
                PreparedStatement checkStmt = connection.prepareStatement(
                        "SELECT available FROM Books WHERE book_id = ?");
                checkStmt.setInt(1, bookId);
                ResultSet resultSet = checkStmt.executeQuery();

                if (resultSet.next() && resultSet.getBoolean("available")) {
                    // Mark book as unavailable
                    PreparedStatement updateStmt = connection.prepareStatement(
                            "UPDATE Books SET available = FALSE WHERE book_id = ?");
                    updateStmt.setInt(1, bookId);
                    updateStmt.executeUpdate();

                    // Add record to Borrowers table
                    PreparedStatement insertStmt = connection.prepareStatement(
                            "INSERT INTO Borrowers (book_id, borrower_name, borrow_date) VALUES (?, ?, CURDATE())");
                    insertStmt.setInt(1, bookId);
                    insertStmt.setString(2, borrowerName);
                    insertStmt.executeUpdate();

                    System.out.println("Book borrowed successfully!");
                } else {
                    System.out.println("Book is not available for borrowing.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        }
    }

    public static void returnBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book ID to return: ");
        int bookId = scanner.nextInt();

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                // Mark book as available
                PreparedStatement updateStmt = connection.prepareStatement(
                        "UPDATE Books SET available = TRUE WHERE book_id = ?");
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();

                // Update return date in Borrowers table
                PreparedStatement returnStmt = connection.prepareStatement(
                        "UPDATE Borrowers SET return_date = CURDATE() WHERE book_id = ?");
                returnStmt.setInt(1, bookId);
                returnStmt.executeUpdate();

                System.out.println("Book returned successfully!");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        }
    }
}