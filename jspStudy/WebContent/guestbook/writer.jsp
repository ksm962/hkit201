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
					<td><input type="text" name="name" value=""></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email" value=""></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="text" name="passwd" value=""></td>
				</tr>
				<tr>
					<td colspan="2">
					<textarea id="content" name="content" rows="4" cols="50"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="button" value="확인" onclick="save('S');">
					<input type="button" value = "취소" onclick="save('A');"></td>
				</tr>
			</table>
	</form> 
	
<script>
function save(value1){
	if(value1 == 'S'){
		confirm('저장하시겠습니까?')
		form.method="post";
		form.action="${path}/guestbook_servlet/writerProc.do";
		form.submit();
		
	}else if(value1 == 'A'){
		
	}
}
</script>		
</body>
</html>
