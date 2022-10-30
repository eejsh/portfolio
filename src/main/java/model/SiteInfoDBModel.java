package model;

import java.sql.*;
import java.util.ArrayList;

import dataconfig.DbConfig;

public class SiteInfoDBModel {
//DTO
	String hhomename = null;
	String hsiteemail = null;
	String hcpnm = null;
	String hcpreginum = null;
	String hcpceo = null;
	String hcptel = null;
	String hcpereginum = null;
	String hcpesubreginum = null;
	String hcpzcord = null;
	String hcpaddr = null;
	String hcpcpo = null;
	String hcpcpomail = null;

	String abanknm = null;
	String abanknum = null;
	String acard = null;
	String aphone = null;
	String agiftmoney = null;
	String aminpo = null;
	String amaxpo = null;
	String acashrece = null;
	String ashipcom = null;
	String ashipcost = null;
	String ashipday = null;

	String pointuse = null;
	String point = null;
	String basiclevel = null;

	public String getHhomename() {
		return hhomename;
	}

	public String getHsiteemail() {
		return hsiteemail;
	}

	public String getHcpnm() {
		return hcpnm;
	}

	public String getHcpreginum() {
		return hcpreginum;
	}

	public String getHcpceo() {
		return hcpceo;
	}

	public String getHcptel() {
		return hcptel;
	}

	public String getHcpereginum() {
		return hcpereginum;
	}

	public String getHcpesubreginum() {
		return hcpesubreginum;
	}

	public String getHcpzcord() {
		return hcpzcord;
	}

	public String getHcpaddr() {
		return hcpaddr;
	}

	public String getHcpcpo() {
		return hcpcpo;
	}

	public String getHcpcpomail() {
		return hcpcpomail;
	}

	public String getAbanknm() {
		return abanknm;
	}

	public String getAbanknum() {
		return abanknum;
	}

	public String getAcard() {
		return acard;
	}

	public String getAphone() {
		return aphone;
	}

	public String getAgiftmoney() {
		return agiftmoney;
	}

	public String getAminpo() {
		return aminpo;
	}

	public String getAmaxpo() {
		return amaxpo;
	}

	public String getAcashrece() {
		return acashrece;
	}

	public String getAshipcom() {
		return ashipcom;
	}

	public String getAshipcost() {
		return ashipcost;
	}

	public String getAshipday() {
		return ashipday;
	}

	public String getPointuse() {
		return pointuse;
	}

	public String getPoint() {
		return point;
	}

	public String getBasiclevel() {
		return basiclevel;
	}

	public void setHhomename(String hhomename) {
		this.hhomename = hhomename;
	}

	public void setHsiteemail(String hsiteemail) {
		this.hsiteemail = hsiteemail;
	}

	public void setHcpnm(String hcpnm) {
		this.hcpnm = hcpnm;
	}

	public void setHcpreginum(String hcpreginum) {
		this.hcpreginum = hcpreginum;
	}

	public void setHcpceo(String hcpceo) {
		this.hcpceo = hcpceo;
	}

	public void setHcptel(String hcptel) {
		this.hcptel = hcptel;
	}

	public void setHcpereginum(String hcpereginum) {
		this.hcpereginum = hcpereginum;
	}

	public void setHcpesubreginum(String hcpesubreginum) {
		this.hcpesubreginum = hcpesubreginum;
	}

	public void setHcpzcord(String hcpzcord) {
		this.hcpzcord = hcpzcord;
	}

	public void setHcpaddr(String hcpaddr) {
		this.hcpaddr = hcpaddr;
	}

	public void setHcpcpo(String hcpcpo) {
		this.hcpcpo = hcpcpo;
	}

	public void setHcpcpomail(String hcpcpomail) {
		this.hcpcpomail = hcpcpomail;
	}

	public void setAbanknm(String abanknm) {
		this.abanknm = abanknm;
	}

	public void setAbanknum(String abanknum) {
		this.abanknum = abanknum;
	}

	public void setAcard(String acard) {
		this.acard = acard;
	}

	public void setAphone(String aphone) {
		this.aphone = aphone;
	}

	public void setAgiftmoney(String agiftmoney) {
		this.agiftmoney = agiftmoney;
	}

	public void setAminpo(String aminpo) {
		this.aminpo = aminpo;
	}

	public void setAmaxpo(String amaxpo) {
		this.amaxpo = amaxpo;
	}

	public void setAcashrece(String acashrece) {
		this.acashrece = acashrece;
	}

	public void setAshipcom(String ashipcom) {
		this.ashipcom = ashipcom;
	}

	public void setAshipcost(String ashipcost) {
		this.ashipcost = ashipcost;
	}

	public void setAshipday(String ashipday) {
		this.ashipday = ashipday;
	}

	public void setPointuse(String pointuse) {
		this.pointuse = pointuse;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public void setBasiclevel(String basiclevel) {
		this.basiclevel = basiclevel;
	}

	//DAO
	
	public ArrayList<SiteInfoDBModel> sitedb() throws ClassNotFoundException, SQLException {

		ArrayList<SiteInfoDBModel> siteview = new ArrayList<SiteInfoDBModel>();

		DbConfig db = new DbConfig();
		db.cafe24();
		
		Connection con = db.cafe24();

		String sql = "select * from homeprefer, payment, preferpoints order by homeprefer.idx desc, payment.idx desc, preferpoints.idx desc limit 1";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			SiteInfoDBModel ad = new SiteInfoDBModel();
			ad.setHhomename(rs.getString("hhomename"));
			ad.setHsiteemail(rs.getString("hsiteemail"));
			ad.setHcpnm(rs.getString("hcpnm"));
			ad.setHcpreginum(rs.getString("hcpreginum"));
			ad.setHcpceo(rs.getNString("hcpceo"));
			ad.setHcptel(rs.getString("hcptel"));
			ad.setHcpereginum(rs.getString("hcpereginum"));
			ad.setHcpesubreginum(rs.getString("hcpesubreginum"));
			ad.setHcpzcord(rs.getString("hcpzcord"));
			ad.setHcpaddr(rs.getString("hcpaddr"));
			ad.setHcpcpo(rs.getString("hcpcpo"));
			ad.setHcpcpomail(rs.getString("hcpcpomail"));

			ad.setAbanknm(rs.getString("abanknm"));
			ad.setAbanknum(rs.getString("abanknum"));
			ad.setAcard(rs.getString("acard"));
			ad.setAphone(rs.getString("aphone"));
			ad.setAgiftmoney(rs.getString("agiftmoney"));
			ad.setAminpo(rs.getString("aminpo"));
			ad.setAmaxpo(rs.getString("amaxpo"));
			ad.setAcashrece(rs.getString("acashrece"));
			ad.setAshipcom(rs.getString("ashipcom"));
			ad.setAshipcost(rs.getString("ashipcost"));
			ad.setAshipday(rs.getString("ashipday"));

			ad.setPointuse(rs.getString("pointuse"));
			ad.setPoint(rs.getString("point"));
			ad.setBasiclevel(rs.getString("basiclevel"));

			siteview.add(ad);
		
		}
		ps.close();
		con.close();

		return siteview;

	}

}