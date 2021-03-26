<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<%@include file = "../include/inc_header.jsp"%>
<!DOCTYPE html>

<form name = "listForm">
<table border = "0" align = "center" width = "80%">
		<tr>
			<td><h2>설문조사 목록</h2></td>
		</tr>

		<tr>
			<td style = "padding: 10px 0px 5px;">전체 ${totalRecord }건입니다.</td>
		</tr>
		
		<tr>
			<td style = "padding: 0 0 20px 0;">
			
		span_list_size:<span id="span_list_size" style="display: ;">${list.size() }</span><br>	
		span_answer_total : <span id="span_answer_total" style="display: ;"></span> <br>  
		<c:forEach var = "dto" items = "${list }">
		<a named="a_${jj }"></a>  
		q_${jj } : <span id="q_${jj }" style="display: ;">${dto.no }</span><br> 
		span_answer_${jj } : <span id = "span_answer_${jj }" style = "display:;"></span><br>
		
		<table border = "1" align = "center" style = "width:100%;" >
		<tr>
			<td>
			 Q) ${dto.question }
			 ${k } 
			</td>
		</tr>
		<tr>
			<td>
				<a href = "#a_${jj} " onclick="check_answer_2('${jj}','1');"><font style = "font_family:'MS Gothic'"><span id = "mun1${jj }">①</span></font></a>
				<a href = "#a_${jj} " onclick = "check_answer_2(${jj},'1');">${dto.ans1 }</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href = "#a_${jj} " onclick="check_answer_2('${jj}','2');"><font style = "font_family:'MS Gothic'"><span id = "mun2${jj }">②</span></font></a>
				<a href = "#a_${jj} " onclick = "check_answer_2(${jj},'2');">${dto.ans2 }</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href = "#a_${jj} " onclick="check_answer_2('${jj}','3');"><font style = "font_family:'MS Gothic'"><span id = "mun3${jj }">③</span></font></a>
				<a href = "#a_${jj} " onclick = "check_answer_2(${jj},'3');">${dto.ans3 }</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href = "#a_${jj} " onclick="check_answer_2('${jj}','4');"><font style = "font_family:'MS Gothic'"><span id = "mun4${jj }">④</span></font></a>
				<a href = "#a_${jj} " onclick = "check_answer_2(${jj},'4');">${dto.ans4 }</a>
			</td>
		</tr>
		<tr>
			<td>상태 : ${dto.status }</td>
		</tr> 
		<tr>
			<td>기간 : ${dto.start_date } ~ ${dto.last_date }</td>
		</tr>
			<c:set var = "jj" value = "${jj = jj - 1 }"></c:set>
	</table>

		</c:forEach>
			
		</td>
		</tr>
		<tr>
		<td colspan = "7" height = "50" align = "center">
		<a href = "#" onclick = "suntaek_page('1');">[첫페이지]</a>
		&nbsp;&nbsp;
		
		<c:if test = "${startPage > blockSize }">
		<a href = "#" onclick = "suntaek_page('${startPage - blockSize}');">[이전 10개]</a>
		</c:if>
		<c:if test = "${startPage <= blockSize }">
		[이전 10개]	
		</c:if>
		&nbsp;
		<c:forEach var = "i" begin = "${startPage }" end = "${lastPage }" step = "1">
		<c:if test = "${i == pageNumber }">
		[${i }]
		</c:if>
		<c:if test = "${i != pageNumber }">
		<a href = "#" onclick = "suntaek_page('${i}');">${i }</a>
		</c:if>
		</c:forEach>
		&nbsp;
		<c:if test = "${lastPage < totalPage }">
		<a href = "#" onclick = "suntaek_page('${startPage + blockSize}');">[다음 10개]</a>
		</c:if>
		<c:if test = "${lastPage >= totalPage }">
		[다음 10개]
		</c:if>
		&nbsp;&nbsp;
		<a href = "#" onclick = "suntaek_page('${totalPage}');">[끝페이지]</a>
		</td>
		</tr>
		<tr>
		<td colspan = "7" height = "50" align = "right">

		<button type = "button" onclick = "GoChuga();">등록하기</button>
		<button type = "button" onclick = "GoSaveProc();">문제 저장하기</button>
		</td>
		</tr>
		</table>
		
		</form>