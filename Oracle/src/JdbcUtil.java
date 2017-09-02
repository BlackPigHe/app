import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
    private static Connection getConn() {
        String driver = "oracle.jdbc.driver.OracleDriver";
        //String url = "jdbc:oracle:thin:@127.0.0.1:1521:pdbmyoracle";// 设置连接字符串
        String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
        String username = "scott";// 用户名
        String password = "Orcal12root";// 密码
        Connection conn = null; // 创建数据库连接对象
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    private static void query() {
        Connection conn = getConn();
        String sql = "select * from emp";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            // 建立一个结果集,用来保存查询出来的结果

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String username = rs.getString("ENAME");
                System.out.println(username);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
        
        query();
    }

}