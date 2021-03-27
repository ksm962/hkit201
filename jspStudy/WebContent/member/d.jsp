<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

    
   <form name="DirForm">
   <input type="hidden" name="no" value="${dto.no }">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>상세보기</h2></td>
		</tr>
		
		<tr>
			<td width="150">아이디</td>
				<td><input type="hidden" name="id" value="${dto.id }">${dto.id }/${sessionScope.cookName}님 </td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd" value=""></td>
		</tr>
		
		<tr>
			<td>이름</td>
			<td>${dto.name}</td>
		</tr>
		
		
		<tr>
			<td>성별</td>
			<td>${dto.gender}
			</td>
		</tr>
		
		<tr>
			<td>생년월일</td>
			<td>${dto.bornyear}</td>
		</tr>
		<tr>
			<td colspan="2" align="center" style="height:50px;">
		
		<button type="button" onclick="join();">삭제하기</button></td>
			
		</tr>
	</table>
</form>


<script>
function join(){
	if(confirm('삭제하시겠습니까?')){
		DirForm.method="post";
		DirForm.action="${path}/member_servlet/dProc.do";
		DirForm.submit();
		
	}
}
</script>	
	