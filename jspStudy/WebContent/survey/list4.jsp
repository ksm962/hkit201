<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
 
span_list_size:<span id="span_list_size" style="display: ;">${list.size() }</span><br>
span_answer_total : <span id="span_answer_total" style="display: ;"></span>   
  
    <c:forEach var="dto" items="${list }">
		 
		 <a named="a_${jj }"></a>
		 q_${jj } : <span id="q_${jj }" style="display: ;">${dto.no }</span><br>
 		span_answer_${jj }:<span id="span_answer_${jj }" style="display:;"></span><br>
 		
	 
   <table border="1" align="center" width="100%">
		<tr>
			<td colspan="2"><h2>상세보기</h2></td>
		</tr> 
		
		<tr>
			<td width="150">Q)${dto.question}
				<%-- <input type="text" name="" id=""></td>--%>
		</tr>
		
		
		<tr>
			
			<td><a href="#a_${jj }" onclick="check_answer_2('${jj}','1');"><font style="font-family:'Ms Gothic';"><span id="mun1${jj }">①</span></font></a>
			<a href="#a_${jj }" onclick="check_answer_2('${jj}','1');">${dto.ans1}</a></td>				
		</tr>
		
		
		<tr>
			<td><a href="#a_${jj }" onclick="check_answer_2('${jj}','2');"><font style="font-family:'Ms Gothic';"><span id="mun2${jj }">②</span></font></a>
			<a href="#a_${jj }" onclick="check_answer_2('${jj}','2');">${dto.ans2}</a></td>				
		</tr>
		
		
		<tr>
			<td><a href="#a_${jj }" onclick="check_answer_2('${jj}','3');"><font style="font-family:'Ms Gothic';"><span id="mun3${jj }">③</span></font></a>
			<a href="#a_${jj }" onclick="check_answer_2('${jj}','3');">${dto.ans3}</a></td>				
		</tr>
		
		
		<tr>
			<td><a href="#a_${jj }" onclick="check_answer_2('${jj}','4');"><font style="font-family:'Ms Gothic';"><span id="mun4${jj }">④</span></font></a>
			<a href="#a_${jj }" onclick="check_answer_2('${jj}','4');">${dto.ans4}</a></td>				
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
		</c:forEach>
				<button type="button" onclick="GoSaveProc();">문제저장하기</button>
		&nbsp;&nbsp;

		