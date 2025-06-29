import java.sql.*;
import javax.swing.JOptionPane;

public class createdb {
    public static void main(String[] args) throws Exception {
        try {
            String url = "jdbc:mysql://localhost:3306/";
            String databaseName = "library";
            String userName = "root";
            String password = "Virus@12";

            Connection connection = DriverManager.getConnection(url, userName, password);

            String sql = "CREATE DATABASE " + databaseName;

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();

            JOptionPane.showMessageDialog(null, "Database Created Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}