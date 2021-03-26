package memo.model.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;



import db.DbExample;
import member.model.dto.MemberDTO;
import memo.model.dto.MemoDTO;
import sqlmap.MybatisManager;

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
		Map<String, Object> map = new HashMap<>(); 
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("memo.setInsert",map);
		session.commit();
		session.close();
		return result;	
	}

	public List<MemoDTO> getSelectAll(int startRecord,int lastRecord){
		Map<String, Object> map = new HashMap<>();
		map.put("startRecord", startRecord); //숫자로 형변환?
		map.put("lastRecord", lastRecord);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemoDTO> list = session.selectList("memo.getSelectAll",map);
		session.close();
		return list;
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
	public int setupdate(MemoDTO dto) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("memo.setupdate",map);
		session.commit();
		session.close();
		return result;
		
	}
	public int setDelete(MemoDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);

		
		
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("memo.setDelete",map);
		session.commit();
		session.close();
		return result;
	
	}
}
