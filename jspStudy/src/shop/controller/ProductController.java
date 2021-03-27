package shop.controller;

import java.io.File

;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import shop.common.UtilProduct;
import shop.model.dao.ProductDAO;
import shop.model.dto.ProductDTO;


@WebServlet("/product_servlet/*")
public class ProductController extends HttpServlet {
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

		Map<String, Integer> naljaMap = new HashMap<>();
		naljaMap.put("now_y", nalja[0]);
		naljaMap.put("now_m", nalja[1]);
		naljaMap.put("now_d", nalja[2]);
		
		String serverInfo[] = util.getServerInfo(request);  // request.getContextPath();
		String refer = serverInfo[0];
		String path = serverInfo[1];
		String url = serverInfo[2];
		String uri = serverInfo[3];
		String ip = serverInfo[4];
		//String ip6 = serverInfo[5];
      
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
		//request.setAttribute("no", no);
		request.setAttribute("search_option", search_option);
		request.setAttribute("search_data", search_data);
      
		ProductDAO dao = new ProductDAO();
		ProductDTO dto = new ProductDTO();
      
		String page = "/main/main.jsp";
      
		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "product_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
 
		} else if (url.indexOf("chuga.do") != -1) {
			request.setAttribute("menu_gubun","product_chuga" );
			page ="/shop/product/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
  
		} else if (url.indexOf("chugaProc.do") != -1) {
			String img_path01 = request.getSession().getServletContext().getRealPath("/attach/image/");
			String img_path02 = img_path01.replace("\\", "/");
			String img_path03 = img_path01.replace("\\", "\\\\");
			int max_size=10*1024*1024; //10MB, 업로드 최대 용량
			//System.out.println(img_path01);
    	  
			/*
			 * System.out.println("img_path01:"+img_path01);
			 * System.out.println("img_path02:"+img_path02);
			 * System.out.println("img_path03:"+img_path03);
			 */
    
			MultipartRequest multi = new MultipartRequest(request, img_path03, max_size, "UTF-8", new DefaultFileRenamePolicy());
			
			String name = multi.getParameter("name");
			temp = multi.getParameter("price");
			int price = Integer.parseInt(temp);
			String description = multi.getParameter("description");
    	  
			/*
			 * System.out.println(name); 
			 * System.out.println(price);
			 * System.out.println(description);
			 */
    	  
			String[] array = new String[3];
          
			for (int i=0; i<array.length; i++) {
				array[i] = "-";
			}
    	  
			Enumeration files = multi.getFileNames();
			while (files.hasMoreElements()) {
				String formName = (String) files.nextElement();
				String filename = multi.getFilesystemName(formName);

				String fileOrgName=multi.getOriginalFileName(formName);
				String fileType=multi.getContentType(formName);
             
				// System.out.println("formName:"+formName);
				// System.out.println("filename:"+filename);
			    // System.out.println(formName+":"+fileName);
			    // System.out.println(fileOrgName+":"+fileType);
             
             
             
				int point_index = filename.lastIndexOf(".");
              
				int k = Integer.parseInt(formName);
				array[k] = filename;
				System.out.println("kk : "+array[k]);
             
				java.io.File f1;
				for (int i=0; i<array.length; i++) {
					filename = array[i];
                	if (filename == null) {//배열안에 파일명이 null인 경우
                		continue;
                	}
                
	                String old_path = img_path03+filename;
	                f1 = new java.io.File(old_path);
	                if (!f1.exists()) {//실제 경로에 파일이 존재하지 않을 경우.
	                	continue;
	                }
	                
	                //확장자 구하기
	                String ext = "";
	                ext = filename.substring(point_index+1).toLowerCase();
	                System.out.println("ext :" + ext);
	                 
	                if (point_index == -1) {
	                	f1.delete(); //잘못 올라온 파일 삭제
	                    System.out.println("잘못 올라온 파일 삭제");
	                    array[i] = "-";
	                    continue;
	                 }
	                 if (!(ext.equals("jpg")||ext.equals("jpeg")||ext.equals("gif")||ext.equals("png"))) {
	                    f1.delete(); //잘못 올라온 파일 삭제
	                    System.out.println("확장자 잘못 올라온 파일 삭제");
	                    array[i] = "-";
	                    continue;
	                 }     
	                 String uuid = util.create_uuid();
	                 System.out.println("uuid:" + uuid);
	                 String new_filename = util.getDateTimeType() + "_" + uuid + "." + ext;
	                 // String new_filename = util.todayTime()+"_"+uuid+"."+ext;
	                 // System.out.println("new_filename:"+new_filename);
	                 java.io.File newFile = new java.io.File(img_path03+new_filename);
	                 f1.renameTo(newFile); //파일이동
	                 array[i] = array[i] + "|" + new_filename;
				}
			}   
			String str = "";
			for (int i=0; i<array.length; i++) {
				str += "," + array[i];
			}
             
			str = str.substring(1);
            System.out.println(str);
             
            /*
            if (formName.equals("0")) {
            	array[0] = filename;
			} else if (formName.equals("1")) {
			    array[1] = filename;
			} else if (formName.equals("2")) {
			    array[2] = filename;
			}
	        */   
              
             
			/*
			 * temp=""; 
			 * for (int i=0; i<array.length; i++) {
			 * //System.out.println("array["+i+"]"+array[i]); 
			 * String imsi= array[i]; 
			 * if(imsi==null) { imsi="-"; } 
			 * temp+=","+imsi;
			 * } 
			 * System.out.println(temp); 
			 * temp=temp.substring(1); 
			 * System.out.println(temp);
			 */

			dto.setName(name);
			dto.setPrice(price);
			dto.setDescription(description);
			dto.setProduct_img(str);
             
			int result = dao.setInsert(dto);
               
		} else  if (url.indexOf("list.do") != -1) {
			int pageSize = 30; //레코드(튜플)을 10개 보여주는것
			int blockSize = 10;//10개의 이동단위가 나타남[1],[2],[3]... 
			
			int totalRecord = dao.getTotalRecord(search_option,search_data); 
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
    		 
    		ArrayList<ProductDTO> list = dao.getList(startRecord,lastRecord,search_option,search_data);
    		  
    		request.setAttribute("menu_gubun", "product_list");
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
    			
  
			page = "/shop/product/list.jsp";
    		
    		RequestDispatcher rd= request.getRequestDispatcher(page);
    		rd.forward(request, response);
    	  
		} else if (url.indexOf("view.do") != -1) {
			System.out.println("no = " + no);
			if (no > 0) {
				dto = dao.getView(no);
    		   
				temp = dto.getDescription();
				temp = temp.replace("\n", "<br>");
				dto.setDescription(temp);
				 
				request.setAttribute("menu_gubun", "product_view");
				request.setAttribute("dto",dto);
				 
				page = "/shop/product/view.jsp";
				
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
    	  
		} else if (url.indexOf("sujung.do") != -1) {
			dto = dao.getView(no);
			request.setAttribute("dto", dto);  
	    	  
			page = "/shop/product/sujung.jsp";
    		
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("sujungproc.do") != -1) { 
			String img_path01 = request.getSession().getServletContext().getRealPath("/attach/image/");
			//디렉토리 경로가 없을때 만들어 주는 것
			java.io.File isDir = new java.io.File(img_path01);
	          
			if (!isDir.isDirectory()) {
				System.out.println("디렉토리가 존재하지 않습니다. 디렉토리를 생성합니다.");
				isDir.mkdir();
			}
  
			String img_path02 = img_path01.replace("\\", "/");
			String img_path03 = img_path01.replace("\\", "\\\\");
			int max_size = 10 * 1024 * 1024; //10MB, 업로드 최대 용량 설정

			MultipartRequest multi = new MultipartRequest(request, img_path03, max_size, "UTF-8", new DefaultFileRenamePolicy()); 
			  
			String[] array = new String[3];

			for(int i=0; i<array.length; i++) {
	    		  array[i] = "-";
			}
	    	  
			Enumeration files = multi.getFileNames();
	          
			while (files.hasMoreElements()) {   
				String formName = (String) files.nextElement();
				String filename = multi.getFilesystemName(formName);
				String fileOrgName = multi.getOriginalFileName(formName);
				String fileType = multi.getContentType(formName);
 
				if (filename == null || filename.trim().equals("")) {
					filename = "-";
				}
				int k = Integer.parseInt(formName);
				array[k] = filename;
			}
	          
			for (int i=0; i<array.length; i++) {
				temp = array[i];
		          
				if (temp.equals("-")) {
					continue;
				}
				String old_path = img_path03 + temp;//원본이 업로드된 절대경로와 파일명을 구한다.
				java.io.File f1 = new java.io.File(old_path);
				
				if (!f1.exists()) {
					array[i] = "-";
					continue;
				}
				String ext = "";
				int point_index = temp.lastIndexOf(".");
				if (point_index == -1) {
					f1.delete(); //잘못 올라온 파일 삭제
					array[i] = "-";
                    continue;
				}
				
				ext = temp.substring(point_index +1).toLowerCase();
				if (!(ext.equals("jpg") || ext.equals("jpeg") || ext.equals("gif") || ext.equals("png"))) {
					f1.delete();
					array[i] = "-";
					continue;
				}
				
				String uuid = util.create_uuid();
				String newFilename = util.getDateTimeType() + "_" + uuid + "." + ext;
				java.io.File newFile = new java.io.File(img_path03 + newFilename);
				f1.renameTo(newFile);
				array[i] = array[i] + "|" + newFilename;
			} 
      
			String str = "";
			for (int i=0; i<array.length; i++) {
				str += "," + array[i];
			}
			str = str.substring(1);
            	
			// 여기까지는 추가 작업
	    	  
	    	  
			temp = multi.getParameter("no");
			no = util.numberCheck(temp, 0);
			String name = multi.getParameter("name");
			temp = multi.getParameter("price");
			int price = Integer.parseInt(temp);
			String description = multi.getParameter("description");
			  
			dto.setNo(no);
			dto.setName(name);
			dto.setPrice(price);
			dto.setDescription(description);
			dto.setProduct_img(str);
			 
			int result;
			
			if (url.indexOf("chugaProc.do") != -1) {
				request.setAttribute("menu_gubun", "product_chugaProc");
				dto.setProduct_img(str);
				result = dao.setInsert(dto);
						             
			} else {
				request.setAttribute("menu_gubun", "product_sujungproc");
				ProductDTO dto2 = dao.getView(no);
				String db_product_img = dto2.getProduct_img();
				
				String deleteFileName = "";
			
				if (str.trim().equals("-,-,-")) { //첨부파일이 없을경우 기존의 데이터로 집어넣기
					dto.setProduct_img(db_product_img);
				} else {//첨부파일이 있을 경우, 순서 고민, 반복문
					temp = "";
					String[] dbArray = db_product_img.split(",");
					for (int i=0; i<array.length; i++) {
						if (array[i].equals("-")) {
							temp += "," + dbArray[i];
						} else {
							temp += "," + array[i];
							deleteFileName += "," + dbArray[i].substring(dbArray[i].lastIndexOf("|") + 1);
						}
					}
					deleteFileName = deleteFileName.substring(1);
					System.out.println(deleteFileName);
					temp = temp.substring(1);
					System.out.println(temp);
					dto.setProduct_img(temp);
				}
				result = dao.setupdate(dto);
	                
				String[] arrayDelete = deleteFileName.split(",");
				for (int i=0; i<arrayDelete.length; i++) {
					if (!arrayDelete[i].trim().equals("-")) {
						java.io.File f1 = new java.io.File(img_path03 + arrayDelete[i]);
						f1.delete();
					}
				}
			}
	 
		} else if (url.indexOf("sakje.do") != -1) {
			dto = dao.getView(no);
			request.setAttribute("dto", dto);
			  
			page = "/shop/product/sakje.jsp";
				
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			  
  
  
		} else if (url.indexOf("sakjeproc.do") != -1) {
			String img_path01 = request.getSession().getServletContext().getRealPath("/attach/image/");
			String img_path02 = img_path01.replace("\\", "/");
			String img_path03 = img_path01.replace("\\", "\\\\");
			 
			ProductDTO dto2 = dao.getView(no);
			String db_product_img = dto2.getProduct_img();
			String deleteFileName = "";
			String[] array = new String[3];
	                
			temp = "";
			String[] dbArray = db_product_img.split(",");
			for (int i=0; i<array.length; i++) {
				if (!dbArray[i].equals("-")) {
					temp += "," + dbArray[i];
				} else {
					temp += "," + array[i];
					deleteFileName += "," + dbArray[i].substring(dbArray[i].lastIndexOf("|") + 1);
				}
			}
			deleteFileName = deleteFileName.substring(1);
			temp = temp.substring(1);
			dto.setProduct_img(temp);
	
                
			String[] arrayDelete = deleteFileName.split(",");
			for (int i=0; i<arrayDelete.length; i++) {
				if (!arrayDelete[i].trim().equals("-")) {
					java.io.File f1 = new java.io.File(img_path03 + arrayDelete[i]);
					f1.delete();
				}
			}		int result = dao.setDelete(no);
		}	  
	}
}


