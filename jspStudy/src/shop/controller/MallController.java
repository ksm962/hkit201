package shop.controller;

import java.io.IOException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.common.UtilProduct;
import shop.model.dao.CartDAO;
import shop.model.dao.ProductDAO;
import shop.model.dto.CartDTO;
import shop.model.dto.ProductDTO;



@WebServlet("/mall_servlet/*")
public class MallController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProc(request, response);
	}
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UtilProduct util = new UtilProduct();
	
		int[] nalja = util.getDateTime();

		Map<String, Integer> naljaMap=new HashMap<>();
		naljaMap.put("now_y", nalja[0]);
		naljaMap.put("now_m", nalja[1]);
		naljaMap.put("now_d", nalja[2]);
		
	    
		String serverInfo[] = util.getServerInfo(request);  // request.getContextPath();
		String refer = serverInfo[0];
		String path = serverInfo[1];
		String url = serverInfo[2];
		String uri = serverInfo[3];
		String ip = serverInfo[4];
		// String ip6 = serverInfo[5];
  
		String temp;
		                        
		temp = request.getParameter("pageNumber");
		int pageNumber = util.numberCheck(temp, 1);
		  
		temp = request.getParameter("no");
		int no = util.numberCheck(temp, 0);
  
		String search_option = request.getParameter("search_option");
		String search_data = request.getParameter("search_data");
		String[] searchArray = util.searchCheck(search_option, search_data);
		search_option = searchArray[0];
		search_data = searchArray[1];
 
		String[] sessionArray = util.sessionCheck(request);
		int cookNo = Integer.parseInt(sessionArray[0]);
		String cookId= sessionArray[1];
		String cookName = sessionArray[2];
	   
		request.setAttribute("naljaMap", naljaMap);
		request.setAttribute("ip", ip);
		request.setAttribute("pageNumber", pageNumber);
		// request.setAttribute("no", no);
		request.setAttribute("search_option", search_option);
		request.setAttribute("search_data", search_data);
		 
		ProductDAO dao = new ProductDAO();
		ProductDTO dto = new ProductDTO();
		  
		CartDAO cartDao = new CartDAO();
		CartDTO cartDto = new CartDTO();
		
	
		
		String page = "/main/main.jsp";
	      
	      
		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "mall_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
	         
		} else if (url.indexOf("mall_list.do") != -1) {
			int pageSize = 12; //레코드(튜플)을 10개 보여주는것
			int blockSize=10;//10개의 이동단위가 나타남[1],[2],[3]... 
	  		int totalRecord = dao.getTotalRecord(search_option,search_data); 
	  		int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
	  		int jj = pagerArray[0];
	  		int startRecord = pagerArray[1];
	  		int lastRecord = pagerArray[2];
	  		int totalPage = pagerArray[3];
	  		int startPage = pagerArray[4];
	  		int lastPage = pagerArray[5];
  		
	  		// 방법1
	  		if (totalRecord > 0) {
	  			totalPage=totalRecord / pageSize+(totalRecord % pageSize == 0 ? 0:1);
	  			startPage=(pageNumber / blockSize - (pageNumber % blockSize != 0 ? 0:1)) * blockSize + 1;
  		

	  			lastPage = startPage + blockSize - 1;
  		
	  			if (lastPage > totalPage) {
	  				lastPage = totalPage;
	  			}
	  		} 
  		 
	  		ArrayList<ProductDTO> list = dao.getList(startRecord,lastRecord,search_option,search_data);
  		  
	  		request.setAttribute("menu_gubun", "mall_list");
	  		request.setAttribute("list", list); 
	  		// request.setAttribute("pageNumber", pageNumber);
	  		request.setAttribute("pageSize", pageSize);
	  		request.setAttribute("blockSize", blockSize);
	  		request.setAttribute("totalRecord", totalRecord);
	  		request.setAttribute("jj", jj);
	  		request.setAttribute("startRecord", startRecord);
	  		request.setAttribute("lastRecord", lastRecord);
	  		request.setAttribute("totalPage", totalPage);
	  		request.setAttribute("startPage", startPage);
	  		request.setAttribute("lastPage", lastPage);
  
  			page = "/shop/mall/mall_list.jsp";
  		
	  		RequestDispatcher rd= request.getRequestDispatcher(page);
	  		rd.forward(request, response);
		} else if (url.indexOf("view.do") != -1) {
			if (no > 0) {
				dto = dao.getView(no);
				temp = dto.getDescription();
				temp = temp.replace("\n", "<br>");
				dto.setDescription(temp);
				 
				request.setAttribute("menu_gubun", "mall_view");
				request.setAttribute("dto",dto);
				 
				page = "/shop/mall/view.jsp";
				
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
				
				
		} else if (url.indexOf("cart_chuga.do") != -1) {
			temp = request.getParameter("jumun_su");
			int jumun_su = util.numberCheck(temp, 0);
			
			cartDto.setMemberNo(cookNo);
			System.out.println("cookNo : "+cookNo);
			cartDto.setProductNo(no);
			System.out.println("no : "+no);
			cartDto.setAmount(jumun_su);
			  
			int result = cartDao.setInsert(cartDto);
			
			
			
		 
		}else if (url.indexOf("cart_list.do") != -1) {

			int pageSize = 10; //레코드(튜플)을 10개 보여주는것
			int blockSize = 10;//10개의 이동단위가 나타남[1],[2],[3]... 
			int totalRecord = cartDao.getTotalRecord(search_option,search_data); 
			int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
			int jj = pagerArray[0];
			int startRecord = pagerArray[1];
			int lastRecord = pagerArray[2];
			int totalPage = pagerArray[3];
			int startPage = pagerArray[4];
			int lastPage = pagerArray[5];
			

    		
    		//방법1
    		if (totalRecord > 0) {
    			totalPage = totalRecord / pageSize + (totalRecord % pageSize == 0 ? 0:1);
    			startPage = (pageNumber / blockSize - (pageNumber % blockSize != 0 ? 0:1)) * blockSize + 1;
    			lastPage = startPage + blockSize - 1;
    		
	    		if(lastPage > totalPage) {
	    		   lastPage = totalPage;
	    		}
    		}  
    		ArrayList<CartDTO> list = cartDao.getList(startRecord,lastRecord,search_option,search_data);  
    		System.out.println(list.size());
    		  
    		request.setAttribute("menu_gubun", "cart_list");
    		request.setAttribute("list", list); 
    		
    		//request.setAttribute("pageNumber", pageNumber);
    		request.setAttribute("pageSize", pageSize);
    		request.setAttribute("blockSize", blockSize);
    		request.setAttribute("totalRecord", totalRecord);
    		request.setAttribute("jj", jj);
    		
    		request.setAttribute("startRecord", startRecord);
    		request.setAttribute("lastRecord", lastRecord);
    		
    		
    		request.setAttribute("totalPage", totalPage);
    		request.setAttribute("startPage", startPage);
    		request.setAttribute("lastPage", lastPage);

			page = "/shop/mall/cart_list.jsp";
				
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
				
		} else if (url.indexOf("cart_clear.do") != -1) {
			temp = request.getParameter("chk_no"); // 11,8,6,3
			String[] array = temp.split(",");
			 
			boolean result = cartDao.setDeleteBatch(array);
		}
	}
}