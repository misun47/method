//Resource = CRUD :method!! 
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.SampleVO;
import util.DbUtil;

public class SampleDAO extends DbUtil {

	public void create(SampleVO vo) {
				
		StringBuffer sql = new StringBuffer();
		
		sql.append("\n INSERT INTO sample ");
		sql.append("\n (num, strData, sampleDate) ");
		sql.append("\n VALUES (null,?,curdate()) ");

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConn(); //db연결
			stmt = conn.prepareStatement(sql.toString());
			
			//stmt.setInt(, vo.getNum());
			stmt.setString(1, vo.getStrData()); //1 : 첫번째 물음표
			//stmt.setDate(, vo.getSampleDate());

			int res = stmt.executeUpdate(); // <이것이 있어야 실행이 된다.
			// 결과처리(Select문만 ResultSet 객체 리턴)
		} catch (Exception e) {
			// ClassNotFoundException -> Exception(최상위)으로 수정
			e.printStackTrace();
		} finally {
//			try {
//			if(stmt != null) stmt.close();
//			if(conn != null) conn.close();
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
			dbClose(conn, stmt, null);  //Num,StrData,SampleDate

		}
		// 닫기
	}

	//전체 목록 가져오기
	public List<SampleVO> read() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM sample ");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<SampleVO> list = new ArrayList<SampleVO>();
		
		// 코드 작성
		try {
			conn = getConn();
			// prepareStatement(SQL작성 실행)
			stmt = conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery(); // executeQuery가 ResultSet을 리턴해서 ResultSet으로 받음
			// 결과처리(Select문만 ResultSet 객체 리턴)
			while(rs.next()) {
				list.add(
				new SampleVO(
						rs.getInt("Num"),
						rs.getString("StrData"), 
						rs.getDate("SampleDate")
					)
				);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, rs);
		}

		// 닫기
		return list;
	}
	
	
	
	
	


public SampleVO read(SampleVO vo){
	
		//코드작성
	StringBuffer sql = new StringBuffer();
	sql.append(" SELECT * FROM sample WHERE num = ?" ); //변수가 들어갈 자리를 ? 로 표시.
	SampleVO sampleVo = null;
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	try {
		conn = getConn();
		//preparedStatement(SQL문+실행)
		stmt = conn.prepareStatement(sql.toString());
		
		stmt.setInt(1, vo.getNum());
		//ResultSet 객체생성
		rs = stmt.executeQuery();
		if(rs.next()) {
			sampleVo = new SampleVO(
				//stmt.setInt(, vo.getNum());
				//stmt.setString(1, vo.getStrData()); //1 : 첫번째 물음표
				//stmt.setDate(, vo.getSampleDate());
					
					rs.getInt("Num"),
					rs.getString("StrData"), 
					rs.getDate("SampleDate")
					);
		}
	} catch (Exception e) {
		
		e.printStackTrace();
	} finally {
		dbClose(conn, stmt, null);
		
//		try {
//			if(rs != null) rs.close();
//			if(stmt != null) stmt.close();
//			if(conn != null) conn.close();
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
		}//닫기
	return sampleVo;
	}	
//코드작성끝
		

/**
 * U : update() 수정하는 메소드 public : 접근지정자 param : 수정될 값 return : 없음
 */

public void update(SampleVO vo) {
	StringBuffer sql = new StringBuffer();
	
	//mysql에서 복사 붙이기해서 가져옴. sql.append 로 갖다붙임.
	sql.append(" UPDATE sample ");
	sql.append(" WHERE num = ? ");
	sql.append(" SET StrData = ? ");
	sql.append(" SampleDate = ? ");
	
	
	Connection conn = null;
	PreparedStatement stmt = null;
	
	int idx = 0;
	
	// 코드 작성
	try {
		conn = getConn();
		// prepareStatement(SQL작성 실행)
		stmt = conn.prepareStatement(sql.toString());
		
		stmt.setInt(++idx, vo.getNum());
		stmt.setString(++idx, vo.getStrData());
		stmt.setDate(++idx, vo.getSampleDate());
	
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
		dbClose(conn, stmt, null);
//		try {
//			if(stmt != null) stmt.close();
//			if(conn != null) conn.close();
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}

	}
	// 닫기
}

/**
 * D : delete() 삭제하는 메소드 public : 접근지정자 param : 삭제될 키값 return : 없음
 */

public void delete(SampleVO vo) {
	StringBuffer sql = new StringBuffer();
	sql.append(" DELETE FROM exam WHERE num = ? ");
	
	Connection conn = null;
	PreparedStatement stmt = null;
	
	
	
	// 코드 작성
	try {
		conn = getConn();
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
		dbClose(conn, stmt, null);
//		try {
//			if(stmt != null) stmt.close();
//			if(conn != null) conn.close();
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
	}
	// 닫기
}
}

