<%@page import="model.CategoryViewModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ArrayList<CategoryViewModel> aaa = (ArrayList<CategoryViewModel>)request.getAttribute("clist");
        String pgno = request.getParameter("pgno");
        
        int ppp= Integer.parseInt(pgno);
        int pageview = (int)request.getAttribute("pageview");  // 개시글 갯수 
        int startpage = (int)request.getAttribute("startpage");  //  시작 페이지 
        int pagenumber = (int)request.getAttribute("pagenumber"); //페이징갯수  
        int total = (int)request.getAttribute("total");  //총 데이터
        int ea = aaa.size();
        String csearch= (String)request.getAttribute("csearch");
        String coption = (String)request.getAttribute("coption");
	
        
        if(csearch == null){
        	csearch ="";
        }
    %>

<meta charset="UTF-8">
<p>카테고리관리 페이지</p>

<div class="subpage_view">
    <span>등록된 카테고리 <%=total%>건</span>
    <span>
        
      <input type="hidden" name="ctn" id="ctn" value="0">
      <input type="hidden" name="allea" id="allea" value="<%=ea%>">

     <form id="frm" name="frm">
     <input type="hidden" name="pgno" value="<%=pgno %>">
     
        <select class="p_select1" name="coption">
       		<option value="0" >옵션을 선택하세요.</option>
            <option value="1" >카테고리명</option>
            <option value="2" >카테고리코드</option>
        </select>
        <input type="text" class="p_input1" placeholder="검색어를 입력해 주세요" name="csearch" value="<%=csearch %>">
        <input type="button" value="검색" title="카테고리 검색" class="p_submit" onclick="catesearch()">
       
    </span>
</div>
<div class="subpage_view2">
    <ul>
        <li><input type="checkbox" name="allck" id="allck" onclick="allbox1()" ></li>
        <li>분류코드</li>
        <li>대메뉴 코드</li>
        <li>대메뉴명</li>
        <li>소메뉴 코드</li>
        <li>소메뉴명</li>
        <li>사용 유/무</li>
        <li>관리</li>
    </ul>
    <%
    if(aaa.size()!=0){
            	for(CategoryViewModel cc : aaa){
    %>

    
    <ul>
     <li><input type="checkbox" id="xbox<%=cc.getIdx() %>" name="xbox" value=" <%=cc.getIdx() %>" onclick="boxoff(this.id);"></li>
        <li style="text-align: left; text-indent: 5px;"><%=cc.getCcode() %></li>
        <li><%=cc.getC1code() %></li>
        <li style="text-align: left; text-indent: 5px;"><%=cc.getC1codenm() %></li>
        <li><%=cc.getC2code() %></li>
        <li style="text-align: left; text-indent: 5px;"><%=cc.getC2codenm() %></li>
        <li><%=cc.getCateuse() %></li>
        <li>수정</li>
    </ul>
    
    <%
}
	
}
else{
	
	
    %>
    
    <ul>
        <li style="width: 100%;">등록된 카테고리가 없습니다.</li>
    </ul>
<%
}
%>

</div>
<div class="subpage_view3">
    <ul class="pageing">
        <%
int p =1;
       int to1 =0;
       if(ppp >= 2){
    	   to1 = ppp-1;
       }else{
    	   to1 = ppp+0;
       }
        
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


 </form>


<div class="subpage_view4">
    <input type="button" value="카테고리 삭제" title="카테고리 삭제" class="p_button" onclick='del()'>
    <span style="float: right;">
    <input type="button" value="상품 리스트" title="상품 리스트" class="p_button p_button_color1" onclick="back()">
    <input type="button" value="카테고리 등록" title="카테고리 등록" class="p_button p_button_color2" onclick="catewt11()">
    </span>
    
</div>

<script>
//전체 박스 
function allbox1() {
	var ck = document.getElementById("allck");
	var boxea = document.getElementsByName("xbox");
	var ea = boxea.length; 
	var w=0;
	if(ck.checked == true){
		while(w<ea){
		boxea[w].checked=true;
			w++;	
		}
	}else{
		while(w<ea){
			boxea[w].checked=false;
			w++;
		}
	 }
}
function boxoff(a1) {
	var allck = document.getElementById("allck");
	var bb = document.getElementById(a1);
	var ck = bb.checked;
	var ctn = document.getElementById("ctn").value;
	var totals = document.getElementById("allea").value;
	
	if(ck==false){
		document.getElementById("ctn").value = Number(ctn) - 1;
		allck.checked=false;
	}else{
		document.getElementById("ctn").value = Number(ctn) + 1;
		
		if(totals==document.getElementById("ctn").value){
		allck.checked=true;
		}
	}
	

}
  

function del(){
	
	var ar = document.getElementsByName("xbox");
	var result = false;
	var count = "";
	for(var i=0; i<ar.length; i++){
		if(ar[i].checked == true){
			result = true;
			count += ar[i].value;
		}
	}
	if(result == false){
		alert("삭제할 카테고리를 선택하세요")
	}else if (	confirm("삭제하시겠습니까?") == true ){
		frm.method="post";
		frm.action="./list_catedel.do?";
		frm.enctype="application/x-www-form-urlencoded";
		frm.submit();
		
	}
		
}
	
function pagego(pgno) {
	var a = frm.coption.value;
    var b = frm.csearch.value;
    
    location.href = './category?pgno=' + pgno + '&coption=' +a + '&csearch=' + b
    
    if(b != null){
	location.href = './category?pgno=' + pgno + '&coption=' +<%=coption%> + '&csearch=' + b
    }
    
    
}
		
		
function catewt11() {
	location.href ="./admin_category_write.jsp";
}
function back() {
	location.href ="./product?pgno=1";
}

function catesearch() {
	var pgno = <%= pgno %>
	var a = frm.coption.value;
    var b = frm.csearch.value;
  
    if(b != null){
	location.href = './category?pgno=' + pgno + '&coption=' +a + '&csearch=' + b;
	}	
}
</script>
