package controller.memo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.memo.dao.MemoDAO;
import model.memo.dto.MemoDTO;



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
	
	String path=request.getContextPath();
	String url=request.getRequestURL().toString();
	String page="/main/main.jsp";
	
	String pageNumber_;
	pageNumber_=request.getParameter("pageNumber");
	if(pageNumber_ == null || pageNumber_.trim().equals("")) {
		pageNumber_="1";
	}
	char imsi;
	
	for(int i=0; i < pageNumber_.length(); i++) {
		imsi = pageNumber_.charAt(i);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(imsi >='0' && imsi <='9'){
			
		}else{
			HttpSession session= request.getSession(); 
			session.invalidate();
			out.println("<script>");
			
			out.println("location.href='"+path+"';");
			out.println("</script>");
		}	
	}
	
	
	int pageNumber=Integer.parseUnsignedInt(pageNumber_);
	
	if(url.indexOf("memo.do") != -1) {
		request.setAttribute("menu_gubun", "memo_memo");
		RequestDispatcher rd= request.getRequestDispatcher(page);
		rd.forward(request, response);
		
	}else if(url.indexOf("memoProc.do") != -1) {
		
		String name = request.getParameter("name");
		String memo = request.getParameter("memo");
	
		
		
			MemoDTO dto = new MemoDTO();
				dto.setName(name);
				dto.setMemo(memo); 
	
			MemoDAO dao = new MemoDAO();
		int result = dao.setInsert(dto);
		
		String temp="";
		if(result>0) {
			temp =path+"/memo_servlet/memolist.do";
	
		}else {
			temp = path+"/memo_servlet/memo.do";
		}
		response.sendRedirect(temp);
	
	}else if(url.indexOf("memolist.do") != -1) {
		
		HttpSession session = request.getSession();
		session.getAttribute("cookNo");
		
		if(session.getAttribute("cookNo")== null){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('로그인 후 이용하세요')");
			out.println("location.href='"+path+"';");
			out.println("</script>");
		
		}else {
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
		
		ArrayList<MemoDTO> list =dao.getSelectAll(startRecord,lastRecord);		
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
	
		
		RequestDispatcher rd= request.getRequestDispatcher(page);
		rd.forward(request, response);
		
		}
	}
	
	
	
	
	
	
	}

}
