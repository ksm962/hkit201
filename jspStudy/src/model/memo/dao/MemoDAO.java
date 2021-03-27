package model.memo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import model.memo.dto.MemoDTO;

public class MemoDAO {
	
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
	 
	 
	public int setInsert(MemoDTO dto) {
		
		int result = 0;
		getConn();
		
		try {
			String sql = "insert into memo values(seq_memo.nextval,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,dto.getName());
			pstmt.setString(2,dto.getMemo());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getConnClose(rs,pstmt,conn);
		}
		 
	
		return result;
	}
	public ArrayList<MemoDTO> getSelectAll_(){
		ArrayList<MemoDTO> list =new ArrayList<>(); 
			getConn();
		
		try {
			String sql = "select * from memo";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){	
				
				MemoDTO dto = new MemoDTO();
				
				
				dto.setId(rs.getInt("Id"));
				dto.setName(rs.getString("name"));
				dto.setMemo(rs.getString("memo"));
				dto.setWdate(rs.getDate("wdate"));
		
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getConnClose(rs,pstmt,conn);
		}return list;
		
	}
	
	public ArrayList<MemoDTO> getSelectAll(int startRecord,int lastRecord){
		ArrayList<MemoDTO> list =new ArrayList<>(); 
			getConn();
		
		try {
			String basicSql="";
			   basicSql = "select * from memo where id > ?";
			   basicSql+= "order by id desc";
		String sql="";
		sql+="select * from (select A.*, Rownum Rnum from("+basicSql+") A)";
		sql+="where Rnum >=? and Rnum <=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, startRecord);
			pstmt.setInt(3, lastRecord);
			rs = pstmt.executeQuery();
			
			while(rs.next()){	
				
				MemoDTO dto = new MemoDTO();
				
				
				dto.setId(rs.getInt("Id"));
				dto.setName(rs.getString("name"));
				dto.setMemo(rs.getString("memo"));
				dto.setWdate(rs.getDate("wdate"));
		
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getConnClose(rs,pstmt,conn);
		}return list;
		
	}
	 
	public int getTotalRecord() {
		int result = 0;
		getConn();
		try {
			String sql="select count(*) from memo where id > ?";
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
