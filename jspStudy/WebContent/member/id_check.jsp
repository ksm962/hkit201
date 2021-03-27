<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ include file="../include/inc_header.jsp"%>
  
    
<form name="DirForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>아이디찾기</h2></td>
		</tr>
		
		<tr>
			<td width="150">아이디</td>
			<td><input type="text" name="id" id="id" value="${id }">
			<c:if test="${check <= 0  }">
				<div style="color:blue">사용할수 있는 아이디입니다.</div>
			</c:if>
			<c:if test="${check > 0  }">
			<div style="color:red">불가능한 아이디입니다.</div>
			</c:if> 
			<input type="hidden" name="use_id" id="use_id" value="${id} ">		
				<br>
				<label id="label_id"></label>
			</td>
		</tr>
		
		
		<tr>
			<td colspan="2" align="center" style="height:50px;">
			<button type="button" onclick="search();">검색</button>
			<button type="button" onclick="save();">적용</button>
			</td>
		</tr>
	</table>
</form>

<script>
	function search(){
	DirForm.method="post";
	DirForm.action="${path}/member_servlet/id_check_win_open_Proc.do";
	DirForm.submit();
	}
	
	function save(){
		var id= document.getElementById("use_id").value;
		opener.document.getElementById("id").value=id;
		window.close();
	}
	
</script>


