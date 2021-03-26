<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>



<table border="1" align="center" width="100%">
	<tr>
		<td colspan="7">
			<h2>설문조사 목록</h2>
		</td>
	</tr>
	<tr>
		<td colspan="7">검색
			<select name="search_option" id="search_option">
				<c:choose>
					<c:when test="${search_option == 'question' }">
						<option value="">-선택-</option>
						<option value="question" selected>질문내용</option>
					</c:when>
					<c:otherwise>
						<option value="" selected>-선택-</option>
						<option value="question">질문내용</option>
					</c:otherwise>
				</c:choose>		
			</select>
			
			<input type="text" name="search_data" id="search_data" value="${search_data }" style="width:150px;">
			&nbsp;
			<input type="date" id="search_date_s" value="${search_date_s }"> ~
			<input type="date" id="search_date_e" value="${search_date_e }"><br>
			
			<input type="checkbox" id="search_date_check" value="O" onclick="checkboxChk();">
 			
			<span id = "check" style="color:blue; font-size: 9px;">
			(날짜 검색시 체크)</span>
			&nbsp;
			<input type="button" value="검색" onclick="search();">
			
		</td>
	</tr>
	<tr>
		<td style="padding: 10px 0px 5px;">전체${totalRecord }건입니다.</td>
	</tr>
	<tr>
		<td style="padding : 0 0 20px 0;">
			<table border="1" align="center" style="width:100%;">
				<tr>
					<td>순번</td>
					<td>질문</td>
					<td>기간</td>
					<td>참여수</td>
					<td>상태</td>
					<td>삭제</td>
				</tr>
				<c:forEach var="dto" items="${list }">
				
			<input type="hidden" name = "no" id="no" value="${dto.no }">	
				<tr>
					<td>${jj}</td>
					<td><a href="#" onclick="suntaek_view('${dto.no }');">${dto.question }</a></td>
					<td>${dto.start_date }<br>${dto.last_date }</td>
					<td>${dto.survey_counter }</td>
					<td>${dto.status }</td>
					<td> <button type="button" onclick="GosakjeProc('${dto.no}');">삭제하기</button></td>
				</tr>
				<c:set var="jj" value="${jj=jj-1 }"></c:set>
				</c:forEach>
			</table>	
		</td>
	</tr>
	<tr>
		<td colspan="7" height="50" align="center">
		 <a href="#" onclick="suntaek_page('1');">[첫페이지]</a>
         &nbsp;&nbsp;
      <c:if test="${startPage >blockSize }">
         <a href="#" onclick="suntaek_page('${startPage-blockSize}');">[이전 10개]</a>         
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
            <a href="#" onclick="suntaek_page('${i}');">${i }</a>
            </c:if>
            </c:forEach>
            &nbsp;
            <c:if test="${lastPage < totalPage }">
               <a href="#" onclick="suntaek_page('${startPage + blockSize}');">[다음 10개]</a>
            </c:if>
            <c:if test="${lastPage >= totalPage }">
               [다음 10개]
            </c:if>
         &nbsp;&nbsp;
      
         <a href="#" onclick="suntaek_page('${totalPage}');">[끝페이지]</a>         
		</td>
	</tr>
	<tr>
		<td colspan="7" height="50" align="right">
			<button type="button" onclick="suntaek_list('all');">전체 설문목록</button>
			<button type="button" onclick="suntaek_list('ing');">진행중인 설문목록</button>
			<button type="button" onclick="suntaek_list('end');">종료된 설문목록</button>
			<button type="button" onclick="GoChuga();">등록하기</button>
			<button type="button" onclick="GoList_2();">문제풀이</button>
			
	</tr>
	</table>
	
	 <script>
		function search(){
	
			$("#span_search_option").text($("#search_option").val());
			$("#span_search_data").text($("#search_data").val());
			
			$("#span_search_date_s").text($("#search_date_s").val());
			$("#span_search_date_e").text($("#search_date_e").val());	
			suntaek_page('1');
		}
		
		 function checkboxChk() {
		       if($("input:checkbox[id=search_date_check]").is(":checked") == true){
		          $("#span_search_date_check").text($("#search_date_check").val());
		          $("#span_search_date_s").text($("#search_date_s").val());
		          $("#span_search_date_e").text($("#search_date_e").val());
		       }else{
		          $("#span_search_date_check").text('');
		          $("#span_search_date_s").text('');
		          $("#span_search_date_e").text('');
		          $("#search_date_s").text('');
		          $("#search_date_e").text('');
		       }
		      
		   }

	
	</script> 
	

	
	
	
	
    
    
    