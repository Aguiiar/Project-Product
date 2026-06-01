package com.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_INVOICE")
public class Invoice {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "TB_ID")
	private Long id;
	@Column(name = "TB_PRICE")
	private double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	


}
