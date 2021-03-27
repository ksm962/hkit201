<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ include file="../include/inc_header.jsp" %>

<table border="1">
	<tr>
		<td colspan="2"><h2>게시글수정</h2></td>
	</tr>
	<tr>
		<td style="align: center;">작성자</td>
		<td><input type="text" name="writer" id="writer" value="${dto.writer }"></td>
	</tr>
	<tr>
		<td style="align: center;">이메일</td>
		<td><input type="text" name="email" id="email" value="${dto.email }"></td>
	</tr>
	<tr>
		<td style="align: center;">비밀번호</td>
		<td><input type="text" name="passwd" id="passwd" value="${dto.passwd }"></td>
	</tr>
	<tr>
		<td style="align: center;">제목</td>
		<td><input type="text" name="subject" id="subject" value="${dto.subject }"></td>
	</tr>
	<tr>
		<td style="align: center;">내용</td>
		<td><textarea name="content" id="content" style="width:300px; height:100px;" wrap="hard">${dto.content }</textarea></td>
	</tr>
	<tr>
		<td style="align: center;">공지글</td>
		<td>
			<input type="text" name="noticeGubun" id="noticeGubun" value="${dto.noticeGubun }">
			<c:if test="${dto.noticeNo > 0 }" >
			<input type="checkbox" name="noticeGubunCheckBox" value="T" onclick="clickChk('noticeGubun');" checked>
			공지글 체크
			</c:if>
			<c:if test="${dto.noticeNo == 0 }" >
			<input type="checkbox" name="noticeGubunCheckBox" value="T" onclick="clickChk('noticeGubun');">
			공지글 체크
			</c:if>
		</td>
	</tr>
	<tr>
		<td style="align: center;">비밀글</td>
		<td>
			<input type="text" name="secretGubun" id="secretGubun" value="${dto.secretGubun }">
			<c:if test="${dto.secretGubun == 'T' }">
			<input type="checkbox" name="secretGubunCheckBox" value="T" onclick="clickChk('secretGubun');" checked>
			비밀글 체크
			</c:if>
			<c:if test="${dto.secretGubun != 'T' }">
			<input type="checkbox" name="secretGubunCheckBox" value="T" onclick="clickChk('secretGubun');">
			비밀글 체크
			</c:if>
			
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2" height="50px">
			<button type="button" id="btnSujung">수정하기</button>
			<button type="button" id="btnList">목록으로</button>
		</td>	
	</tr>
</table>
s
<script>
	$(document).ready(function(){
		$("#subject").select();
		$("#subject").focus();
		
		$("#btnSujung").click(function(){
			if(confirm('수정하시겠습니까?')){
				GoPage('sujungProc');
			}
		});
	
	$("#btnList").click(function(){
		suntaek_page('1');
	});
});

</script>