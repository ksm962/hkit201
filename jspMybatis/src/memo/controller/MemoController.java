package memo.controller;

import java.io.IOException;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.dao.MemberDAO;
import member.model.dto.MemberDTO;
import member.util.MemberUtil;
import memo.model.dto.MemoDTO;
import memo.model.dao.MemoDAO;


@WebServlet("/memo_servlet/*") //다른 컨트롤러와 중복되면 안된다.
public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProc(request, response);
	}
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	MemberUtil util = new MemberUtil();
	
	String serverInfo[] = util.getServerInfo(request);  // request.getContextPath();
    String refer = serverInfo[0];
    String path = serverInfo[1];
    String url = serverInfo[2];
    String uri = serverInfo[3];
    String ip = serverInfo[4];
	
	String pageNumber_;
	pageNumber_=request.getParameter("pageNumber");
	if(pageNumber_ == null || pageNumber_.trim().equals("")) {
		pageNumber_="1";
	}

	int pageNumber=Integer.parseInt(pageNumber_);
	String page = "/main/main.jsp";
	
	if(url.indexOf("index.do") != -1) {
		request.setAttribute("menu_gubun", "memo_index");
		RequestDispatcher rd= request.getRequestDispatcher(page);
		rd.forward(request, response);

	} else if (url.indexOf("memo.do") != -1) {
		request.setAttribute("menu_gubun", "memo_memo");
		
		page="/memo/memo.jsp";
		RequestDispatcher rd= request.getRequestDispatcher(page);
		rd.forward(request, response);
		
	}else if(url.indexOf("memoProc.do") != -1) {

		String name = request.getParameter("name");
		String memo = request.getParameter("content");
		
			MemoDTO dto = new MemoDTO();
				dto.setName(name);
				dto.setMemo(memo); 
	
			MemoDAO dao = new MemoDAO();
		int result = dao.setInsert(dto);
		
	
	
	}else if(url.indexOf("memolist.do") != -1) {
	
		MemoDAO dao = new MemoDAO();
		
		//페이징 처리소스 
		int pageSize=10; //레코드(튜플)을 10개 보여주는것
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
		
		
		lastPage = startPage + blockSize - 1;
		
		if(lastPage>totalPage) {
		   lastPage=totalPage;
		}
	}		 
		
		List<MemoDTO> list =dao.getSelectAll(startRecord,lastRecord);		
		request.setAttribute("menu_gubun", "memo_memolist");
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
	
		page = "../memo/memolist.jsp";
		
		RequestDispatcher rd= request.getRequestDispatcher(page);
		rd.forward(request, response);
		
		}else if(url.indexOf("modfiyProc.do") != -1) {
			
			System.out.println("모디파이프록 들어옴");
			
			String id_ = request.getParameter("id");
			String name = request.getParameter("name");
			String memo = request.getParameter("content");			
			int id = Integer.parseInt(id_);
			
			MemoDAO dao = new MemoDAO();
			MemoDTO dto = new MemoDTO();
			dto.setId(id);
			dto.setName(name);
			dto.setMemo(memo); 
			int result = dao.setupdate(dto);	
			
	
		}else if(url.indexOf("sakjeProc.do") != -1) {
			String id_ = request.getParameter("id");

			int id = Integer.parseInt(id_);
			
			MemoDAO dao = new MemoDAO();
			MemoDTO dto = new MemoDTO();
			
			dto.setId(id);
			
			int result = dao.setDelete(dto);
				
		}
	
	
	}

}
