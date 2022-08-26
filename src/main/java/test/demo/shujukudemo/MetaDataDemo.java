package test.demo.shujukudemo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MetaDataDemo {
    public static void main(String[] args) throws SQLException {
        Properties pro = new Properties();
        pro.setProperty("driver", "com.mysql.jdbc.Driver");
        pro.setProperty("url", "jdbc:mysql///user");
        pro.setProperty("user", "root");
        pro.setProperty("password", "451535");
        Connection connection = DriverManager.getConnection("url", pro);
        DatabaseMetaData metaData = connection.getMetaData();
        metaData.getDriverName();
    }
}
