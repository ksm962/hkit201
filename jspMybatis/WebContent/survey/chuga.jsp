<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<form name="DirForm" id="DirForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>설문조사 등록</h2></td>
		</tr>
		<tr>
			<td style="align:center;">question</td>
			<td><input type="text" name="question" id="question"></td>
		</tr>
		<tr>
			<td style="algin:center;">ans1</td>
			<td><input type="text" name="ans1" id="ans1"></td>
		</tr>
		<tr>
			<td style="algin:center;">ans2</td>
			<td><input type="text" name="ans2" id="ans2"></td>
		</tr>
		<tr>
			<td style="algin:center;">ans3</td>
			<td><input type="text" name="ans3" id="ans3"></td>
		</tr>
		<tr>
			<td style="algin:center;">ans4</td>
			<td><input type="text" name="ans4" id="ans4"></td>
		</tr>
	
		<tr>
			<td style="algin:center;">status</td>
			<td><input type="radio" name="status" id="status" value="1">진행중
			<input type="radio" name="status" id="status" value="0">종료</td>
		</tr>
		
		<tr> 
		<td style="align:center;">start_date</td>
		<td>

		<select name="syear" id="syear">
	<c:forEach var="i" begin="${naljaMap['now_y']-1}" end="${naljaMap['now_y']+1}" step="1">
				<c:if test="${naljaMap['now_y']==i }">
					<option value="${i }" selected>${i }</option>
				</c:if>
				
				<c:if test="${naljaMap['now_y'] !=i}">
				<option value="${i }">${i }</option>
				</c:if>
			</c:forEach>
			</select>
			

			<select name="smonth" id="smonth">
			<c:forEach var="i" begin="1" end="12" step="1">
				
			<c:if test="${naljaMap['now_m']==i }">
					<c:if test="${i<10 }">
						<option value="0${i }" selected>0${i}</option>
					</c:if>
				</c:if>
				<c:if test="${naljaMap['now_m']==i }">
					<c:if test="${i>=10 }">
						<option value="${i }" selected>${i}</option>
					</c:if>
				</c:if>
				<c:if test="${naljaMap['now_m']!=i }">
					<c:if test="${i<10 }">
						<option value="0${i }">0${i}</option>
					</c:if>
				</c:if>
				<c:if test="${naljaMap['now_m']!=i }">
					<c:if test="${i>=10 }">
						<option value="${i }">${i}</option>
					</c:if>
				</c:if>
			</c:forEach>
			</select>
			
			
			
			<select name="sday" id="sday">
			<c:forEach var="i" begin="1" end="31" step="1">
				<c:if test="${naljaMap['now_d']==i }">
					<c:if test="${i<10 }">
						<option value="0${i }" selected>0${i }</option>
					</c:if>
				</c:if>
				<c:if test="${naljaMap['now_d']==i }">
					<c:if test="${i>=10 }">
						<option value="${i }" selected>${i }</option>
					</c:if>
				</c:if>
				<c:if test="${naljaMap['now_d'] !=i}">
				<c:if test="${i<10 }">
						<option value="0${i }">0${i }</option>
					</c:if>
				</c:if>
				<c:if test="${naljaMap['now_d'] !=i }">
					<c:if test="${i>=10 }">
						<option value="${i }" >${i }</option>
					</c:if>
				</c:if>
			</c:forEach>
			</select>
				
			
			
		
		
		
		<%-- 
			<select>
 				<c:forEach var="i" begin="1921" end="2021" step="1">
 					<option>${i }</option>
 				</c:forEach>
 			</select>
 			<select>	
 				<c:forEach var="j" begin="01" end="12" step="1">
 					<option>${j }</option>
 				</c:forEach>
 			</select>	
 			<select>
 				<c:forEach var="a" begin="01" end="31" step="1">
 					<option>${a }</option>		
 				</c:forEach>
 			</select>	 --%>

		
		</tr>
		<tr> 
		<td style="align:center;">last_date</td>
		<td>
				
		<select name="lyear" id="lyear">
	<c:forEach var="i" begin="${naljaMap['now_y']-1}" end="${naljaMap['now_y']+1}" step="1">
				<c:if test="${naljaMap['now_y']==i }">
					<option value="${i }" selected>${i }</option>
				</c:if>
				<c:if test="${naljaMap['now_y'] !=i}">
				<option value="${i }">${i }</option>
				</c:if>
			</c:forEach>
			</select>
			

			<select name="lmonth" id="lmonth">
			<c:forEach var="i" begin="1" end="12" step="1">
				
				<c:if test="${naljaMap['now_m']==i }">
					<c:if test="${i<10 }">
						<option value="0${i }" selected>0${i}</option>
					</c:if>
				</c:if>
				<c:if test="${naljaMap['now_m']==i }">
					<c:if test="${i>=10 }">
						<option value="${i }" selected>${i}</option>
					</c:if>
				</c:if>
				<c:if test="${naljaMap['now_m']!=i }">
					<c:if test="${i<10 }">
						<option value="0${i }">0${i}</option>
					</c:if>
				</c:if>
				<c:if test="${naljaMap['now_m']!=i }">
					<c:if test="${i>=10 }">
						<option value="${i }">${i}</option>
					</c:if>
				</c:if>
				</c:forEach>
			</select>
			
			<select name="lday" id="lday">
			<c:forEach var="i" begin="1" end="31" step="1">
				
				<c:if test="${naljaMap['now_d']==i }">
					<c:if test="${i<10 }">
						<option value="0${i }" selected>0${i }</option>
					</c:if>
				</c:if>
				<c:if test="${naljaMap['now_d']==i }">
					<c:if test="${i>=10 }">
						<option value="${i }" selected>${i }</option>
					</c:if>
				</c:if>
				<c:if test="${naljaMap['now_d'] !=i}">
				<c:if test="${i<10 }">
						<option value="0${i }">0${i }</option>
					</c:if>
				</c:if>
				<c:if test="${naljaMap['now_d'] !=i }">
					<c:if test="${i>=10 }">
						<option value="${i }" >${i }</option>
					</c:if>
				</c:if>
			
			</c:forEach>
			</select>
		</tr>
		<tr>
			<td colspan = "5" align="center">
				<button type="button" id="btnSave">저장하기</button>
				<button type="button" id="btnlist">목록으로</button>
			</td>
		</tr>
		
	
	<script>
			$(document).ready(function(){
				$("#btnSave").click(function(){
					GoChugaProc();
				});
			});
			
			$(document).ready(function(){
				$("#btnlist").click(function(){
					GoList();
				});
			});
			
	</script>
		
		
		
	</table>	
</form>

