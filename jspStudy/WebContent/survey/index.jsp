<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>



    <input type="text" name="a" style="display:;">  <br>
   		list_gubun:<span id="span_list_gubun">${list_gubun }</span><br>
   		pageNumber : <span id="span_pageNumber">${pageNumber }</span><br>
 		no:<span id="span_no">${no }</span><br>
 		search_option:<span id="span_search_option">${search_option }</span><Br>
 		search_data:<span id="span_search_data">${search_data }</span><Br>
 		search_date_check:<span id="span_search_date_check">${search_date_check }</span><br>
 		search_date_s:<span id="span_search_date_s">${search_date_s }</span><br>
 		search_date_e:<span id="span_search_date_e">${search_date_e }</span><br>
 	menuMove:<span id="span_menuMove">${menuMove }</span><br>
 		
			
     
 <div id="result" style="border: 1px solid red;"></div> 
     
 <c:if test="${menu_gubun == 'survey_index'}">
 	<script>
 		$(document).ready(function(){
 		GoList();
 		});

 	</script> 
 </c:if>
 
  <c:if test="${menu_gubun == 'survey3_index'}">
 	<script>
 		$(document).ready(function(){
 		GoList_2();
 		});

 	</script> 
 </c:if>
 
<script>
	function GoChuga(){	
		var param = {}
		$.ajax({
			type:"post",
			data:param,
			url:"${path}/survey_servlet/chuga.do",
			success:function(data){
				$("#result").html(data);	
			}
		})
	}

function GoChugaProc(){
	if(confirm('등록하시겠습니까?')){
		$.ajax({
			type:"post",
			data:$("#DirForm").serialize(),
			url:"${path}/survey_servlet/chugaProc.do",
			success:function(){ //콜백함수(서버에서 처리가 완료된 후 실행되는 코드)
				suntaek_page('1');	
			}
		});
	}
}
function GoList(){
	var param={
			"list_gubun" : $("#span_list_gubun").text(),
			"pageNumber" : $("#span_pageNumber").text(),
			"search_option":$("#span_search_option").text(),
			"search_data" : $("#span_search_data").text(),
			"search_date_s":$("#span_search_date_s").text(),
			"search_date_e":$("#span_search_date_e").text(),
			"search_date_check":$("#span_search_date_check").text()
	};
	$.ajax({
		type:"post",
		data:param,
		url:"${path}/survey_servlet/list.do",
		success: function(result){
		 	$("#result").html(result);
				if($("#span_search_date_check").text() =="O"){
					$("input[id=search_date_check]:checkbox").prop("checked", true);
				}else{
					$("input[id=search_date_check]:checkbox").prop("checked", false);	
			}  
		}
	});
}

function GoList_2(){
	var param={}
	$.ajax({
		type:"post",
		data:param,
		url:"${path}/survey_servlet/list2.do",
		success: function(data){
			$("#result").html(data);
		}
	});
}

function GoSaveProc(){
	if(confirm('저장하시겠습니까?')){
		var param={
				"answer_total": $("#span_answer_total").text()
		}
			$.ajax({
				type:"post",
				data: param,
				url: "${path}/survey_servlet/saveProc.do",
				success: function(){
					suntaek_page('1');
				}
			});
	}	
	
}
function check_answer_2(value1, value2){
	$("#span_answer_"+value1).text(value2);
	
	if(value2 == '1'){
		$("#mun1"+value1).text('❶');
		$("#mun2"+value1).text('②');
		$("#mun3"+value1).text('③');
		$("#mun4"+value1).text('④');
		
	}else if(value2 == '2'){
		$("#mun1"+value1).text('①');
		$("#mun2"+value1).text('❷');
		$("#mun3"+value1).text('③');
		$("#mun4"+value1).text('④');
	}else if(value2 == '3'){
		$("#mun1"+value1).text('①');
		$("#mun2"+value1).text('②');
		$("#mun3"+value1).text('❸');
		$("#mun4"+value1).text('④');
	}else if(value2 == '4'){
		$("#mun1"+value1).text('①');
		$("#mun2"+value1).text('②');
		$("#mun3"+value1).text('③');
		$("#mun4"+value1).text('❹');	
	}
	
var counter = parseInt($("#span_list_size").text());

	var msg="";
	for (i=counter; i>0; i--){
		q_no = $("#q_"+i).text();
		answer = $("#span_answer_"+i).text();
		
		if(answer.length > 0){
			if(msg == ''){
				msg = q_no + ":"+answer;
				
			}else{
				msg=msg+"|"+q_no+":"+answer;
			}
			
		}
	}
	$("#span_answer_total").text(msg);
}




function GoViewProc(){
	var param={
			"no":$("#span_no").text(),
			"answer":$("#span_answer").text()
	}
	$.ajax({
		type:"post",
		data:param,
		url:"${path}/survey_servlet/viewProc.do",
		success: function(data){
			GoList();
		}
	});
}



function suntaek_list(value1){
	$("#span_list_gubun").text(value1);
	$("#span_pageNumber").text(1);
	GoList();
}


function suntaek_page(value1){
	$("#span_pageNumber").text(value1);
	GoList();
}

function suntaek_view(value1){
	$("#span_no").text(value1);
	
	var param="&no="+value1;
	$.ajax({
		type:"post",
		data:param,
		url:"${path}/survey_servlet/viewex.do",
		success: function(result){
		 	$("#result").html(result);
				
		}
	});
} 

function check_answer(value1){
	$("#span_answer").text(value1);
	if(value1 == '1'){
		$("#mun1").text('❶');
		$("#mun2").text('②');
		$("#mun3").text('③');
		$("#mun4").text('④');
		
	}else if(value1 == '2'){
		$("#mun1").text('①');
		$("#mun2").text('❷');
		$("#mun3").text('③');
		$("#mun4").text('④');
	}else if(value1 == '3'){
		$("#mun1").text('①');
		$("#mun2").text('②');
		$("#mun3").text('❸');
		$("#mun4").text('④');
	}else if(value1 == '4'){
		$("#mun1").text('①');
		$("#mun2").text('②');
		$("#mun3").text('③');
		$("#mun4").text('❹');	
	}
}



</script>
  
