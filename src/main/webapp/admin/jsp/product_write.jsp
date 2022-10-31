 <%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form name="fw" >

<meta charset="UTF-8">
<p>상품 등록 페이지</p>

<div class="product_insert">

    <ul>
 
        <li>대메뉴 카테고리</li>
   
        <li>
            <input type="text" class="product_input1" name="c1code">
        </li>
    </ul>
    <ul>
        <li>소메뉴 카테고리</li>
        <li>
            <input type="text" class="product_input1" name="c2code"> 
        </li>
    
    </ul>

    <ul>
        <li>상품코드</li>
        <li>
            <input type="text" class="product_input1" name="pcode" id="pcode" maxlength="6">  
            																				<!-- prock();  product_ajax();-->
            <input type="button" value="중복확인" title="중복확인" class="product_btn" onclick="prock();"> <span class="help_text">※ 상품코드는 절대 중복되지 않도록 합니다.(6자리 코드를 입력하세요.)</span>
        <input type="hidden" value=""  name="ck" id="ck">
        
        </li>
    </ul>
    <ul>
        <li>상품명</li>
        <li>
            <input type="text" class="product_input2" maxlength="100" id="pname" name="pname"> <span class="help_text">※ 상품명은 최대 100자까지만 적용할 수 있습니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품 부가설명</li>
        <li>
            <input type="text" class="product_input4" maxlength="200" id="padd" name="padd"> <span class="help_text">※ 상품명은 최대 200자까지만 적용할 수 있습니다.</span>
        </li>
    </ul>
    <ul>
        <li>판매가격</li>
        <li>
            <input type="text" class="product_input3" maxlength="7" name="pprice" id="pprice" onkeyup="proup(this.value)"> <span class="help_text">※ , 없이 숫자만 입력하세요 최대 7자리</span>
        </li>
    </ul>
    <ul>
        <li>할인율</li>
        <li>
            <input type="text" class="product_input3" maxlength="2" value="0" name="pdisrate" id="pdisrate" onkeyup="proup(this.value);" >% <span class="help_text" >※ 숫자만 입력하세요</span>
        </li>
    </ul>
    <ul>
        <li>할인가격</li>
        <li>
            <input type="text" class="product_input3" maxlength="7" value="" name="pdisprice" id="pdisprice" readonly> <span class="help_text" >※ 할인율이 0%일 경우 할인가격은 0으로 처리 합니다.</span>
 
        </li>
    </ul>
    <ul>
        <li>상품재고</li>
        <li>
            <input type="text" class="product_input3" maxlength="4" value="0" id="pstock" name="pstock">EA <span class="help_text">※ 숫자만 입력하세요. 재고가 0일 경우 soldout이 됩니다.</span>
        </li>
    </ul>
    <ul>
        <li>판매 유/무</li>
        <li>
            <label class="product_label">
            <input type="radio" name="psale" value="Y" style="vertical-align:-1px;" checked id="a"> 판매시작
            </label>
            <label class="product_label">
            <input type="radio" name="psale" value="N" style="vertical-align:-1px;" id="a"> 판매종료
            </label> <span class="help_text">※ 숫자만 입력하세요. 재고가 0일 경우 soldout이 됩니다.</span>
        </li>
    </ul>
    <ul>
        <li>조기품절</li>
        <li>
            <label class="product_label">
                <input type="radio" id="b" style="vertical-align:-1px;" value="Y" name="pout"> 사용
            </label>
            <label class="product_label">
                <input type="radio" id="b" style="vertical-align:-1px;" value="N" name="pout" checked> 미사용
            </label>
        </li>
    </ul>
    <ul style="height: 160px;">
        <li>상품 대표이미지</li>
        <li>
            <ol style="width:100%; height: auto;">
            <li style="width:100%; height:45px;">
         
            <input type="file" name="pimg1" id="pimg1">
            <span class="help_text">※ 상품 대표이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
            </li>
            <li style="height:45px;">
            <input type="file" name="pimg2" id="pimg2">
            <span class="help_text">※ 추가 이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
            </li>
            <li style="height:45px;">
            <input type="file" name="pimg3" id="pimg3">
            <span class="help_text">※ 추가 이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
            </li>
            </ol>
        </li>
    </ul>
    <ul style="height: 400px;">
        <li>상품 상세설명</li>
        <li>
            <textarea class="product_text1" name="ptext" id="ptext"></textarea>
            <script>
            
            CKEDITOR.replace("ptext",{
            	height:290,
            	width : '100%'
            });
            
            </script>
            
        </li>
    </ul>
