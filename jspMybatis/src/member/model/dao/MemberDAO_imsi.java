package member.model.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import member.model.dto.MemberDTO;


 
public class MemberDAO_imsi {
	
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
	public int setInsert(MemberDTO dto) {
		
		int result = 0;
		getConn();
		
		try {
			String sql = "insert into member values(seq_member.nextval,?,?,?,?,?,current_timestamp,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,dto.getId());
			pstmt.setString(2,dto.getPasswd());
			pstmt.setString(3,dto.getName());
			pstmt.setString(4,dto.getGender());
			pstmt.setInt(5,dto.getBornyear());
			pstmt.setString(6,dto.getPostcode());
			pstmt.setString(7,dto.getAddress());
			pstmt.setString(8,dto.getDetailaddress());
			pstmt.setString(9,dto.getExtraaddress());
	
			result = pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getConnClose(rs,pstmt,conn);
		}
		 
	
		return result;
	}
	
	public MemberDTO login(MemberDTO dto)	{	
		MemberDTO dto1 = new MemberDTO();
		getConn();
		try {
			String sql="select * from member where id=? and passwd=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			rs = pstmt.executeQuery();
			while(rs.next()) {
		
				dto1.setNo(rs.getInt("no"));
				dto1.setId(rs.getString("id"));
				dto1.setPasswd(rs.getString("passwd"));
				dto1.setName(rs.getString("name"));
				dto1.setGender(rs.getString("gender"));
				dto1.setBornyear(rs.getInt("bornyear"));
				dto1.setRegiDate(rs.getTimestamp("regidate"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			getConnClose(rs,pstmt,conn);
		}

		return dto1;
		
	}
	
	
	public ArrayList<MemberDTO> getSelectAll_(){
		ArrayList<MemberDTO> list =new ArrayList<>(); 
			getConn();
		try {
			String sql = "select * from member";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){	
				
				MemberDTO dto = new MemberDTO();
				
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("Id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setBornyear(rs.getInt("bornyear"));
				dto.setRegiDate(rs.getTimestamp("regidate"));
				dto.setPostcode(rs.getString("postcode"));
				dto.setAddress(rs.getString("address"));
				dto.setDetailaddress(rs.getString("detailaddress"));
				dto.setExtraaddress(rs.getString("extraaddress"));
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getConnClose(rs,pstmt,conn);
		}return list;
		
		
		
	}
	
	public ArrayList<MemberDTO> getSelectAll(int startRecord,int lastRecord, String search_option, String search_data){ //스타트와 마지막 레코드를 읽는것(레코드개수관련)
		ArrayList<MemberDTO> list =new ArrayList<>(); 
			getConn();
		try {
			String basicSql="";
				   basicSql+= "select * from member where no > 0";
				   
				   if(search_option.length() > 0 && search_data.length() > 0) {
						if(search_option.equals("id") || search_option.equals("name") || search_option.equals("gender")) {
							basicSql+= " and " + search_option + " like ? ";
							
						}else if (search_option.equals("id_name_gender")) {
							basicSql+=" and (id like ? or name like ? or gender like ?)";
						}
				   }
				   basicSql+= " order by no desc";
				   
			String sql="";
			sql+="select * from (select A.*, Rownum Rnum from("+basicSql+") A)";
			sql+="where Rnum >=? and Rnum <=?";
			
			
			int k = 0;
			pstmt = conn.prepareStatement(sql);
			
			if(search_option.length() > 0 && search_data.length() > 0) {
				if(search_option.equals("id") || search_option.equals("name") || search_option.equals("gender")) {
					pstmt.setString(++k, '%' + search_data + '%');
				}else if (search_option.equals("id_name_gender")) {
					pstmt.setString(++k, '%' + search_data + '%');
					pstmt.setString(++k, '%' + search_data + '%');
					pstmt.setString(++k, '%' + search_data + '%');
				}
			}
			
			pstmt.setInt(++k, startRecord);
			pstmt.setInt(++k, lastRecord);
			rs = pstmt.executeQuery();
			while(rs.next()){	
				
				MemberDTO dto = new MemberDTO();
				
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("Id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setBornyear(rs.getInt("bornyear"));
				dto.setRegiDate(rs.getTimestamp("regidate"));
				dto.setPostcode(rs.getString("postcode"));
				dto.setAddress(rs.getString("address"));
				dto.setDetailaddress(rs.getString("detailaddress"));
				dto.setExtraaddress(rs.getString("extraaddress"));
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getConnClose(rs,pstmt,conn);
		}return list;
		
		
		
	}
	

	public MemberDTO getView(int no){
		  
		MemberDTO dto=new MemberDTO();
		   getConn();
		   try {
		      String sql = "select * from member where no = ?";
		      pstmt =conn.prepareStatement(sql);
		      pstmt.setInt(1, no);
		      rs=pstmt.executeQuery();  
		      
		      if(rs.next()) {
		         dto.setNo(rs.getInt("no"));
					dto.setId(rs.getString("Id"));
					dto.setPasswd(rs.getString("passwd"));
					dto.setName(rs.getString("name"));
					dto.setGender(rs.getString("gender"));
					dto.setBornyear(rs.getInt("bornyear"));
					dto.setRegiDate(rs.getTimestamp("regidate"));
					dto.setPostcode(rs.getString("postcode"));
					dto.setAddress(rs.getString("address"));
					dto.setDetailaddress(rs.getString("detailaddress"));
					dto.setExtraaddress(rs.getString("extraaddress"));

		      }
		   }catch(Exception e) {e.printStackTrace();
		   }finally {
				getConnClose(rs,pstmt,conn);
			}return dto;
		}
	public int setupdate(MemberDTO dto) {
		int result = 0;
		
		getConn();
		
		try {
			String sql = "update member set no=?,passwd=?,name=?,gender=?,bornyear=? ,postcode=?, address=?, detailaddress=?, extraaddress=? where id=?";
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setInt(1,dto.getNo());
			pstmt.setString(2,dto.getPasswd());
			pstmt.setString(3,dto.getName());
			pstmt.setString(4,dto.getGender());
			pstmt.setInt(5,dto.getBornyear());
			pstmt.setString(6,dto.getPostcode());
			pstmt.setString(7,dto.getAddress());
			pstmt.setString(8,dto.getDetailaddress());
			pstmt.setString(9,dto.getExtraaddress());
			pstmt.setString(10,dto.getId());
			result = pstmt.executeUpdate();
			

		}catch(Exception e) {
			   e.printStackTrace();
			}
		return result;

		
	}
	public int setDelete(MemberDTO dto) {
		int result = 0;
		getConn();
		try {
			String sql = "delete from member where no = ? and passwd= ?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1,dto.getNo()); 
			pstmt.setString(2,dto.getPasswd());
			
			result = pstmt.executeUpdate();
		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public int getIdcheck(String id) {
		int result = 0;
		getConn();
		try {
			String sql="select count(*) from member where id=? ";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1,id); 
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}return result;
	}
	public String getIdcheckWin(String id) {
		String result = "";
		getConn();
		try {
			String sql="select count(*) from member where id=? ";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1,id); 
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result=Integer.toString(rs.getInt(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(result.equals("0")) {
			result ="";
		}return result;
	}	
	
	

	public int getTotalRecord(String search_option, String search_data) {
		int result = 0;
		getConn();
		try {
			String sql="select count(*) from member where no > 0";
			
			if(search_option.length() > 0 && search_data.length() > 0) {
				if(search_option.equals("id") || search_option.equals("name") || search_option.equals("gender")) {
					sql+= " and " + search_option + " like ? ";
						
				}else if (search_option.equals("id_name_gender")) {
					sql+=" and (id like ? or name like ? or gender like ?)";
				}
			}
			
			int k=0;
			pstmt =conn.prepareStatement(sql);
			
			if(search_option.length() > 0 && search_data.length() > 0) {
				if(search_option.equals("id") || search_option.equals("name") || search_option.equals("gender")) {
					pstmt.setString(++k, '%' + search_data + '%');
				}else if (search_option.equals("id_name_gender")) {
					pstmt.setString(++k, '%' + search_data + '%');
					pstmt.setString(++k, '%' + search_data + '%');
					pstmt.setString(++k, '%' + search_data + '%');
				}
			}
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
