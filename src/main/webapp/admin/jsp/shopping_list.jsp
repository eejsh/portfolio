<%@page import="model.CouponViewModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%
   ArrayList<CouponViewModel> culist = (ArrayList<CouponViewModel>)request.getAttribute("coupon");
  int ea = culist.size();
	   %> 
<p>쇼핑몰 관리 페이지</p>
    <div class="subpage_view">
        <span>※ 쿠폰발행 현황 리스트 | <em>[ 사용중인 쿠폰 : <%=ea %> 개  ]</em></span>
        <span>
         <ol class="coupon_title">
            <li>번호</li>
            <li>쿠폰명</li>
            <li>쿠폰종류</li>
            <li>사용시작일</li>
            <li>사용종료일</li>
            <li>쿠폰타입</li>
            <li>할인금액</li>
            <li>최소주문금액</li>
         </ol>
         <%
         if(culist.size() != 0){
        	 for(CouponViewModel pp : culist){
        		 String type = null;
        		 int cc = Integer.parseInt(pp.getCtype());
        		 String dd = null;
        		 if(cc == 1){
        			type="정액할인";
        			dd = "원" ;        			
        			}else{
        			type="정률할인";
        			dd = "%";
        		}
         %>
         <ol class="coupon_lists">
            <li><%= pp.getIdx() %></li>
            <li><%=pp.getCname() %></li>
            <li><%=pp.getCkind() %></li>
            <li><%=pp.getCsttime().substring(0,19) %></li>
            <li><%=pp.getClstime().substring(0,19) %></li>
            <li><%=type %></li>
            <li><%=pp.getCrate()+dd %></li>
            <li><%=pp.getCmin() %></li>
         </ol>
       <%
        	 }  
}else{
	       %>  
         <ol class="coupon_none_lists">
            <li>등록된 쿠폰이 없습니다.</li>
        </ol>
        <%
}
        %>
        </span>
         <span><input type="button" value="쿠폰 등록하기" class="shopping_btn" onclick="couponadd()"></span>
    </div>
    
    <script>
    function couponadd() {
	location.href="./admin_coupon_config.jsp";
	}
    
    </script>