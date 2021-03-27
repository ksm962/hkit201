package model.board.dto;

import java.sql.Date;

public class Boardchk {

	int no;
	String tbl;
	String tblname;
	String servicegubun;
	Date regidtae;
	
	
	public Boardchk() {
		// TODO Auto-generated constructor stub
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getTbl() {
		return tbl;
	}


	public void setTbl(String tbl) {
		this.tbl = tbl;
	}


	public String getTblname() {
		return tblname;
	}


	public void setTblname(String tblname) {
		this.tblname = tblname;
	}


	public String getServicegubun() {
		return servicegubun;
	}


	public void setServicegubun(String servicegubun) {
		this.servicegubun = servicegubun;
	}


	public Date getRegidtae() {
		return regidtae;
	}


	public void setRegidtae(Date regidtae) {
		this.regidtae = regidtae;
	}
	
	
	
	
	
}
