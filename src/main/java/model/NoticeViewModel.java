package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataconfig.DbConfig;

public class NoticeViewModel {

	
	
	public String getOutput() {
		return output;
	}

	public String getIdx() {
		return idx;
	}

	public String getSubject() {
		return subject;
	}

	public String getWriter() {
		return writer;
	}

	public String getIndate() {
		return indate;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	
	public String getPageco() {
		return pageco;
	}
	

	public void setPageco(String pageco) {
		this.pageco = pageco;
	}

	private String output = null;
	private String idx = null;
	
	private String subject = null;
	private String writer = null;
	private String indate = null;
	private String pageco= null;
	


	String page = null;
	public String aa(String nn) {
		this.page = nn;
		return this.page;
	}

	int pageview =0;
	int startpage = 1 ;  //시작 페이지 
	int pagenumber = 1 ; //페이징 갯수
	
	public int getPageview() {
		return pageview;
	}

	public void setPageview(int pageview) {
		this.pageview = pageview;
	}
	
	public int getStartpage() {
		return startpage;
	}

	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}

	public int getPagenumber() {
		return pagenumber;
	}

	public void setPagenumber(int pagenumber) {
		this.pagenumber = pagenumber;
	}

	
	
	public ArrayList<NoticeViewModel>notilist() throws ClassNotFoundException,SQLException{
		
		

		ArrayList<NoticeViewModel> noticelist = new ArrayList<NoticeViewModel>();
		
		DbConfig db = new DbConfig();
		db.cafe24();
		Connection con = db.cafe24();

		this.pageview = 10 ; // 1페이지당 보여지는 데이터 갯수 
		this.startpage = 1 ;  //시작 페이지 
		this.pagenumber = 1 ; //페이징 갯수
		
		String pgno = this.page;   // 페이징번호 파라미터
	   
	   String ct = "select count(*) as ct from notice"; //21
	   
	   PreparedStatement psct = con.prepareStatement(ct);
	   ResultSet rsct = psct.executeQuery();
	
	   int total = 0;
	   
	   while(rsct.next()) {
		   total = rsct.getInt("ct");    //db개수 파악 ..
	   }
		
		   //페이지 설정..
		   if(pgno==null || pgno=="") {
			   this.startpage = 0;
		   }else{
			   this.startpage=(Integer.parseInt(pgno)-1) * this.pageview;
		   }
		   
		   //페이징 총 갯수 파악 ..
		   if(total % pageview==0) {
			   this.pagenumber = total/this.pageview;
		   }else {
			   this.pagenumber = (total/this.pageview)+1;
		   }
		
		String sql = "select * from notice order by output='Y' desc, idx desc limit ?,?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, startpage);
		ps.setInt(2, pageview);
		
		ResultSet rs = ps.executeQuery();
	
		while(rs.next()) {
			NoticeViewModel nc = new NoticeViewModel();
			nc.setIdx(rs.getString("idx"));
			nc.setOutput(rs.getString("output"));
		    nc.setSubject(rs.getString("subject"));
			nc.setWriter(rs.getString("writer"));
			nc.setPageco(rs.getString("pageco"));
			nc.setIndate(rs.getString("indate"));
			
			noticelist.add(nc);
			
		}
		psct.close();
		ps.close();
		con.close();
	    
		
		return noticelist;
	}	
}
