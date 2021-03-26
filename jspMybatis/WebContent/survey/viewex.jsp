<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<span id="span_answer"></span>
    	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>상세보기</h2></td>
		</tr> 
		
		<tr>
			<td width="150">Q)${dto.question}</td>
		</tr>
		
		
		<tr>
			<td><a href="#" onclick="check_answer('1');"><font style="font-family:'Ms Gothic';"><span id="mun1">①</span></font></a>
			<a href="#" onclick="check_answer('1');">${dto.ans1}</a></td>				
		</tr>
		
		
		<tr>
			<td><a href="#" onclick="check_answer('2');"><font style="font-family:'Ms Gothic';"><span id="mun2">②</span></font></a>
			<a href="#" onclick="check_answer('2');">${dto.ans2}</a></td>				
		</tr>
		
		
		<tr>
			<td><a href="#" onclick="check_answer('3');"><font style="font-family:'Ms Gothic';"><span id="mun3">③</span></font></a>
			<a href="#" onclick="check_answer('3');">${dto.ans3}</a></td>				
		</tr>
		
		
		<tr>
			<td><a href="#" onclick="check_answer('4');"><font style="font-family:'Ms Gothic';"><span id="mun4">④</span></font></a>
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
		</table>
		<tr>
			<td colspan="7" height="50" align="right">
			<button type="button" onclick="GoViewProc();"> 설문조사 저장하기</button>
			<button type="button" onclick="GoList();">목록으로 가기</button>
			<button type="button" onclick="Gomodfiy();">수정하기</button>
		</tr>
		
		
		
		