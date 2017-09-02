import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {
	private static final ThreadLocal<Connection> tt = new ThreadLocal<Connection>();

	public static Connection getConnection() throws Exception {
		Connection conn = tt.get();

		if (conn == null) {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context
					.lookup("java:comp/env/jdbc/oracle");
			conn = ds.getConnection();
			tt.set(conn);
			System.out.println(conn);
		}

		return conn;
	}

	public static void close(ResultSet rs, Statement stmt, Connection conn)
			throws Exception {
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (Exception e) {
			}
		if (conn != null)
			try {
				conn.close();
				tt.remove();
			} catch (Exception e) {
			}
	}
}