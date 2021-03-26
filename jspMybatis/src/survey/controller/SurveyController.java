package survey.controller;

import java.io.IOException;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Util;
import survey.model.dao.SurveyAnswerDAO;
import survey.model.dao.SurveyDAO;
import survey.model.dto.SurveyAnswerDTO;
import survey.model.dto.SurveyDTO;


@WebServlet("/survey_servlet/*") //다른 컨트롤러와 중복되면 안된다.
public class SurveyController extends HttpServlet {
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
		
		
		String search_option = request.getParameter("search_option");
		String search_data= request.getParameter("search_data");
		String search_date_s= request.getParameter("search_date_s");
		String search_date_e= request.getParameter("search_date_e");
		String search_date_check=request.getParameter("search_date_check");
				
		
		String[] searchArray= util.searchCheck(search_option,search_data,search_date_s,search_date_e,search_date_check);
		search_option=searchArray[0];
		search_data=searchArray[1];
		search_date_s=searchArray[2];
		search_date_e=searchArray[3];
		search_date_check=searchArray[4];
		
	
		
		request.setAttribute("naljaMap", naljaMap);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("list_gubun", list_gubun);
		request.setAttribute("search_option", search_option);
		request.setAttribute("search_data", search_data);
		request.setAttribute("search_date_s", search_date_s);
		request.setAttribute("search_date_e", search_date_e);
		request.setAttribute("search_date_check", search_date_check);
				
			
		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		 
		SurveyDAO dao = new SurveyDAO();
		SurveyDTO dto = new SurveyDTO();
		
		String page = "/main/main.jsp";
	
		

