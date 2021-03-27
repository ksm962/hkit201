package controller.member;

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

import model.member.dao.MemberDAO;
import model.member.dto.MemberDTO;

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

		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		String page = "/main/main.jsp";

		String pageNumber_;
		pageNumber_ = request.getParameter("pageNumber");
		if (pageNumber_ == null || pageNumber_.trim().equals("")) {
			pageNumber_ = "1";
		}

		/*
		 * 주소를 잘못치고 들어왓을때 걸러내는법 오류가 뜨는건 소스가 보이기 떄문에 안좋다고함. 그러니 스크립트로 가림
		 * 
		 * 
		 * String pageNumber_="12a45";
		 * 
		 * 기본개념 이해 char imsi; for(int i=0; i < pageNumber_.length(); i++) { imsi =
		 * pageNumber_.charAt(i); if(imsi >='0' && imsi <='9'){ out.println(imsi +
		 * ": 숫자입니다.<br>"); }else if (imsi >='a' && imsi <='z'){
		 * out.println(imsi+": 소문자입니다.<br>"); }else if (imsi >='A' && imsi <='Z'){
		 * out.println(imsi+": 대문자입니다.<br>"); }else{ out.println(imsi+":그냥 문자입니다."); } }
		 * 함수사용 for(int i=0; i < pageNumber_.length(); i++) { imsi =
		 * pageNumber_.charAt(i);
		 * 
		 * if(Character.isDigit(imsi) ==true){ out.println(imsi + ":숫자입니다.<br>"); }else{
		 * out.println(imsi+":문자입니다.<br>"); }
		 * 
		 * }
		 */

		int pageNumber = Integer.parseUnsignedInt(pageNumber_);

		if (url.indexOf("chuga.do") != -1) {
			request.setAttribute("menu_gubun", "member_chuga");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.indexOf("chugaProc.do") != -1) {

			String id = request.getParameter("id");

			String passwd = request.getParameter("passwd");

			// 치환하는방법(passwd) 모든 페이지에 다들어가야됨 중요
			// db에 접속해서 저장하거나 찾는구간은 다 이렇게 적어야됨
			passwd = passwd.replace("<", "&lt;");
			passwd = passwd.replace(">", "&gt;");
			passwd = passwd.replace("&", "&amp;");
			passwd = passwd.replace("\"", "&quot;");
			passwd = passwd.replace("'", "&apos;");

			String passwdchk = request.getParameter("passwdchk");

			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String bornyear_ = request.getParameter("bornyear");
			String address = request.getParameter("address");
			String detailaddress = request.getParameter("detailaddress");
			String extraaddress = request.getParameter("extraaddress");
			String postcode_ = request.getParameter("postcode");
			int postcode = Integer.parseInt(postcode_);
			int bornyear = Integer.parseInt(bornyear_);

			String checked = passwd.replaceAll(" ", "");

			int check1 = checked.length();
			int check2 = passwd.length();

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();

			if (check1 != check2) {
				out.println("<script>");
				out.println("alert('비정상적 패스워드입니다.')");
				out.println("location.href='" + path + "';");
				out.println("</script>");
				return;
			}
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

			String temp = "";
			if (result > 0) {
				temp = path + "/member_servlet/login.do";

			} else {
				temp = path + "/member_servlet/chuga.do";
			}
			response.sendRedirect(temp);

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


			String temp;
			if (resultDto.getNo() == 0) {
				temp = path + "/member_servlet/login.do";

			} else {
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
				int totalRecord = dao.getTotalRecord(); // 레코드 총갯수
				int jj = totalRecord - pageSize * (pageNumber - 1);
				int startRecord = pageSize * (pageNumber - 1) + 1;
				int lastRecord = pageSize * pageNumber;

				int totalPage = 0;
				int startPage = 1;
				int lastPage = 1;

				// 방법1
				if (totalRecord > 0) {
					totalPage = totalRecord / pageSize + (totalRecord % pageSize == 0 ? 0 : 1);
					startPage = (pageNumber / blockSize - (pageNumber % blockSize != 0 ? 0 : 1)) * blockSize + 1;
				}

				/*
				 * 방법2
				 * 
				 * if(pageNumber % blockSize !=0) { startPage = (int)(pageNumber / blockSize) *
				 * blockSize + 1; }else { startPage = ((int)(pageNumber / blockSize) - 1) *
				 * blockSize+1; }
				 */

				lastPage = startPage + blockSize - 1;

				if (lastPage > totalPage) {
					lastPage = totalPage;
				}

				// 여기까지

				ArrayList<MemberDTO> list = dao.getSelectAll(startRecord, lastRecord);

				request.setAttribute("menu_gubun", "member_list"); // 물고가는것
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

				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);

			}
		} else if (url.indexOf("view.do") != -1) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);

			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getView(no);

			request.setAttribute("menu_gubun", "member_view"); // 물고가는것
			request.setAttribute("dto", dto);

			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.indexOf("modify.do") != -1) {

			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getView(no);

			request.setAttribute("menu_gubun", "member_modify"); // 물고가는것
			request.setAttribute("dto", dto);

			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.indexOf("modifyProc.do") != -1) {

			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String bornyear_ = request.getParameter("bornyear");
			int bornyear = Integer.parseInt(bornyear_);
			String address = request.getParameter("address");
			String detailaddress = request.getParameter("detailaddress");
			String extraaddress = request.getParameter("extraaddress");
			String postcode_ = request.getParameter("postcode");
			int postcode = Integer.parseInt(postcode_);

			MemberDAO dao = new MemberDAO();
			MemberDTO dto = new MemberDTO();
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

			String temp = "";
			if (result > 0) {
				temp = path + "/member_servlet/view.do?no=" + dto.getNo();

			} else {
				temp = path + "/member_servlet/list.do";
			}
			response.sendRedirect(temp);

		} else if (url.indexOf("d.do") != -1) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getView(no);

			request.setAttribute("menu_gubun", "member_d"); // 물고가는것
			request.setAttribute("dto", dto);

			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.indexOf("dProc.do") != -1) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			String passwd = request.getParameter("passwd");

			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getView(no);

			String temp = "";
			if (!passwd.equals(dto.getPasswd())) {
				temp = path + "/member_servlet/view.do?no=" + dto.getNo();

			} else {
				int result = dao.setDelete(dto);

				if (result > 0) {
					temp = path + "/member_servlet/logout.do";

				} else {
					temp = path + "/member_servlet/view.do?no=" + dto.getNo();
				}
			}
			response.sendRedirect(temp);

		} else if (url.indexOf("id_check.do") != -1) {
			String id = request.getParameter("id");

			MemberDAO dao = new MemberDAO();
			int result = dao.getIdcheck(id);

			PrintWriter out = response.getWriter();
			out.println(result);
			return;

		} else if (url.indexOf("id_check_win.do") != -1) {
			response.sendRedirect(path + "/member/id_check.jsp");

		} else if (url.indexOf("id_check_win_open_Proc.do") != -1) {
			String id = request.getParameter("id");

			MemberDAO dao = new MemberDAO();
			int result = dao.getIdcheck(id);

			request.setAttribute("result", result);
			request.setAttribute("id", id);

			RequestDispatcher rd = request.getRequestDispatcher("/member/id_check.jsp");
			rd.forward(request, response);

		}

	}
}
