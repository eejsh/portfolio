<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String ck = (String)request.getAttribute("c1codeonly");
String check = "";
if(ck=="Y"){
check = "checked";
}
%>

<meta charset="UTF-8">
<p>카테고리 등록 페이지</p>
<form name="f" id="f" action="./list_category.do" method="post">
<div class="cate_insert">
    <ul>
        <li>분류코드</li>
        <li><input type="text" class="cate_input1" readonly name="ccode" id="ccode"></li>
        <li>※ 분류코드는 대메뉴 코드와 소메뉴 코드를 합쳐서 자동 입력 됩니다.</li>
    </ul>
    <ul>
        <li>대메뉴 코드</li>
        <li>
            <input type="text" class="cate_input2" list="lg_menu1" name="c1code" id="c1code" onkeyup="plus()">
            <datalist id="lg_menu1">
                <option>1</option>
                <option>2</option>
                <option>3</option>
            </datalist>
        </li>
        <li>※ 대메뉴에 사용할 코드 번호를 입력하세요 최소 2자 이상의 숫자로 입력하셔야 합니다.</li>
    </ul>
    <ul>
        <li>대메뉴명</li>
        <li><input type="text" class="cate_input3" name="c1codenm">
        <label><input type="checkbox" style="margin-left:10px; margin-right: 5px;"id="ckbox11" name="c1codeonly" onclick="ckbox();" value="Y"<%=check%> >대메뉴만 생성</label></li>
        <li>※ 소메뉴만 등록시 대메뉴 코드와 대메뉴명은 무조건 입력 되어야 합니다.</li>
    </ul>
    <ul>
        <li>소메뉴 코드</li>
        <li>
            <input type="text" class="cate_input2" list="lg_menu2" name="c2code" id="c2code" onkeyup="plus()">
            <datalist id="lg_menu2">
                <option>1</option>
                <option>2</option>
                <option>3</option>
            </datalist>
        </li>
        <li>※ 소메뉴에 사용할 코드 번호를 입력하세요 최소 2자 이상의 숫자로 입력하셔야 합니다.</li>
    </ul>
    <ul>
        <li>소메뉴명</li>
        <li><input type="text" class="cate_input3" name="c2codenm"></li>
        <li>※ 대메뉴만 등록시 소메뉴 코드 및 소메뉴명은 입력하지 않으셔도 됩니다.</li>
    </ul>
    <ul>
        <li>사용 유/무</li>
        <li>
            <label class="rmargin"><input type="radio" value="Y" name="cateuse" id="cateuse" checked>사용함 </label>
            <label class="rmargin"><input type="radio" value="N" name="cateuse" id="cateuse" onclick="no();">사용안함</label>
        </li>
        <li>※ 카테고리 사용안함으로 설정시 쇼핑몰에 해당 메뉴는 생성 되지 않습니다.</li>
    </ul>
</div>
</form>


<div class="subpage_view4" style="text-align:center;">
    <input type="button" value="카테고리 리스트" title="카테고리 리스트" class="p_button p_button_color1" style="margin-right: 5px;"  onclick="catelist()">
    <input type="button" value="카테고리 생성" title="카테고리 생성" class="p_button p_button_color2" onclick="cafeinse()">
    </span>
</div>

<script>

function catelist() {
	 location.href="category?pgno=1";
}

function plus() {
	var c1code = document.getElementById("c1code").value;
	var c2code = document.getElementById("c2code").value;
	
	var to = c1code + c2code;
	var ccode = document.getElementById("ccode").value= to;
	
}

function ckbox() {
	if(f.c1codeonly.checked==true){
		//카데1 체크 되었을 때 카테고리 
		//f.catecode2value=""
		//f.catecode2.readOnly=true;
		f.c2code.value="";
		f.c2codenm.value="";
		f.c2code.readOnly=true;
		f.c2codenm.readOnly=true;
		document.getElementById("ccode").value = f.c1code.value;
	}else{
		f.c2code.readOnly=false;
		f.c2codenm.readOnly=false;
		
	}
}
function cafeinse() {
var ck = document.getElementById("ckbox11").value;  // Y
var c1only = f.c1codeonly.checked; // false 
var use = f.cateuse.value; // Y
	if(f.c1code.value==""){
			alert("대메뉴코드는 최소 2자 이상의 숫자로 입력하셔야 합니다.");
		}else if(f.c1codenm.value==""){
			alert("대메뉴명을 입력하세요.");
		}else if(c1only==false){
			if(f.c2code.value==null || f.c2code.value==""){
				alert("소메뉴 코드를 입력하세요.");
				}
			else if(f.c2codenm.value==""){
				alert("소메뉴명를 입력하세요.");
				}else{
				f.submit();
			}
		}else{
			f.submit();
			
		}
	}
function no() {
	if(	confirm("카테고리 사용안함 체크 시 해당 메뉴는 생성되지 않습니다.") == true ){
	}}

</script>

    