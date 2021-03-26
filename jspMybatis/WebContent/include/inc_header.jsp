<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--  알집4개넣은 것을 라이브러리화--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  
<c:set var="path" value="${pageContext.request.contextPath }" /> 
<c:set var="url" value="${pageContext.request.requestURL }" />
<c:set var="uri" value="${pageContext.request.requestURI }" />

<script src="http://code.jquery.com/jquery-3.3.1.min.js">
</script>

<%

response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires",0);

%>


<%-- c:set var = "변수" value = "${값}"

형식(
EL:${변수명 or 수식}이 들어갈수 있음
JSTL(jsp standard tag library)
)
찎는법
path : ${path}
url : ${url}
uri:${uri }  

  --%>


