<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<%@ include file="../include/inc_header.jsp" %>        
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%-- <%@ include file="../include/inc_sessionCheck.jsp" %>     --%>


<h2>한줄 메모장</h2>

<form name ="memoWriteForm">

   <table border="1" width="80%" align="center">
      <tr>
         <td>이름 : </td>
         <td><input type="text" name="name" id="name"></td>
      </tr>
      
      <tr>
         <td>메모 : </td>
         <td>
            <input type="text" name="memo" id="content">
            &nbsp;&nbsp;&nbsp;
    		<span id="spanView2" style="display: none;">
            <button type="button" id="btnChuga">확인</button>
             </span>	
            <span id="spanView" style="display: none;">
             <button type="button" id="btnmodfiy">수정</button>
            </span>	
         </td>
      </tr>
   </table>
</form>
   <table border="1" width="80%" align="center">
      <tr>
         <th>ID</th>
         <th>제목</th>
         <th>메모</th>
         <th style="width: 100px">날짜</th>
         <th>여풀때기</th>
      </tr>
   <c:if test="${list.size() == 0}">
      <tr>
         <td colspan ="7" height="200">
            등록된 메모가 없습니다.
         </td>
      </tr>
   </c:if>
   
   <c:forEach var="dto" items="${list}">
      <tr>
         <td>${dto.id}</td>
         <td>${dto.name}</td>
         <td>${dto.memo}</td>
         <td>${fn:substring(dto.wdate,0,19)}</td>
         

         <td style= "align :center;">
            <a href="#" onclick="aaa('${dto.id}', '${dto.name} ', '${dto.memo }')">
               [수정]
            </a>
            &nbsp;&nbsp;&nbsp;
           <button type="button" onclick="suntaek_proc('sakjeProc', '1','${dto.id}')">삭제하기</button>
         </td>
      </tr>
   </c:forEach>
	<c:if test ="${totalRecord > 0}">
		<tr>
			<td colspan="7" height="50" align="center">
				<c:set var = "tempGubun" value = "memolist"></c:set>
			<a href="#" onclick="suntaek_proc('${tempGubun}','1','');">[첫페이지]</a>
			&nbsp;&nbsp;
    		<c:if test="${startPage >blockSize }">
         		<a href="#" onclick="suntaek_proc('${tempGubun}','${startPage-blockSize}');">[이전 10개]</a>         
	 		</c:if>
		     
			<c:if test="${startPage <=blockSize }">
				[이전 10개]         
       		</c:if>
      		&nbsp;
		        
        	<c:forEach var="i" begin="${startPage }" end="${lastPage }" step="1">
         		<c:if test="${i == pageNumber }">
   					[${i }]  
	           	</c:if>
	           	<c:if test="${i != pageNumber }">
	           		<a href="#" onclick="suntaek_proc('${tempGubun}','${i}','');">${i }</a>
	           	</c:if>
       		</c:forEach>
		          
			&nbsp; 
         	<c:if test="${lastPage < totalPage }">
         		<a href="#" onclick="suntaek_proc('${tempGubun}','${startPage + blockSize}','');">[다음 10개]</a>
           	</c:if>
			<c:if test="${lastPage >= totalPage }">
				[다음 10개]
           	</c:if>
       		 &nbsp;&nbsp;
			<a href="#" onclick="suntaek_proc('${tempGubun}','${totalPage}','');">[끝페이지]</a>         
			</td>
		</tr>	
   </c:if>
   
   <c:if test ="${totalRecord == 0}">
      <tr>
         <td colspan="100" height="50" align="right">
            등록된 메모가 없습니다.
         </td>
      </tr>
   </c:if>
   </table>
<script>
$(document).ready(function() {
	$("#spanView").hide();
	$("#spanView2").show();
	$("#memo").focus();
   
   $("#btnChuga").click(function() {
      if(confirm('등록하시겠습니까?')){
         suntaek_proc('memoProc', '1', '');
      }
   });
   
   $("#btnmodfiy").click(function() {
	      if(confirm('수정하시겠습니까?')){
	         suntaek_proc('modfiyProc', '1', $("#span_no").text());
	      }
	   });

});


function aaa(value1, value2, value3) {
   $("#span_no").text(value1);
   $("#name").val(value2);
   $("#content").val(value3);
   $("#spanView").show();
   $("#spanView2").hide();
}

</script>