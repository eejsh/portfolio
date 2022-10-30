package model;

import java.sql.*;
import java.util.ArrayList;

import dataconfig.DbConfig;

public class CategoryModel {

	private String re = null;
	

	public String getRe() {
		return re;
	}

	public void setRe(String re) {
		this.re = re;
	}

	public void list_cateinsert(ArrayList<String> catein) throws ClassNotFoundException, SQLException {

		DbConfig db = new DbConfig();
		Connection con = db.cafe24();

		String sql = "insert into category values('0',?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);

		int w = 1;
		while (w <= catein.size()) {
			ps.setString(w, catein.get(w - 1));
			w++;
		}

		int n = ps.executeUpdate();
		
		if (n > 0) {
			this.setRe("ok");
			con.close();

		} else {
			this.setRe("no");
		}
		
		ps.close();
		con.close();

	}


}
