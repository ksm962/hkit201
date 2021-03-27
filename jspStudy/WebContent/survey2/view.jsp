<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../include/inc_header.jsp" %>
    

<form name="Form">
	<input type="text" name="answer" id="answer">
	<input type="text" name="no" id="no" value="${dto.no }">
    	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>상세보기</h2></td>
		</tr> 
			
		<tr>
			<td width="150">Q)${dto.question}</td>
		</tr>
		
		
		<tr>
			<td><a href="#" onclick="check_answer('1');" ><font style="font-family:'Ms Gothic';"><span id="mun1">①</span></font></a>
			<a href="#" onclick="check_answer('1');">${dto.ans1}</a></td>				
		</tr>
		
		
		<tr>
			<td><a href="#" onclick="check_answer('2');" ><font style="font-family:'Ms Gothic';"><span id="mun2">②</span></font></a>
			<a href="#" onclick="check_answer('2');" >${dto.ans2}</a></td>				
		</tr>
		
		
		<tr>
			<td><a href="#" onclick="check_answer('3');" ><font style="font-family:'Ms Gothic';"><span id="mun3">③</span></font></a>
			<a href="#" onclick="check_answer('3');">${dto.ans3}</a></td>				
		</tr>
		
		
		<tr>
			<td><a href="#" onclick="check_answer('4');" ><font style="font-family:'Ms Gothic';"><span id="mun4">④</span></font></a>
			<a href="#" onclick="check_answer('4');">${dto.ans4}</a></td>				
		</tr>
		
		<tr>
			<td>${dto.status}</td>				
		</tr>
		<tr>
			<td>기간 :${dto.start_date}
						&nbsp;
					${dto.last_date }
			</td>				
		</tr>
		
		<tr>
			<td colspan="7" height="50" align="right">
			<button type="button" onclick="GoViewProc();"> 설문조사 저장하기</button>
			<button type="button" onclick="list();">목록으로 가기</button>
			</td>
		</tr>
</table>
</form>		
		
<script>
	
function check_answer(value1){
	$("#answer").val(value1);
	
	
	if(value1 == '1'){
		$("#mun1").text('❶');
		$("#mun2").text('②');
		$("#mun3").text('③');
		$("#mun4").text('④');
		
	}else if(value1 == '2'){
		$("#mun1").text('①');
		$("#mun2").text('❷');
		$("#mun3").text('③');
		$("#mun4").text('④');
	}else if(value1 == '3'){
		$("#mun1").text('①');
		$("#mun2").text('②');
		$("#mun3").text('❸');
		$("#mun4").text('④');
	}else if(value1 == '4'){
		$("#mun1").text('①');
		$("#mun2").text('②');
		$("#mun3").text('③');
		$("#mun4").text('❹');	
	}
}


function GoViewProc(){
	if(confirm('등록하시겠습니까?')){
		Form.method="post";
		Form.action="${path}/survey2_servlet/viewProc.do";
		Form.submit();
	}
}

function list(){
	location.href='${path}/survey2_servlet/list.do';
	}
</script> 