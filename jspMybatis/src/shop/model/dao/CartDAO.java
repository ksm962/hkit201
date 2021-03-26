package shop.model.dao;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


import shop.model.dto.CartDTO;

import sqlmap.MybatisManager;

public class CartDAO {
	public int setInsert(CartDTO dto) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("dto", dto);
			
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("mall.setInsert",map);
		session.commit();
		session.close();
		return result;	
	}
	
	public int getTotalRecord(String search_option, String search_data) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("search_option", search_option);
		map.put("search_data", search_data);
			
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("mall.getTotalRecord",map);
		session.close();
		return result;	
		
	}
	
	public List<CartDTO> getList(int startRecord,int lastRecord,String search_option, String search_data){ //스타트와 마지막 레코드를 읽는것(레코드개수관련)
		Map<String, Object> map = new HashMap<>();
		map.put("startRecord", startRecord); //숫자로 형변환?
		map.put("lastRecord", lastRecord);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<CartDTO> list = session.selectList("mall.getList",map);
		session.close();
		return list;			
		
	}
	public boolean setDeleteBatch(String[] array) {
		Map<String, Object> map = new HashMap<>();
		map.put("array", array);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result=session.delete("mall.setDeleteBatch",map);
		session.commit();
		session.close();
		boolean a;
		if(result > 0) {
			a = true;
		}else {
			a = false;
		}
		return a;
		
	}
	
	public List<CartDTO> getListCartProductGroup(){ //스타트와 마지막 레코드를 읽는것(레코드개수관련)
		SqlSession session = MybatisManager.getInstance().openSession();
		List<CartDTO> list = session.selectList("mall.getListCartProductGroup");
		session.close();
		return list;		
	}
}


	

	

