<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../include/inc_header.jsp"%> 
    
    
    
    
<form name="DirForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>상세보기</h2></td>
		</tr>
		
		<tr>
			<td width="150">아이디</td>
			<td>${dto.id}</td>
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
			<td>주소</td>
			<td>
			${dto.postcode}<br>
			${dto.address}<br>
			${dto.detailaddress}
			${dto.extraaddress}
			</td>
		</tr>	
		
		<tr>
			<td>생년월일</td>
			<td>${dto.bornyear}</td>
		</tr>
		<tr>
			<td colspan="2" align="center" style="height:50px;">
			<button type="button" onclick="suntaek_proc('modify','1','${dto.no}');">수정</button>
			<button type="button" onclick="suntaek_proc('d','1','${dto.no}');">삭제</button>
			</td>
		</tr>
	</table>
</form>

