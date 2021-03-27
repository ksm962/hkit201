<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/inc_header.jsp" %>


menu_gubun : ${menu_gubun }<br>
naljaMap : ${naljaMap }<br>
ip : ${ip }<br>
tbl : <span id="span_tbl">${tbl }</span><br>
pageNumber : <span id="span_pageNumber">${pageNumber }</span><br>
no : <span id="span_no">${no }</span><br>
comment_no : <span id="span_comment_no">${commentdto.comment_no }</span><br>
search_option : <span id="span_search_option">${search_option }</span><br>
search_data : <span id="span_search_data">${search_data }</span><br>
passwd : <span id="span_passwd">${passwd }</span><br>


<input type="text" name="a" style="display: ;"><br><!--  ajax 테스트 위한것 -->
<div id="result" style='border:1px solid red; height:80%;'></div>

<Script>

$(document).ready(function() {
   <c:if test="${menu_gubun == 'board_index'}">
     GoPage('list');
   //GoPage('chuga');   
   </c:if>
})

function GoPage(value1, value2) {
	var url = "${path}/board_servlet/"+value1+".do";
	if (value1 == "chuga"){
		$("#span_no").text(""); 
		var param = {}
	}else if (value1 == "chugaProc" || value1 == "sujungProc" || value1 == "sakjeProc"){

		var param = {
				"no" : $("#span_no").text(),
				"tbl" : $("#span_tbl").text(),
				"writer" : $("#writer").val(),
				"email" : $("#email").val(),
				"passwd" : $("#passwd").val(),
				"subject" : $("#subject").val(),
				"content" : $("#content").val(),
				"noticeGubun" : $("#noticeGubun").val(),
				"secretGubun" : $("#secretGubun").val()
		}
	}else if (value1 =="list"){
		var param={
				"tbl" : $("#span_tbl").text(),
				"pageNumber" : $("#span_pageNumber").text(),
				"search_option" : $("#span_search_option").text(),
				"search_data" : $("#span_search_data").text()
		}
	}else if (value1 =="view"){
		$("#span_no").text(value2);
		var param={
				"no" : value2,
				"tbl" : $("#span_tbl").text(),
				"pageNumber" : $("#span_pageNumber").text(),
				"search_option" : $("#span_search_option").text(),
				"search_data" : $("#span_search_data").text(),
				"view_passwd" : $("#view_passwd").val()
		}
	}else if(value1 == "reply" || value1 == "sujung" || value1 == "sakje"){
		var param={
				"no" : $("#span_no").text()
		}

	}
	
   $.ajax({
      type: "post",
      data: param,
      url: url,
      success: function(data){
    	  if(value1 == "chugaProc") {
    		  suntaek_page('1');
    	  }else if (value1 == "sujungProc" || value1 == "sakjeProc"){
	 			$("#result").html(data); 
    	  }else if (value1 == "list"){
    		  if('${isUsingTable}' == 'F'){
    			  alert('잘못된 접근입니다.');
    			  history.back();
    		  }else{
    			  $("#result").html(data); 
    		  }
    	  		 			
		  }else{
    			 $("#result").html(data);  
    		  }  
    	  }
   });
}
  


function suntaek_page(value){
	$("#span_pageNumber").text(value);
	$("#span_no").text("");
	GoPage('list','');
}

function clickChk(value1){
	if(value1 == 'noticeGubun'){
		if($("input:checkbox[name=noticeGubunCheckBox]").is(":checked") ==true){
			$("#noticeGubun").val("T");
		}else{
			$("#noticeGubun").val("");
		}
	}else{
		if($("input:checkbox[name=secretGubunCheckBox]").is(":checked") == true){
			$("#secretGubun").val("T");
		}else{
			$("#secretGubun").val("");
		}
	}
}


</Script>




