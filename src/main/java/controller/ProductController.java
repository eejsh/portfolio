package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.ProductModel;

@MultipartConfig(
		fileSizeThreshold = 1024*1024*1,
		maxFileSize =  1024*1024*2,
		maxRequestSize =  1024*1024*2	
		)

public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ProductController() {
    }

	public void init(ServletConfig config) throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=utf-8");
		
	PrintWriter pw = response.getWriter();
		
    String c1code = request.getParameter("c1code");
    String c2code = request.getParameter("c2code");
    String catecode = c1code + c2code;
   String pcode = request.getParameter("pcode");
	String pname = request.getParameter("pname");
    String padd = request.getParameter("padd");
    String pprice = request.getParameter("pprice");
    String pdisrate = request.getParameter("pdisrate");
    String pdisprice = request.getParameter("pdisprice");
    String pstock = request.getParameter("pstock");
    String psale = request.getParameter("psale");
    String pout = request.getParameter("pout");
    
    String ptext = request.getParameter("ptext");
 
    String pimg1 ="";
	String pimg2 ="";
	String pimg3 ="";

	Date dr = new Date();
	SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmm");
	String tt = sd.format(dr);
    
    
    //파일
    Collection<Part> p = request.getParts();  //파일 여러개 받을 때 사용함. 
    String paths = request.getServletContext().getRealPath(""); //경로 
    String upload = paths + "upload/"; // 저장공간.. 
     
	for(Part part: p ) { 
		String filename = part.getSubmittedFileName();//file이름
		String names = part.getName(); // 파라미터 name 값
		String imgurl = ""; 
		
		if(filename != null && filename != "") {  // null 값이 아닌것만 저장하세요.
			part.write(upload+tt+ filename); //파일저장
			imgurl = "./upload/" +tt +filename ;
		}
		else {
			imgurl = "";
		}
		
		switch(names) {
		case "pimg1" :
			pimg1 = imgurl;
			break;
		case "pimg2" :
			pimg2 = imgurl;
			break;
		case "pimg3" :
			break;
		}	
	
	}
	
	ProductModel pi = new ProductModel();
	 ArrayList<String> plist = new ArrayList<String>();
	    plist.add(c1code);
	    plist.add(c2code);
	    plist.add(catecode);
	    plist.add(pcode);
	    plist.add(pname);
	    plist.add(padd);
	    plist.add(pprice);
	    plist.add(pdisrate);
	    plist.add(pdisprice);
	    plist.add(pstock);
	    plist.add(psale);
	    plist.add(pout);
		plist.add(pimg1);
		plist.add(pimg2);
		plist.add(pimg3);
		plist.add(ptext);
		
	  try {
		  
    	pi.pro_insert(plist);
    	
    	String sign = pi.getSign();
    	if(sign=="ok") {
			pw.print("<script>alert('저장되었습니다.'); location.href='./product?pgno=1'; </script>");
		}else {
			
			throw new Exception("error");				
		}
	} catch (Exception ee) {
		 pw.print("<script>"
		+ "alert('db저장에 문제가 생겼습니다.');"
 		+ "history.go(-1);"
 		+ "</script>"); 
		}
 	}

}
