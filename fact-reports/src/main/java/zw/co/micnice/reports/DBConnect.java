
package zw.co.micnice.reports;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
public static Connection getConnection() {
        Connection jdbcConnection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            jdbcConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/council", "hitrac","hitrac");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jdbcConnection;
    }
}
