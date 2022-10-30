package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataconfig.DbConfig;

public class LoginModel {
	
	private String mpass = null;
	private String mname = null;
	private String memail = null;
	private String mtel1 = null;
	private String mtel2 = null;
	private String mtel3 = null;
	private String mdepart = null;
	private String mpo = null;
	private String mapp = null;
	private String mid = null;
	private String midx = null;

	private String mindate = null;

	public String aa = null;

    public String loginok[] = new String[4];

	public String results_ok() {

		return this.loginok[4];

	}


	public String Results() {
		return this.aa;
	}



	public String getMindate() {
		return mindate;
	}

	public void setMindate(String mindate) {
		this.mindate = mindate;
	}

	public String getMidx() {
		return midx;
	}

	public void setMidx(String midx) {
		this.midx = midx;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpass() {
		return mpass;
	}

	public void setMpass(String mpass) {
		this.mpass = mpass;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMemail() {
		return memail;
	}

	public void setMemail(String memail) {
		this.memail = memail;
	}

	public String getMtel1() {
		return mtel1;
	}

	public void setMtel1(String mtel1) {
		this.mtel1 = mtel1;
	}

	public String getMtel2() {
		return mtel2;
	}

	public void setMtel2(String mtel2) {
		this.mtel2 = mtel2;
	}

	public String getMtel3() {
		return mtel3;
	}

	public void setMtel3(String mtel3) {
		this.mtel3 = mtel3;
	}

	public String getMdepart() {
		return mdepart;
	}

	public void setMdepart(String mdepart) {
		this.mdepart = mdepart;
	}

	public String getMpo() {
		return mpo;
	}

	public void setMpo(String mpo) {
		this.mpo = mpo;
	}

	public String getMapp() {
		return mapp;
	}

	public void setMapp(String mapp) {
		this.mapp = mapp;
	}
	
	
	//main_view
	public ArrayList<LoginModel> viewdb() throws ClassNotFoundException, SQLException {

		ArrayList<LoginModel> dblist = new ArrayList<LoginModel>();

		DbConfig.cafe24();

		Connection con = DbConfig.cafe24();

		String sql = "select * from login order by mindate desc ";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			LoginModel ds = new LoginModel();
			ds.setMidx(rs.getString("midx"));
			ds.setMid(rs.getString("mid"));
			ds.setMname(rs.getString("mname"));
			ds.setMemail(rs.getString("memail"));
			ds.setMtel1(rs.getString("mtel1"));
			ds.setMtel2(rs.getString("mtel2"));
			ds.setMtel3(rs.getString("mtel3"));
			ds.setMdepart(rs.getString("mdepart"));
			ds.setMpo(rs.getString("mpo"));
			ds.setMapp(rs.getString("mapp"));
			ds.setMindate(rs.getString("mindate"));

			dblist.add(ds);

		}
		ps.close();
		con.close();

		return dblist;

	}
	
	//login admin id check
	public void Results(String results) throws ClassNotFoundException, SQLException { // setter

		this.aa = results;
		
		Connection con = DbConfig.cafe24();
		String sql = "select * from login where mid ='" + this.aa + "'";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		if (rs.next() == true) {
			this.aa = "no";
		} else if (this.aa == null || this.aa == "") {
			this.aa = "no";
		} else {
			this.aa = "ok";
		}
		con.close();
	}

	
	//login 
	public void results_ok(String mid) throws ClassNotFoundException, SQLException {
		Connection con = DbConfig.cafe24();

		String sql = "select mid, mpass, mname, mapp from login where mid = '" + mid + "'";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			LoginModel ds = new LoginModel();
			ds.setMid(rs.getString("mid"));
			ds.setMpass(rs.getString("mpass"));
			ds.setMname(rs.getString("mname"));
			ds.setMapp(rs.getString("mapp"));

			this.loginok[0] = ds.getMid();
			this.loginok[1] = ds.getMpass();
			this.loginok[2] = ds.getMname();
			this.loginok[3] = ds.getMapp();
		

		}
		ps.close();
		con.close();
		
		

	}
}
