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
	<form name="form">
			<table border="1">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" id="name" value=""></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email" id="email" value=""></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="passwd" id="passwd" value=""></td>
				</tr>
				<tr>
					<td colspan="2">
					<textarea id="content" name="content" rows="4" cols="50"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<button type="button" id="btnChuga">추가</button>
				<button type="button" id="btnList">목록으로</button>
					</td>
				</tr>
			</table>
	</form> 
<script>
	$(document).ready(function(){
		$("#name").focus();

		$("#btnChuga").click(function(){
			if (confirm('추가하시겠습니까?')){
				suntaek_proc('writerProc','1','');
			}
		});
		$("#btnList").click(function(){
			suntaek_proc('list','1','')
		});
	});


</script>		
</body>
</html>
