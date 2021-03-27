package model.memo.dto;

import java.sql.Date;

public class MemoDTO {
	int id;
	String name;
	String memo;
	Date wdate;
	
	
	
	public Date getWdate() {
		return wdate;
	}



	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}



	public MemoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	
	
	

}
