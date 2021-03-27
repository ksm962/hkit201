<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ page import = "org.json.simple.JSONObject" %>
 <%@ page import = "java.io.PrintWriter" %>   

     
    
     
    
    <%
    String name = request.getParameter("name");
    String kor_ = request.getParameter("kor");
    String eng_ = request.getParameter("eng");
    String mat_ = request.getParameter("mat");
    String sci_ = request.getParameter("sci");
    String his_ = request.getParameter("his");
    
    if(name == null || name.trim().equals("")){
    	name="";
    }
    
    
    
    int kor=Integer.parseInt(kor_);
    int eng=Integer.parseInt(eng_);
    int mat=Integer.parseInt(mat_);
    int sci=Integer.parseInt(sci_);
    int his=Integer.parseInt(his_);

    int tot = kor+eng+mat+sci+his;
    double avg = tot/(double)5;
    
   /*  out.println(name);
    out.println(kor);
    out.println(eng);
    out.println(mat);
    out.println(sci);
    out.println(his); */
    
    JSONObject jsonobj = new JSONObject();
    jsonobj.put("name", name);
    jsonobj.put("kor", kor);
    jsonobj.put("eng", eng);
    jsonobj.put("mat", mat);
    jsonobj.put("sci", sci);
    jsonobj.put("his", his);
    jsonobj.put("tot", tot);
    jsonobj.put("avg", avg);
    String json_sj = jsonobj.toJSONString();
 
    out.println(json_sj);
    
  /*  PrintWriter pw = response.getWriter();
   pw.print(json_sj); */
    
    
    
    %>