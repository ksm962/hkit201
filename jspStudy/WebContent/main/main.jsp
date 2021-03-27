<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../include/inc_header.jsp"%>
    
    
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>

<h1>main.jsp</h1>
<table border="1" align="center">
	<tr>
		<td style="padding:20px 20px;">
			<jsp:include page="../include/inc_menu.jsp"></jsp:include>
		</td>
	</tr>
	
	<tr>
		<td align="center" style="padding:50px 50px; height:1000px;">
		
	<%-- 
		<c:if test="${menu_gubun == 'index' }" >  이프문 꼭 알기 
		<jsp:include page="./a.jsp" />
		</c:if>
		
		<c:if test="${menu_gubun != 'index' }" >
		<jsp:include page="./b.jsp" />
		</c:if>
		--%>
		
	
		
	<c:choose>

		<c:when test="${menu_gubun == 'index' }" > <%-- 다중이프문 꼭 알기 --%>
			<jsp:include page="../main/main_sub.jsp" />
		</c:when>
		 
		<c:when test="${menu_gubun == 'member_chuga' }" >
			<jsp:include page="../member/chuga.jsp" />
		
		</c:when>
		
		<c:when test="${menu_gubun == 'member_login' }" >
			<jsp:include page="../member/login.jsp" />
		
		</c:when>
			
		<c:when test="${menu_gubun == 'member_list' }" >
			<jsp:include page="../member/list.jsp" />
		</c:when>
		
		<c:when test="${menu_gubun == 'member_view' }" >
			<jsp:include page="../member/view.jsp" />
		</c:when>
		
		<c:when test="${menu_gubun == 'member_modify' }" >
			<jsp:include page="../member/modify.jsp" />
		</c:when>
		
		<c:when test="${menu_gubun == 'member_d' }" >
			<jsp:include page="../member/d.jsp" />
		</c:when>
		
		<c:when test="${menu_gubun == 'memo_memo' }" >
			<jsp:include page="../memo/memo.jsp" />
		</c:when>
		
		<c:when test="${menu_gubun == 'memo_memolist' }" >
			<jsp:include page="../memo/memolist.jsp" />
		</c:when>
		
		<c:when test="${menu_gubun == 'guestbook_writer' }" >
			<jsp:include page="../guestbook/writer.jsp" />
		</c:when>
		
		<c:when test="${menu_gubun == 'guestbook_list' }" >
			<jsp:include page="../guestbook/list.jsp" />
		</c:when>
		
		<c:when test="${menu_gubun == 'survey_index' }" >
			<jsp:include page="../survey/index.jsp" />
		</c:when>

		<c:when test="${menu_gubun == 'survey2_list' }" >
			<jsp:include page="../survey2/list.jsp" />
		</c:when>
		
		<c:when test="${menu_gubun == 'survey2_chuga' }" >
			<jsp:include page="../survey2/chuga.jsp" />
		</c:when>
		
		<c:when test="${menu_gubun == 'survey2_view' }" >
			<jsp:include page="../survey2/view.jsp" />
		</c:when>
		<c:when test="${menu_gubun == 'survey3_index' }" >
			<jsp:include page="../survey/index.jsp" />
		</c:when>

		
		
		
		<c:when test="${menu_gubun == 'board_index' }" >
			<jsp:include page="../board/index.jsp" />
		</c:when>
		
		<c:when test="${menu_gubun == 'product_index' }" >
			<jsp:include page="../shop/product/index.jsp" />
		</c:when>
		
		<c:when test="${menu_gubun == 'mall_index' }" >
			<jsp:include page="../shop/mall/index.jsp" />
		</c:when>
		
		<c:when test="${menu_gubun == 'chart_index' }" >
			<jsp:include page="../chart/index.jsp" />
		</c:when>
		
		<c:when test="${menu_gubun == 'email_index' }" >
			<jsp:include page="../email/index.jsp" />
		</c:when>
	
		<c:otherwise>

		</c:otherwise>
	</c:choose>		
		</td>
	</tr>
	
	<tr>
		<td style="padding:20px 20px;" align="center">
			<jsp:include page="../include/inc_bottom.jsp"></jsp:include>
		</td>
		
	</tr>
	

</table>

</body>
</html>