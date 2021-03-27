package controller.survey;

import java.io.IOException;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Util;
import model.survey.dao.Survey2DAO;
import model.survey.dao.SurveyAnswerDAO;
import model.survey.dao.SurveyDAO;
import model.survey.dto.SurveyAnswerDTO;
import model.survey.dto.SurveyDTO;

@WebServlet("/survey2_servlet/*") //다른 컨트롤러와 중복되면 안된다.
public class Survey2Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final SurveyAnswerDTO Dto = null;
       
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProc(request, response);
	}
protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	
	Util util = new Util();
	int[] nalja = util.getDateTime();
 
	Map<String, Integer> naljaMap=new HashMap<>();
	naljaMap.put("now_y", nalja[0]); 
	naljaMap.put("now_m", nalja[1]);
	naljaMap.put("now_d", nalja[2]);
	
	String temp;
	temp=request.getParameter("pageNumber");
	int pageNumber=util.numberCheck(temp, 1);
	
	temp=request.getParameter("no");
	int no=util.numberCheck(temp,0);
	
	temp=request.getParameter("list_gubun");
	String list_gubun=util.list_gubunCheck(temp);
	
	
	request.setAttribute("naljaMap", naljaMap);
	request.setAttribute("list_gubun", list_gubun);
	
	
	
	String path=request.getContextPath();
	String url=request.getRequestURL().toString();
	String page="/main/main.jsp";
	
	
	
	
	
	
	if(url.indexOf("list.do") != -1) {
		
		
		
		Survey2DAO dao = new Survey2DAO();
		ArrayList<SurveyDTO> list =dao.getSelectAll_();
		request.setAttribute("menu_gubun", "survey2_list");
		request.setAttribute("list", list); 
		 
		RequestDispatcher rd= request.getRequestDispatcher(page);
		rd.forward(request, response);
		
	
	}else if(url.indexOf("chuga.do")!= -1) {
		request.setAttribute("menu_gubun", "survey2_chuga");
		RequestDispatcher rd= request.getRequestDispatcher(page);
		rd.forward(request, response);
		
		
	}else if(url.indexOf("chugaProc.do")!= -1) {
			
			String question=request.getParameter("question");
			String ans1=request.getParameter("ans1");
			String ans2=request.getParameter("ans2");
			String ans3=request.getParameter("ans3");
			String ans4=request.getParameter("ans4");
			String status=request.getParameter("status");
			
			String syear=request.getParameter("syear");
			String smonth=request.getParameter("smonth");
			String sday=request.getParameter("sday");
			
			String lyear=request.getParameter("lyear");
			String lmonth=request.getParameter("lmonth");
			String lday=request.getParameter("lday");
	
			String start_date_="";
			start_date_+=syear+"-"+smonth+"-"+sday;
			start_date_+=" 00:00:00.0";
			
			java.sql.Timestamp start_date=	java.sql.Timestamp.valueOf(start_date_);
			
			String last_date_="";
			last_date_+=lyear+"-"+lmonth+"-"+lday;
			last_date_+=" 23:59:59.9";
			
			java.sql.Timestamp last_date=java.sql.Timestamp.valueOf(last_date_);
	
			Survey2DAO dao = new Survey2DAO();
			SurveyDTO dto = new SurveyDTO();
			
			dto.setQuestion(question);
			dto.setAns1(ans1);
			dto.setAns2(ans2);
			dto.setAns3(ans3);
			dto.setAns4(ans4);
			dto.setStatus(status);
			dto.setStart_date(start_date);
			dto.setLast_date(last_date);
			
			int result=dao.setInsert(dto);
			 temp = "";
			if (result > 0) {
				temp = path + "/survey2_servlet/list.do";

			} else {
				temp = path + "/survey2_servlet/chuga.do";
			}
			response.sendRedirect(temp);


}else if(url.indexOf("view.do")!= -1) {
	
	String no_=request.getParameter("no");
	 no=Integer.parseInt(no_);
	Survey2DAO dao = new Survey2DAO();
	
	SurveyDTO dto = dao.getView(no);
	
	request.setAttribute("menu_gubun", "survey2_view"); //물고가는것
	request.setAttribute("dto", dto); 
	

	RequestDispatcher rd= request.getRequestDispatcher(page);
	rd.forward(request, response);
	
}else if(url.indexOf("viewProc.do")!= -1) {
	
	String answer_=request.getParameter("answer");
	int answer=Integer.parseInt(answer_);

	SurveyAnswerDTO answerDto = new SurveyAnswerDTO();
	answerDto.setNo(no);
	answerDto.setAnswer(answer);
   
	Survey2DAO dao2 = new Survey2DAO();
	int result = dao2.setInsertAnswer(answerDto);
	
	 temp = "";
		if (result > 0) {
			temp = path + "/survey2_servlet/list.do";

		} else {
			temp = path + "/survey2_servlet/view.do";
		}
		response.sendRedirect(temp);
}

	





}
}
