<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%> 
    
${list.size()}의 레코드가 있습니다.<br>


 
<table border = "1">
  	<tr>
  		<td colspan="15"><h2>회원목록</h2></td>
  	</tr>
  	<tr>
  		<td colspan="15">
		<select name="search_option" id="search_option">
         <c:choose>
            <c:when test="${search_option =='id' }">
                     <option value="">-선택-</option>
                     <option value="id" selected>아이디</option>
                     <option value="name">이름</option>
                     <option value="gender">성별</option>
                     <option value="id_name_gender">아이디+이름+성별</option>
            </c:when>
            <c:when test="${search_option =='name' }">
                     <option value="">-선택-</option>
                     <option value="id">아이디</option>
                     <option value="name" selected>이름</option>
                     <option value="gender">성별</option>
                     <option value="id_name_gender">아이디+이름+성별</option>
            </c:when>
            <c:when test="${search_option =='gender' }">
                     <option value="">-선택-</option>
                     <option value="id">아이디</option>
                     <option value="name">이름</option>
                     <option value="gender" selected>성별</option>
                     <option value="id_name_gender">아이디+이름+성별</option>
            </c:when>
            <c:when test="${search_option =='id_name_gender' }">
                     <option value="">-선택-</option>
                     <option value="id">아이디</option>
                     <option value="name">이름</option>
                     <option value="gender">성별</option>
                     <option value="id_name_gender"  selected>아이디+이름+성별</option>
            </c:when>
            <c:otherwise>
                     <option value=""  selected>-선택-</option>
                     <option value="id">아이디</option>
                     <option value="name">이름</option>
                     <option value="gender">성별</option>
                     <option value="id_name_gender">아이디+이름+성별</option>
            </c:otherwise>
         </c:choose>
         </select>
  
 		<input type="text" name="search_data" id="search_data" value="${search_data }" style="width: 150px;">
		&nbsp;
		<input type="button" value="검색" onclick="search();">
		
	<script>
			function search(){
				if(confirm('검색 OK?')){
					$("#span_search_option").text($("#search_option").val());
					$("#span_search_data").text($("#search_data").val());
					suntaek_proc('list','1','');
				}
			}
	</script>
     </td>    
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
  		<td><a href="#" onclick="suntaek_proc('view','','${dto.no}')">${dto.id }</a></td>
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
		<c:set var = "tempGubun" value = "list"></c:set>.
	
		 <a href="#" onclick="suntaek_proc('${tempGubun}','1','');">[첫페이지]</a>
         &nbsp;&nbsp;
      <c:if test="${startPage >blockSize }">
         <a href="#" onclick="suntaek_proc('${tempGubun}','${startPage-blockSize}','');">[이전 10개]</a>         
	  </c:if>
     
      <c:if test="${startPage <=blockSize }">
         [이전 10개]         
       </c:if>
            &nbsp;
        
         <c:forEach var="i" begin="${startPage }" end="${lastPage }" step="1">
            <c:if test="${i == pageNumber }">
            [${i }]  
            </c:if>
           
            <c:if test="${i!=pageNumber }">
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
	<tr>
		<td colspan="15" height="50" align="right">
			<button type="button" onclick="suntaek_all();">전체목록</button>
			<button type="button" onclick="suntaek_proc('chuga','1','');">가입하기</button>
		</td>
	</tr>	


  </table>




  		
  	
  	
