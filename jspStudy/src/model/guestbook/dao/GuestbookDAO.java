package model.guestbook.dao;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import model.guestbook.dto.GuestbookDTO;



public class GuestbookDAO {

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
		
		int result = 0;
		getConn();
		
		try {
			String sql = "insert into guestbook values(seq_guestbook.nextval,?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,dto.getName());
			pstmt.setString(2,dto.getEmail());
			pstmt.setString(3,dto.getPasswd());
			pstmt.setString(4,dto.getContent());
			result = pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<GuestbookDTO> getListAll_(){
		ArrayList<GuestbookDTO> list = new ArrayList<>();
		getConn();
		try {
			String sql = "select * from guestbook";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
		while(rs.next()) { 
			GuestbookDTO dto = new GuestbookDTO();
			dto.setNo(rs.getInt("no"));
			dto.setName(rs.getString("name"));
			dto.setEmail(rs.getString("email"));
			dto.setPasswd(rs.getString("passwd"));
			dto.setContent(rs.getString("content"));
			dto.setRegi_date(rs.getDate("regi_date"));
	
			list.add(dto);
		}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
		
		
	}
	 
	public ArrayList<GuestbookDTO> getListAll(int startRecord,int lastRecord){
		ArrayList<GuestbookDTO> list = new ArrayList<>();
		getConn();
		try {
			String basicSql="";
			   basicSql = "select * from guestbook where no > ?";
			   basicSql+= "order by regi_date desc";
		String sql="";
		sql+="select * from (select A.*, Rownum Rnum from("+basicSql+") A)";
		sql+="where Rnum >=? and Rnum <=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, startRecord);
			pstmt.setInt(3, lastRecord);
			rs=pstmt.executeQuery();
		while(rs.next()) { 
			GuestbookDTO dto = new GuestbookDTO();
			dto.setNo(rs.getInt("no"));
			dto.setName(rs.getString("name"));
			dto.setEmail(rs.getString("email"));
			dto.setPasswd(rs.getString("passwd"));
			dto.setContent(rs.getString("content"));
			dto.setRegi_date(rs.getDate("regi_date"));
	
			list.add(dto);
		}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getConnClose(rs,pstmt,conn);
		}
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
	
}
