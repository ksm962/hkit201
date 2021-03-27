package board.controller;

import java.io.IOException

;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.dao.BoardDAO;
import board.model.dto.BoardCommentDTO;
import board.model.dto.BoardDTO;
import common.UtilBoard;



@WebServlet("/board_servlet/*")
public class boardController extends HttpServlet {
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
      //String ip6 = serverInfo[5];
      
      String temp;
                            
      temp = request.getParameter("pageNumber");
      int pageNumber = util.numberCheck(temp, 1);
       
      temp = request.getParameter("tbl");
 		
		String tbl = util.tblCheck(temp, "freeboard");
		ArrayList<String> tblStatus = util.tblStatus(tbl);
		String isUsingTable = tblStatus.get(0);
		String tableName = tblStatus.get(1);


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
      request.setAttribute("tbl", tbl);
      request.setAttribute("pageNumber", pageNumber);
      request.setAttribute("no", no);
      request.setAttribute("search_option", search_option);
      request.setAttribute("search_data", search_data);
      request.setAttribute("isUsingTable", isUsingTable);
      request.setAttribute("tableName", tableName);
      
      BoardDAO dao = new BoardDAO();
      BoardDTO dto = new BoardDTO();
      
       
      String page = "/main/main.jsp";
      
      if (url.indexOf("index.do") != -1) {
    	  
         request.setAttribute("menu_gubun", "board_index");
         RequestDispatcher rd = request.getRequestDispatcher(page);
         rd.forward(request, response);
         
      }else if (url.indexOf("chuga.do") != -1  || url.indexOf("reply.do") !=-1 ) {
    	  request.setAttribute("menu_gubun", "board_chuga");
    	  
    	  if(no > 0) { //답변글의 핵심
    		  dto = dao.getView(no);
    		  temp = "["+ dto.getWriter()+ "]님이 작성한 글입니다.\n";
				
				 temp+= dto.getContent(); 
				 temp+= "\n--------------------------\n"; 
				 temp = temp.replace("\n", "<br>");
			 dto.setContent(temp);
				 
    		 
    		  request.setAttribute("dto", dto);
    		  
    	  }
    	  
    	  page="/board/chuga.jsp";
    	  RequestDispatcher rd = request.getRequestDispatcher(page);
          rd.forward(request, response);
          
      }else if (url.indexOf("chugaProc.do") !=-1) {
    	  String writer = request.getParameter("writer");
    	  String email = request.getParameter("email");
    	  String passwd = request.getParameter("passwd");
    	  String subject = request.getParameter("subject");
    	  String content = request.getParameter("content");
    	  String noticeGubun = request.getParameter("noticeGubun");
    	  
    	  int noticeNo;
    	  
    	  if(noticeGubun == null || noticeGubun.trim().equals("") || !noticeGubun.equals("T")) {
    		  noticeNo = 0;
    	  }else {
    		  noticeNo = dao.getMaxNoticeNo(tbl)+1;
    	  }
    	   
    	  String secretGubun = request.getParameter("secretGubun");
    	  if(secretGubun == null || secretGubun.trim().equals("") || !secretGubun.equals("T")) {
    		  secretGubun = "F";
    		  
    	  }else {
    		  secretGubun = "T";
    	  }
    	  
    	  int num = dao.getMaxNum()+1;
    	  int refNo = dao.getMaxRefNo() +1; // 글 그룹을 의미 = 쿼리를 실행시켜서 가장 큰 ref 값을 가져온 후 +1을 해주면됨.
    	  int stepNo = 1;
    	  int levelNo = 1;
    	  int parentNo = 0;
    	  
    	  if(no > 0 ) {// 답변글
    		  BoardDTO dto2 = dao.getView(no);
    		  dao.setUpdateReLevel(dto2);
    		refNo = dto2.getRefNo(); // 글 그룹을 의미 = 쿼리를 실행시켜서 가장 큰 ref 값을 가져온 후 +1을 해주면됨.
        	stepNo = dto2.getStepNo() + 1;
        	levelNo = dto2.getLevelNo() + 1;
        	parentNo = dto2.getNo();
        	
			/*
			 * refNo : 부모글 refNo 번호 
			 * stepNo : 부모글 stepNo + 1 
			 * levelNo : 부모 levelNo 보다 큰 숫자들은 1씩 증가, 그리고 부모글 levelNo + 1
			 */
    	  }
    	  
    	  int hit = 0;
    	  	
    	  dto.setNo(no);
    	  dto.setNum(num);
    	  dto.setTbl(tbl);
    	  dto.setWriter(writer);
    	  dto.setSubject(subject);
    	  dto.setContent(content);
    	  dto.setEmail(email);
    	  dto.setPasswd(passwd);
    	  
    	  dto.setRefNo(refNo);
    	  dto.setStepNo(stepNo);
    	  dto.setLevelNo(levelNo);
    	  dto.setParentNo(parentNo);
    	  dto.setHit(hit);
    	  dto.setIp(ip);
    	  
    	  dto.setMemberNo(cookNo);
    	  dto.setNoticeNo(noticeNo);
    	  dto.setSecretGubun(secretGubun);
    	  
    	  int result = dao.setInsert(dto);
    	  
    	  
    	  
      }else if (url.indexOf("list.do") !=-1) {
    		//페이징 처리소스  
    		int pageSize=10; //레코드(튜플)을 10개 보여주는것
    		int blockSize=10;//10개의 이동단위가 나타남[1],[2],[3]... 

    		int totalRecord = dao.getTotalRecord(tbl, search_option, search_data); 
    		int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
    		int jj= pagerArray[0];
    		int startRecord = pagerArray[1];
    		int lastRecord = pagerArray[2];
    		int totalPage= pagerArray[3];
    		int startPage= pagerArray[4];
    		int lastPage=pagerArray[5];
    		
    		//방법1
    		if(totalRecord > 0) {
    			totalPage=totalRecord/pageSize+(totalRecord % pageSize == 0 ? 0:1);
    			startPage=(pageNumber/blockSize - (pageNumber % blockSize != 0 ? 0:1)) * blockSize + 1;
    		

    		lastPage = startPage + blockSize - 1;
    		
    		if(lastPage>totalPage) {
    		   lastPage=totalPage;
    		}
    	} 
    		
    		List<BoardDTO> list =dao.getList(startRecord,lastRecord, tbl, search_option, search_data);
    		  
    		request.setAttribute("menu_gubun", "board_list");
    		request.setAttribute("list", list); 
    		
    
    		request.setAttribute("pageSize", pageSize);
    		request.setAttribute("blockSize", blockSize);
    		request.setAttribute("totalRecord", totalRecord);
    		request.setAttribute("jj", jj);
    		
    		request.setAttribute("startRecord", startRecord);
    		request.setAttribute("lastRecord", lastRecord);
    		
    		
    		request.setAttribute("totalPage", totalPage);
    		request.setAttribute("startPage", startPage);
    		request.setAttribute("lastPage", lastPage);
    			
  
    			page="/board/list.jsp";
    		
    		RequestDispatcher rd= request.getRequestDispatcher(page);
    		rd.forward(request, response);
    	  
    	   
      }else if (url.indexOf("view.do") !=-1) {
    	  dao.setUpdatHit(no);
    	  dto = dao.getView(no);
    	   
    	  String imsiPage = "viewPage";
    	  if(dto.getSecretGubun().equals("T")){ //비밀글이면 패스워드 체크
    		  String view_passwd = util.nullCheck(request.getParameter("view_passwd"));
    		  if(dto.getPasswd().equals(view_passwd) && !dto.getPasswd().equals("")) {
    			  
    		  }else {
    			  imsiPage = "viewPasswdPage";
    		  }
    	  }
    	  
    	  request.setAttribute("menu_gubun", "board_view");
    	  request.setAttribute("dto", dto);
    	  request.setAttribute("imsiPage", imsiPage);
    	  
    	  page = "/board/view.jsp";
    	  RequestDispatcher rd= request.getRequestDispatcher(page);
  		  rd.forward(request, response);
      }else if (url.indexOf("sujung.do") !=-1) {
    	  request.setAttribute("menu_gubun", "board_sujung");
    	  
    	  dto = dao.getView(no);
    	  request.setAttribute("dto", dto);
    	  
    	  	page="/board/sujung.jsp";
    		
    		RequestDispatcher rd= request.getRequestDispatcher(page);
    		rd.forward(request, response);
      
      }else if (url.indexOf("sujungProc.do") !=-1) {
    	  
    	  String writer = request.getParameter("writer");
    	  String email = request.getParameter("email");
    	  String passwd = request.getParameter("passwd");
    	  String subject = request.getParameter("subject");
    	  String content = request.getParameter("content");
    	  String noticeGubun = request.getParameter("noticeGubun");

    	  	dto = dao.getView(no);
    	 String dbPasswd = dto.getPasswd();
   	  response.setContentType("text/html; charset=utf-8");
    	  PrintWriter out = response.getWriter();
    	  
    	  if(!passwd.equals(dbPasswd)) {
    		  out.println("<script>");
    		  out.println("alert('비밀번호가 다릅니다');");
    		  out.println("GoPage('view','"+no+"');");
    		  out.println("</script>");
    		  
    	  }else {
	    	  
	    	  int noticeNo;
	    	  
	    	  if(noticeGubun == null || noticeGubun.trim().equals("") || !noticeGubun.equals("T")) {
	    		  noticeNo = 0;
	    	  }else {
	    		  noticeNo = dao.getMaxNoticeNo(tbl)+1;
	    	  }
	    	   
	    	  String secretGubun = request.getParameter("secretGubun");
	    	  if(secretGubun == null || secretGubun.trim().equals("") || !secretGubun.equals("T")) {
	    		  secretGubun = "F";
	    		  
	    	  }else {
	    		  secretGubun = "T";
	    	  }
	    	  
	    	  dto.setNo(no);
	    	  dto.setWriter(writer);
	    	  dto.setSubject(subject);
	    	  dto.setContent(content);
	    	  dto.setEmail(email);
	    	  dto.setPasswd(passwd);
	    	  dto.setMemberNo(cookNo);
	    	  dto.setNoticeNo(noticeNo);
	    	  dto.setSecretGubun(secretGubun);
	    	  
	    	  int result = dao.setUpdate(dto);
	    	  
	   
	    	  
	    	  out.println("<script>");
    		  out.println("alert('성공');");
    		  out.println("GoPage('view','"+no+"');");
    		  out.println("</script>");
	    	  
	    	  
   	  	}
    	  out.flush();
    	  out.close();
    	  
      }else if (url.indexOf("sakje.do") !=-1) {
    	  //request.setAttribute("menu_gubun", "board_sakje");
    	  dto = dao.getView(no);
    	  request.setAttribute("dto", dto);
    	  
    	  	page="/board/sakje.jsp";
    		
    		RequestDispatcher rd= request.getRequestDispatcher(page);
    		rd.forward(request, response);
    	  
    	  
      }else if (url.indexOf("sakjeProc.do") !=-1) {
    
			String passwd = request.getParameter("passwd");

		
			 dto = dao.getView(no);

			String a = "";
			if (!passwd.equals(dto.getPasswd())) {
				temp = path + "/board_servlet/view.do?no=" + dto.getNo();

			} else {
				int result = dao.setDelete(dto);

				if (result > 0) {
					temp = path + "/board_servlet/list.do";

				} else {
					temp = path + "/board_servlet/view.do?no=" + dto.getNo();
				}
			}
			response.sendRedirect(temp);
    	  
    	  
      }else if (url.indexOf("commentList.do") !=-1) {
    	  
    	  temp =request.getParameter("commentPageNumber");
    	  int commentPageNumber = util.numberCheck(temp, 1);
    	  
    	int pageSize=5; //레코드(튜플)을 10개 보여주는것
  		int blockSize=10;//10개의 이동단위가 나타남[1],[2],[3]... 

  		int totalRecord = dao.getTotalRecordComment(no); 
  		int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, commentPageNumber);
  		int jj= pagerArray[0];
  		int startRecord = pagerArray[1];
  		int lastRecord = pagerArray[2];
  		int totalPage= pagerArray[3]; 
  		int startPage= pagerArray[4];
  		int lastPage=pagerArray[5];
  		
