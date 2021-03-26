<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>    
    
<form name="DirForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>수정하기</h2></td>
		</tr>

		<tr>
				
			<td width="150">아이디</td>
			<td><input type="hidden" name="id" id = "id" value="${dto.id }">${dto.id }/${sessionScope.cookName}님 </td>
		</tr>
		
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd" id = "passwd" value="${dto.passwd }"></td>
		</tr>
		
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" id = "name" value="${dto.name }"></td>
		</tr>
		
		
		<tr>
			<td>성별</td>
			<td><input type="radio" name="gender" id = "gender" value="F" <c:if test="${dto.gender.equals('F')}">checked</c:if>>여자
			
				<input type="radio" name="gender" id = "gender" value="M" <c:if test="${dto.gender.equals('M')}">checked</c:if>>남자
			<%--	
				<c:if test="${dto.gender.equals('F')}">
					<input type="radio" name="gender" value="F" checked>여자
					<input type="radio" name="gender" value="M" >남자			
				</c:if>
				
				<c:if test="${dto.gender.equals('M')}">
					<input type="radio" name="gender" value="F" >여자
					<input type="radio" name="gender" value="M" checked>남자			
				</c:if>
				 --%>
			</td>
		</tr>
				<tr>
			<td>주소</td>
			<td>
			<input type="text" name ="postcode" id="sample6_postcode" value="${dto.postcode }">
			<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
			<input type="text" name= "address" id="sample6_address" value="${dto.address }"><br>
			<input type="text" name= "detailaddress" id="sample6_detailAddress" value="${dto.detailaddress }">
			<input type="text" name= "extraaddress" id="sample6_extraAddress" value="${dto.extraaddress }">
			</td>
			
		</tr>	
		<tr>
			<td>생년월일</td>
			<td><input type="text" name="bornyear" id = "bornyear" value="${dto.bornyear}"></td>
		</tr>
		<tr>
			<td colspan="2" align="center" style="height:50px;">
			<button type="button" id="btnModify">수정하기</button></td>
		</tr>
	</table>
</form>

<script>
$(document).ready(function(){
	$("#btnModify").click(function(){
	if(confirm('수정하시겠습니까?')){
			suntaek_proc('modifyProc','1','${dto.no}');
		}
	})
})	
</script>	
	
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>
    
    
    