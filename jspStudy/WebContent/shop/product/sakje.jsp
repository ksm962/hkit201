<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="../../include/inc_header.jsp" %>
   
   
    	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>상품삭제하기</h2></td>
		</tr>
		
		<tr>
			<td width="150">상품코드 : </td>
			<td>${dto.no}</td>
		</tr>
		
		
		<tr>
			<td>상품사진</td>
			<td>
				<c:if test="${dto.product_img == '-,-,-' }">이미지 x
			</c:if>	
			<c:if test="${dto.product_img !='-,-,-' }">
				<c:set var="temp1" value="${fn:split(dto.product_img,',')[0] }"></c:set>
				<c:set var="temp2" value="${fn:split(temp1,'|')[0] }"></c:set>
				<c:set var="temp3" value="${fn:split(temp1,'|')[1] }"></c:set> 
				<img src="${path }/attach/image/${temp3}" alt="${dto.name }" title="${dto.name }" style="width:50px; height:50px;">
			</c:if>	
			
			</td>
		</tr>
		
		
		<tr>
			<td>상품명</td>
			<td>${dto.name}
			</td>
		</tr>
			
		<tr>
			<td>가격</td>
			<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${dto.price }" />
			</td>
		</tr>

		<tr>
			<td style="align: center;">상품설명</td>
			<td id="content">${dto.description}</td>
		</tr>
		
		<tr>
			<td>파일명</td>
			<td>${fn:split(dto.product_img,'|')[0]}<td>
		</tr>
		
		<tr>
			<td>등록일</td>
			<td>${dto.regi_date}</td>
		</tr>
		
		<tr>
			<td>장바구니수량</td>
			<td><fmt:formatNumber type="number" maxFractionDigits="3" value="" /></td>
		</tr>
		
		
		<tr>
			<td colspan="2" align="center" style="height:50px;">
			
				<button type="button" id="btnsakje">삭제하기</button>
				<button type="button" id="btnList">목록으로</button>
			</td>
		</tr>
	</table>
	
	<script>
	$(document).ready(function(){

		
		$("#btnsakje").click(function(){
			if(confirm('삭제하시겠습니까?')){
				suntaek_proc('sakjeproc','','${dto.no}');
			}
		})
		$("#btnList").click(function(){
			suntaek_proc('list','1','');
		});
	});
</script>	
	
	
	