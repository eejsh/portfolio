package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataconfig.DbConfig;
import dataconfig.Timer;

public class CouponViewModel {

	
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCkind() {
		return ckind;
	}
	public void setCkind(String ckind) {
		this.ckind = ckind;
	}
	public String getCsttime() {
		return csttime;
	}
	public void setCsttime(String csttime) {
		this.csttime = csttime;
	}
	public String getClstime() {
		return clstime;
	}
	public void setClstime(String clstime) {
		this.clstime = clstime;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getCrate() {
		return crate;
	}
	public void setCrate(String crate) {
		this.crate = crate;
	}
	public String getCmin() {
		return cmin;
	}
	public void setCmin(String cmin) {
		this.cmin = cmin;
	}
	public String getCimg() {
		return cimg;
	}
	public void setCimg(String cimg) {
		this.cimg = cimg;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	private String idx;
	private String cname;
	private String ckind;
	private String csttime;
	private String clstime;
	private String ctype;
	private String crate;
	private String cmin;
	private String cimg;
	private String indate;
	
	public ArrayList<CouponViewModel> coulist() throws ClassNotFoundException, SQLException {
	ArrayList<CouponViewModel> cplist = new ArrayList<CouponViewModel>();
	
	 Connection con = DbConfig.cafe24();
	 Timer nowtt = new Timer();
	 String nowdate = nowtt.now_time();
	 
	 String sql = "select * from coupon where clstime  >= '"+nowdate+"'";
	 PreparedStatement ps = con.prepareStatement(sql);
	 
	 ResultSet rs = ps.executeQuery();
	 
	 while(rs.next()) {
		 CouponViewModel cs = new CouponViewModel();
		 cs.setIdx(rs.getString("idx"));
		 cs.setCname(rs.getString("cname"));
		 cs.setCkind(rs.getString("ckind"));
		 cs.setCsttime(rs.getString("csttime"));
		 cs.setClstime(rs.getString("clstime"));
		 cs.setCtype(rs.getString("ctype"));
		 cs.setCrate(rs.getString("crate"));
		 cs.setCmin(rs.getString("cmin"));
		 cs.setCimg(rs.getString("cimg"));
		 cs.setIndate(rs.getString("indate"));
		 
		 cplist.add(cs);
	 }
	 ps.close();
	 con.close();
	 
		return cplist;
	}

		
}
