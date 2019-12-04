package Models;

import com.mysql.cj.log.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Tools {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if(connection == null)
        {
            Properties props = new Properties();
            try(InputStream in = Files.newInputStream(Paths.get("src/database.properties"))){
                props.load(in);
            } catch (IOException e) {
                //TODO Сделать логирование ошибок конфигурации поключения к базе
                return null;
            }
            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");

             connection = DriverManager.getConnection(url, username, password);
        }

        return connection;
    }
}