		if(url.indexOf("index.do") != -1) {
			String menuMove = request.getParameter("menuMove");
			
			if(menuMove == null || menuMove.equals("")) {
				request.setAttribute("menu_gubun", "survey_index");
			}else {
				request.setAttribute("menu_gubun", "survey3_index");
				request.setAttribute("menuMove", menuMove);
			}
			
			
			RequestDispatcher rd= request.getRequestDispatcher(page);
			rd.forward(request, response);
	
		}else if(url.indexOf("chuga.do")!= -1) {
			page="/survey/chuga.jsp";
			request.setAttribute("menu_gubun", "survey_chuga");
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
	
			/*
			System.out.println("1"+question);
			System.out.println("2"+ans1);
			System.out.println("3"+ans2);
			System.out.println("4"+ans3);
			System.out.println("5"+ans4);
			System.out.println("6"+status);
			System.out.println("7"+syear);
			System.out.println("8"+smonth);
			System.out.println("9"+sday);
			System.out.println("9"+lyear);
			System.out.println("9"+lmonth);
			System.out.println("9"+lday);
			
			*/
			
			String start_date_="";
			start_date_+=syear+"-"+smonth+"-"+sday;
			start_date_+=" 00:00:00.0";
			
			java.sql.Timestamp start_date=	java.sql.Timestamp.valueOf(start_date_);
			
			String last_date_="";
			last_date_+=lyear+"-"+lmonth+"-"+lday;
			last_date_+=" 23:59:59.9";
			
			java.sql.Timestamp last_date=java.sql.Timestamp.valueOf(last_date_);
			
			dto.setQuestion(question);
			dto.setAns1(ans1);
			dto.setAns2(ans2);
			dto.setAns3(ans3);
			dto.setAns4(ans4);
			dto.setStatus(status);
			dto.setStart_date(start_date);
			dto.setLast_date(last_date);
	
			int result=dao.setInsert(dto);
	
	
}else if(url.indexOf("list.do")!= -1 || url.indexOf("list2.do") != -1) {
	//페이징 처리소스  
	int pageSize=10; //레코드(튜플)을 10개 보여주는것
	if( url.indexOf("list2.do") != -1) {
		 pageSize=50;
	}
	int blockSize=10;//10개의 이동단위가 나타남[1],[2],[3]... 

	int totalRecord = dao.getTotalRecord(list_gubun, search_option, search_data,search_date_s,search_date_e,search_date_check); 
	
	int jj = totalRecord-pageSize*(pageNumber-1);
	
	int startRecord = pageSize*(pageNumber-1)+1;
	int lastRecord = pageSize*pageNumber;
	 
	int totalPage = 0;
	int startPage = 1; 
	int lastPage = 1; 
	
	//방법1
	if(totalRecord > 0) {
		totalPage=totalRecord/pageSize+(totalRecord % pageSize == 0 ? 0:1);
		startPage=(pageNumber/blockSize - (pageNumber % blockSize != 0 ? 0:1)) * blockSize + 1;
	

	lastPage = startPage + blockSize - 1;
	
	if(lastPage>totalPage) {
	   lastPage=totalPage;
	}
}
	
	List<SurveyDTO> list =dao.getList(startRecord,lastRecord,list_gubun,search_option,search_data,search_date_s,search_date_e,search_date_check);
	
	if(url.indexOf("list.do") !=-1) {
		request.setAttribute("menu_gubun", "survey_list");
	}else { 
		request.setAttribute("menu_gubun", "survey_list2");
	}
	
	
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
		
	if(url.indexOf("list.do") !=-1) {
		page="/survey/list.jsp";
	}else { 
		page="/survey/list2.jsp";
	}
	
	RequestDispatcher rd= request.getRequestDispatcher(page);
	rd.forward(request, response);
	
	
	
		}else if(url.indexOf("viewex.do")!= -1) {
			
	
			
			dto = dao.getView(no);
			
			request.setAttribute("menu_gubun", "survey_view"); //물고가는것
			request.setAttribute("dto", dto); 
			
			page="/survey/viewex.jsp";
			RequestDispatcher rd= request.getRequestDispatcher(page);
			rd.forward(request, response);

		}else if(url.indexOf("viewProc.do")!= -1) {
			String answer_=request.getParameter("answer");
			int answer=Integer.parseInt(answer_);
			
			SurveyAnswerDTO answerDto = new SurveyAnswerDTO();
			answerDto.setNo(no);
			answerDto.setAnswer(answer);
		   
			SurveyAnswerDAO dao2 = new SurveyAnswerDAO();
			int result = dao2.setInsertAnswer(answerDto);
			
		}else if(url.indexOf("saveProc.do")!= -1) {
			
			String answer_total = request.getParameter("answer_total");
		
			String[] answer_totalArr = answer_total.split("[|]");

			
			for(int i=0; i<answer_totalArr.length; i++) {
				String[] imsiArr = answer_totalArr[i].split(":");
				int tempNo = Integer.parseInt(imsiArr[0]);
				int tempAnswer = Integer.parseInt(imsiArr[1]);
				
		
				
				SurveyAnswerDTO answerDto = new SurveyAnswerDTO();
				answerDto.setAnswer(tempAnswer);
				answerDto.setNo(tempNo);
				SurveyAnswerDAO dao2 = new SurveyAnswerDAO();
				dao2.setInsertAnswer(answerDto);
				  
				 
				
			}
		} else if(url.indexOf("modfiy.do")!= -1) {
			dto = dao.getView(no);
			
			request.setAttribute("dto", dto); 
			
			page="/survey/modfiy.jsp";
			RequestDispatcher rd= request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
			
		} else if(url.indexOf("modfiyProc.do")!= -1) {
		
			
			
			
			
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
			
			dto.setNo(no);
			dto.setQuestion(question);
			dto.setAns1(ans1);
			dto.setAns2(ans2);
			dto.setAns3(ans3);
			dto.setAns4(ans4);
			dto.setStatus(status);
			dto.setStart_date(start_date);
			dto.setLast_date(last_date);
	
			int result=dao.setupdate(dto);
			
			
		} else if(url.indexOf("sakjeProc.do")!= -1) {
	
			
			dto.setNo(no);
			
			int result = dao.setDelete(dto);
					
		}	
	}
}