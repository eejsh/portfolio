package dataconfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Timer {

	private String datetime = null;
	private String filetime = null;
	
	
	
	public String now_time () {
		
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter fm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		this.datetime = ldt.format(fm);
		
		
	return this.datetime;
	}
	
	public String filetime() {
		
		LocalDateTime ldt2 = LocalDateTime.now();
		DateTimeFormatter fm2 = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		this.filetime = ldt2.format(fm2);
		
		return this.filetime;
		
	}
	
	
}
