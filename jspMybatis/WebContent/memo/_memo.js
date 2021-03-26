


$(document).ready(function(){
	suntaek_proc('memolist','1','');
})



function suntaek_proc(value1, value2, value3){
	$("#span_proc").text(value1);
	
	if (value2 != "0"){// 0-건너뜀 기존에 있는것을 보관하라
		$("#span_pageNumber").text(value2);
	}
	if (value2 != "0"){// 0-건너뜀 기존에 있는것을 보관하라
		$("#span_no").text(value3);
	}
	
	GoPage(value1);	
}
function GoPage(value1){
	var param;
//	var url = "${path}/memo_servlet/" + value1 + ".do";
	var url = $("#span_path").text() + "/memo_servlet/" + value1 + ".do";         
	
	if (value1 == "memolist"){
		param = {
			"id" : $("#span_no").text(),
			"pageNumber" : $("#span_pageNumber").text()	
		}
	} else if (value1 == "memo"){
		param = {}
		
	} else if (value1 == "memoProc" || value1 == "modfiyProc" || value1 == "sakjeProc"){
		param = {
			"id" : $("#span_no").text(),
			"name" : $("#name").val(),
			"content" : $("#content").val()
		}
	} 

	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: function(data){
			if (value1 == 'memolist'){
				$("#result").html(data);
			} else if (value1 == 'memo'){
				$("#result").html(data);
			} else if (value1 == 'memoProc' || value1 == 'modfiyProc' || value1 == 'sakjeProc'){
				
				suntaek_proc('memolist','1');
			} 
		}
	});

} 
