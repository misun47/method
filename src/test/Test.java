package test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import dao.ExamDAO;
import domain.ExamVO;
//import test.dao.Simple2DAO;

public class Test {

	public static void main(String[] args) {
		ExamDAO dao = new ExamDAO();
		
		ExamVO createVo = new ExamVO(0, "가변폭문자열", "고정폭문자열", 0.123, new Date(), new Timestamp(System.currentTimeMillis()));
		//생성자를 이용해서 객체를 만들었다.
		
		//System.out.println(createVo);
		//dao.create(createVo);
		
		//num컬럼이 4번인 데이터만 가지고 오기
		ExamVO readVo = new ExamVO();
		readVo.setNum(4);
		
		ExamVO readVoRes = dao.read(readVo);
		
		//try or if(readVoRes != null) {} 선택적 사용.
		try {
		System.out.print(readVoRes.getNum()+"\t");
		System.out.print(readVoRes.getVarcharTest()+"\t");
		System.out.print(readVoRes.getCharTest()+"\t");
		System.out.print(readVoRes.getDoubleTest()+"\t");
		System.out.print(readVoRes.getDateTest()+"\t");
		System.out.print(readVoRes.getDateTimeTest()+"\t");
		} catch(Exception e) {
			
		}
		//num컬럼이 4번인 데이터만 가지고 오기 끝
		//num컬럼이 4번인 데이터를 수정합시다.
		ExamVO updateVo = new ExamVO();
		updateVo.setNum(4);
		updateVo.setVarcharTest("바꿀값");
		updateVo.setDoubleTest(123.12);
		//dao.update(updateVo);
		//num컬럼이 ?번인 데이터를 수정합시다. 끝.
		
		
		//num컬럼이 3번인 데이터를 삭제합시다.
		ExamVO deleteVo = new ExamVO();
		deleteVo.setNum(3);
		dao.delete(deleteVo);
		//num컬럼이 3번인 데이터를 삭제합시다. 끝.
		
//		List<ExamVO> list = dao.read();
//		list.iterator();
//		Iterator<ExamVO> it = list.iterator(); //iterator : 순서대로 출력하겠다.
//		while(it.hasNext()) {
//			ExamVO vo = it.next();
//			System.out.print(vo.getNum()+"\t");
//			System.out.print(vo.getVarcharTest()+"\t");
//			System.out.print(vo.getCharTest()+"\t");
//			System.out.print(vo.getDoubleTest()+"\t");
//			System.out.print(vo.getDateTest()+"\t");
//			System.out.print(vo.getDateTimeTest()+"\t");
//			System.out.println();
//		}
		//dao.read();의 ()에 createVo를 넣었어니 create에 오류가 났다. ExamDAO의 create창 옆 ()에 ExamVO createVo 넣으면 오류 해결..
		
		
		
		
		
		
		
//		Simple2DAO dao = new Simple2DAO();
//		dao.create();
//		dao.read();
//		dao.update();
//		dao.delete();
//		simple2DAO랑 연결되어 있음.
		
	}

}
