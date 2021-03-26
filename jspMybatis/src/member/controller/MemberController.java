package member.controller;

import java.io.IOException;



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
import javax.servlet.http.HttpSession;

import common.UtilBoard;
import member.model.dao.MemberDAO;
import member.model.dto.MemberDTO;
import member.util.MemberUtil;




@WebServlet("/member_servlet/*") // 다른 컨트롤러와 중복되면 안된다.
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProc(request, response);
	}

	protected void doProc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		 MemberUtil util = new MemberUtil();
	      
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

	      request.setAttribute("pageNumber", pageNumber);

	      request.setAttribute("search_option", search_option);
	      request.setAttribute("search_data", search_data);

	      String page = "/main/main.jsp";

		if (url.indexOf("index.do") != -1) {
			String menuMove = request.getParameter("menuMove");
			
			request.setAttribute("menu_gubun", "member_index");
			request.setAttribute("menuMove", menuMove);
			
					
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		}else if (url.indexOf("chuga.do") != -1) {
			request.setAttribute("menu_gubun", "member_chuga");
			
			page = "/member/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.indexOf("chugaProc.do") != -1) {
			
			
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String passwdchk = request.getParameter("passwdchk");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String bornyear_ = request.getParameter("bornyear");
			String address = request.getParameter("sample6_address");
			String detailaddress = request.getParameter("sample6_detailAddress");
			String extraaddress = request.getParameter("sample6_extraAddress");
			String postcode = request.getParameter("sample6_postcode");
		
			int bornyear = Integer.parseInt(bornyear_);

			MemberDTO dto = new MemberDTO();
			dto.setId(id); 
			dto.setPasswd(passwd);
			dto.setPasswdchk(passwdchk);
			dto.setName(name);
			dto.setGender(gender);
			dto.setBornyear(bornyear);
			dto.setPostcode(postcode);
			dto.setAddress(address);
			dto.setDetailaddress(detailaddress);
			dto.setExtraaddress(extraaddress);

			MemberDAO dao = new MemberDAO();
			int result = dao.setInsert(dto);

		} else if (url.indexOf("login.do") != -1) {
			request.setAttribute("menu_gubun", "member_login");
			
			
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.indexOf("loginProc.do") != -1) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");

			
			
			MemberDTO dto = new MemberDTO();
			dto.setId(id);
			dto.setPasswd(passwd);

			MemberDAO dao = new MemberDAO();	
			MemberDTO resultDto = dao.login(dto);
			System.out.println("resultDto = "+resultDto);

			
			if(resultDto == null) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('아이디 or 비밀번호가 다릅니다')");
				out.println("history.back();");
				out.println("</script>");
				return;
			
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("cookNo", resultDto.getNo());
				session.setAttribute("cookId", resultDto.getId());
				session.setAttribute("cookName", resultDto.getName());
				temp = path;
			}

			response.sendRedirect(temp);

		} else if (url.indexOf("logout.do") != -1) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();

			out.println("<script>");
			out.println("alert('로그아웃 되었습니다.\\n 즐거운 하루 되세요.')");
			out.println("location.href='" + path + "';");
			out.println("</script>");
			


		} else if (url.indexOf("list.do") != -1) {
			HttpSession session = request.getSession();
			session.getAttribute("cookNo");

			if (session.getAttribute("cookNo") == null) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();

				out.println("<script>");
				out.println("alert('로그인 후 이용하세요')");
				out.println("location.href='" + path + "';");
				out.println("</script>");

			} else {
				MemberDAO dao = new MemberDAO();

				// 페이징 처리소스
				int pageSize = 10; // 레코드(튜플)을 10개 보여주는것
				int blockSize = 10;// 10개의 이동단위가 나타남[1],[2],[3]...
				int totalRecord = dao.getTotalRecord(search_option, search_data); // 레코드 총갯수
				int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
	    		int jj= pagerArray[0];
	    		int startRecord = pagerArray[1];
	    		int lastRecord = pagerArray[2];
	    		int totalPage= pagerArray[3];
	    		int startPage= pagerArray[4];
	    		int lastPage=pagerArray[5];
	    		
	    		

				List<MemberDTO> list = dao.getSelectAll(startRecord, lastRecord, search_option, search_data);

				request.setAttribute("menu_gubun", "member_list"); // 물고가는것
				request.setAttribute("list", list);

			//	request.setAttribute("pageNumber", pageNumber);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("blockSize", blockSize);
				request.setAttribute("totalRecord", totalRecord);
				request.setAttribute("jj", jj);

				request.setAttribute("startRecord", startRecord);
				request.setAttribute("lastRecord", lastRecord);

				request.setAttribute("totalPage", totalPage);
				request.setAttribute("startPage", startPage);
				request.setAttribute("lastPage", lastPage);
				
				page = "/member/list.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
			
		} else if (url.indexOf("view.do") != -1) {
	
			
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getView(no);

			request.setAttribute("menu_gubun", "member_view"); // 물고가는것
			request.setAttribute("dto", dto);

			page = "../member/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.indexOf("modify.do") != -1) {

		
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getView(no);

			request.setAttribute("menu_gubun", "member_modify"); // 물고가는것
			request.setAttribute("dto", dto);

			page="../member/modify.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.indexOf("modifyProc.do") != -1) {
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getView(no);
			

			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String passwdchk = request.getParameter("passwdchk");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String bornyear_ = request.getParameter("bornyear");
			String address = request.getParameter("sample6_address");
			String detailaddress = request.getParameter("sample6_detailAddress");
			String extraaddress = request.getParameter("sample6_extraAddress");
			String postcode = request.getParameter("sample6_postcode");
			int bornyear = Integer.parseInt(bornyear_);
			response.setContentType("text/html; charset=utf-8");
	    	PrintWriter out = response.getWriter();
	    	
			if(!passwd.equals(dto.getPasswd())) {
				 out.println("<script>$('#span_passwd').text('X');</script>");
			}else {
				dto.setNo(no);
				dto.setPasswd(passwd);
				dto.setId(id);
				dto.setName(name);
				dto.setGender(gender);
				dto.setBornyear(bornyear);
				dto.setPostcode(postcode);
				dto.setAddress(address);
				dto.setDetailaddress(detailaddress);
				dto.setExtraaddress(extraaddress);

				int result = dao.setupdate(dto);
				out.println("<script>$('#span_passwd').text('O');</script>");
			}
			


		} else if (url.indexOf("d.do") != -1) {

			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getView(no);

			request.setAttribute("menu_gubun", "member_d"); // 물고가는것
			request.setAttribute("dto", dto);

			page = "../member/d.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.indexOf("dProc.do") != -1) {

			String passwd = request.getParameter("passwd");
			

			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getView(no);
		
			response.setContentType("text/html; charset=utf-8");
	    	PrintWriter out = response.getWriter();
			if (!passwd.equals(dto.getPasswd())) {
				 out.println("<script>$('#span_passwd').text('X');</script>");

			} else {
				int result = dao.setDelete(dto);
				out.println("<script>$('#span_passwd').text('O');</script>");
				response.setContentType("text/html;charset=utf-8");
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제되었습니다')");
				
				if(cookNo == dto.getNo()) {
					HttpSession session = request.getSession();
					session.invalidate();
					out.println("alert('로그아웃 되었습니다.\\n 즐거운 하루 되세요.')");
					out.println("location.href='" + path + "';");
					out.println("</script>");
				}
				
				
			}

		} else if (url.indexOf("id_check.do") != -1) {
			String id = request.getParameter("id");

			MemberDAO dao = new MemberDAO();
			int result = dao.getIdcheck(id);

			
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
			return;

		} else if (url.indexOf("id_check_win.do") != -1) {
		//	response.sendRedirect(path + "/member/id_check.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/member/id_check.jsp");
			rd.forward(request, response);

		} else if (url.indexOf("id_check_win_Proc.do") != -1) {

			String id = request.getParameter("id");

			MemberDAO dao = new MemberDAO();
			String result = dao.getIdcheckWin(id);

			
			if(result == null || result.equals("")) {
				result = id;
			} else {
				result = "";
			}

			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}

	}
}