  		//방법1
  		if(totalRecord > 0) {
  			totalPage=totalRecord/pageSize+(totalRecord % pageSize == 0 ? 0:1);
  			startPage=(pageNumber/blockSize - (pageNumber % blockSize != 0 ? 0:1)) * blockSize + 1;
  		

  		lastPage = startPage + blockSize - 1;
  		
  		if(lastPage>totalPage) {
  		   lastPage=totalPage;
  		}
  	}  
  		 
  		List<BoardCommentDTO> list =dao.getListComment(startRecord,lastRecord, no);
  		  
  		request.setAttribute("menu_gubun", "board_comment_list");
  		request.setAttribute("list", list); 
  		
  		request.setAttribute("commentPageNumber", commentPageNumber);
  		request.setAttribute("pageSize", pageSize);
  		request.setAttribute("blockSize", blockSize);
  		request.setAttribute("totalRecord", totalRecord);
  		request.setAttribute("jj", jj);
  		
  		request.setAttribute("startRecord", startRecord);
  		request.setAttribute("lastRecord", lastRecord);
  		
  		
  		request.setAttribute("totalPage", totalPage);
  		request.setAttribute("startPage", startPage);
  		request.setAttribute("lastPage", lastPage);
  			

  			page="/board/comment_list.jsp";
  		
