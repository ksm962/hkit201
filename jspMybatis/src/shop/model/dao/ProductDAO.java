package shop.model.dao;

import java.sql.Connection

;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


import shop.model.dto.ProductDTO;
import sqlmap.MybatisManager;


public class ProductDAO {
	public int setInsert(ProductDTO dto) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("dto", dto);
			
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("product.setInsert",map);
		session.commit();
		session.close();
		return result;	
		
	}
	 
	public List<ProductDTO> getList(int startRecord,int lastRecord,String search_option, String search_data){ //스타트와 마지막 레코드를 읽는것(레코드개수관련)
		Map<String, Object> map = new HashMap<>();
		map.put("startRecord", startRecord); //숫자로 형변환?
		map.put("lastRecord", lastRecord);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<ProductDTO> list = session.selectList("product.getList",map);
		session.close();
		return list;			
		
	}
		
	public int getTotalRecord(String search_option, String search_data) {
		Map<String, Object> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
			
		SqlSession session = MybatisManager.getInstance().openSession();
		int result= session.selectOne("product.getTotalRecord",map);
		session.close();
		return result;	

	}
		
	public ProductDTO getView(int no){
		SqlSession session = MybatisManager.getInstance().openSession();
		ProductDTO dto = session.selectOne("product.getView",no);
		
		session.close();
		return dto;
	}

	public int setupdate(ProductDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("product.setupdate",map);
		session.commit();
		session.close();
		return result;
		
	}
	public int setDelete(int no) {
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("product.setDelete",no);
		session.commit();
		session.close();
		return result;
	}
	
}
