<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../include/inc_header.jsp"%>    
    
    
    
${list.size()}의 레코드가 있습니다.



  <table border = "1">
  	<tr>
  		<td colspan="15"><h2>회원목록</h2></td>
  	</tr>
  	<tr>	
  		<td>ID</td>
  		<td>이름</td>
  		<td>메모</td>
  		<td>날짜</td>
  
  	</tr>
	<c:if test="${list.size() ==0 }">
		<tr>
			<td colspan="4" height="200">
			등록된 회원이 없습니다.
		</tr>
	</c:if>
  	
 	<c:forEach var="dto" items="${list }">
 	
 	<tr> 
  		<td>${dto.id}</td>
  		<td>${dto.name }</td>
  		<td>${dto.memo }</td>
  		<td>${dto.wdate }</td>
  	</tr>
 	</c:forEach>
 	
 	 	<c:if test="${totalRecord == 0 }">
 			
 	<tr>
 		<td colspan="15" height="50" align="center">
 				없습니다.
				 
 		</td>
 	</tr>
 	  </c:if>
 	   	
 	   	<c:if test="${totalRecord > 0 }">		
 	<tr>
 		<td colspan="15" height="50" align="center">
 			<a href="#" onclick="GoPage('memo_memolist','1','');">[첫페이지]</a>	  
 				&nbsp;&nbsp;  
 				
 			<c:if test="${startPage > blockSize }">	 
 				<a href="#" onclick="GoPage('memo_memolist','${startPage - blockSize}','');">[이전 10개]</a>
 			</c:if>
 			<c:if test="${startPage <= blockSize }">	 
 				
 			</c:if>
 				&nbsp;
 				
 				<c:forEach var="i" begin="${startPage }" end="${lastPage }" step="1">
 					<c:if test="${i == pageNumber }">
 						[${i }]
 					</c:if>
 					<c:if test="${i != pageNumber }">
 						<a href="#" onclick="GoPage('memo_memolist','${i}','');">${i }</a>
 					</c:if>
 				</c:forEach>
 					&nbsp;
 					
 				<c:if test="${lastPage < totalPage }">
 					<a href="#" onclick="GoPage('memo_memolist','${startPage + blockSize }','');">[다음10개]</a>	
 				</c:if>
 				<c:if test="${lastPage >= totalPage }">
 					
				</c:if>
				 	&nbsp;&nbsp;
				 	
				 <a href="#" onclick="GoPage('memo_memolist','${totalPage}','');">[끝페이지]</a>			
 					
 		</td>
 	</tr>
 	  </c:if>
 	
 	<tr>
  		<td colspan="15" height="50" align="right">
  		<button type="button" onclick="GoPage('memo_memo','','')">메모하기</button></td>
  	</tr>
 </table>	
 
 	<script>
	function GoPage(value1, value2, value3){
		if(value1 == 'memo_memolist'){
			location.href='${path}/memo_servlet/memolist.do?pageNumber='+value2;
		}else if (value1 =='memo_memo'){
			location.href='${path}/memo_servlet/memo.do';
		}
	}
</script>  
 	
 	