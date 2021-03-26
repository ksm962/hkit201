package guestbook.model.dao;

import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import db.DbExample;
import guestbook.model.dto.GuestbookDTO;
import member.model.dto.MemberDTO;
import sqlmap.MybatisManager;



public class GuestbookDAOmyBatis {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Connection getConn() {
		conn=DbExample.dbConn();
		return conn;
	}
	 public void getConnClose(ResultSet rs, PreparedStatement pstmt,Connection conn) {
	      DbExample.dbConnClose(rs,pstmt,conn);
	   }
	 
	 
	public int setInsert(GuestbookDTO dto) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("guestbook.setInsert",map);
		session.commit();
		session.close();
		return result;	
	}
	 
	public List<GuestbookDTO> getListAll(int startRecord,int lastRecord){
		Map<String, String> map = new HashMap<>();
		map.put("startRecord", startRecord + ""); //숫자로 형변환?
		map.put("lastRecord", lastRecord + "");
			
		SqlSession session = MybatisManager.getInstance().openSession();
		List<GuestbookDTO> list = session.selectList("guestbook.getListAll",map);
		session.close();
		return list;
		
	}
	
	public int getTotalRecord() {
		int result = 0;
		getConn();
		try {
			String sql="select count(*) from guestbook where no > ?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		 }finally {
				getConnClose(rs,pstmt,conn);	
		}return result;
	}
	
	public GuestbookDTO getView(int no){
		  
		GuestbookDTO dto=new GuestbookDTO();
		   getConn();
		   try {
		      String sql = "select * from guestbook where no = ?";
		      pstmt =conn.prepareStatement(sql);
		      pstmt.setInt(1, no);
		      rs=pstmt.executeQuery();  
		      
		      if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setContent(rs.getString("content"));
				dto.setRegi_date(rs.getDate("regi_date"));
		      }
		   }catch(Exception e) {e.printStackTrace();
		   }finally {
				getConnClose(rs,pstmt,conn);
			}return dto;
		}
	public int setupdate(GuestbookDTO dto) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("guestbook.setupdate",map);
		session.commit();
		session.close();
		return result;
	
	}
	public int setDelete(GuestbookDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("guestbook.setDelete",map);
		session.commit();
		session.close();
		return result;
	
	}
	
}
