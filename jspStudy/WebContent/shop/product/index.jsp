<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../include/inc_header.jsp" %>



    <input type="text" name="a" style="display:;">  <br>
    	proc : <span id="span_proc"> </span><br> 
		pageNumber : <span id="span_pageNumber">${pageNumber }</span><br>
		no:<span id="span_no">${no }</span><br>
 		search_option:<span id="span_search_option">${search_option }</span><Br>
 		search_data:<span id="span_search_data">${search_data }</span><br>
 		
 		<input type="text" name="a" style="display: ;"><br><!--  테스트 -->
 <div id="result" style="border: 1px solid red;"></div> 
    
    
 <script>

    $(document).ready(function() {
    	   <c:if test="${menu_gubun == 'product_index'}">
    	   suntaek_proc('list','1','');
    	   </c:if>    
    	})
    
  
  function suntaek_proc(value1,value2,value3){ <%--위의 값을 찍어주는 역할 --%>
  
  		if(value1=="chuga"){
  		
  	    	$("#span_no").text("");
  		}else if(value1=="chugaProc"){
  			suntaek_proc('list','1','');
  			
  		}else if(value1=="view"){
  			$("#span_no").text(value3);
  		}
  		
  		if(value1 !=''){
  			$("#span_proc").text(value1);
  		}
  		if(value2 !=''){
  			$("#span_pageNumber").text(value2);
  		}
  		if(value3 !=''){
  			$("#span_no").text(value3);
  		}
  		
		GoPage(value1);
    
    	
    }
    
  function GoPage(value1){
    	var param ={}
    	var process_data;       <%--file 첨부할 경우 쓴다. --%>
    	var content_type; 		<%--file때문에 쓴다. --%>
    	var url="${path}/product_servlet/"+value1+".do";
    	
    	if(value1=="chuga"){
    		param={}
    	}else if(value1=="chugaProc"){
    		process_data = false;
    		content_type = false;
    		
    	
    		param = new FormData();
    		
    
    		param.append("name",$("#name").val());
    		param.append("price",$("#price").val());
    		param.append("description",$("#description").val());
    		
    		<%-- 	
    		console.log($('input[name="file"]')[0].files[0]);
    		console.log($('input[name="file"]')[1].files[0]);
    		console.log($('input[name="file"]')[2].files[0]);
    		
    		return;
    		--%>
    		 
    		var file_counter = parseInt($('input[name="file"]').length);
    		for(i=0; i<file_counter; i++){
    			param.append(i, $('input[name="file"]')[i].files[0]);
    		}
    	}else if(value1 =="view" || value1 == "sujung" || value1 == "sakje" || value1 == "sakjeproc"){
    		param.no=$("#span_no").text();
    		
    	}else if(value1 == "list"){
    		param={
    	    		"search_option" : $("#span_search_option").text(),
    	    		"search_data" : $("#span_search_data").text(),
    	    		"pageNumber" : $("#span_pageNumber").text()
    	    	}
    	
    	}else if(value1=="sujungproc" || value1 == "sakjeproc"){
    		process_data = false;
    		content_type = false;
    		
    	
    		param = new FormData();
    		
    		param.append("no",$("#span_no").text());    
    		param.append("name",$("#name").val());
    		param.append("price",$("#price").val());
    		param.append("description",$("#description").val());
    		
    		<%-- 	
    		console.log($('input[name="file"]')[0].files[0]);
    		console.log($('input[name="file"]')[1].files[0]);
    		console.log($('input[name="file"]')[2].files[0]);
    		
    		return;
    		--%>
    		 
    		var file_counter = parseInt($('input[name="file"]').length);
    		for(i=0; i<file_counter; i++){
    			param.append(i, $('input[name="file"]')[i].files[0]);
    		}
    	}
    	
    	
    
	  $.ajax({	
    		type: "post",
    		data: param,
    		processData: process_data,
    		contentType: content_type,
    		url : url,
    		success: function(data){
    			if(value1=="chugaProc"){
    	  			suntaek_proc('list','1','');
    			}else if(value1=="sujungproc" || value1 == "sakjeproc"){
    	  			suntaek_proc('list','1','');
    			}else{
    				$("#result").html(data);
    			}
    			
    		}
    		
    	});
	  
    }  	
    
    
 </script> 