package member.model.dao;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import member.model.dto.MemberDTO;
import sqlmap.MybatisManager;


 
public class MemberDAO{
	
	 public int setInsert(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("member.setInsert",map);
		session.commit();
		session.close();
		return result;	
	}

	public MemberDTO login(MemberDTO dto)	{	
		
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		MemberDTO Dto = session.selectOne("member.login",map);
		session.close();
		return Dto;
	
	}

	public List<MemberDTO> getSelectAll(int startRecord,int lastRecord, String search_option, String search_data){ //스타트와 마지막 레코드를 읽는것(레코드개수관련)
		
		Map<String, String> map = new HashMap<>();
		map.put("startRecord", startRecord + ""); //숫자로 형변환?
		map.put("lastRecord", lastRecord + "");
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("table_1", table_1);
			
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemberDTO> list = session.selectList("member.getSelectAll",map);
		session.close();
		return list;
	}
	
	public MemberDTO getView(int no){
		
		SqlSession session = MybatisManager.getInstance().openSession();
		MemberDTO dto = session.selectOne("member.getView",no);
		
		session.close();
		return dto;

		}
	public int setupdate(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("member.setupdate",map);
		session.commit();
		session.close();
		return result;
		
	}
	public int setDelete(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("member.setDelete",map);
		session.commit();
		session.close();
		return result;
	
	}
	public int getIdcheck(String id) {
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("member.getIdcheck",id);
		session.close();
		return result;
		
	}
	public String getIdcheckWin(String id) {
		SqlSession session = MybatisManager.getInstance().openSession();
		String result = session.selectOne("member.getIdcheckWin",id);
		session.close();
		return result;
	}	
	
	
	String table_1 = "member";

	public int getTotalRecord(String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("table_1", table_1);
		
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("member.getTotalRecord",map);
		session.close();
		return result;
	
	
	}
	
	 
	
	
}
