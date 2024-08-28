package logico;

import java.sql.Date;
import java.sql.Timestamp;

public class Log {

	
	private String username;
	private String id;
	private Date FyH;
	
	public Log(String username, String id, Date fyh2) {
		super();
		this.username = username;
		this.id = id;
		FyH = fyh2;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFyH() {
		return FyH;
	}

	public void setFyH(Date fyH) {
		FyH = fyH;
	}
	
	
}
