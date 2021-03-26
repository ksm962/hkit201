package shop.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DbExample;
import shop.model.dto.CartDTO;


public class CartDAO2 {
	
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
		
		
		public int setInsert(CartDTO cartDto) {
			int result = 0;
			conn = getConn();

			try {
				 String sql = "insert into cart values(seq_cart.nextval,?,?,?,current_timestamp)";
				
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,cartDto.getMemberNo());
			pstmt.setInt(2,cartDto.getProductNo());
			pstmt.setInt(3,cartDto.getAmount());
			
			
			result = pstmt.executeUpdate();
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					getConnClose(rs,pstmt,conn);
				}
				return result;
		}
		
		public int getTotalRecord(String search_option, String search_data) {
			int result = 0;
			getConn();
			try {
				String sql="select count(*) from cart where no > 0";

				if(search_option.length() > 0 && search_data.length() > 0) {
					if(search_option.equals("name") || search_option.equals("description")) {
						sql+= " and " + search_option + " like ? ";
						
					}else if (search_option.equals("name_description")) {
						sql+=" and (name like ? or description like ?)";
					}
				}
				int k = 0;
				pstmt =conn.prepareStatement(sql);

				if(search_option.length() > 0 && search_data.length() > 0) {
					if(search_option.equals("name") || search_option.equals("description")) {
						pstmt.setString(++k, '%' + search_data + '%');
					}else if (search_option.equals("name_description")) {
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
		
		public ArrayList<CartDTO> getList(int startRecord,int lastRecord,String search_option, String search_data){ //스타트와 마지막 레코드를 읽는것(레코드개수관련)
			ArrayList<CartDTO> list2 = new ArrayList<>(); 
			getConn();
			try {
				String basicSql="";
				basicSql += "select product.no product_no, cart.no no, product.name name, product.price price, ";
				basicSql += "product.product_img product_img, cart.amount amount, cart.regi_date regi_date, ";
				basicSql += "(product.price * cart.amount) buymoney  from cart,product where cart.productNo = product.no ";
				
				if(search_option.length() > 0 && search_data.length() > 0) {
					if(search_option.equals("name") || search_option.equals("description")) {
						basicSql+= " and " + search_option + " like ? ";
						
					}else if (search_option.equals("name_description")) {
						basicSql+=" and (name like ? or description like ?)";
					}
				}
				String sql="";
				sql+="select * from (select A.*, Rownum Rnum from("+basicSql+") A)";
				sql+=" where Rnum >=? and Rnum <=?";
					
				int k = 0;
				pstmt = conn.prepareStatement(sql);
			
			
				
				if(search_option.length() > 0 && search_data.length() > 0) {
					if(search_option.equals("name") || search_option.equals("description")) {
						pstmt.setString(++k, '%' + search_data + '%');
					}else if (search_option.equals("name_description")) {
						pstmt.setString(++k, '%' + search_data + '%');
						pstmt.setString(++k, '%' + search_data + '%');
					}
				}
				pstmt.setInt(++k, startRecord);
				pstmt.setInt(++k, lastRecord);
				rs = pstmt.executeQuery();
				while(rs.next()){	
						
					CartDTO dto = new CartDTO();
					dto.setProductNo(rs.getInt("product_no"));
					dto.setNo(rs.getInt("no"));
					dto.setProduct_img(rs.getString("product_img"));
					dto.setProduct_name(rs.getString("name"));
					dto.setProduct_price(rs.getInt("price"));
					dto.setAmount(rs.getInt("amount"));
					dto.setBuy_money(rs.getInt("buymoney"));
					dto.setRegi_date(rs.getTimestamp("regi_date"));
					
					list2.add(dto);
				}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					getConnClose(rs,pstmt,conn);
				}return list2;					
		}
		public boolean setDeleteBatch(String[] array) {
			int[] count = new int [array.length];
			
			conn = getConn();
			try {
				conn.setAutoCommit(false); //자동커밋을 취소
				
				String sql = "delete from cart where no = ?";
				pstmt = conn.prepareStatement(sql);
				
				for (int i=0; i<array.length; i++) {
					if (array[i].equals("on")) {
						continue;
					}
					pstmt.setInt(1, Integer.parseInt(array[i]));
					pstmt.addBatch();
				}
				count = pstmt.executeBatch();
				conn.commit();
			}catch(Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} finally {
				try {
					conn.setAutoCommit(true);
				}catch (SQLException e2) {
					e2.printStackTrace();
				}
				getConnClose(rs, pstmt, conn);
			}
			// 리턴값 -2 는 성공은 했지만, 변경된 row의 개수를 알수 없을때 리턴되는 값이다.
			boolean result = true;
			for (int i=0; i<count.length; i++) {
				System.out.println(i + "." + count[i]);
				if (count[i] != -2) {
					result = false;
					break;
				}
			}
			return result;
		}
		
		
		public ArrayList<CartDTO> getListCartProductGroup(){ //스타트와 마지막 레코드를 읽는것(레코드개수관련)
			ArrayList<CartDTO> list = new ArrayList<>(); 
			getConn();
			try {
				String sql="";
				sql += "select p.name product_name, sum(c.amount * p.price) buy_money ";
				sql += "from cart c inner join product p on c.productNo = p.no ";
				sql += "group by p.name ";
				sql += "order by product_name asc";

				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()){	
						
					CartDTO dto = new CartDTO();

					dto.setProduct_name(rs.getString("product_name"));
					dto.setBuy_money(rs.getInt("buy_money"));
					list.add(dto);
				}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					getConnClose(rs,pstmt,conn);
				}return list;					
		}
	}


	

	

