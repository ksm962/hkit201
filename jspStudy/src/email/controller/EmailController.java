package email.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.UtilBoard;
import email.model.dto.EmailDTO;
import email.service.EmailService;


@WebServlet("/email_servlet/*")
public class EmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		      doProc(request, response);
		   }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		      doProc(request, response);
		   }
		   
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	  
		UtilBoard util = new UtilBoard();
	  
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
		// String ip6 = serverInfo[5];
	  
	  
		String page = "/main/main.jsp";
	  
		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "email_index");
			
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
	  } else if (url.indexOf("chuga.do") != -1) {
		  request.setAttribute("menu_gubun", "email_chuga");
		  page = "/email/chuga.jsp";
		  RequestDispatcher rd = request.getRequestDispatcher(page);
		  rd.forward(request, response);
		  
	  } else if (url.indexOf("chugaProc.do") != -1) {
		  String fromName = request.getParameter("fromName");
		  String fromEmail = request.getParameter("fromEmail");
		  String toEmail = request.getParameter("toEmail");
		  String subject = request.getParameter("subject");
		  String content = request.getParameter("content");
		 
		  System.out.println("fromName : " + fromName);
		  System.out.println("fromEmail : " + fromEmail);
		  System.out.println("toEmail : " + toEmail);
		  System.out.println("subject : " + subject);
		  System.out.println("content : " + content);
		  
		  
		  EmailDTO dto = new EmailDTO();
		  dto.setFromName(fromName);
		  dto.setFromEmail(fromEmail);
		  dto.setToEmail(toEmail);
		  dto.setSubject(subject);
		  dto.setContent(content);
		  
		 
		  EmailService service = new EmailService();
		  try {
			  service.mailSender(dto);
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
	  
	  }
	}

}
