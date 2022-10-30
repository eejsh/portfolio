<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String name = (String)session.getAttribute("dbnm");
String id = (String)session.getAttribute("mid");
PrintWriter pr = response.getWriter();
if(name==null){
	pr.print("<script>alert('접근이 잘못되었습니다.'); location.href='./index.html'; </script>");
}else{
session.setMaxInactiveInterval(30*60);
}
%>