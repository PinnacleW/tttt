import java.sql.*;
import java.util.Scanner;


public class JdbcDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String name = sc.nextLine();
        System.out.print("请输入密码：");
        String password = sc.nextLine();

        login(name, password);
    }

    public static void login(String name, String password){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtil.getConnection();
            statement = connection.createStatement();

            String sql = "select * from user where name = '" + name + "' and password = '" + password + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                System.out.println("验证通过，欢迎您：" + name);
            }else {
                System.out.println("验证失败，请输入正确的用户名密码！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(resultSet, statement, connection);
        }
    }
// select * from user where name = 'newboy' and password = 'a' or '1'='1'
}
