package test.dao;
/**
 * DB를 조회해보자~~~~~~~~~!!
 */
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Simple5JDBCDAO {

	public static void main(String[] args) {
		double param = 100.001;
		String url = "jdbc:mysql://localhost:3306/smart?characterEndoing=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		String sql = "SELECT * FROM exam";
		//String sql = "SELECT * FROM exam WHERE intTest = ? ";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
//			1. 드라이버 로드 (Class.forName())
			Class.forName("com.mysql.cj.jdbc.Driver");
//			2. DB연결(DriverManager.getConnection())
			conn = DriverManager.getConnection(url, user, password);
//			3. SQL문작성(Statement, PrepareStatement)
			stmt = conn.prepareStatement(sql);
			
			//stmt.setDouble(1, param);
			// param→1→?
			
//			4. SQL문실행(executeQuery(), executeUpdate())
			rs = stmt.executeQuery(); // Query 실행.
//			5. Select문 만 ResultSet 객체를 반환한다.
//			   나머진 int를 반환한다.
			while (rs.next()) {
				System.out.printf("varcharTest:%s,", rs.getString("varchartest"));
				System.out.printf("charTest:%s,", rs.getString("chartest"));
				System.out.printf("intTest:%s,", rs.getString("inttest"));
				System.out.printf("dateTest:%s,", rs.getString("datetest"));
				System.out.printf("datetimeTest:%s %n", rs.getString("datetimetest"));
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
//			6. 닫기(close())
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
