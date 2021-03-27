package controller.index;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//url-mapping 경로를속인다
@WebServlet("/index.do")
public class IndexController extends HttpServlet {
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
		System.out.println(path);
		System.out.println(url);
		
		
		request.setAttribute("menu_gubun","index"); //굉장히 중요함 "변수","값" 을 말한다
		//request.setAttribute("aaa","111"); //aaa 변수에 111이라는 값이 담김

		String page="/main/main.jsp";
		RequestDispatcher rd= request.getRequestDispatcher(page);
		rd.forward(request, response);
				
		
	}
}
