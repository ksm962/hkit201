package member.model.dto;

import java.sql.Timestamp;

public class MemberDTO {
	private int no;
	private String id;
	private String passwd;
	private String passwdchk;
	private String name;
	private String gender;
	private int bornyear;
	private Timestamp regiDate;
	private String postcode;
	private String address;
	private String detailaddress;
	private String extraaddress;
	private int preNo;
	private String preId;
	private int nxtNo;
	private String nxtId;
	
	 
	
	
	public String getPostcode() {
		return postcode;
	}



	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}



	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}



	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getDetailaddress() {
		return detailaddress;
	}




	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}




	public String getExtraaddress() {
		return extraaddress;
	}




	public void setExtraaddress(String extraaddress) {
		this.extraaddress = extraaddress;
	}




	public int getNo() {
		return no;
	}




	public void setNo(int no) {
		this.no = no;
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getPasswd() {
		return passwd;
	}




	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}




	public String getPasswdchk() {
		return passwdchk;
	}




	public void setPasswdchk(String passwdchk) {
		this.passwdchk = passwdchk;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getGender() {
		return gender;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public int getBornyear() {
		return bornyear;
	}




	public void setBornyear(int bornyear) {
		this.bornyear = bornyear;
	}




	public Timestamp getRegiDate() {
		return regiDate;
	}




	public void setRegiDate(Timestamp regiDate) {
		this.regiDate = regiDate;
	}
	
	
	
	
	
	
	
}
