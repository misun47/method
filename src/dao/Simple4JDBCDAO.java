package dao;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Simple4JDBCDAO {

	public static void main(String[] args) {
		double param = 100;
		String url = "jdbc:mysql://localhost:3306/smart?characterEndoing=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		String sql = "SELECT * FROM exam WHERE intTest = " + param;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
//			1. 드라이버 로드 (Class.forName())
			Class.forName("com.mysql.cj.jdbc.Driver");
//			2. DB연결(DriverManager.getConnection())
			conn = DriverManager.getConnection(url, user, password);
//			3. SQL문작성(Statement, PrepareStatement)
			stmt = conn.createStatement();
//			4. SQL문실행(executeQuery(), executeUpdate())
			rs = stmt.executeQuery(sql); // Query 실행
//			5. Select문 만 ResultSet 객체를 반환한다.
//			   나머진 int를 반환한다.
			while (rs.next()) {
				System.out.printf("varcharTest:%s,", rs.getString("varchartest"));
				System.out.printf("varcharTest:%s,", rs.getString("chartest"));
				System.out.printf("varcharTest:%s,", rs.getString("inttest"));
				System.out.printf("varcharTest:%s,", rs.getString("datetest"));
				System.out.printf("varcharTest:%s %n", rs.getString("datetimetest"));
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
//			6. 닫기(close())
			try {
				if(rs !=null)rs.close();
				if(stmt !=null)stmt.close();
				if(conn !=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			

		}
	}
}
