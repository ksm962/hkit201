<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%> 
    
${list.size()}의 레코드가 있습니다.
 
  
<table border = "1">
  	<tr>
  		<td colspan="15"><h2>회원목록</h2></td>
  	</tr>
  	<tr>	
  		<td>일련번호</td>
  		<td>아이디</td>
  		<td>패스워드</td>
  		<td>이름</td>
  		<td>성별</td>
  		<td>생년월일</td>
  		<td>가입일</td>
  		<td>우편번호</td> 
  		<td>주소</td>
  		<td>상세주소</td>
  		<td>참고항목</td>
  	</tr>
	<c:if test="${list.size() ==0 }">
		<tr>
			<td colspan="15" height="200">
			등록된 회원이 없습니다.
		</tr>
	</c:if>
  	
 	<c:forEach var="dto" items="${list }">
 	
 	<tr> 
  		<td>${dto.no}</td>
  		<td><a href="#" onclick="GoPage('member_view','','${dto.no}')">${dto.id }</a></td>
  		<td>${dto.passwd }</td>
  		<td>${dto.name }</td>
  		<td>${dto.gender }</td>
  		<td>${dto.bornyear }</td>
  		<td>${dto.regiDate }</td>
  		<td>${dto.postcode }</td>
  		<td>${dto.address }</td>
  		<td>${dto.detailaddress }</td>
  		<td>${dto.extraaddress }</td>
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
 			<a href="#" onclick="GoPage('member_list','1','');">[첫페이지]</a>	  
 				&nbsp;&nbsp;  
 				
 			<c:if test="${startPage > blockSize }">	 
 				 <a href="#" onclick="GoPage('member_list','${startPage - blockSize}','');">[이전 10개]</a>
 			</c:if>
 			<c:if test="${startPage <= blockSize }">	 
 				[이전 10개]
 			</c:if>
 				&nbsp;
 				
 				<c:forEach var="i" begin="${startPage }" end="${lastPage }" step="1">
 					<c:if test="${i == pageNumber }">
 						[${i }]
 					</c:if>
 					<c:if test="${i != pageNumber }">
 						<a href="#" onclick="GoPage('member_list','${i}','');">${i }</a>
 					</c:if>
 				</c:forEach>
 					&nbsp;
 					
 				<c:if test="${lastPage < totalPage }">
 					<a href="#" onclick="GoPage('member_list','${startPage + blockSize }','');">[다음10개]</a>	
 				</c:if>
 				<c:if test="${lastPage >= totalPage }">
 					[다음 10개]
				</c:if>
				 	&nbsp;&nbsp;
				 	
				 <a href="#" onclick="GoPage('member_list','${totalPage}','');">[끝페이지]</a>			
 					
 		</td>
 	</tr>
 	  </c:if>
 	
 	  
 	  
  	<tr>
  		<td colspan="15" height="50" align="right">
  		<button type="button" onclick="GoPage('member_chuga','','')">가입하기</button></td>
  	</tr>
  </table>
<script>
	function GoPage(value1, value2, value3){
		if(value1 == 'member_list'){
			location.href='${path}/member_servlet/list.do?pageNumber='+value2;
		}else if (value1 =='member_chuga'){
			location.href='${path}/member_servlet/chuga.do';
		}else if (value1 =='member_view'){
			location.href='${path}/member_servlet/view.do?pageNumber='+value2+'&no='+value3;	
		}

	}
</script>  
    



  		
  	
  	
