<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>



    	<input type="text" name="a" style="display:;">  <br>
   		list_gubun:<span id="span_list_gubun">${list_gubun }</span><br>
   		proc:<span id="span_proc">${proc }</span>
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
 			GoPage('list','')
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
	function GoPage(value1, value2) {
		var url = "${path}/survey_servlet/"+value1+".do";
		if (value1 == "chuga"){
			$("#span_no").text(""); 
			var param = {}
		}else if (value1 == "chugaProc" || value1 == "modfiyProc" || value1 == "sakjeProc"){
	
			var param = {
					"no" : $("#span_no").text(),
					"question" : $("#question").val(),
					"ans1" : $("#ans1").val(),
					"ans2" : $("#ans2").val(),
					"ans3" : $("#ans3").val(),
					"ans4" : $("#ans4").val(),
					"status" :$("#status").val(),
					"syear" : $("#syear").val(),
					"smonth" : $("#smonth").val(),
					"sday" : $("#sday").val(),
					"lyear" : $("#lyear").val(),
					"lmonth" : $("#lmonth").val(),
					"lday" : $("#lday").val()	
			}
		}else if (value1 =="list"){
			var param={
					"list_gubun" : $("#span_list_gubun").text(),
					"pageNumber" : $("#span_pageNumber").text(),
					"search_option":$("#span_search_option").text(),
					"search_data" : $("#span_search_data").text(),
					"search_date_s":$("#span_search_date_s").text(),
					"search_date_e":$("#span_search_date_e").text(),
					"search_date_check":$("#span_search_date_check").text()
			}
			
		} else if (value1 == "list2"){
			var param={}
	
		}else if (value1 =="view"){
			var param={
					"no":$("#span_no").text(),
					"answer":$("#span_answer").text()
			}
		}else if(value1 == "modfiy"){
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
	    	  }else if (value1 == "modfiyProc" || value1 == "sakjeProc"){
	    		//GoPage('view', $("#span_no").text());
	    		
	    		 $("#result").html(data);  
	    		  
	    	  }else{
	    			  $("#result").html(data);  
	    		  }  
	    	  }
	   });
	}
	  


	

</script> 
 
 
 
 	 