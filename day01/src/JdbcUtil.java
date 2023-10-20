import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class JdbcUtil {

    /*
        1）用户名、密码、URL，驱动类配置
        2）数据库连接getConnection
        3）关闭所有打开的自愿
    */

    private static String url;
    private static String user;
    private static String password;

    static{
        Properties properties = new Properties();
        ClassLoader classLoader = JdbcUtil.class.getClassLoader();
        URL resource = classLoader.getResource("jdbc.properties");
        String path = Objects.requireNonNull(resource).getPath();
        System.out.println(path);
        try {
            properties.load(new FileReader(path));
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            String driver = properties.getProperty("driver");

            Class.forName(driver);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // 释放连接
    public static void close(ResultSet resultSet, Statement statement, Connection connection){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        JdbcUtil.close(statement, connection);
    }

    public static void close(Statement statement, Connection connection){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
