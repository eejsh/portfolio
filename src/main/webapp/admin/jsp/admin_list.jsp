<%@page import="model.LoginModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<script src="./js/jquery.js"></script>

 
<p>신규등록 관리자</p>
<ol class="new_admin_title">
	<li>NO</li>
	<li>관리자명</li>
	<li>아이디</li>
	<li>전화번호</li>
	<li>이메일</li>
	<li>담당부서</li>
	<li>담당직책</li>
	<li>가입일자</li>
	<li>승인여부</li>
</ol>

<%
ArrayList<LoginModel>lists = (ArrayList<LoginModel>)request.getAttribute("list");
if(lists.size()==0){
%>
<span>
<ol class="new_admin_none">
	<li>신규 등록된 관리자가 없습니다.</li>
</ol>
</span>
<%

}else{
	for(LoginModel  dblist : lists){
	int num = Integer.parseInt(dblist.getMdepart());
		String mde = null;
		switch(num){
		case 1 : mde ="임원"; break;
		case 2 : mde ="전산팀"; break;
		case 3 : mde ="디자인팀"; break;
		case 4 : mde ="CS팀"; break;
		case 5 : mde ="MD팀"; break;
		}
	
		
		int num2 = Integer.parseInt(dblist.getMpo());
		String mpo = null;
		switch(num2){
		case 1 : mpo ="대표"; break;
		case 2 : mpo ="부장"; break;
		case 3 : mpo ="팀장"; break;
		case 4 : mpo ="과장"; break;
		case 5 : mpo ="대리"; break;
		case 6 : mpo ="사원"; break;
	     }
		
	String tel1 = dblist.getMtel1();
	String tel2 = dblist.getMtel2();
	String tel3 = dblist.getMtel3();
	String tel = tel1 + tel2 + tel3;
	
	String dbno = dblist.getMidx();
	String dbapp = dblist.getMapp();
%>

<ol class="new_admin_lists">
	<li><%=dbno%></li>
	<li><%=dblist.getMname() %></li>
	<li><%=dblist.getMid() %></li>
	<li><%=tel%></li>
	<li><%=dblist.getMemail() %></li>
	<li><%=mde %></li>
	<li><%=mpo %></li>
	<li><%=dblist.getMindate().substring(0,10)%></li>
	<li>
	<input type="button" value="승인" class="new_addbtn1" onclick="admin(<%=dbno%>+',Y')">
	<input type="button" value="미승인" class="new_addbtn2" onclick="admin(<%=dbno%>+',N')">
	</li>
</ol>
<% 
	}
}
%>

<script>

function admin(midx) {
	console.log(midx);
var midx = midx;
let ick;
function wck() {
	if(window.XMLHttpRequest){
		return new XMLHttpRequest();
	}
}
function adok(){
	if(ick.readyState==XMLHttpRequest.DONE && ick.status==200){
	  if(this.response=="Y"){
		  alert('승인');
	  }else if(this.response=="N"){
		  alert('미승인');
	  }
	}else if(ick.status==404){
		console.log('error');
	}
}
ick = wck();
ick.onreadystatechange = adok;
ick.open("post", "./mappupdate.do?midx="+midx , true);
ick.send();

}

</script>