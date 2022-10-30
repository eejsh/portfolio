<%@page import="dataconfig.DbConfig"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   request.setCharacterEncoding("utf-8");
   response.setContentType("text/html; charset=utf-8");
   
    String idx = request.getParameter("no");
	String no = request.getParameter("no");
	
	DbConfig db = new DbConfig();
	Connection con = DbConfig.cafe24();

   // String sql1 = " update notice as a join (select max(pageco)+1 as pco "+
   // 			" from notice where idx= '"+no+"' ) as b set a.pageco  = b.pco where a.idx ="+no+"' ";
	
	
    String sql = "select * from notice where idx = '"+idx+"' ";
	PreparedStatement ps = con.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
   
     String sql1 = "update notice  set pageco = pageco + 1 where idx = '"+no+"' ";
     PreparedStatement ps1 = con.prepareStatement(sql1);
     ps1.executeUpdate();
    
    ArrayList<String> noti = new ArrayList<>();
   
    while(rs.next()){
	   noti.add(0, rs.getString("output"));
	   noti.add(1, rs.getString("subject"));
	   noti.add(2, rs.getString("writer"));
	   noti.add(3, rs.getString("upfile"));
	   noti.add(4, rs.getString("content"));
   }
   ps.close();
   con.close();
    
   %>
    
    <meta charset="UTF-8">
<p>공지사항 VIEW 페이지</p>


<input type="hidden" name="ckbox" value="<%=no %>">

<div class="write_view">
<ul>
    <li> 공지번호</li>
    <li><%=idx %>번째 공지글</li>
    
</ul>
<ul>
    <li>공지사항 여부</li>
    <li>
        <label class="label_notice"><em class="cbox"><input type="checkbox" <%if(noti.get(0).equals("Y"))out.print("checked"); %> name="output" value="Y" ></em> 공지 출력</label> ※ 공지출력을 체크할 시 해당 글 내용은 최상단에 노출 되어 집니다.
    </li>
</ul>
<ul>
    <li>공지사항 제목</li>
    <li>
        <input type="text" class="notice_input1" value="<%=noti.get(1)%>" name="subject" >
<!--          ※ 최대 150자까지 입력이 가능 -->
    </li>
</ul>
<ul>
    <li>글쓴이</li>
    <li>
        <input type="text" class="notice_input2" readonly value="<%=noti.get(2)%>"name="writer"> ※ 관리자 이름이 노출 됩니다.       
    </li>
</ul>
<ul>
    <li>첨부파일</li>
    <li>
        <input type="file" name="upfile"> ※ 새로운 첨부파일 적용시 기본 첨부파일은 삭제 됩니다.
        <em class="fileview">기존 첨부 파일명 : <%=noti.get(3)%></em>
    </li>
</ul>
<ul class="ul_height">
    <li>공지내용</li>
    <li>
        <textarea class="notice_input3" name="content"><%=noti.get(4)%></textarea>
         	            <script>
            
            CKEDITOR.replace("content",{
            	height:290,
            	width : '100%'
            });
            
            </script>
    </li>
</ul>
</div>

<div class="board_btn">
    <button class="border_list" type="button" onclick="list();" >공지목록</button>
    <button class="border_modify"type="button" onclick="b();">공지수정</button>
    <button class="border_del"type="button" onclick="c();">공지삭제</button>
</div>
<script>

function list() {
	location.href="./notice?pgno=1";
}



function b() { //수정
	var ct = CKEDITOR.instances.content.getData();
	var result = confirm("공지를 수정 하시겠습니까?");
	if(result){
		
		f.method ="POST";
		f.action = "notiupdate.do?no=" + <%=idx%>;
		f.enctype="multipart/form-data";
		f.submit();
		
	}else{
		
	    alert("취소 되었습니다.");
	}
	
}
function c() { //삭제
	if(	confirm("삭제하시겠습니까?") == true ){
		f.method="POST";
		f.action="./noti_del.do?";
		f.enctype="application/x-www-form-urlencoded";
		f.submit();
	}	
}
</script>

