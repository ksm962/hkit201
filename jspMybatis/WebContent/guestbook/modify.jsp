<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


	<form name="form">
			<table border="1">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" id="name" value="${dto.name }"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email" id="email" value="${dto.email }"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="passwd" id="passwd" value="${dto.passwd }"></td>
				</tr>
				<tr>
					<td colspan="2">
					<textarea id="content" name="content" rows="4" cols="50">${dto.content }</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<button type="button" id="btnModify">수정하기</button>
					<button type="button" id="btnList">목록으로</button>
					</td>
				</tr>
			</table>
		</form> 

<script>
$(document).ready(function(){
	$("#btnModify").click(function(){
	if(confirm('수정하시겠습니까?')){
			suntaek_proc('modifyProc','1','${dto.no}');
		}
	})
	$("#btnList").click(function(){
			suntaek_proc('list','1','');
		
	})
	
})


</script>	
	
	
	