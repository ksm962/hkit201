<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="java.net.URLEncoder" %>
    
    
    <%
    String pageNumber = request.getParameter("pageNumber");
    String list_gubun = request.getParameter("list_gubun");
    String search_option = request.getParameter("search_option");
    String search_data = request.getParameter("search_data");
    String search_date_s = request.getParameter("search_date_s");
    String search_date_e = request.getParameter("search_date_e");
    String search_date_check=request.getParameter("search_date_check");
    
 
       System.out.println(pageNumber);
       System.out.println(list_gubun);
       System.out.println(search_option);
       System.out.println(search_data);
       System.out.println(search_date_s);
       System.out.println(search_date_e);
       
       
   

    %>    
