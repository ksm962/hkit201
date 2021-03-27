<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/inc_header.jsp" %>    





<form name="form">
<table border="1">
<tr>
	<th colspan="15" width="50px"><input type="text" name="result" value="0"></th>
</tr>
	<tr>
		<td><input type="text" ></td>
		<td width="50px" height="50px" align="center" onclick="cal('8');">8</td>
		<td width="50px" height="50px" align="center" onclick="cal('9');">9</td>
		<td width="50px" height="50px" align="center" onclick="cal('+');">+</td>
	</tr>
	<tr>
		<td width="50px" height="50px" align="center" onclick="cal('4');">4</td>
		<td width="50px" height="50px" align="center" onclick="cal('5');">5</td>
		<td width="50px" height="50px" align="center" onclick="cal('6');">6</td>
		<td width="50px" height="50px" align="center" onclick="cal('-');">-</td>
	</tr>
	<tr>
		<td width="50px" height="50px" align="center" onclick="cal('1');">1</td>
		<td width="50px" height="50px" align="center" onclick="cal('2');">2</td>
		<td width="50px" height="50px" align="center" onclick="cal('3');">3</td>
		<td width="50px" height="50px" align="center" onclick="cal('*');">*</td>
	</tr>
	<tr>
		<td width="50px" height="50px" align="center" onclick="cal('c');">c</td>
		<td width="50px" height="50px" align="center" onclick="cal('0');">0</td>
		<td width="50px" height="50px" align="center" onclick="cal('=');">=</td>
		<td width="50px" height="50px" align="center" onclick="cal('/');">/</td>
	</tr>
</table>
</form>
		
<script>
 function cal(){
	 
 }	 
</script>		
	




