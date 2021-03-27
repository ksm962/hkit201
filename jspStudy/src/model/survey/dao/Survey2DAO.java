package model.survey.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import model.member.dto.MemberDTO;
import model.survey.dto.SurveyAnswerDTO;
import model.survey.dto.SurveyDTO;

public class Survey2DAO {


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
	
	 
	 public int setInsert(SurveyDTO dto) {
			
			int result = 0;
			getConn();
			
			try {
				String sql = "insert into survey values(seq_survey.nextval,?,?,?,?,?,?,?,?,current_timestamp)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,dto.getQuestion());
				pstmt.setString(2,dto.getAns1());
				pstmt.setString(3,dto.getAns2());
				pstmt.setString(4,dto.getAns3());
				pstmt.setString(5,dto.getAns4());
				pstmt.setString(6,dto.getStatus());
				pstmt.setTimestamp(7,dto.getStart_date());
				pstmt.setTimestamp(8,dto.getLast_date());
				 
		
				result = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose(rs,pstmt,conn);
			}
			 
		
			return result;
		}
	 
	 
	 
		public ArrayList<SurveyDTO> getSelectAll_(){
			ArrayList<SurveyDTO> list =new ArrayList<>(); 
				getConn();
			try {
				String sql = "select * from Survey";
				
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()){	
					
					SurveyDTO dto = new SurveyDTO();
					
		            dto.setNo(rs.getInt("no"));
	                 dto.setQuestion(rs.getString("question"));
	                 dto.setAns1(rs.getString("ans1"));
	                 dto.setAns2(rs.getString("ans2"));
	                 dto.setAns3(rs.getString("ans3"));
	                 dto.setAns4(rs.getString("ans4"));
	                 dto.setStatus(rs.getString("status"));
	                 dto.setStart_date(rs.getTimestamp("start_date"));
	                 dto.setLast_date(rs.getTimestamp("last_date"));
	                 dto.setRegi_date(rs.getTimestamp("regi_date"));
	      
	                 
	                 list.add(dto);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose(rs,pstmt,conn);
			}return list;
			
			
			
		}
		
		public ArrayList<SurveyDTO> getSelectAll(int startRecord, int lastRecord,String list_gubun){
			ArrayList<SurveyDTO> list =new ArrayList<>(); 
				getConn();
			try {
				String sql = "select * from Survey";
				
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()){	
					
					SurveyDTO dto = new SurveyDTO();
					
		            dto.setNo(rs.getInt("no"));
	                 dto.setQuestion(rs.getString("question"));
	                 dto.setAns1(rs.getString("ans1"));
	                 dto.setAns2(rs.getString("ans2"));
	                 dto.setAns3(rs.getString("ans3"));
	                 dto.setAns4(rs.getString("ans4"));
	                 dto.setStatus(rs.getString("status"));
	                 dto.setStart_date(rs.getTimestamp("start_date"));
	                 dto.setLast_date(rs.getTimestamp("last_date"));
	                 dto.setRegi_date(rs.getTimestamp("regi_date"));
	      
	                 
	                 list.add(dto);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose(rs,pstmt,conn);
			}return list;
			
			
			
		}
		
	 
		public SurveyDTO getView(int no){
			  
			SurveyDTO dto=new SurveyDTO();
			   getConn();
			   try {
			      String sql = "select * from survey where no=?";
			      pstmt =conn.prepareStatement(sql);
			      pstmt.setInt(1, no);
			      rs=pstmt.executeQuery();  
			      
			      if(rs.next()) {
			    	  dto.setNo(rs.getInt("no"));
		                 dto.setQuestion(rs.getString("question"));
		                 dto.setAns1(rs.getString("ans1"));
		                 dto.setAns2(rs.getString("ans2"));
		                 dto.setAns3(rs.getString("ans3"));
		                 dto.setAns4(rs.getString("ans4"));
		                 dto.setStatus(rs.getString("status"));
		                 dto.setStart_date(rs.getTimestamp("start_date"));
		                 dto.setLast_date(rs.getTimestamp("last_date"));
		                 dto.setRegi_date(rs.getTimestamp("regi_date"));

			      }
			   }catch(Exception e) {e.printStackTrace();
			   }finally {
					getConnClose(rs,pstmt,conn);
				}return dto;
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
