<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../../include/inc_header.jsp" %>
 
 <table border="1" align="center" width="80%">
	<tr>
		<td colspan="2"><h2>Shopping Mall</h2></td>
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
		<td>${fn:split(dto.product_img,'|')[0]}</td>
	</tr>
	
	<tr>
		<td>등록일</td>
		<td>${dto.regi_date}</td>
	</tr>
	
	<tr>
		<td colspan="2" align="center">
			<select name="jumun_su" id="jumun_su">
				<c:forEach var = "i" begin = "1" end = "10" step = "1">
					<option value = "${i }">${i }</option>
				</c:forEach>	
			</select>
			
		<button type="button" onclick="suntaek_proc('cart_chuga','','${dto.no}');">장바구니담기</button>
		<button type="button" onclick="suntaek_proc('gumae','','${dto.no}');">바로구매</button>
		<button type="button" onclick="suntaek_proc('mall_list','1','');">쇼핑하기</button>
		<button type="button" onclick="suntaek_proc('cart_list','1','');">장바구니</button>		
		</td>
	</tr>
		
<%-- 	<tr>
		<td colspan = "2" height = "50px">
			<table border = "1" width = "100%" align = "center">
				<tr>
					<td width = "100px"> 이전상품 : </td>
					<td>
						<c:if test="${dto.preName == null }">
							이전상품이 없습니다.
						</c:if>
						<c:if test = "${dto.preName != null }">
							<a href="#" onclick = "suntaek_proc('mall_view','','${dto.preNo}');">${dto.preName }</a>
						</c:if>
					</td>
				</tr>
				<tr>
					<td width = "100px">다음상품 : </td>
					<td>
						<c:if test="${dto.nxtName == null }">
							이전상품이 없습니다.
						</c:if>
						<c:if test = "${dto.nxtName != null }">
							<a href="#" onclick = "suntaek_proc('mall_view','','${dto.nxtNo}');">${dto.nxtName }</a>
						</c:if>
					</td>
				</tr>
			</table>	 --%>		
</table>











