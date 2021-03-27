<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
 
<body>
	 <table>
      <tr>
         <td>이름</td>
         <td><input type="text" name="name" id="name"></td>
      </tr>
      
      <tr>
         <td>국어</td>
         <td><input type="text" name="kor" id="kor"></td>
      </tr>
      
      <tr>
         <td>영어</td>
         <td><input type="text" name="eng" id="eng"></td>
      </tr>
      
      <tr>
         <td>수학</td>
         <td><input type="text" name="mat" id="mat"></td>
      </tr>
      
      <tr>
         <td>과학</td>
         <td><input type="text" name="sci" id="sci"></td>
      </tr>
      
      <tr>
         <td>역사</td>
         <td><input type="text" name="his" id="his"></td>
      </tr>
      <tr>
      	<td><button type="button" id="btnSave">확인</button></td>
      </tr>
   </table>
<hr>
name : <span id="json_name"></span><br>
kor :  <span id="json_kor"></span><br>
eng : <span id="json_eng"></span><br>
mat : <span id="json_mat"></span><br>
sci : <span id="json_sci"></span><br>
his : <span id="json_his"></span><br>
tot : <span id="json_tot"></span><br>
avg : <span id="json_avg"></span><br>
	
	
<span id="result_json"></span>
		
<script>
	$(document).ready(function(){
		$("#btnSave").click(function(){
			json01Proc();
		});
	});

	function json01Proc(){
		var param={
				"name" : $("#name").val(),
				"kor" : $("#kor").val(),
				"eng" : $("#eng").val(),
				"mat" : $("#mat").val(),
				"sci" : $("#sci").val(),
				"his" : $("#his").val()
				
		}
		$.ajax({
			type:"post",
			data:param,
			datatype:"json",
			url:"json02.jsp",
			success: function(data){
				$("#result_json").text(data);
				var json_sj = JSON.parse(data);
				
				$("#json_name").text(json_sj.name);
				$("#json_kor").text(json_sj.kor);
				$("#json_eng").text(json_sj.eng);
				$("#json_mat").text(json_sj.mat);
				$("#json_sci").text(json_sj.sci);
				$("#json_his").text(json_sj.his);
				$("#json_tot").text(json_sj.tot);
				$("#json_avg").text(json_sj.avg);
				
			}
			
		});
	}
	
	
</script>

</body>
</html>






