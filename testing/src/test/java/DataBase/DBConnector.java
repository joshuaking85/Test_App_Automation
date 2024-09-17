package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {

    private String URL;
    private String USER;
    private String PASSWORD;

    public DBConnector(String URL, String USER, String PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }

    public List<Record> connectAndQuery() {
        List<Record> records = new ArrayList<>();

        try {
            // Load PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish the connection to the database
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement statement = connection.createStatement()) {

                // Execute a query
                String query = "SELECT * FROM \"Record\"";
                ResultSet resultSet = statement.executeQuery(query);

                // Process the result set
                while (resultSet.next()) {
                    // Retrieve and store data
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String dob = resultSet.getString("dob");
                    String gender = resultSet.getString("gender");

                    records.add(new Record(id, firstName, lastName, gender, dob));
                }

            } catch (SQLException e) {
                System.err.println("SQL Exception occurred.");
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        }

        return records;
    }

}

