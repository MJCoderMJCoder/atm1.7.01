package com.bean;

public class ATM { //存取款机
	private double currentMoney; //存取款机的钱
	private User[] users; //存取款机保存的合法用户
	
	/*
	 * 取款流程：先插入卡，输入用户名和密码
	 * 当用户名和密码（可以从存款机中保存着的数据去判断）都正确之后，才可以进入主界面
	 * 如果存取款机上的钱不足，则无法取款
	 * 如果账户余额不足，也不能取款
	 */
	public double getCurrentMoney() {
		return currentMoney;
	}
	public void setCurrentMoney(double currentMoney) {
		this.currentMoney = currentMoney;
	}
	public User[] getUsers() {
		return users;
	}
	public void setUsers(User[] users) {
		this.users = users;
	}
}
