<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.NoticeViewModel"%>
<%@page import="java.util.ArrayList"%>
<%
ArrayList<NoticeViewModel> noti = (ArrayList<NoticeViewModel>)request.getAttribute("notice");
String pgno = request.getParameter("pgno");
int ppp = Integer.parseInt(pgno);
int pageview = (int)request.getAttribute("pageview");
int startpage = (int)request.getAttribute("startpage"); 
int pagenumber = (int)request.getAttribute("pagenumber");
if(noti.size()!=0){
%>
<meta charset="UTF-8">
<p>공지사항 관리페이지</p>

<form id="ff" name="ff">

<div class="subpage_view">
<ul>

    <li><input type="checkbox" name="allck" id="allck" onclick="allckbox()"></li>
    <li>NO</li>
    <li>제목</li>
    <li>글쓴이</li>
    <li>날짜</li>
    <li>조회</li>
</ul>

<%
for(NoticeViewModel nlist : noti){
	String boxno = nlist.getIdx();
	String bbn = nlist.getIdx();
	String boxno1 = nlist.getOutput();
	if(boxno1.equals("Y")){
		boxno ="[공지]";
	}
%>

<ol>
		<input type="hidden" id="ckboxx" value="0">
    <li><input type="checkbox" id="bbox<%=nlist.getIdx()%>" name="ckbox" value="<%=nlist.getIdx()%>" onclick="boxon(this.id);" ></li>
    <li><%=boxno %></li>
    <li onclick="modify(<%=bbn%>)"><%=nlist.getSubject() %></li>
    <li><%=nlist.getWriter() %></li>
    <li><%=nlist.getIndate().substring(0,10)%></li>
    <li><%=nlist.getPageco() %></li>
</ol>
<%

	}
}


else{
%>
<div class="subpage_view">
<ul>
    <li><input type="checkbox"></li>
    <li>NO</li>
    <li>제목</li>
    <li>글쓴이</li>
    <li>날짜</li>
    <li>조회</li>
</ul>
<ol class="none_text">
    <li>등록된 공지 내용이 없습니다.</li>
</ol>
</div>
<%

 }
%>

</form>
<div class="board_btn">
    <button class="border_del" type="button" onclick="noticedel();">공지삭제</button>
    <button class="border_add"  type="button" onclick="noticeins()">공지등록</button>
</div>

<div class="border_page">
    <ul class="pageing">
        <%
	   int p =1;
       int to1 =0;
       if(ppp >= 2){ to1 = ppp-1; }else{ to1 = ppp+0;}
        %>
        <li onclick = "pagego(<%=p%>)-1"><img src="./ico/double_left.svg"></li>
        <li onclick = "pagego(<%=to1%>)"><img src="./ico/left.svg"></li>
        <%
	while(p <= pagenumber){
		%>	
        <li onclick = "pagego(<%=p %>)"><%=p %></li>
		<% 
		p++;
		}
        int to2=0;
        if(pagenumber > ppp){
        	to2 = ppp+1;
        }else if(pagenumber <=ppp){
        	to2 = ppp+0;
        }
%>  
        <li onclick = "pagego(<%=to2%>)"><img src="./ico/right.svg"></li>
        <li onclick = "pagego(<%=pagenumber %>)"><img src="./ico/double_right.svg"></li>
    </ul>
</div>


<script>
// 전체 체크 
function allckbox() {
	var ck = document.getElementById("allck");
	var ckboxx = document.getElementById("ckboxx");
	var ea = ff.ckbox.length;
	var w=0;
	if(ck.checked == true){
		while(w<ea){
		ff.ckbox[w].checked=true;
		ckboxx.value= ea;
			w++;
		}
	}else{
		while(w<ea){
			ff.ckbox[w].checked=false;
			ckboxx.value= 0;
			w++;
		}
	 }
}
function boxon(a1){
	var allck = document.getElementById("allck");
	
	var bb = document.getElementById(a1);
	var ck = bb.checked; // true  
	
	var ckboxx = document.getElementById("ckboxx");
	var ckea = ckboxx.length;
	console.log(ckea);
	
	var ea = ff.ckbox.length;
	
	if(ck==false){
		allck.checked=false;
		ckboxx.value = ckboxx.value +2;
		console.log(ckboxx.value)
		
	
	}else{
		if(ck==true){
		   ckboxx.value + 1;
			console.log(ckboxx.value);
		}
		
// 	if(){
// 			allck.checked=true;
// 		}
		
	}
	
}

function noticedel() { 
	var ck = document.getElementsByName("ckbox");
	var result = false;
	var count = "";
	for(var f=0; f<ck.length; f++){
		if(ck[f].checked==true){
			result = true;
			count += ck[f].value;
			
		}
	}
	
	if(result == false){
		alert("삭제할 공지를 선택하세요")
	}else if (	confirm("삭제하시겠습니까?") == true ){
		ff.method="post";
		ff.action="./noti_del.do?"
		ff.enctype="application/x-www-form-urlencoded";
		ff.submit();
	  	}
}

function modify(no){ // 수정 파트 
	
 location.href = './admin_notice_view.jsp?no=' + no;
 	
}
function noticeins(){ //공지등록 
	location.href = "./admin_notice_write.jsp";
}
//pageing
function pagego(pgno) {
	location.href = './notice?pgno='+pgno;
	}
</script>
