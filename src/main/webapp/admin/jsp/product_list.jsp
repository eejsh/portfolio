<%@page import="java.util.ArrayList"%>
<%@page import="model.ProductViewModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<ProductViewModel> bb = (ArrayList<ProductViewModel>)request.getAttribute("pselect");
String pgno = request.getParameter("pgno");
int ppp = Integer.parseInt(pgno);
int pageview = (int)request.getAttribute("pageview");
int startpage = (int)request.getAttribute("startpage"); 
int pagenumber = (int)request.getAttribute("pagenumber");
int total = (int)request.getAttribute("total");
String psearch = (String)request.getAttribute("psearch");
String poption = (String)request.getAttribute("poption");
int ea = bb.size();

if(psearch == null ) {
	psearch ="";
}
// <%if(ptype.equals("2"))out.print("selected");
%>

<meta charset="UTF-8">

<style>
.imgs{width: 130px; height: 27px;  }
</style>

<p>상품관리 페이지</p>
<input type="hidden" name="ctn" id="ctn" value="0">
<input type="hidden" name="allea" id="allea" value="<%=ea %>">

<form name="f" id="f">
<div class="subpage_view">
    <span>등록된 상품 <%=total%>건</span>
    <span>
        <select class="p_select1" name="poption">
			<option value="0">선택</option>
            <option value="1">상품명</option>
            <option value="2">상품코드</option>
                    </select> 
	<input type="hidden" name="pgno" value="<%=pgno%>">
       <input type="text" class="p_input1" placeholder="검색어를 입력해 주세요" name="psearch" value="<%=psearch%>">
        <input type="button" value="검색" title="상품검색" class="p_submit"  onclick="prosearch()">
        
    </span>
</div>
<div class="subpage_view2">
    <ul>
        <li><input type="checkbox" name="allck" id="allck" onclick="allbox1()" ></li>
        <li>코드</li>
        <li>이미지</li>
        <li>상품명</li>
        <li>카테고리 분류</li>
        <li>판매가격</li>
        <li>할인가격</li>
        <li>할인율</li>
        <li>재고현황</li>
        <li>판매유/무</li>
        <li>품절</li>
        <li>관리</li>
    </ul>
    
    <%
          String thumb = null;
          ///portfolio/img
           if(bb.size()!=0){
        	   for(ProductViewModel ps : bb){
        		 if(ps.getPimg1()=="" || ps.getPimg1()==null){
        	 thumb ="<img src='../upload/none.png'  class='imgs'>";
        	 
        		 }else{
        	  thumb ="<img src =."+ ps.getPimg1() +" class='imgs'>";
        		 }
        %>
    <ul>
        <li><input type="checkbox" id="xbox<%=ps.getIdx() %>" name="xbox" value=" <%=ps.getIdx() %>" onclick="boxoff(this.id);"></li>
        <li><%=ps.getPcode() %></li>
        <li><%=thumb%></li>
        <li><%=ps.getPname() %></li>
        <li><%=ps.getCatecode() %></li>
        <li><%=ps.getPprice() %></li>
        <li><%=ps.getPdisprice() %></li>
        <li><%=ps.getPdisrate() %> %</li>
        <li><%=ps.getPstock() %></li>
        <li><%=ps.getPsale() %></li>
        <li><%=ps.getPout() %></li>
        <li>수정</li>
    </ul>
    <%
	   }
  }else{
    %>
    <ul>
        <li style="width: 100%;">등록된 상품이 없습니다.</li>
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
        <li onclick = "pagego(<%=p%>)"><img src="./ico/double_left.svg"></li>
        <li onclick = "pagego(<%=to1%>)"> <img src="./ico/left.svg"></li>
        <%
while(p <= pagenumber){
%>
        <li onclick = "pagego(<%=p%>)"><%=p %></li>
<% 
p++;
}
        int to2=0;
        if(pagenumber > ppp){
        	to2=  ppp+1;
        }else if(pagenumber <=ppp){
       		to2= ppp+0;
        }
        
%>  
  			<li onclick = "pagego(<%=to2%>)">
  			 <img src="./ico/right.svg"></li>
        <li onclick = "pagego(<%=pagenumber %>)"><img src="./ico/double_right.svg"></li>
    </ul>
</div>
</form>
<div class="subpage_view4">
    <input type="button" value="선택상품 삭제" title="선택상품 삭제" class="p_button" onclick="del()">
    <span style="float: right;">
    <input type="button" value="신규상품 등록" title="신규상품 등록" class="p_button p_button_color1" onclick="newpro()">
    <input type="button" value="카테고리 등록" title="카테고리 등록" class="p_button p_button_color2" onclick="catewt()">
    </span>
</div>

<script>
//삭제 box..
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
		ctn = Number(ctn)-1;
		console.log(ctn)
		allck.checked=false;
		
	}else{
		ctn = Number(ctn)+1;
		console.log(ctn)
		if(totals == ctn){
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
		alert("삭제할 상품를 선택하세요")
	}else if (	confirm("삭제 하시겠습니까?") == true ){
		
		f.method="post";
		f.action="./productdel.do";
		f.enctype="application/x-www-form-urlencoded";
		f.submit();
	}
}





function catewt() {
	location.href ="./category?pgno=1";
	}
function newpro() {
	location.href ="./admin_product_write.jsp";
}
function pagego(pgno){
	
	var a = f.poption.value;
    var b = f.psearch.value;
    location.href = './product?pgno=' + pgno +  '&poption=' + a + '&psearch=' +b  ;  
    
    if(b != null){
    	 location.href = './product?pgno=' + pgno +  '&poption=' + <%=poption%> + '&psearch=' + b  ;  
    }
    
    
		
}
function prosearch() {
	var pgno = <%= pgno %>
	var a = f.poption.value;
    var b = f.psearch.value;
    
  	 if(b != null){
	location.href = './product?pgno=' + pgno + '&poption=' +a + '&psearch=' + b;
	}	
}

</script>


    