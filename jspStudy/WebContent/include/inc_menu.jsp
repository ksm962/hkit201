<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>


	
	<table border="0" align="center">
		<tr>
			<td colspan="15" align="right" style="padding:5px 20px 10px;">
				<c:if test="${sessionScope.cookNo == null || sessionScope.cookNo ==0}">
			<a href="${path }/member_servlet/login.do">로그인</a>
     	</c:if>
     	
    
            <c:if test="${sessionScope.cookNo != null && sessionScope.cookNo !=0}">
    
        	    
			<a href="${path }/member_servlet/modify.do?no=${sessionScope.cookNo }">[회원정보 수정]</a>
			<a href="${path }/member_servlet/d.do?no=${sessionScope.cookNo }">[회원 탈퇴]</a>
			<a href="${path }/member_servlet/logout.do">[로그아웃]</a>
            </c:if>
			 
			</td>
		</tr>
		<tr>
			
			<td style="padding:0px 20px;" id="home"><a href="${path }">HOME</a></td>
			<td style="padding:0px 20px;" id="member">
			
			<a href="${path}/member_servlet/list.do">회원관리</a></td>
			
			<td style="padding:0px 20px;" id="memo"><a href="${path }/memo_servlet/memolist.do">메모장</a></td>
			<td style="padding:0px 20px;" id="guestbook"><a href="${path}/guestbook_servlet/list.do">방명록</a></td>
			<td style="padding:0px 20px;" id="survey"><a href="${path}/survey_servlet/index.do">설문조사</a></td>
			<td style="padding:0px 20px;" id="survey3"><a href="${path}/survey_servlet/index.do?menuMove=aa">설문조사(문제은행)</a></td>
			<td style="padding:0px 20px;" id="survey2"><a href="${path}/survey2_servlet/list.do">설문조사2</a></td>
			<td style="padding:0px 20px;" id="board"><a href="${path}/board_servlet/index.do">게시판</a></td>
			<td style="padding:0px 20px;" id="product"><a href="${path }/product_servlet/index.do">쇼핑물</a></td>
			<td style="padding:0px 20px;" id="mall"><a href="${path }/mall_servlet/index.do">mall</a></td>
			<td style="padding:0px 20px;" id="chart"><a href="${path }/chart_servlet/index.do">Chart</a></td>
			<td style="padding:0px 20px;" id="smtpEmail"><a href="${path }/email_servlet/index.do">Email</a></td>
			
			
			<td style="padding:0px 20px;"><a href="#">관리자</a></td>
		</tr>
	</table>
	<c:set var="menu_str01" value="${fn:indexOf(menu_gubun,'_')}"/>
	<c:set var="menu_str02" value="${fn:substring(menu_gubun,0,menu_str01)}"/>

	 
	${menu_gubun}/${menu_str01}/${menu_str02}
	
	<c:choose>
	<c:when test="${menu_str02 == 'index'}">
	<script>$("#home").css("background-color","silver");</script>
	</c:when>
	<c:when test="${menu_str02 == 'member'}">
	<script>$("#member").css("background-color","silver");</script>
	</c:when>
	<c:when test="${menu_str02 == 'memo'}">
	<script>$("#memo").css("background-color","silver");</script>
	</c:when>
	<c:when test="${menu_str02 == 'guestbook'}">
	<script>$("#guestbook").css("background-color","silver");</script>
	</c:when>
	<c:when test="${menu_str02 == 'survey'}">
	<script>$("#survey").css("background-color","silver");</script>
	</c:when>
	
	<c:when test="${menu_str02 == 'survey3'}">
	<script>$("#survey3").css("background-color","silver");</script>
	</c:when>
	
	<c:when test="${menu_str02 == 'survey2'}">
	<script>$("#survey2").css("background-color","silver");</script>
	</c:when>
		<c:when test="${menu_str02 == 'board'}">
	<script>$("#board").css("background-color","silver");</script>
	</c:when>
	
	<c:when test="${menu_str02 == 'product'}">
	<script>$("#product").css("background-color","silver");</script>
	</c:when>
	
	<c:when test="${menu_str02 == 'mall'}">
	<script>$("#mall").css("background-color","silver");</script>
	</c:when>
	
	<c:when test="${menu_str02 == 'chart'}">
	<script>$("#chart").css("background-color","silver");</script>
	</c:when>
	
	<c:when test="${menu_str02 == 'smtpEmail'}">
	<script>$("#smtpEmail").css("background-color","silver");</script>
	</c:when>
	
	
	
	</c:choose>
	
	
	 
	
	
	
 	
	  
	

	