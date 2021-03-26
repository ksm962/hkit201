<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>    
    
<form name="DirForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>회원가입</h2></td>
		</tr>
		
		<tr>
			<td width="150">아이디</td>
			<td><input type="text" name="id" id="id" value="">
				<button type="button" onclick="id_check_div();">아이디찾기</button>
				<button type="button" onclick="id_check_win();">아이디찾기(새창)</button>
				<br>
				<label id="label_id"></label>
			</td>
		</tr>
		
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd" id="passwd"></td>
		</tr>
		
		
		<tr>
			<td>비밀번호확인</td>
			<td><input type="password" name="passwdchk" id ="passwdchk"></td>
		</tr>
		
		
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" id ="name"></td>
		</tr>
		
		
		<tr>
			<td>성별</td>
			<td><input type="radio" name="genderRadio" value="F" onclick="suntaek_gender('F');">여자
				<input type="radio" name="genderRadio" value="M" onclick="suntaek_gender('M');" CHECKED>남자<br>
				<input type="text" name="gender" id="gender" value ="M">
				
				<script>
					function suntaek_gender(value1){
						if(value1 == "M"){
							$("#gender").val(value1);
						}else {
							$("#gender").val(value1);
						}
					}
				
				</script>	
			</td>
		</tr>

	
		
		
		<tr>
			<td>주소</td>
			<td>
			<input type="text" name ="postcode" id="sample6_postcode" placeholder="우편번호">
			<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
			<input type="text" name= "address" id="sample6_address" placeholder="주소"><br>
			<input type="text" name= "detailaddress" id="sample6_detailAddress" placeholder="상세주소">
			<input type="text" name= "extraaddress" id="sample6_extraAddress" placeholder="참고항목">
			</td>
			
		</tr>	
		<tr>
			<td>생년월일</td>
			<td><input type="text" name="bornyear" id="bornyear" value="1990"></td>
		</tr>
		  

		<tr>
			<td colspan="2" align="center" style="height:50px;">
				<button type="button" id="btnChuga">가입</button>
				<button type="button" id="btnList">목록으로</button>
			</td>
			
		</tr>
	</table>
</form>

<script>
 function id_check_div(){
	 var id=$("#id").val();
 	 if(id == ''){
		 $("#label_id").html('<strong>아이디를 입력하세요.</strong>');
		 $("#label_id").css('color','green');
		 $("#label_id").css('font-size','12px');
		 return;  
	 }
	 var param="id="+id;
	 
	 $.ajax({
		 type:"post",
		 data: param, 
		 url:"${path}/member_servlet/id_check.do", 
		 success:function(result){
 
			   if(result>0){
 				 $("#id").val('');
				 $("#label_id").html("사용할 수 없는 아이디입니다.");
				 $("#label_id").css('color','red');
				 $("#label_id").css('font-size','12px');
			 }else{
				 $("#label_id").html("사용할 수 있는 아이디입니다.");
				 $("#label_id").css('color','blue');
				 $("#label_id").css('font-size','12px');
			 }    
		 }
	 }); 
 }
 </script>
 <script>
function id_check_win(){
	window.open("${path}/member_servlet/id_check_win.do", "id check","width=640, height=480, toolbar=no, menubar=no, scrollbars=no, resizable=yes");
}
 
 
$(document).ready(function(){
	$("#writer").focus();

	$("#btnChuga").click(function(){
		if (confirm('가입하시겠습니까?')){
			suntaek_proc('chugaProc','1','');
		}
	});
	$("#btnList").click(function(){
		suntaek_proc('list','1','')
	});
	
	$("#id_check_div").click(function(){
		id_check_div();
	});
	$("#id_check_win").click(function(){
		id_check_win();
	});
	
})

</script>	
	
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 7내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
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