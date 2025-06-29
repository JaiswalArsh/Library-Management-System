import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "Virus@12"; // Replace with your MySQL password

    public static void main(String[] args) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement statement = connection.createStatement()) {

                // Create Books table
                String createBooksTable = "CREATE TABLE IF NOT EXISTS Books (" +
                        "book_id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "title VARCHAR(255) NOT NULL, " +
                        "author VARCHAR(255) NOT NULL, " +
                        "available BOOLEAN DEFAULT TRUE)";
                statement.execute(createBooksTable);

                // Create Borrowers table
                String createBorrowersTable = "CREATE TABLE IF NOT EXISTS Borrowers (" +
                        "borrow_id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "book_id INT NOT NULL, " +
                        "borrower_name VARCHAR(255) NOT NULL, " +
                        "borrow_date DATE NOT NULL, " +
                        "return_date DATE, " +
                        "FOREIGN KEY (book_id) REFERENCES Books(book_id))";
                statement.execute(createBorrowersTable);

                System.out.println("Tables created successfully!");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        }
    }
}