package model.survey.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;

import model.survey.dto.SurveyDTO;

public class SurveyDAO {

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
	 
	 
	   public int getTotalRecord(String list_gubun, String search_option ,String search_data ,String search_date_s, String search_date_e, String search_date_check) {
		      getConn();
		      int result = 0;
		      try {
		         
		         if(search_date_s.length() > 0 && search_date_e.length() > 0) {
		            search_date_s = search_date_s + " 00:00:00.0";
		            search_date_e = search_date_e + " 23:59:59.999999";
		            //java.sql.Timestamp start_date = java.sql.Timestamp.valueOf(search_date_s);
		            //java.sql.Timestamp last_date = java.sql.Timestamp.valueOf(search_date_e);
		         }
		         
		         
		         String sql = "select count(*) from "+tableName01+" where no > ? ";
		         if(list_gubun.equals("ing")){
		            sql += " and CURRENT_TIMESTAMP BETWEEN start_date AND last_date ";   
		         } else if(list_gubun.equals("end")) {
		            sql += " and CURRENT_TIMESTAMP > last_date ";     
		         }

		         if(search_option.length() > 0 && search_data.length() > 0) {
		            sql += " and " +search_option+" like ? ";
		         }
		         
		         if(search_date_check.equals("O") && search_date_s.length() > 0 && search_date_e.length() > 0) {
		            sql += " and (start_date >= to_timestamp(?) and last_date <= to_timestamp(?))";
		         }
		         
		         
		         int k = 1;
		         pstmt = conn.prepareStatement(sql);   
		         pstmt.setInt(k++, 0);
		           if (search_option.length() > 0 && search_data.length() > 0) {
		               pstmt.setString(k++, '%' + search_data + '%');
		            }
		           if(search_date_check.equals("O") && search_date_s.length() > 0 && search_date_e.length() > 0) {
		              pstmt.setString(k++, search_date_s);
		              pstmt.setString(k++, search_date_e);
		           }
		           
		         rs = pstmt.executeQuery();
		         
		         if(rs.next()) {
		            result = rs.getInt(1);         
		         }
		      } catch(Exception e) {
		         e.printStackTrace();
		      } finally {
		         getConnClose(rs, pstmt, conn);
		      }
		      
		      return result;
		   }

	 
	 
	  public ArrayList<SurveyDTO> getList(int startRecord, int lastRecord,String list_gubun, String search_option, String search_data, String search_date_s, String search_date_e, String search_date_check) {
	      ArrayList<SurveyDTO> list = new ArrayList<SurveyDTO>();
	      getConn();
	      try {
	    	  
	    	     if(search_date_s.length() > 0 && search_date_e.length() > 0) {
			            search_date_s = search_date_s + " 00:00:00.0";
			            search_date_e = search_date_e + " 23:59:59.999999";
			            //java.sql.Timestamp start_date = java.sql.Timestamp.valueOf(search_date_s);
			            //java.sql.Timestamp last_date = java.sql.Timestamp.valueOf(search_date_e);
			         }
			        
	         String basicSql = "";
	         basicSql += "select t1.*, ";
	         basicSql += "(select count(*) from "+ tableName02+" t2 where t2.no=t1.no) survey_counter ";
	         basicSql += "from " + tableName01+" t1 where no >? ";
	         	if(list_gubun.equals("ing")) {
	         		basicSql +="and current_timestamp between start_date and last_date ";
	         	}else if(list_gubun.equals("end")) {
	         		basicSql +="and current_timestamp > last_date ";
	         	}
	         	if(search_option.length() > 0 && search_data.length() > 0) {
	         		basicSql+=" and "+ search_option+" like ? ";
	         	}
	         	if(search_date_check.equals("O") && search_date_s.length() > 0 && search_date_e.length() > 0) {
		            basicSql += " and (start_date >= to_timestamp(?) and last_date <= to_timestamp(?))";
		         }
	         	
	         	basicSql+="order by no desc";
	         
	         String sql = "";
	         sql += "select * from (select A.*, Rownum Rnum from (" + basicSql + ") A)";
	         sql += "where Rnum >= ? and Rnum <= ?";
	          
	         int k=0;
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setInt(++k, 0);
	         
	         if(search_option.length() > 0 && search_data.length() > 0) {
	        	 pstmt.setString(++k, '%'+search_data+'%');
	         } 
	         if(search_date_s.length() > 0 && search_date_e.length() > 0) {
	              pstmt.setString(++k, search_date_s);
	              pstmt.setString(++k, search_date_e);
	           }
	     	
	         pstmt.setInt(++k, startRecord);
	         pstmt.setInt(++k, lastRecord);
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
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
	                 dto.setSurvey_counter(rs.getInt("survey_counter"));
	                 
	                 list.add(dto);
	         }
	      } catch(Exception e) {
	         e.printStackTrace();
	      } finally {
	         getConnClose(rs, pstmt, conn);
	      }
	         return list;
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


	 
	 
	 
	 
	 
}
