<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../include/inc_header.jsp"%> 
        ${list.size()}의 레코드가 있습니다.


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>목록</h1>
<button type="button" onclick="GoPage('guestbook_writer','','')">가입하기</button>
	
<table border="0" width="100%" height="100px">
<c:forEach var="dto" items="${list }">
<tr>
	<td>
	<table border="1" width="100%">
		<tr>
			<td>이름</td>
			<td>${dto.no}</td>
			<td>날짜</td>
			<td>${dto.regi_date }</td>
		</tr>
		<tr>	
			<td>이메일</td>
			<td colspan="3">${dto.email }</td>
		</tr>
		<tr>	
			<td colspan="4">${dto.content }</td>

		</tr>
		</table>
	</td>
</tr>		
</c:forEach>



 	<c:if test="${totalRecord > 0 }">		
 	<tr>
 		<td colspan="15" height="50" align="center">
 			<a href="#" onclick="GoPage('guestbook_list','1','');">[첫페이지]</a>	  
 				&nbsp;&nbsp;  
 				
 			<c:if test="${startPage > blockSize }">	 
 				 <a href="#" onclick="GoPage('guestbook_list','${startPage - blockSize}','');">[이전 10개]</a>
 			</c:if>
 			<c:if test="${startPage <= blockSize }">	 
 				[이전 10개]
 			</c:if>
 				&nbsp;
 				
 				<c:forEach var="i" begin="${startPage }" end="${lastPage }" step="1">
 					<c:if test="${i == pageNumber }">
 						[${i }]
 					</c:if>
 					<c:if test="${i != pageNumber }">
 						<a href="#" onclick="GoPage('guestbook_list','${i}','');">${i }</a>
 					</c:if>
 				</c:forEach>
 					&nbsp;
 					
 				<c:if test="${lastPage < totalPage }">
 					<a href="#" onclick="GoPage('guestbook_list','${startPage + blockSize }','');">[다음10개]</a>	
 				</c:if>
 				<c:if test="${lastPage >= totalPage }">
 					[다음 10개]
				</c:if>
				 	&nbsp;&nbsp;
				 	
				 <a href="#" onclick="GoPage('guestbook_list','${totalPage}','');">[끝페이지]</a>			
 					
 		</td>
 	</tr>
 	  </c:if>
 	
</table>

<script>
	function GoPage(value1, value2, value3){
		if(value1 == 'guestbook_list'){
			location.href='${path}/guestbook_servlet/list.do?pageNumber='+value2;
		}else if (value1 =='guestbook_writer'){
			location.href='${path}/guestbook_servlet/writer.do';
		}
	}
</script>  




</body>
</html>