<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<table border="0" align="center" width="95%">
	<tr>
		<td style="padding: 0 0 20 0;">
			이름:<input type="text" id="comment_writer" size="10" value="${cookName}">
			비밀번호:<input type="password" id="comment_passwd" size="10" value="">
			<br>
			댓글:<input type="text" id="comment_content" size="40" value="">
			<span id="spanView2" style="display: none;">
				<button type="button" onclick="comment_save('commentProc')">확인</button>
			</span>
			<span id="spanView" style="display: none;">
				<button type="button" onclick="comment_save('commentmodifyProc')">수정</button>
			</span>
		</td>
	</tr>
</table>

<c:if test="${totalRecord > 0 }">
	<table border="0" align="center" width="95%">
		<c:forEach var="dto" items="${list }">
			<tr>
				<td style="padding: 0 0 10 10;">
					<table border="0" align="left" style="width:100%">
						<tr>
							<td>${dto.writer } &nbsp;(${dto.regidate })</td>
						</tr>
						<tr>
							<td>${dto.content }</td>
					<td><a href="#" onclick="aaa('${dto.comment_no}', '${dto.writer}', '${dto.content }')">
              				 [수정]
           			 	</a>
							<button type="button" onclick="comment_save('commentSakjeProc','${dto.comment_no}')">삭제하기</button></td>
						</tr>
					</table>
					<hr>
				</td>
			</tr>
			<c:set var="jj" value="${jj = jj-1 }"></c:set>
		</c:forEach>
		<tr>
			<td colspan="7" height="50" align="center">
				<a href="#comment" onclick="comment_suntaek_page('1');">[첫페이지]</a>
				&nbsp;&nbsp;
				<c:if test="${startPage > blockSize }">
					<a href="#comment" onclick="comment_suntaek_page('${startPage - blockSize }');">[이전10개]</a>
				</c:if>
				<c:if test="${startPage <=blockSize }">
	        		 [이전 10개]         
	      		 </c:if>
	            &nbsp;
				 <c:forEach var="i" begin="${startPage }" end="${lastPage }" step="1">
	            <c:if test="${i == commentPageNumber }">
	            [${i }]  
	            </c:if>
	           
	            <c:if test="${i!=commentPageNumber }">
	            <a href="#comment" onclick="comment_suntaek_page('${i}');">${i }</a>
	            </c:if>
	           </c:forEach>
	           
	            &nbsp; 
	            <c:if test="${lastPage < totalPage }">
	               <a href="#comment" onclick="comment_suntaek_page('${startPage + blockSize}');">[다음 10개]</a>
	            </c:if>
	           
	            <c:if test="${lastPage >= totalPage }">
	               [다음 10개]
	            </c:if>
				<a href="#comment" onclick="comment_suntaek_page('${totalPage}');">[끝페이지]</a> 
			</td>
		</tr>
	</table>         
</c:if>

<script>
$(document).ready(function() {
	$("#spanView").hide();
	$("#spanView2").show();
});

	function comment_save(value1,value2){
		var url = "${path}/board_servlet/"+value1+".do";
		
		if(value1 == "commentProc" || value1 == "commentSakjeProc" || value1 == "commentmodifyProc"){
			$("#span_comment_no").text(value2);
		var param = {
				"commentPageNumber" : $("#span_commentPageNumber").text(),
				"no" : $("#span_no").text(),
				"comment_no" : $("#span_comment_no").text(),
				"writer" : $("#comment_writer").val(),
				"passwd" : $("#comment_passwd").val(),
				"content" : $("#comment_content").val(),
			}
		}	
		$.ajax({
			type:"post",
			data: param,
			url : url,
			success:function(data){
					$("#span_commentPageNumber").text(${commentPageNumber});
					comment_list();
			}
		});
	}

	function comment_suntaek_page(value1){
		$("#span_commentPageNumber").text(value1);
		comment_list();
	}
	function aaa(value1, value2, value3) {
		   $("#spanView").show();
		   $("#spanView2").hide();
		   $("#span_comment_no").text(value1);
		   $("#comment_writer").val(value2);
		   $("#comment_content").val(value3);
		   
		}	
	
</script>








							