</div>

</form>

<div class="subpage_view4" style="text-align:center;">
    <span>
    <input type="button" value="상품 리스트" title="상품 리스트" class="p_button p_button_color1" style="margin-right: 5px;" onclick="prolist()" >
    <input type="button" value="상품 등록" title="상품 등록" class="p_button p_button_color2" onclick="proinser()">
    </span>
</div>


<script>

function prolist() {
	location.href ="./product?pgno=1";
}

//중복체크 
function prock(){
var ckpro = document.getElementById("pcode").value;

if(ckpro==""){
alert("상품코드를 입력하세요.");
}
else if(ckpro.length < 6){
	alert("상품코드는 6자리를 입력하셔야 합니다. ");
}
else{

prock1(ckpro);
console.log(ckpro);
}
}

let pppok;

function prock1(ckpro) {
let ck;
function pck(){
if(window.XMLHttpRequest){
return new XMLHttpRequest();
	}
}
function ckget(){
if(ck.readyState==XMLHttpRequest.DONE && ck.status==200){
console.log(this.response);
if(this.response=="ok"){
alert("등록 가능한 상품 코드 입니다.");
fw.pcode.style.readOnly = true;
//fw.codeok.value="ok"; //히든에 값 넣어서 ok 사인 보내기
pppok = this.response;

}else{
alert("해당코드는 중복된 코드 입니다.");

}

}else if(ck.status==404){
console.log("통신오류");
}
}
ck = pck();
ck.onreadystatechange = ckget;
ck.open("GET", "./pro_ck.do?ckpro=" + ckpro , true);
ck.send();

}

//할인금액 자동계산
function proup(z) {

	if(fw.pprice.value==""){
		alert("할인율 적용 시 판매가격 1000원 이상 입력하세요.");
	}else{
	var pprice = document.getElementById("pprice").value;
	var pdisrate = document.getElementById("pdisrate").value;
	var t = pprice-((pdisrate / 100) * pprice);
	var total = Math.round(t);
	var pdisprice = document.getElementById("pdisprice").value=total;

	}
	
} 


function proinser(){
	var ct = CKEDITOR.instances.ptext.getData();
	//var ct = CKEIDTOR.instances.content.getData();

	var codeea = fw.pcode.value.length;
	console.log(codeea);
	
	if(fw.pcode.value==""){
	 alert("상품 코드를 입력하세요.");
	}else if(fw.pname.value==""){
	alert("상품명을 입력해 주세요.");
	}else if(fw.padd.value==""){
	alert("상품 부가 설명을 입력하세요.");
	}else if(fw.pprice.value==""){
	alert("판매 가격을 입력하세요.");
	}else if(fw.pdisrate.value==""){
	 alert("할인율을 입력하세요.");
	 }
	else if(fw.pstock.value==""){
	 alert("상품 재고를 입력하세요.");
	 }else if(fw.pimg1.value==null || fw.pimg2.value==null || fw.pimg3.value==null){
	 alert("대표 상품 이미지는 1개 이상 필수 등록입니다. ");
	 }else if(ct==""){
	 alert("상품 상세 설명을 입력해 주세요.");
	 }
	 else {
		 if(pppok!="ok"){
		alert("상품 코드 중복을 확인해주세요.");
		}else{
	
			//파일첨부 체크
			var w = 1;
			var ct =0;
			while(w<4){
				var filenm = document.getElementById("pimg"+w).value;
				console.log(filenm)
				var propery = filenm.slice(filenm.indexOf(".")+1).toLowerCase(); //lowerCase : 속성명 소문자로 변경하기.
				
				if(filenm != ""){
				if( propery !="jpg" && propery !="jpeg" && propery!="bmp" && propery != "gif" && propery !="png" && propery !="webp" ){
					alert("상품 이미지는 이미지파일만 등록하세요");
					return;
					document.getElementById("pimg"+w).value="";
				}else{
					ct++;
					}
				}
			
				w++;
			}
				var gopage ="yes";		
				var z = 1;
				while(z<=ct){
					var ck = document.getElementById("pimg"+z).value;
					if(ck==""){
					alert("파일 첨부는 순서대로 첨부하시길 바랍니다.");	
					gopage="no";	
					}
					z++;
				}				
				
			if(gopage == "yes"){
				
			
		fw.method="post";
		fw.action="./pro_write.do";
		fw.enctype="multipart/form-data";
		fw.submit();
			}
  } 
 }
}

</script>
