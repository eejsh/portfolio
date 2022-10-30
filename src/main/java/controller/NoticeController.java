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

import dataconfig.Timer;
import model.NoticeModel;

@MultipartConfig(
		fileSizeThreshold = 1024*1024*1,
		maxFileSize =  1024*1024*2,
		maxRequestSize =  1024*1024*2	
		)

public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NoticeController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		PrintWriter pw = response.getWriter();
		
		Timer tr = new Timer();
		String indate = tr.now_time();
		
		
		String output = request.getParameter("output");
		if(output==null) {
			output="N";
		}

		String subject = request.getParameter("subject");  
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		
		Date dr = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmm");
		String tt = sd.format(dr);
		
		
		String savefile = null;
		String savefile1 = null;
		
		
		Part filepart = request.getPart("file");  // 경로
	
		String paths = request.getServletContext().getRealPath(""); 
				
		String filename = filepart.getSubmittedFileName();  // 파일이름
		
		if(filename=="") {
				savefile1=null;
			
		}else {
			savefile = paths + "upload/" + filename; // 저장공간
			
			savefile1 = "./upload/"  + tt + filename;
			
			FileOutputStream fo = new FileOutputStream(savefile);//저장..  
			InputStream is = filepart.getInputStream();
			
			byte[] bf = new byte[1024*2];
			int size =0;
			while((size=is.read(bf))!= -1) {
				fo.write(bf,0,size);
			}
			
		}
		
		 NoticeModel ni = new NoticeModel();
		 
		 ArrayList<String> nolist = new ArrayList<>();
		 nolist.add(output);
		 nolist.add(subject);
		 nolist.add(writer);
		 nolist.add(savefile1);
		 nolist.add(content);
		 nolist.add(indate);
		 
		 ni.notice_insert(nolist);
		 	
		 String ook = ni.getResult();
		 
		 try {
			 if(ook=="ok") {
				 pw.print("<script>alert('등록이 되었습니다. ');  "
				 		+ "location.href ='./notice?pgno=1';"
				 		+ "</script>");
			 }else {
				 throw new Exception("error");
			 }
			 
		 }catch (Exception e) {
			 
			 pw.print("<script>"
						+ "alert('저장에 문제가 생겼습니다.');"
						+ "location.href ='./notice?pgno=1';"
				 		+ "</script>"); 
		 
		 }
	}




}