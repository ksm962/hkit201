<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %> 
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<body>
<form name="DirForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h1>한줄메모장</h1></td>
		</tr>
		
		<tr>
			<td width="150">이름</td>
			<td><input type="text" name="name" id ="name" value=""></td>
		</tr>
		
		<tr>
			<td>메모</td>
			<td><input type="text" name="memo" id="content" value=""></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center" style="height:50px;">
			<button type="button" id="btnChuga">메모</button></td>
		</tr>
	</table>
</form>


<script>
$(document).ready(function(){
	$("#name").focus();
	
	$("#btnChuga").click(function(){
		if (confirm('가입하시겠습니까?')){
			suntaek_proc('memoProc','');
		}
	});
});
</script>		
		
		
</body>
</html>