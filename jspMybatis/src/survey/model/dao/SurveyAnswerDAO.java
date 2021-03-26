package survey.model.dao;

import java.sql.Connection
;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import db.DbExample;
import sqlmap.MybatisManager;
import survey.model.dto.SurveyAnswerDTO;



public class SurveyAnswerDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String tableName01 = "survey";
	String tableName02 = "survey_answer";
	
	 
	 public int setInsertAnswer(SurveyAnswerDTO Dto) {
		 Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
			map.put("Dto", Dto);
			
			SqlSession session = MybatisManager.getInstance().openSession();
			int result = session.insert("survey.setInsertAnswer",map);
			session.commit();
			session.close();
			return result;	
		}
			
		
			
		}

	 
	 
	 

