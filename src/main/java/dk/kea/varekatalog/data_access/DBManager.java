package dk.kea.varekatalog.data_access;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {

    private static String user;
    private static String password;
    private static String url;
    private static Connection connection = null;

    public static Connection getConnection() {

        if (connection != null)
            return connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (InputStream input = new ClassPathResource("application.properties").getInputStream()) {
            Properties properties = new Properties();
            properties.load(input);
            url = properties.getProperty("spring.datasource.url");
            user = properties.getProperty("spring.datasource.username");
            password = properties.getProperty("spring.datasource.password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