  		RequestDispatcher rd= request.getRequestDispatcher(page);
  		rd.forward(request, response);
  	  
    	  
      }else if (url.indexOf("commentProc.do") !=-1) {
    	  
    	  String writer =request.getParameter("writer");
    	  String passwd =request.getParameter("passwd");
    	  String content =request.getParameter("content");
    	  String no_ =request.getParameter("no");
    	  

    	  BoardCommentDTO commentdto = new BoardCommentDTO();
    	  
    	  commentdto.setBoard_no(no);
    	  commentdto.setWriter(writer);
    	  commentdto.setContent(content);
    	  commentdto.setPasswd(passwd);
    	  commentdto.setMemberNo(cookNo);
    	  commentdto.setIp(ip);
    	    
    	  int result = dao.setInsertComment(commentdto);
    	  
      } else if(url.indexOf("commentSakjeProc") !=-1) {
    	  System.out.println("댓글삭제들어옴");
    	  
    	  String comment_no_ = request.getParameter("comment_no");
    	  int comment_no = Integer.parseInt(comment_no_);
    	
    	  
    	  BoardCommentDTO commentdto = new BoardCommentDTO();
    	  commentdto.setComment_no(comment_no);
    	  
    	  
    	  int result = dao.setCommentdelete(commentdto);
    	  
      } else if(url.indexOf("commentmodifyProc") !=-1) {
    	  
    	  String comment_no_ = request.getParameter("comment_no");
    	  int comment_no = Integer.parseInt(comment_no_);
    	  String writer =request.getParameter("writer");
    	  String passwd =request.getParameter("passwd");
    	  String content =request.getParameter("content");
    	  String no_ =request.getParameter("no");
    	  

    	  BoardCommentDTO commentdto = new BoardCommentDTO();
    	  
    	  commentdto.setComment_no(comment_no);
    	  commentdto.setBoard_no(no);
    	  commentdto.setWriter(writer);
    	  commentdto.setContent(content);
    	  commentdto.setPasswd(passwd);
    	  commentdto.setMemberNo(cookNo);
    	  commentdto.setIp(ip);
    	  
    	  int result = dao.setUpdateComment(commentdto);
    	  
      }
   } 
   
}
