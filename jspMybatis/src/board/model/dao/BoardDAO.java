package board.model.dao;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import board.model.dto.BoardCommentDTO;
import board.model.dto.BoardDTO;
import member.model.dto.MemberDTO;
import sqlmap.MybatisManager;


public class BoardDAO {



	String tableName01 = "board";
	String tableName02 = "board_comment";

	public int setInsert(BoardDTO dto) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("dto", dto);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("board.setInsert",map);
		session.commit();
		session.close();
		return result;	
	}

	public int getMaxNum() {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("board.getMaxNum",map);
		session.commit();
		session.close();
		return result;
	}

	public int getMaxRefNo() {
		Map<String, Object> map = new HashMap<>();
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("board.getMaxNum",map);
		session.commit();
		session.close();
		return result;
	}

	public int getMaxNoticeNo(String tbl) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("tbl", tbl);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("board.getMaxNum",map);
		session.commit();
		session.close();
		return result;

	}
	
	public List<BoardDTO> getList(int startRecord,int lastRecord, String tbl, String search_option, String search_data){ //스타트와 마지막 레코드를 읽는것(레코드개수관련)
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("tbl", tbl);
		map.put("startRecord", startRecord); //숫자로 형변환?
		map.put("lastRecord", lastRecord);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<BoardDTO> list = session.selectList("board.getList",map);
		session.close();
		return list;
		
	}
	
	public int getTotalRecord(String tbl, String search_option, String search_data) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("tbl", tbl);
		map.put("search_option",search_option);
		map.put("search_data", search_data);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("board.getTotalRecord",map);
		session.close();
		return result;
	}
	
	public BoardDTO getView(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		BoardDTO dto  = session.selectOne("board.getView",map);
		session.close();
		return dto;
	}
	
	public void setUpdatHit(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("board.setUpdatHit",map);
		session.commit();
		session.close();
	}
	public void setUpdateReLevel(BoardDTO dto) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("dto", dto);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("board.setUpdateReLevel",map);
		session.commit();
		session.close();
	}
	
	public int setUpdate(BoardDTO dto) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("dto", dto);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("board.setUpdate",map);
		session.commit();
		session.close();
		return result;
	}
	
	public int setDelete(BoardDTO dto) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("dto", dto);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("board.setDelete",map);
		session.commit();
		session.close();
		return result;
	}
	public int getTotalRecordComment(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("board.getTotalRecordComment",map);
		session.close();
		return result;
		
	}
	public List<BoardCommentDTO> getListComment(int startRecord,int lastRecord, int no){ //스타트와 마지막 레코드를 읽는것(레코드개수관련)
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
	
		map.put("startRecord", startRecord); //숫자로 형변환?
		map.put("lastRecord", lastRecord);
		map.put("no",no);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<BoardCommentDTO> list = session.selectList("board.getListComment",map);
		session.close();
		return list;
		
	}
	public int setInsertComment(BoardCommentDTO dto) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("dto", dto);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("board.setInsertComment",map);
		session.commit();
		session.close();
		return result;	
	}
	public int setCommentdelete(BoardCommentDTO dto) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("dto", dto);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("board.setCommentdelete",map);
		session.commit();
		session.close();
		return result;	
	}
	public int setUpdateComment(BoardCommentDTO dto) {
		Map<String, Object> map = new HashMap<>();   //스트링과 인트가 같이 있어서 오브젝트로 받는것
		map.put("dto", dto);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("board.setUpdateComment",map);
		session.commit();
		session.close();
		return result;	
	}
	
	
	
}
