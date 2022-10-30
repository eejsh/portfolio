package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.CouponModel;


@MultipartConfig(
		fileSizeThreshold = 1024*1024*1,
		maxFileSize =  1024*1024*2,
		maxRequestSize =  1024*1024*2	
		)
public class CouponController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CouponController() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter pw = response.getWriter();
		String cname= request.getParameter("cname");
		String ckind = request.getParameter("ckind");
		String csttime = request.getParameter("csttime");
		String clstime = request.getParameter("clstime");
		String ctype= request.getParameter("ctype");
		String crate = request.getParameter("crate");
		String cmin = request.getParameter("cmin");
		
		dataconfig.Timer tm = new dataconfig.Timer();
		String indate = tm.now_time(); // db 저장용
	
		
		Date dr = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmm");
		String tt = sd.format(dr);
		

		String file = null; // 파일이름 디비 저장. 
		String filesave = null; //파일저장 
		
		Part filepart = request.getPart("cimg");
	 	
		String paths = request.getServletContext().getRealPath("");  
		
		String filename = filepart.getSubmittedFileName(); //이름
				
		if(filename=="") {
			file = null;
		}else {

			filesave = paths + "upload/" + filename;  // 파일저장...
			file = "./upload/"+tt+filename;
	
			FileOutputStream fo = new FileOutputStream(filesave);
			InputStream is =  filepart.getInputStream();
			
			byte[] bf = new byte[1024*2];
			int size = 0;
			while((size=is.read(bf))!= -1) {
				fo.write(bf,0,size);
				}

		}
		
		String startime = csttime + " 00:00:01";
		String lasttime = clstime + " 23:59:59";
		
		ArrayList<String> cp = new ArrayList<>();
		cp.add(cname);
		cp.add(ckind);
		cp.add(startime);
		cp.add(lasttime);
		cp.add(ctype);
		cp.add(crate);
		cp.add(cmin);
		cp.add(file);
		cp.add(indate);
		
		
		CouponModel ci = new CouponModel();
		
		try {
			ci.cou_insert(cp);
			String sign = ci.getResult();
			
			if(sign == "ok") {
				pw.print("<script>alert('저장되었습니다.'); location.href='./shopping?pgno=1'; </script>");
			}else {
				throw new Exception("error");				
			}
			
		} catch(Exception e) {
			e.getMessage();
			 pw.print("<script>"
			+ "alert('db저장에 문제가 생겼습니다.');"
			+ "history.go(-1);"
			+ "</script>"); 
		
			}
		}
	}

