package com.bean;

public class ATM { //��ȡ���
	private double currentMoney; //��ȡ�����Ǯ
	private User[] users; //��ȡ�������ĺϷ��û�
	
	/*
	 * ȡ�����̣��Ȳ��뿨�������û���������
	 * ���û��������루���ԴӴ����б����ŵ�����ȥ�жϣ�����ȷ֮�󣬲ſ��Խ���������
	 * �����ȡ����ϵ�Ǯ���㣬���޷�ȡ��
	 * ����˻����㣬Ҳ����ȡ��
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
