package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Account {
	
	public Account () {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "typeId")
	private int typeId;
	
	@Column(name = "dateBorn")
	private Date dateBorn;
	
	@Column(name = "stateId")
	private int stateId;
	
	@Column(name = "user")
	private String user;
	
	@Column(name = "lastRent")
	private Date lastRent;
	
	@Column(name = "countRent")
	private int countRent;
	
	@Column(name = "price")
	private int price;
	
	@Column(name ="loginEmail")
	private String loginEmail;
	
	@Column(name = "password")
	private String password;

	public Account(int typeId, Date dateBorn, int stateId, String user, Date lastRent, int countRent, int price,
			String loginEmail, String password) {
		super();
		this.typeId = typeId;
		this.dateBorn = dateBorn;
		this.stateId = stateId;
		this.user = user;
		this.lastRent = lastRent;
		this.countRent = countRent;
		this.price = price;
		this.loginEmail = loginEmail;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public Date getDateBorn() {
		return dateBorn;
	}

	public void setDateBorn(Date dateBorn) {
		this.dateBorn = dateBorn;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getLastRent() {
		return lastRent;
	}

	public void setLastRent(Date lastRent) {
		this.lastRent = lastRent;
	}

	public int getCountRent() {
		return countRent;
	}

	public void setCountRent(int countRent) {
		this.countRent = countRent;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}