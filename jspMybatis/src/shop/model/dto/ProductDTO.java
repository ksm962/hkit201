package shop.model.dto;

import java.sql.Timestamp;

public class ProductDTO {

	int no;
	String name;
	int price;
	String description;
	String product_img;
	Timestamp regi_date;
	int buy_counter;
	
	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	public int getBuy_counter() {
		return buy_counter;
	}





	public void setBuy_counter(int buy_counter) {
		this.buy_counter = buy_counter;
	}





	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProduct_img() {
		return product_img;
	}

	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}

	public Timestamp getRegi_date() {
		return regi_date;
	}

	public void setRegi_date(Timestamp regi_date) {
		this.regi_date = regi_date;
	}
	
	
	
	
	
	
}
