package survey.model.dao;

import java.sql.Connection
;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import db.DbExample;
import member.model.dto.MemberDTO;
import sqlmap.MybatisManager;
import survey.model.dto.SurveyDTO;



public class SurveyDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	

	 
	 public int setInsert(SurveyDTO dto) {
		 Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
			map.put("dto", dto);
			
			SqlSession session = MybatisManager.getInstance().openSession();
			int result = session.insert("survey.setInsert",map);
			session.commit();
			session.close();
			return result;	
		}
	 
	 
	   public int getTotalRecord(String list_gubun, String search_option ,String search_data ,String search_date_s, String search_date_e, String search_date_check) {
		   Map<String, String> map = new HashMap<>();
		   
			map.put("list_gubun", list_gubun);
			map.put("search_date_s", search_date_s);
			map.put("search_date_e", search_date_e);
			map.put("search_date_check", search_date_check);
			map.put("search_option", search_option);
			map.put("search_data", search_data);
		   
			SqlSession session = MybatisManager.getInstance().openSession();
			int result = session.selectOne("survey.getTotalRecord",map);
			session.close();
			return result;
		   
		   }

	 
	 
	  public List<SurveyDTO> getList(int startRecord, int lastRecord,String list_gubun, String search_option, String search_data, String search_date_s, String search_date_e, String search_date_check) {
	    
			Map<String, Object> map = new HashMap<>();
			map.put("startRecord", startRecord); //숫자로 형변환?
			map.put("lastRecord", lastRecord);
			map.put("list_gubun", list_gubun);
			map.put("search_date_s", search_date_s);
			map.put("search_date_e", search_date_e);
			map.put("search_date_check", search_date_check);
			map.put("search_option", search_option);
			map.put("search_data", search_data);
			
				
			SqlSession session = MybatisManager.getInstance().openSession();
			List<SurveyDTO> list = session.selectList("survey.getList",map);
			session.close();
			return list;
	  }
	      
		public SurveyDTO getView(int no){
			SqlSession session = MybatisManager.getInstance().openSession();
			SurveyDTO dto = session.selectOne("survey.getView",no);
			session.close();
			return dto;
			}

 
		public int setupdate(SurveyDTO dto) {
			Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
			map.put("dto", dto);
			
			SqlSession session = MybatisManager.getInstance().openSession();
			int result = session.update("survey.setupdate",map);
			session.commit();
			session.close();
			return result;
		}
		public int setDelete(SurveyDTO dto) {
			Map<String, Object> map = new HashMap<>();
			map.put("dto", dto);
			
			SqlSession session = MybatisManager.getInstance().openSession();
			int result = session.delete("survey.setDelete",map);
			session.commit();
			session.close();
			return result;
		
		}
	 
	 
	 
}
