package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataconfig.DbConfig;

public class CategoryViewModel {

	public String getCcode() {
		return ccode;
	}

	public String getC1code() {
		return c1code;
	}

	public String getC1codenm() {
		return c1codenm;
	}

	public String getC2code() {
		return c2code;
	}

	public String getC2codenm() {
		return c2codenm;
	}

	public String getCateuse() {
		return cateuse;
	}

	public String getC1codeonly() {
		return c1codeonly;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public void setC1code(String c1code) {
		this.c1code = c1code;
	}

	public void setC1codenm(String c1codenm) {
		this.c1codenm = c1codenm;
	}

	public void setC2code(String c2code) {
		this.c2code = c2code;
	}

	public void setC2codenm(String c2codenm) {
		this.c2codenm = c2codenm;
	}

	public void setCateuse(String cateuse) {
		this.cateuse = cateuse;
	}

	public void setC1codeonly(String c1codeonly) {
		this.c1codeonly = c1codeonly;
	}
	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}
	String idx = null;
	
	String ccode = null;
	String c1code = null;
	String c1codenm = null;
	String c2code = null;
	String c2codenm = null;
	String cateuse = null;
	String c1codeonly = null;
	
	String page = null;
	public String aa(String nn) {
		this.page = nn;
		return this.page;
	}
	
	int total =0;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	
	public int getPageview() {
		return pageview;
	}

	public void setPageview(int pageview) {
		this.pageview = pageview;
	}
	
	
	//page
	int pageview =0;     // 페이지 나타낼 갯수 
	int startpage = 1 ;  //시작 페이지 
	int pagenumber = 1 ; //페이징 갯수
	
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
	
	
	
	
	
	String coption = null;
	String csearch = null;
	
			
	public void coption(String coption) {
		this.coption = coption;
	}

	public void csearch(String csearch) {
		this.csearch = csearch ;
	}
	
	
	public ArrayList<CategoryViewModel> catelistt() throws ClassNotFoundException, SQLException {

		ArrayList<CategoryViewModel> cateview = new ArrayList<>();
		
		Connection con = DbConfig.cafe24();
		
		this.pageview = 5 ;
		this.startpage = 1 ;  
		this.pagenumber = 1 ; 
		
		
	   String pgno = this.page;   
	   
	   String ct ="";
	   //   String ct = "select count(*) as ct from category"; 
	   
	   if(this.csearch == null || this.csearch.length()==0) {
		ct = "select count(*) as ct from category";
		 }else if(this.coption.equals("1")) {
		 ct="select count(*) as ct from category where c1codenm like '%"+ this.csearch+"%'";
		 }else if (this.coption.equals("2")) {
		 ct =  "select count(*)as ct from category where ccode like '%"+ this.csearch+"%'";
		 }else {
		 ct = "select count(*) as ct from category";
			  }
	   
	   PreparedStatement psct = con.prepareStatement(ct);
	   ResultSet rsct = psct.executeQuery();
	
	   while(rsct.next()) {
		   this.total = rsct.getInt("ct");   
	   }

	 
	   if(pgno==null || pgno=="") {
		   this.startpage = 0;
	   }else{
		   this.startpage=(Integer.parseInt(pgno)-1)*this.pageview;
	   }
	   
	   
	   if(total % this.pageview==0) {
		   this.pagenumber = total/this.pageview;
		   
	   }else {
		   this.pagenumber = (total/this.pageview)+1;
	   }
	   
 	   
	   
		
	//	String sql = "select * from category limit ?,? ";
	String sql = null;
		  if(this.csearch == null || this.csearch.length()==0) {
			  sql ="select * from category order by idx desc limit ?,?" ;
		  }else if(this.coption.equals("1")) {
			  sql = " select * from category where c1codenm like '%"+this.csearch+"%' order by idx desc limit ?,?" ;
		  }else if (this.coption.equals("2")) {
			  sql = " select * from category where ccode like '%"+this.csearch+"%' order by idx desc limit ?,?" ;
		  }else {
			  sql ="select * from category order by idx desc limit ?,?" ;
		  }
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, startpage);
		ps.setInt(2, pageview);
		
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			
			CategoryViewModel lic = new CategoryViewModel();
			
			lic.setIdx(rs.getString("idx"));
			lic.setCcode(rs.getString("ccode"));
			lic.setC1code(rs.getString("c1code"));
			lic.setC1codenm(rs.getString("c1codenm"));
			lic.setC1codeonly(rs.getString("c1codeonly"));
			lic.setC2code(rs.getString("c2code"));
			lic.setC2codenm(rs.getString("c2codenm"));
			lic.setCateuse(rs.getString("cateuse"));
			
			cateview.add(lic);
		}
		ps.close();
		con.close();
		
		return cateview;
		
	}


}
