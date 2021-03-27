package model.mail.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import model.mail.dto.MailDTO;
import model.member.dto.MemberDTO;


public class MailDAO {
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
	 
	 public int setInsert(MailDTO dto) {
			
			int result = 0;
			getConn();
			
			try {
				String sql = "insert into product values(seq_product.nextval,?,?,?,?,current_timestamp)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,dto.getName());
				pstmt.setInt(2,dto.getPrice());
				pstmt.setString(3,dto.getDescription());
				pstmt.setString(4,dto.getProduct_img());
				 
		
				result = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose(rs,pstmt,conn);
			}
			 
		
			return result;
		}
	 
	 public ArrayList<MailDTO> getSelectAll(int startRecord,int lastRecord){ //스타트와 마지막 레코드를 읽는것(레코드개수관련)
			ArrayList<MailDTO> list =new ArrayList<>(); 
				getConn();
			try {
				String basicSql="";
					   basicSql = "select * from product where no > ?";
					   basicSql+= "order by name desc";
				String sql="";
				sql+="select * from (select A.*, Rownum Rnum from("+basicSql+") A)";
				sql+="where Rnum >=? and Rnum <=?";
				
		
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 0);
				pstmt.setInt(2, startRecord);
				pstmt.setInt(3, lastRecord);
				rs = pstmt.executeQuery();
				while(rs.next()){	
					
					MailDTO dto = new MailDTO();
					
					dto.setNo(rs.getInt("no"));
					dto.setName(rs.getString("name"));
					dto.setPrice(rs.getInt("price"));
					dto.setDescription(rs.getString("description"));
					dto.setProduct_img(rs.getString("product_img"));
					dto.setRegi_date(rs.getTimestamp("regi_date"));
			
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
				String sql="select count(*) from product where no > ?";
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
