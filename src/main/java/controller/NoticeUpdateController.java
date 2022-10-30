package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dataconfig.DbConfig;

@MultipartConfig(
		fileSizeThreshold = 1024*1024*1,
		maxFileSize =  1024*1024*2,
		maxRequestSize =  1024*1024*2	
		)

public class NoticeUpdateController extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
       
    public NoticeUpdateController() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=utf-8");
	PrintWriter pw = response.getWriter();
	
	
	String idx = request.getParameter("no");
	String output = request.getParameter("output");
	String subject = request.getParameter("subject");
	String writer = request.getParameter("writer");

	String content = request.getParameter("content");
	
		if(output==null) {
		output="N";
	}

	Part filepart = request.getPart("upfile");  // 경로
	
	String filename = filepart.getSubmittedFileName();  // 파일이름
	
	Date dr = new Date();
	SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmm");
	String tt = sd.format(dr);
	
	String paths = request.getServletContext().getRealPath("");
	
	String savefile = null;
	String savefile1 = null;
	
	if(filename=="") {
			savefile=null;
		
	}else {
	
		savefile = paths + "upload/" + filename; // 저장공간
		
		savefile1 = "./upload/"  + tt + filename;
		
		FileOutputStream fo = new FileOutputStream(savefile);
		InputStream is = filepart.getInputStream();
		
			
		byte[] bf = new byte[1024*2];
		int size =0;
		while((size=is.read(bf))!= -1) {
			fo.write(bf,0,size);
		}
		
	}
	
	try {
		DbConfig db = new DbConfig();
		Connection ct = db.cafe24();
		String sql ="update notice set output=?, subject=?, writer=?, upfile=?, content=? where idx='"+idx+"'";
		
		PreparedStatement ps = ct.prepareStatement(sql);
		
		ps.setString(1, output);
		ps.setString(2, subject);
		ps.setString(3, writer);
		ps.setString(4, savefile1);
		ps.setString(5, content);
		
		
		int n=0;
		n = ps.executeUpdate();
		
		if(n>0) {
			
			 pw.print("<script>alert('공지 수정되었습니다.');  "
				 		+ "location.href ='./notice?pgno=1 ';"
				 		+ "</script>");
			
		}else {
			
			 pw.print("<script>"
						+ "alert('db저장에 문제가 생겼습니다.');"
						+ "location.href ='./notice?pgno=1';"
				 		+ "</script>"); 
		
		}
		ct.close();
		
	}catch (Exception e) {
			e.getMessage();
	}
	
	}
	


}
