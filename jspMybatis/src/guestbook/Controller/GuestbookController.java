package guestbook.Controller;

import java.io.IOException;


import java.io.PrintWriter;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import guestbook.model.dao.GuestbookDAOmyBatis;
import guestbook.model.dto.GuestbookDTO;
import member.model.dao.MemberDAO;
import member.model.dto.MemberDTO;
import member.util.MemberUtil;




@WebServlet("/guestbook_servlet/*") //다른 컨트롤러와 중복되면 안된다.
public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProc(request, response);
	}
	
protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	
	String path=request.getContextPath();
	String url=request.getRequestURL().toString();
	String page="/main/main.jsp";
	
	MemberUtil util = new MemberUtil();
	
	String temp = "";
	
    temp = request.getParameter("no");
    int no = util.numberCheck(temp, 0);
	
	String pageNumber_;
	pageNumber_=request.getParameter("pageNumber");
		if(pageNumber_ == null || pageNumber_.trim().equals("")) {
		pageNumber_="1";
	}
		int pageNumber=Integer.parseUnsignedInt(pageNumber_);
		
		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "guestbook_index");
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("writer.do") != -1) {
			request.setAttribute("menu_gubun", "guestbook_writer");
			
			page = "/guestbook/writer.jsp";
			RequestDispatcher rd= request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if (url.indexOf("writerProc.do") != -1) {
			
			String name=request.getParameter("name");
		    String email=request.getParameter("email");
		    String passwd=request.getParameter("passwd");
		    String content=request.getParameter("content");
		    
		   GuestbookDTO dto = new GuestbookDTO();
		    dto.setName(name);
		    dto.setEmail(email);
		    dto.setPasswd(passwd);
		    dto.setContent(content);
		   GuestbookDAOmyBatis dao= new GuestbookDAOmyBatis();
		    int result=dao.setInsert(dto);
		    
		
		    
		}else if(url.indexOf("list.do") != -1) {
			
				GuestbookDAOmyBatis dao = new GuestbookDAOmyBatis();
				
				//페이징 처리소스 
				int pageSize=5; //레코드(튜플)을 10개 보여주는것
				int blockSize=10;//10개의 이동단위가 나타남[1],[2],[3]...
				int totalRecord = dao.getTotalRecord();  // 레코드 총갯수
				int jj= totalRecord-pageSize*(pageNumber-1);
				int startRecord = pageSize*(pageNumber-1)+1;
				int lastRecord = pageSize*pageNumber;
				 
				int totalPage=0;
				int startPage=1; 
				int lastPage=1;
		
				
				//방법1
				if(totalRecord > 0) {
					totalPage=totalRecord/pageSize+(totalRecord % pageSize == 0 ? 0:1);
					startPage=(pageNumber/blockSize - (pageNumber % blockSize != 0? 0:1)) * blockSize + 1;
				}
				
				lastPage = startPage + blockSize - 1;
				
				if(lastPage>totalPage) {
				   lastPage=totalPage;
				}
			

				
				List<GuestbookDTO> list = dao.getListAll(startRecord,lastRecord);
				
				
				request.setAttribute("menu_gubun", "guestbook_list"); //물고가는것 
				request.setAttribute("list", list);
				
				
				request.setAttribute("pageNumber", pageNumber);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("blockSize", blockSize);
				request.setAttribute("totalRecord", totalRecord);
				request.setAttribute("jj", jj);
				
				request.setAttribute("startRecord", startRecord);
				request.setAttribute("lastRecord", lastRecord);
				
				
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("startPage", startPage);
				request.setAttribute("lastPage", lastPage);
				
				
				
				page = "/guestbook/list.jsp";
				RequestDispatcher rd= request.getRequestDispatcher(page);
				rd.forward(request, response);
			} else if (url.indexOf("modify.do") != -1) {
				
				
				
				GuestbookDAOmyBatis dao = new GuestbookDAOmyBatis();
				GuestbookDTO dto = dao.getView(no);

				request.setAttribute("menu_gubun", "guestbook_modify"); // 물고가는것
				request.setAttribute("dto", dto);

				page = "../guestbook/modify.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);

			} else if (url.indexOf("modifyProc.do") != -1) {
				
				String name=request.getParameter("name");
			    String email=request.getParameter("email");
			    String passwd=request.getParameter("passwd");
			    String content=request.getParameter("content");
			    
			    GuestbookDAOmyBatis dao= new GuestbookDAOmyBatis();
			    GuestbookDTO dto = dao.getView(no);
			    response.setContentType("text/html; charset=utf-8");
			    PrintWriter out = response.getWriter();
			   
			    String dbPasswd = dto.getPasswd();
			   
			   if (!passwd.equals(dbPasswd)) {
				   out.println("<script>$('#span_passwd').text('X');</script>");
				
			   } else {
					
				  	dto.setNo(no);
				    dto.setName(name);
				    dto.setEmail(email);
				    dto.setPasswd(passwd);
				    dto.setContent(content);
					
					int result = dao.setupdate(dto);
					out.println("<script>$('#span_passwd').text('O');</script>");
				}
				out.flush();
				out.close(); 
   

			} else if (url.indexOf("sakje.do") != -1) {
				GuestbookDAOmyBatis dao = new GuestbookDAOmyBatis();
				GuestbookDTO dto = dao.getView(no);

				request.setAttribute("menu_gubun", "guestbook_sakje"); // 물고가는것
				request.setAttribute("dto", dto);

				page = "../guestbook/sakje.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
				
			} else if (url.indexOf("sakjeProc.do") != -1) {
				
				GuestbookDAOmyBatis dao = new GuestbookDAOmyBatis();
				GuestbookDTO dto = dao.getView(no);

				String passwd = request.getParameter("passwd");
			 	response.setContentType("text/html; charset=utf-8");
		    	PrintWriter out = response.getWriter();
				if(!passwd.equals(dto.getPasswd())) {
					   out.println("<script>$('#span_passwd').text('X');</script>");
		    
				}else {
					int result = dao.setDelete(dto);
					out.println("<script>$('#span_passwd').text('O');</script>");
				}
				
				
				
				System.out.println(passwd);
				System.out.println(dto.getPasswd());
			

					
				
				
					
			}


		}	
}
