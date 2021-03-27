package model.survey.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DbExample;
import model.survey.dto.SurveyAnswerDTO;


public class SurveyAnswerDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String tableName01 = "survey";
	String tableName02 = "survey_answer";
	
 
	public Connection getConn() {
		conn=DbExample.dbConn();
		return conn;
	}
	 public void getConnClose(ResultSet rs, PreparedStatement pstmt,Connection conn) {
	      DbExample.dbConnClose(rs,pstmt,conn);
	   }
	 
	 public int setInsertAnswer(SurveyAnswerDTO Dto) {
			
			int result = 0;
			getConn();
			
			try {
				String sql = "insert into "+tableName02+" values (seq_survey_answer.nextval,?,?, current_timestamp)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,Dto.getNo());
				pstmt.setInt(2,Dto.getAnswer());
				
			
				result = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose(rs,pstmt,conn);
			}
			 
		
			return result;
		}

	 
	 
	 
}
