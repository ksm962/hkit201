<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../include/inc_header.jsp"%> 
    

    	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>상세보기</h2></td>
		</tr> 
		
		<tr>
			<td width="150">Q)${dto.question}</td>
		</tr>
		
		
		<tr>
			<td><input type="radio" value="1" >${dto.ans1}</td>				
		</tr>
		
		<tr>
			<td><input type="radio" value="2" >${dto.ans2}</td>				
		</tr>
		
		<tr>
			<td><input type="radio" value="3" >${dto.ans3}</td>				
		</tr>
		
		<tr>
			<td><input type="radio" value="4" >${dto.ans4}</td>				
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
		