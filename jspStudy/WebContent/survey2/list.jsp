<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../include/inc_header.jsp" %>

    
 
    <table border="1" align="center" width="100%">
	<tr>
		<td colspan="7">
			<h2>설문조사 목록</h2>
		</td>
	</tr>
	<tr>
		<td colspan="7">검색
	<form name = "searchform">		
			<select name="search_option" id="search_option">
			<c:choose>
			<c:when test="${search_option='question' }">
				<option value="">-선택-</option>
				<option value="question">질문내용</option>
			</c:when>
			<c:otherwise>
				<option value="">-선택-</option>
				<option value="question">질문내용</option>
			</c:otherwise>
			</c:choose>	
			</select>
			
			<input type="text" name="search_data" id="search_data" value="${search_data }" style="width:150px;">
			&nbsp;
			<input type="date" name="search_date_s" value="${search_date_s }"> ~
			<input type="date" name="search_date_e" value="${search_date_e }"><br>
			
			<input type="checkbox" name="search_date_check" value="O" >
 			
			<span id = "check" style="color:blue; font-size: 9px;">
			(날짜 검색시 체크)</span>
			&nbsp;
			<input type="button" value="검색" onclick="search();">
			</form>	
		</td>
	</tr>



    <table border="1" align="center" style="width:100%;">
				<tr>
					<td>순번</td>
					<td>질문</td>
					<td>기간</td>
					<td>참여수</td>
					<td>상태</td>
				</tr>
				<c:forEach var="dto" items="${list }">
				
				 
				<tr>
					<td>${jj}</td>
					<td><a href="#" onclick="GoPage('survey2_view','','${dto.no}')">${dto.question }</a></td>
					<td>${dto.start_date }<br>${dto.last_date }</td>
					<td>${dto.survey_counter }</td>
					<td>${dto.status }</td>
				</tr>
				<c:set var="jj" value="${jj=jj-1 }"></c:set>
				</c:forEach>
				
				<tr>
  		<td colspan="15" height="50" align="right">
  			<button type="button" onclick="suntaek_list('all');">전체 설문목록</button>
			<button type="button" onclick="suntaek_list('ing');">진행중인 설문목록</button>
			<button type="button" onclick="suntaek_list('end');">종료된 설문목록</button>
			<button type="button" onclick="GoList_2();">문제풀이</button>
  			<button type="button" onclick="GoPage('survey2_chuga','','')">가입하기</button>
  		</td>
  	</tr>
			</table>	
			
<script>
	function search(){
		
		searchform.method="post";
		searchform.action="${path}/survey2/Survey_search.jsp";
		searchform.submit();
			}
	
	function GoPage(value1, value2, value3){
		if(value1 == 'member_list'){
			location.href='${path}/member_servlet/list.do?pageNumber='+value2;
		}else if (value1 =='survey2_chuga'){
			location.href='${path}/survey2_servlet/chuga.do';
		}else if (value1 =='survey2_view'){
			location.href='${path}/survey2_servlet/view.do?pageNumber='+value2+'&no='+value3;	
		}
	}
</script>  			
			
			
			
			