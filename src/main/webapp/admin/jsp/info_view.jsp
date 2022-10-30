<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import= "dataconfig.DbConfig" %>
<%
  request.setCharacterEncoding("utf-8");
  response.setContentType("text/html; charset=utf-8");
  String mid = id;
  String sql ="select * from login where mid = '"+mid+"' ";
  DbConfig db = new DbConfig();
  Connection con = DbConfig.cafe24();
  
  PreparedStatement ps = con.prepareStatement(sql);
  ResultSet rs = ps.executeQuery();
  
  ArrayList<String> aa = new ArrayList<>();
  while(rs.next()){
	  aa.add(0, rs.getString("mid"));
	  aa.add(1, rs.getString("mname"));
	  aa.add(2, rs.getString("memail"));
	  aa.add(3, rs.getString("mtel1"));
	  aa.add(4, rs.getString("mtel2"));
	  aa.add(5, rs.getString("mtel3"));
	  aa.add(6, rs.getString("mdepart"));
	  aa.add(7, rs.getString("mpo"));
  }
  con.close();
    %>
    
    <form name="f" id="f">
    
    <div class="admin_login_add">
    <ul>
        <li class="font_color1">관리자 정보 수정</li>
        <li>
        <input type="text" class="add_input1" readonly value="<%=id%>" name="mid">
        </li>


        <li>
            <input type="password" class="add_input1" placeholder="변경할 패스워드를 입력하세요" name="mpass" >
            <input type="password" class="add_input1" placeholder="동일한 패스워드를 입력하세요" name="mpassck">
        </li>
        
        <li class="font_color1">관리자 기본정보 입력</li>
        <li>
            <input type="text" class="add_input1" name="mname" value="<%=aa.get(1) %>" placeholder="변경할 담당자 이름을 입력하세요.">
        </li>
        <li>
        <input type="text" class="add_input1 emails" name="memail" value="<%=aa.get(2) %>" placeholder="변경할 담당자 이메일을 입력하세요.">
        </li>
        <li class="font_color1">
                <input type="text" class="add_input1 hp1" placeholder="HP" value="010" maxlength="3" name="mtel1" value="<%=aa.get(3)%>">
                -
                <input type="text" class="add_input1 hp2" placeholder="1234" maxlength="4" name="mtel2" value="<%=aa.get(4)%>">
                -
                <input type="text" class="add_input1 hp2" placeholder="5678" maxlength="4" name="mtel3" value="<%=aa.get(5)%>">
                </li>
        <li class="font_color1">관리자 담당부서 및 직책</li>
        <li>
            <select class="add_select1" name="mdepart" >
                        <option <%if(aa.get(6).equals("0"))out.print("selected"); %> value="0">담당자 부서를 선택하세요</option>
                        <option <%if(aa.get(6).equals("1"))out.print("selected"); %> value="1">임원</option>
                        <option <%if(aa.get(6).equals("2"))out.print("selected"); %> value="2">전산팀</option>
                        <option <%if(aa.get(6).equals("3"))out.print("selected"); %> value="3">디자인팀</option>
                        <option <%if(aa.get(6).equals("4"))out.print("selected"); %> value="4">CS팀</option>
                        <option <%if(aa.get(6).equals("5"))out.print("selected"); %> value="5">MD팀</option>
            </select>
            <select class="add_select1" name="mpo">
                        <option <%if(aa.get(7).equals("0"))out.print("selected"); %> value="0">담당자 직책을 선택하세요</option>
                        <option <%if(aa.get(7).equals("1"))out.print("selected"); %> value="1">대표</option>
                        <option <%if(aa.get(7).equals("2"))out.print("selected"); %> value="2">부장</option>
                        <option <%if(aa.get(7).equals("3"))out.print("selected"); %> value="3">팀장</option>
                        <option <%if(aa.get(7).equals("4"))out.print("selected"); %> value="4">과장</option>
                        <option <%if(aa.get(7).equals("5"))out.print("selected"); %> value="5">대리</option>
                        <option <%if(aa.get(7).equals("6"))out.print("selected"); %> value="6">사원</option>
                    </select>
                    
        </li>
	</form>
    
        <li class="font_color1">※ 아이디 외에 모든 정보는 수정 할 수 있습니다.</li>
    </ul>
    <span class="admin_addbtn">
        <button type="button" class="btn_button btncolor1" title="관리자 등록"  onclick="update()">정보 수정</button>
    </span>
</div>



<script>
function update() {
//	./	실제 있는 주소..

	if(f.mpass.value.length < 4){
			alert("비밀번호는 4자리 이상 16자리 이하 입니다. ");	
	}
	else if(f.mpass.value != f.mpassck.value){
			alert("동일한 비밀번호를 입력하세요.");
	}else{	
		f.method= "POST";		
		f.action= "admin_update.do";
		f.enctype= "application/x-www-form-urlencoded";
		f.submit();
	 }
	
}
	    </script>

