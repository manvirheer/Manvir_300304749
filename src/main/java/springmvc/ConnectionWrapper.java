package springmvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionWrapper {

    public Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        //Name of the database is "student",
        Connection connectionWrapper = DriverManager.getConnection("jdbc:mysql://localhost/student", "root", "");
        return connectionWrapper;
    }
}
