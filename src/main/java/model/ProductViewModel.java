package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataconfig.DbConfig;

public class ProductViewModel {

	public String getIdx() {
		return idx;
	}

	public String getPcode() {
		return pcode;
	}

	public String getPimg1() {
		return pimg1;
	}

	public String getPname() {
		return pname;
	}

	public String getCatecode() {
		return catecode;
	}

	public String getPprice() {
		return pprice;
	}

	public String getPdisprice() {
		return pdisprice;
	}

	public String getPdisrate() {
		return pdisrate;
	}

	public String getPstock() {
		return pstock;
	}

	public String getPsale() {
		return psale;
	}

	public String getPout() {
		return pout;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public void setPimg1(String pimg1) {
		this.pimg1 = pimg1;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public void setCatecode(String catecode) {
		this.catecode = catecode;
	}

	public void setPprice(String pprice) {
		this.pprice = pprice;
	}

	public void setPdisprice(String pdisprice) {
		this.pdisprice = pdisprice;
	}

	public void setPdisrate(String pdisrate) {
		this.pdisrate = pdisrate;
	}

	public void setPstock(String pstock) {
		this.pstock = pstock;
	}

	public void setPsale(String psale) {
		this.psale = psale;
	}

	public void setPout(String pout) {
		this.pout = pout;
	}

	String idx = null;
	String pcode = null;
	String pimg1 = null;
	String pname = null;
	String catecode = null;
	String pprice = null;
	String pdisprice = null;
	String pdisrate = null;
	String pstock = null;
	String psale = null;
	String pout = null;
	
	
	//pageing
	String page = null;
	int total =0;
	int pageview =0;
	int startpage = 1 ;  
	int pagenumber = 1 ; 

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

	public String aa(String nn) {
	
		this.page = nn;
		return this.page;
	}
	
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
	
	//검색 
	String poption = null;
	String psearch = null;
	
	public void poption(String poption) {
		this.poption = poption;
		
	}

	public void psearch(String psearch) {
		this.psearch = psearch;
		
	}
	

	public ArrayList<ProductViewModel> pvlist() throws ClassNotFoundException, SQLException {

		ArrayList<ProductViewModel> pvlistt = new ArrayList<ProductViewModel>();
		
		DbConfig db = new DbConfig();
		Connection con = db.cafe24();
		
		
		this.pageview = 5 ; // 1페이지당 보여지는 데이터 갯수 
		this.startpage = 1 ;   // 시작 페이지 
		this.pagenumber = 1 ;  //페이징 갯수 
		String pgno = this.page; //파라미터
		
		String ct  = "";
		 
		  if(this.psearch == null || this.psearch.length()==0) {
			 ct = "select count(*) as ct from productcode";
		  }else if(this.poption.equals("1")) {
			  ct="select count(*) as ct from productcode where pname like '%"+ this.psearch+"%'";
		  }else if (this.poption.equals("2")) {
			  ct =  "select count(*)as ct from productcode where pcode like '%"+ this.psearch+"%'";
		  }else {
			  ct = "select count(*) as ct from productcode";
		  }
		
		
		   PreparedStatement psct = con.prepareStatement(ct);  
		   ResultSet rsct = psct.executeQuery();

		   while(rsct.next()) {
			   this.total = rsct.getInt("ct");    //db개수 파악 ..
			  }
		   
		   //페이지 설정..
		   if(pgno==null || pgno=="") {
			   this.startpage = 0;
		   }else{
			   this.startpage=(Integer.parseInt(pgno)-1)* this.pageview;
		   }
		   
		   //페이징 총 갯수 파악 ..
		   
		   if(total % this.pageview==0) {
			   this.pagenumber = total/this.pageview;
			   
		   }else {
			   this.pagenumber = (total/this.pageview)+1;
		   }
		
		   String sql = "";
		  
		  if(this.psearch == null || this.psearch.length()==0) {
			  sql ="select * from productcode order by idx desc limit ?,?" ;
		  }else if(this.poption.equals("1")) {
			  sql = " select * from productcode where pname like '%"+this.psearch+"%' order by idx desc limit ?,?" ;
		  }else if (this.poption.equals("2")) {
			  sql = " select * from productcode where pcode like '%"+this.psearch+"%' order by idx desc limit ?,?" ;
		  }else {
			  sql ="select * from productcode order by idx desc limit ?,?" ;
		  }
			  
		//String sql ="select * from productcode order by idx desc limit ?,?" ;
		  
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, startpage);
		ps.setInt(2, pageview);
		
		ResultSet rs = ps.executeQuery();
		
		
		while(rs.next()) {
			ProductViewModel pp = new ProductViewModel();

			pp.setIdx(rs.getString("idx"));
			pp.setPcode(rs.getString("pcode"));
			pp.setPimg1(rs.getString("pimg1"));
			pp.setPname(rs.getString("pname"));
			pp.setCatecode(rs.getString("catecode"));
			pp.setPprice(rs.getString("pprice"));
			pp.setPdisprice(rs.getString("pdisprice"));
			pp.setPdisrate(rs.getString("pdisrate"));
			pp.setPstock(rs.getString("pstock"));
			pp.setPsale(rs.getString("pstock"));
			pp.setPout(rs.getString("pout"));
			
			pvlistt.add(pp);			
			
		}
		con.close();
		psct.close();
		
		return pvlistt;

	}

}
