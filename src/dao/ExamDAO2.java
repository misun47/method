package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.ExamVO;

public class ExamDAO2 {

	/**
	 * C : create() 등록하는 메소드 public : 접근지정자 param : 등록될 값 return : 없음..
	 */

	public void create(ExamVO vo) {
		// 코드 작성
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		StringBuffer sql = new StringBuffer();
		sql.append("\n INSERT INTO exam ");
		sql.append("\n (varcharTest, charTest, doubleTest, dateTest, dateTimeTest) ");
		sql.append("\n VALUES (?,?,?,?,?) ");
		//sql.append("\n VALUES ('가변폭문자열', '고정폭문자열', 1.1, curdate(), now()) ");

		Connection conn = null;
		PreparedStatement stmt = null;
		
		//int idx = 1;

		try {
			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver"); // 패키지명.클래스명(클래스는 대문자 패키지는 소문자)
			// DB연결
			conn = DriverManager.getConnection(url, user, password);
			// prepareStatement(SQL작성 실행)
			stmt = conn.prepareStatement(sql.toString());
			// prepareStatement로 리턴값을 받는다. 메소드를 객체로 만들어서 객체.메소드로 실행을 시킨다.
			
			//?에 값설정
			
//			stmt.setString(idx++, vo.getVarcharTest());  1부터 시작함. | idx = 0 일 경우엔 ++idx로 입력.
//			stmt.setString(idx++, vo.getCharTest());
//			stmt.setDouble(idx++, vo.getDoubleTest());
//			stmt.setDate(idx++, new Date(vo.getDateTest().getTime())); //그냥 vo.getDateTest() 만 쓰면 에러가뜬다.
//			stmt.setTimestamp(idx++, vo.getDateTimeTest()); 마지막은 idx만 적어도 무관.
			stmt.setString(1, vo.getVarcharTest());
			stmt.setString(2, vo.getCharTest());
			stmt.setDouble(3, vo.getDoubleTest());
			stmt.setDate(4, new Date(vo.getDateTest().getTime())); //그냥 vo.getDateTest() 만 쓰면 에러가뜬다.
			stmt.setTimestamp(5, vo.getDateTimeTest());
			
			int res = stmt.executeUpdate(); // <이것이 있어야 실행이 된다.
			// 결과처리(Select문만 ResultSet 객체 리턴)
		} catch (Exception e) {
			// ClassNotFoundException -> Exception(최상위)으로 수정
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		// 닫기
	}

	/**
	 * R : read() 조회하는 메소드 public : 접근지정자 param : 조회할 값 return : List
	 */

	public List read() {
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM exam ");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<ExamVO> list = new ArrayList<ExamVO>();
		
		// 코드 작성
		try {
			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DB연결
			conn = DriverManager.getConnection(url, user, password);
			// prepareStatement(SQL작성 실행)
			stmt = conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery(); // executeQuery가 ResultSet을 리턴해서 ResultSet으로 받음
			// 결과처리(Select문만 ResultSet 객체 리턴)
			while(rs.next()) {
				list.add(
				new ExamVO(
					rs.getInt("num"),
					rs.getString("varcharTest"),
					rs.getString("charTest"),
					rs.getDouble("doubleTest"), //getString으로도 받을 수 있음. 모든 데이터는 String(문자)다.
					rs.getDate("dateTest"),
					rs.getTimestamp("dateTimeTest")
					)
				);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		// 닫기
		return list;
	}
	
	public ExamVO read(ExamVO vo){
		//코드작성
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM exam WHERE num = ?" ); //변수가 들어갈 자리를 ? 로 표시.
		ExamVO examVo = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			//드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			//연결
			conn = DriverManager.getConnection(url,user,password);
			//preparedStatement(SQL문+실행)
			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setInt(1, vo.getNum());
			//ResultSet 객체생성
			rs = stmt.executeQuery();
			if(rs.next()) {
				examVo = new ExamVO(
						rs.getInt("num"),
						rs.getString("varcharTest"),
						rs.getString("charTest"),
						rs.getDouble("doubleTest"), //getString으로도 받을 수 있음. 모든 데이터는 String(문자)다.
						rs.getDate("dateTest"),
						rs.getTimestamp("dateTimeTest")
						);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		//닫기
		//코드작성끝
		return examVo;
	}

	/**
	 * U : update() 수정하는 메소드 public : 접근지정자 param : 수정될 값 return : 없음
	 */

	public void update(ExamVO vo) {
		String url= "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user= "root";
		String password= "smart";
		StringBuffer sql = new StringBuffer();
		
		//mysql에서 복사 붙이기해서 가져옴. sql.append 로 갖다붙임.
		sql.append(" UPDATE exam ");
		sql.append(" SET varcharTest = ?, doubleTest = ?  ");
		sql.append(" WHERE num = ? ");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		int idx = 0;
		
		// 코드 작성
		try {
			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DB연결
			conn = DriverManager.getConnection(url, user, password);
			// prepareStatement(SQL작성 실행)
			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setString(++idx, vo.getVarcharTest());
			stmt.setDouble(++idx, vo.getDoubleTest());
			stmt.setInt(++idx, vo.getNum());
			
			int res = stmt.executeUpdate();
			// 결과처리(Select문만 ResultSet 객체 리턴)
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

		}
		// 닫기
	}

	/**
	 * D : delete() 삭제하는 메소드 public : 접근지정자 param : 삭제될 키값 return : 없음
	 */

	public void delete(ExamVO vo) {
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE FROM exam WHERE num = ? ");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		
		// 코드 작성
		try {
			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DB연결
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setInt(1, vo.getNum());
			
			int res = stmt.executeUpdate();
			// prepareStatement(SQL작성 실행)
			// 결과처리(Select문만 ResultSet 객체 리턴)
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		// 닫기
	}
}
