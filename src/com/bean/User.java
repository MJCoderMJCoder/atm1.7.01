package com.bean;

public class User {
	private int id;
	private String name;
	private String password;
	private String bankCard; //银行卡
	private double currentMoney; //现金
	
	
	public User() {
	}

	public User(int id, String name, String password, String bankCard,
			double currentMoney) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.bankCard = bankCard;
		this.currentMoney = currentMoney;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	public double getCurrentMoney() {
		return currentMoney;
	}
	public void setCurrentMoney(double currentMoney) {
		this.currentMoney = currentMoney;
	}
}
