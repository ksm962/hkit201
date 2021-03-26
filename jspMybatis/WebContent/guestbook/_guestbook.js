$(document).ready(function(){
		suntaek_proc('list','1','')
});

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

function GoPage(value1,value2){
	var param;
//	var url = "${path}/member_servlet/" + value1 + ".do";
	var url = $("#span_path").text() + "/guestbook_servlet/" + value1 + ".do";         
	if (value1 == "list"){
		param = {
			"pageNumber" : $("#span_pageNumber").text(),
			"search_option" : $("#span_search_option").text(),
			"search_data" : $("#span_search_data").text()
		}
	} else if (value1 == "writer"){
		$("#span_no").text("");
		param = {}
	} else if (value1 == "writerProc" || value1 == "modifyProc" || value1 == "sakjeProc"){
		param = {
		"no" : $("#span_no").text(),
		"name" : $("#name").val(),
		"email" : $("#email").val(),
		"passwd" : $("#passwd").val(),
		"content" : $("#content").val()
		}
	} else if (value1 == "modify" || value1 == "sakje"){
		param = {
		"no" : $("#span_no").text(),
		"passwd" : $("#span_isSamePasswd").text()
		}
	}
		
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: function(data){
			if (value1 == 'list'){
				$("#result").html(data);
			} else if (value1 == 'writer' || value1 == 'modify' || value1 == 'sakje'){
				$("#result").html(data);
			} else if (value1 == 'writerProc'){
				
				suntaek_proc('list','1','');
			} else if (value1 == 'modifyProc'){
				$("#result").html(data);
				
				if ($("#span_passwd").text() == 'O'){
						alert('수정되었습니다.');
						suntaek_proc('list','1','');
						
				} else if ($("#span_passwd").text() == 'X'){
					alert('비밀번호가 다릅니다.')
					GoPage('modify',$("#span_no").text());
				}
			} else if (value1 == 'sakjeProc'){
				$("#result").html(data);
				
				if ($("#span_passwd").text() == 'O'){
						alert('삭제되었습니다.');
						suntaek_proc('list','1','');
						
				} else if ($("#span_passwd").text() == 'X'){
					alert('비밀번호가 다릅니다.')
					GoPage('sakje',$("#span_no").text());
				}
			} 
			
			
		}
	});

} 