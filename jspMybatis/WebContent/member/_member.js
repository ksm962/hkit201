



function suntaek_all(){
	$("#span_search_option").text("");
	$("#span_search_data").text("");
	suntaek_proc('list','1','')
}

function suntaek_proc(value1, value2, value3){
	$("#span_proc").text(value1);
	
	if (value2 != "0"){// 0-건너뜀 기존에 있는것을 보관하라
		$("#span_pageNumber").text(value2);
	}
	
	if (value3 != "0"){
		$("#span_no").text(value3);
	}
	GoPage(value1);	
}
function GoPage(value1){
	var param;
//	var url = "${path}/member_servlet/" + value1 + ".do";
	var url = $("#span_path").text() + "/member_servlet/" + value1 + ".do";         
	if (value1 == "list"){
		param = {
			"pageNumber" : $("#span_pageNumber").text(),
			"search_option" : $("#span_search_option").text(),
			"search_data" : $("#span_search_data").text()
		}
	} else if (value1 == "chuga"){
		$("#span_no").text("");
		param = {}
		
		} else if (value1 == "view"){
		param = {
			"no" : $("#span_no").text(),
			"pageNumber" : $("#span_pageNumber").text(),
			"search_option" : $("#span_search_option").text(),
			"search_data" : $("#span_search_data").text()
		}	
	} else if (value1 == "chugaProc" || value1 == "modifyProc" || value1 == "dProc"){
		param = {
			"no" : $("#span_no").text(),
			"id" : $("#id").val(),
			"passwd" : $("#passwd").val(),
			"passwdchk": $("#passwdchk").val(),
			"name" : $("#name").val(),
			"gender" : $("#gender").val(),
			"bornyear" : $("#bornyear").val(),
			"sample6_address" : $("#sample6_address").val(),
			"sample6_detailAddress" : $("#sample6_detailAddress").val(),
			"sample6_extraAddress" : $("#sample6_extraAddress").val(),
			"sample6_postcode" : $("#sample6_postcode").val()
		}
	} else if (value1 == "modify" || value1 == "d"){
		param = {
			"no" : $("#span_no").text()
		}
	} 

	
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: function(data){
			if (value1 == 'list'){
				$("#result").html(data);
			} else if (value1 == 'chuga' || value1 == 'view'){
				$("#result").html(data);
			} else if (value1 == 'chugaProc'){
				suntaek_proc('list','1','');
			} else if (value1 == 'modify'){
				$("#result").html(data);
			} else if (value1 == 'modifyProc'){
				$("#result").html(data);
				if ($("#span_passwd").text() == 'O'){
					alert('수정되었습니다.');
					suntaek_proc('view','0', $("#span_no").text());
				}else if ($("#span_passwd").text() =='X'){
					alert('비밀번호가 다릅니다.')
					suntaek_proc('modify','0', $("#span_no").text());
				}

			} else if (value1 == 'd'){
				$("#result").html(data)
				
			} else if (value1 == 'dProc'){
				$("#result").html(data);
				
				if ($("#span_passwd").text() == 'O'){
					suntaek_proc('list','0', '');

				}else if ($("#span_passwd").text() =='X'){
					alert('비밀번호가 다릅니다.')
					suntaek_proc('d','0', $("#span_no").text());
				}
				
			} 
		}
	});
}
 
