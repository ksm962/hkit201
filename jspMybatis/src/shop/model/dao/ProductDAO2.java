package shop.model.dao;

import java.sql.Connection
;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;

import shop.model.dto.ProductDTO;


public class ProductDAO2 {
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
	 
	 public int setInsert(ProductDTO dto) {
			
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
	 
	 public ArrayList<ProductDTO> getList(int startRecord,int lastRecord,String search_option, String search_data){ //스타트와 마지막 레코드를 읽는것(레코드개수관련)
			ArrayList<ProductDTO> list =new ArrayList<>(); 
				getConn();
			try {
				String basicSql="";
					   basicSql = "select p.*, (select sum(amount) from cart where productNo = p.no) pCount from product p where no > 0";
					   
						
						if(search_option.length() > 0 && search_data.length() > 0) {
							if(search_option.equals("name") || search_option.equals("description")) {
								basicSql+= " and " + search_option + " like ? ";
								
							}else if (search_option.equals("name_description")) {
								basicSql+=" and (name like ? or description like ?)";
							}
						}
					   basicSql+= "order by no desc";
				String sql="";
				sql+="select * from (select A.*, Rownum Rnum from("+basicSql+") A)";
				sql+="where Rnum >=? and Rnum <=?";
	
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
					ProductDTO dto = new ProductDTO();
					dto.setNo(rs.getInt("no"));
					dto.setName(rs.getString("name"));
					dto.setPrice(rs.getInt("price"));
					dto.setDescription(rs.getString("description"));
					dto.setProduct_img(rs.getString("product_img"));
					dto.setRegi_date(rs.getTimestamp("regi_date"));
					dto.setBuy_counter(rs.getInt("pCount"));
			
					list.add(dto);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose(rs,pstmt,conn);
			}return list;
			
			
			
		}
		
	public int getTotalRecord(String search_option, String search_data) {
			int result = 0;
			getConn();
			try {
				String sql="select count(*) from product where no > 0";
				
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
		
	public ProductDTO getView(int no){
		  
		ProductDTO dto=new ProductDTO();
		   getConn();
		   try {
		      String sql = "select * from product where no=?";
		      pstmt =conn.prepareStatement(sql);
		      pstmt.setInt(1, no);
		      rs=pstmt.executeQuery();  
		      
		      if(rs.next()) {
		    	  dto.setNo(rs.getInt("no"));
					dto.setName(rs.getString("name"));
					dto.setPrice(rs.getInt("price"));
					dto.setDescription(rs.getString("description"));
					dto.setProduct_img(rs.getString("product_img"));
					dto.setRegi_date(rs.getTimestamp("regi_date"));
		      }
		   }catch(Exception e) {e.printStackTrace();
		   }finally {
				getConnClose(rs,pstmt,conn);
			}return dto;
		}

	public int setupdate(ProductDTO dto) {
		int result = 0;
		
		getConn();
		
		try {
			String sql = "update product set name=?,price=?,description=?,Product_img=? where no=?";
		
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1,dto.getName());
			pstmt.setInt(2,dto.getPrice());
			pstmt.setString(3,dto.getDescription());
			pstmt.setString(4,dto.getProduct_img());
			pstmt.setInt(5, dto.getNo());
			result = pstmt.executeUpdate();
			

		}catch(Exception e) {
			   e.printStackTrace();
			}
		return result;

		
	}
	public int setDelete(int no) {
		int result = 0;
		getConn();
	
		try {
			String sql = "delete from product where no = ?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1,no); 
	
			result = pstmt.executeUpdate();
		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
