<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %> 


proc : <span id="span_proc">${proc }</span><br>
id : <span id="span_no">${no }</span><br>
pageNumber : <span id="span_pageNumber">${pageNumber }</span><br>
path : <span id="span_path">${path }</span><br>
<input type="text" name="a" style="display: ;"><br><!--  ajax 테스트 위한것 -->
<div id="result" style='border:1px solid red; height:80%;'></div>
<script type="text/javascript" src="${path }/memo/_memo.js"></script>