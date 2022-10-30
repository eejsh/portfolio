package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import dataconfig.DbConfig;

//model.
public class SiteInfoModel {

	String call1 = null;
	String call2 = null;
	String call3 = null;

	public void a(ArrayList<String> homeprefer) throws ClassNotFoundException, SQLException {

			DbConfig db = new DbConfig();
			Connection ct = db.cafe24();

			String sql = "insert into homeprefer values('0',?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = ct.prepareStatement(sql);

			int w = 1;
			while (w <= homeprefer.size()) {
				ps.setString(w, homeprefer.get(w - 1));
				w++;
			}

			int n = 0;
			n = ps.executeUpdate();

			if (n > 0) {
				this.call1 = "ok";
			} else {
				this.call1 = "no";

			}
			ps.close();
			ct.close();
	
	}

	public void b(ArrayList<String> payment) throws ClassNotFoundException, SQLException  {

			DbConfig db = new DbConfig();
			Connection ct = db.cafe24();

			String sql = "insert into payment values('0',?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = ct.prepareStatement(sql);

			int w = 1;
			while (w <= payment.size()) {
				ps.setString(w, payment.get(w - 1));
				w++;
			}
			int n = 0;
			n = ps.executeUpdate();

			if (n > 0) {
				this.call2 = "ok";
			} else {
				this.call2 = "no";

			}
			ps.close();
			ct.close();
		

	}

	public void c(ArrayList<String> preferpoints)  throws ClassNotFoundException, SQLException {

		
			DbConfig db = new DbConfig();
			Connection ct = db.cafe24();

			String sql = "insert into preferpoints values('0',?,?,?,?)";

			PreparedStatement ps = ct.prepareStatement(sql);

			int w = 1;
			while (w <= preferpoints.size()) {
				ps.setString(w, preferpoints.get(w - 1));
				w++;
			}
			int n = 0;
			n = ps.executeUpdate();

			if (n > 0) {
				this.call3 = "ok";
			} else {
				this.call3 = "no";

			}
			ps.close();
			ct.close();

	}

	public String[] call() {

		String[] recall = new String[3];
		recall[0] = this.call1;
		recall[1] = this.call2;
		recall[2] = this.call3;

		return recall;

	}

}
