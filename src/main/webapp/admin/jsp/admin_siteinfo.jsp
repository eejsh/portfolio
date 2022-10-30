<%@page import="model.SiteInfoDBModel" %>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String ck = "Y";
%>

 <%
 ArrayList<SiteInfoDBModel> ssi = (ArrayList<SiteInfoDBModel>)request.getAttribute("siteinfo");
   for( SiteInfoDBModel aab : ssi ){
 %>

<meta charset="UTF-8">
<p style="width:200px;">홈페이지 가입환경 설정</p>
<div class="subpage_view">
<ul class="info_form">
    <li>홈페이지 제목</li>
    <li>
          <input type="text" class="in_form1" name="hhomename" id="hhomename" value="<%=aab.getHhomename()%>"> 
    </li>
</ul>
<ul class="info_form">
    <li>관리자 메일 주소</li>
    <li>
       <input type="text" class="in_form2" name="hsiteemail" id="hsiteemail" value="<%=aab.getHsiteemail() %>" > ※ 관리자가 보내고 받는 용도로 사용하는 메일 주소를 입력합니다.(회원가입,인증메일,회원메일발송 등에서 사용)
      </li>
</ul> 
<ul class="info_form">
    <li>포인트 사용 유/무</li>
    <li class="checkcss">   
        <em><label><input type="radio" class="ckclass" value="Y" name="pointuse" id="pointuse" <%if(aab.getPointuse().equals("Y"))out.print("checked");%>>포인트 사용</label></em> 
        <em><label><input type="radio" class="ckclass" value="N" name="pointuse" id="pointuse" <%if(aab.getPointuse().equals("N"))out.print("checked");%>>포인트 미사용</label></em>
    </li>
</ul>
<ul class="info_form2" style="border-bottom:1px solid rgb(81, 61, 61);">
    <li>회원가입시 적립금</li>
    <li>
        <input type="text" class="in_form3" maxlength="10" name="point" id="point" value="<%=aab.getPoint() %>"> 점
    </li>
    <li>회원가입시 권한레벨</li>
    <li>
        <input type="text" class="in_form3" maxlength="1" name="basiclevel" id="basiclevel" value="<%=aab.getBasiclevel() %>" > 레벨
    </li>
</ul> 
</div>
<p style="width:200px;">홈페이지 기본환경 설정</p>
<div class="subpage_view">
<ul class="info_form2">
    <li>회사명</li>
    <li>
        <input type="text" class="in_form0" name="hcpnm" id="hcpnm" value="<%=aab.getHcpnm() %>" > 
    </li>
    <li>사업자등록번호</li>
    <li>
        <input type="text" class="in_form0" name="hcpreginum" id="hcpreginum" value="<%=aab.getHcpreginum() %>" >  
    </li>
</ul> 
<ul class="info_form2">
    <li>대표자명</li>
    <li>
        <input type="text" class="in_form0" name="hcpceo" id="hcpceo" value="<%=aab.getHcpceo() %>">  
    </li>
    <li>대표전화번호</li>
    <li>
        <input type="text" class="in_form0" name="hcptel" id="hcptel" value="<%=aab.getHcptel()%>"> 
    </li>
</ul>
<ul class="info_form2">
    <li>통신판매업 신고번호</li>
    <li>
        <input type="text" class="in_form0" name="hcpereginum" id="hcpereginum" value="<%=aab.getHcpereginum()%>"> 
    </li>
    <li>부가통신 사업자번호</li>
    <li>
        <input type="text" class="in_form0" name="hcpesubreginum" id="hcpesubreginum" value="<%=aab.getHcpesubreginum() %>"> 
    </li>
</ul>
<ul class="info_form2">
    <li>사업장 우편번호</li>
    <li>
        <input type="text" class="in_form0" name="hcpzcord" id="hcpzcord" value="<%=aab.getHcpzcord()%>"> 
    </li>
    <li>사업장 주소</li>
    <li>
        <input type="text" class="in_form2" name="hcpaddr" id="hcpaddr" value="<%=aab.getHcpaddr()%>"> 
    </li>
</ul>
<ul class="info_form2" style="border-bottom:1px solid rgb(81, 61, 61);">
    <li>정보관리책임자명</li>
    <li>
        <input type="text" class="in_form0" name="hcpcpo" id="hcpcpo" value="<%=aab.getHcpcpo()%>">  
    </li>
    <li>정보책임자 E-mail</li>
    <li>
        <input type="text" class="in_form1" name="hcpcpomail" id="hcpcpomail" value="<%=aab.getHcpcpomail() %>"> 
    </li>
</ul>
</div>
<p>결제 기본환경 설정</p>
<div class="subpage_view">  
<ul class="info_form2">
    <li>무통장 은행</li>
    <li>
        <input type="text" class="in_form0" name="abanknm" id="abanknm" value="<%=aab.getAbanknm() %>"> 
    </li>
    <li>은행계좌번호</li>
    <li>
        <input type="text" class="in_form1" name="abanknum" id="abanknum" value="<%=aab.getAbanknum() %>"> 
    </li>
</ul>
<ul class="info_form">
    <li>신용카드 결제 사용</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" name="acard" id="acard" value="Y"<%if(aab.getAcard().equals("Y"))out.print("checked");%>> 사용</label></em> 
        <em><label><input type="radio" class="ckclass" name="acard" id="acard" value="N"<%if(aab.getAcard().equals("N"))out.print("checked");%>> 미사용</label></em> ※ 해당 PG사가 있을 경우 사용으로 변경합니다.
    </li>
</ul>
<ul class="info_form">
    <li>휴대폰 결제 사용</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" name="aphone" id="aphone" value="Y" <%if(aab.getAphone().equals("Y"))out.print("checked");%> > 사용</label></em> 
        <em><label><input type="radio" class="ckclass" name="aphone" id="aphone" value="N" <%if(aab.getAphone().equals("N"))out.print("checked");%> > 미사용</label></em> ※ 주문시 휴대폰 결제를 가능하게 할 것인지를 설정합니다.
        
    </li>
</ul>
<ul class="info_form">
    <li>도서상품권 결제사용</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" name="agiftmoney" id="agiftmoney" value="Y" <%if(aab.getAgiftmoney().equals("Y"))out.print("checked"); %>  > 사용</label></em> 
        <em><label><input type="radio" class="ckclass" name="agiftmoney" id="agiftmoney" value="N" <%if(aab.getAgiftmoney().equals("N"))out.print("checked"); %> > 미사용</label></em> ※ 도서상품권 결제만 적용이 되며, 그 외에 상품권은 결제 되지 않습니다.
    </li>
</ul>
<ul class="info_form2">
    <li>결제 최소 포인트</li>
    <li>
        <input type="text" class="in_form0" name="aminpo" id="aminpo" maxlength="10" value="<%=aab.getAminpo()%>"> 점
    </li>
    <li>결제 최대 포인트</li>
    <li>
        <input type="text" class="in_form0" name="amaxpo" id="amaxpo" maxlength="10" value="<%=aab.getAmaxpo()%>"> 점
    </li>
</ul>
<ul class="info_form">
    <li>현금 영수증 발급사용</li>
    <li class="checkcss">
<%--     <%if(aab.getAcashrece().equals("Y"))out.print("checked");%> --%>
        <em><label><input type="radio" class="ckclass" name="acashrece" id="acashrece" value="Y"  <%if(aab.getAcashrece().equals("Y"))out.print("checked"); %> >사용</label></em> 
        <em><label><input type="radio" class="ckclass" name="acashrece" id="acashrece" value="N"  <%if(aab.getAcashrece().equals("N"))out.print("checked"); %>>미사용</label></em> ※ 현금영수증 관련 사항은 PG사 가입이 되었을 경우 사용가능 합니다.
    </li>
</ul>
<ul class="info_form2">
    <li>배송업체명</li>
    <li>
        <input type="text" class="in_form0" name="ashipcom" id="ashipcom" value="<%=aab.getAshipcom() %>"> 
    </li>
    <li>배송비 가격</li>
    <li>
        <input type="text" class="in_form0" maxlength="5" name="ashipcost" id="ashipcost" value="<%=aab.getAshipcost()%>"> 원
    </li>
</ul>
<ul class="info_form" style="border-bottom:1px solid rgb(81, 61, 61);">
    <li>희망배송일</li>
    <li class="checkcss">

        <em><label><input type="radio" class="ckclass" name="ashipday" id="ashipday" value="Y" <%if(aab.getAshipday().equals("Y"))out.print("checked"); %> > 사용</label></em> 
        <em><label><input type="radio" class="ckclass" name="ashipday" id="ashipday" value="N" <%if(aab.getAshipday().equals("N"))out.print("checked"); %>> 미사용</label></em> ※ 희망배송일 사용시 사용자가 직접 설정 할 수 있습니다.
    </li>
</ul>
</div>
<%
}
%>


<div class="btn_div">
    <button type="button" class="sub_btn1" title="설정 저장" onclick="a(1);">설정 저장</button>
    <button type="button" class="sub_btn2" title="저장 취소" onclick="a(2);">저장 취소</button>
</div>


<script>
function a(no){
console.log(no)
	if(no==2){
		confirm("취소하시겠습니까??");
	    location.reload();
}	else if(no==1){
		frm.method="POST";		
		frm.action="siteinfoinsert.do";
		frm.enctype="application/x-www-form-urlencoded";
		frm.submit();	
		}
}
</script>