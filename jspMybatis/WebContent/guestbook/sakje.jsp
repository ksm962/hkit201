<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

    
<table border="1" align="center" width="80%">
	<tr>
		<td colspan="2"><h2>삭제하기</h2></td>
	</tr>
	
	<tr>
		<td width="150">이름</td>
			<td>${dto.name }</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd" id="passwd" value=""></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<button type="button" id="btnsakje">삭제하기</button>
		</td>
	</tr>			
</table>

<script>

$(document).ready(function(){
	$("#btnsakje").click(function(){
	if(confirm('삭제하시겠습니까?')){
			suntaek_proc('sakjeProc','1','${dto.no}');
		}
	});
});
	

</script>
	
		
 