package com.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.bean.ATM;
import com.bean.User;

public class ATMui {
	public static void main(String[] args) {

		/*
		 * ��ʼ����ȡ�������Ϣ
		 */
		ATM atm = new ATM(); //�½�һ̨ȡ�������
		atm.setCurrentMoney(10000); //��ʼ��ȡ��������ֽ�һ��

		//int id, String name, String password, String bankCard,double currentMoney
		User[] users = new User[4];
		users[0] = new User(1, "1", "1", "001", 1000);
		users[1] = new User(2, "2", "2", "002", 10000);
		users[2] = new User(3, "3", "3", "003", 500);
		users[3] = new User(4, "4", "4", "004", 9000);
		atm.setUsers(users); //��ʼ��ȡ����Ϸ����û�


		/*
		 * ����UI����
		 */
		new ATMui(atm);
	}

	JFrame loginFrame;
	JFrame mainFrame;
	ATM atm;
	User user;

	public ATMui(ATM atm){
		loginFrame = login(); //���ý����¼ҳ��ķ���login()
		mainFrame = mainUI(); //����������ķ���main();
		this.atm = atm; //����ҳ��֮����Ҫ�õ�ȡ�����������ݣ����Խ�������
	}

	public JFrame login() {
		final JFrame frame = new JFrame("��¼");
		loginFrame = frame; //�ֲ������Ĵ��壬����ȫ�ֱ����ĵ�¼����
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null); //����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		JPanel panel = new JPanel(new GridLayout(4, 1));
		frame.add(panel);

		JPanel titlePanel = new JPanel();
		panel.add(titlePanel);
		JLabel title = new JLabel("��¼����");
		title.setFont(new Font("����", Font.BOLD, 25));
		titlePanel.add(title);

		JPanel namePanel = new JPanel();
		panel.add(namePanel);
		JLabel nameLabel = new JLabel("�������ǳƣ�");
		final JTextField nameText = new JTextField(10);
		final JLabel nameMsg = new JLabel("  ע �� �� ��   ");
		nameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(nameText.getText().equals("")){
					nameMsg.setText("�ǳƲ���Ϊ��");
					nameMsg.setForeground(Color.red);
				}else{
					nameMsg.setText("  ע �� �� ��   ");
					nameMsg.setForeground(Color.black);
				}
			}
		});
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		namePanel.add(nameMsg);

		JPanel passwordPanel = new JPanel(); //�������
		panel.add(passwordPanel);
		JLabel passwordLabel = new JLabel("���������룺");
		final JPasswordField password = new JPasswordField(10);
		final JLabel passwordMsg = new JLabel("  �� �� �� ��   ");
		password.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(password.getText().equals("")){
					passwordMsg.setText("���벻��Ϊ��");
					passwordMsg.setForeground(Color.red);
				}else{
					passwordMsg.setText("  �� �� �� ��   ");
					passwordMsg.setForeground(Color.black);
				}
			}
		});
		passwordPanel.add(passwordLabel);
		passwordPanel.add(password);
		passwordPanel.add(passwordMsg);

		JPanel buttonPanel = new JPanel();
		panel.add(buttonPanel);
		JButton login = new JButton("��¼");
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isPass = true;
				for(int i =0;i<atm.getUsers().length;i++){
					if(nameText.getText().equals(atm.getUsers()[i].getName())&&
							password.getText().equals(atm.getUsers()[i].getPassword())){//���ȥ��ֵ���������е�ĳ�������ֵ
						JOptionPane.showMessageDialog(frame, "��¼�ɹ�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
						loginFrame.setVisible(false);
						user = atm.getUsers()[i];
						isPass = false;
						mainFrame.setVisible(true);
					}
				}
				if(isPass && (!nameText.getText().equals("")) && (!password.getText().equals(""))){
					JOptionPane.showMessageDialog(frame, "�û��������������", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}else{
				}
			}
		});
		JButton reset = new JButton("����");
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nameText.setText("");
				password.setText("");
			}
		});
		buttonPanel.add(login);
		buttonPanel.add(reset);


		frame.setVisible(true);
		return frame;
	}

	public JFrame mainUI() {
		final JFrame frame = new JFrame("��Ϸ��");
		frame.setSize(450,160);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		final JPanel mainPanel = new JPanel(); //����ȫ����ť��������
		mainPanel.setLayout(new GridLayout(3, 3));
		frame.add(mainPanel);

		JPanel drawPanel = new JPanel();
		mainPanel.add(drawPanel);
		JButton drawMoney = new JButton("֧��"); //ȡ�֧��
		drawPanel.add(drawMoney);

		JPanel pane1 = new JPanel();
		mainPanel.add(pane1);
		final JLabel l1 = new JLabel();
		pane1.add(l1);

		JPanel queryPanel = new JPanel();
		mainPanel.add(queryPanel);
		JButton query = new JButton("��ѯ");
		queryPanel.add(query);

		JPanel depPanel = new JPanel();
		mainPanel.add(depPanel);
		JButton deposit = new JButton("��ֵ"); //����ֵ
		depPanel.add(deposit);

		final JPanel pane2 = new JPanel();
		mainPanel.add(pane2);
		final JLabel l2 = new JLabel();
		pane2.add(l2);

		JPanel exitPanel = new JPanel();
		mainPanel.add(exitPanel);
		JButton exit = new JButton("�˳�");
		exitPanel.add(exit);

		JPanel tranPanel = new JPanel();
		mainPanel.add(tranPanel);
		JButton transfer = new JButton("ת��");
		tranPanel.add(transfer);

		final JPanel pane3 = new JPanel();
		mainPanel.add(pane3);
		final JLabel l3 = new JLabel();
		pane3.add(l3);

		JPanel otherPanel = new JPanel();
		mainPanel.add(otherPanel);
		JButton other = new JButton("����");
		otherPanel.add(other);

		final JTextField textDraw = new JTextField(10);
		final JTextField textDeposit = new JTextField(10);
		final JTextField textTransfer = new JTextField(10);
		final JTextField textTransfer0 = new JTextField(10);
		final JButton buttonDraw = new JButton();
		final JButton buttonDeposit = new JButton();
		final JButton buttonABC = new JButton();
		final JButton buttonB = new JButton();
		final JButton buttonTransfer = new JButton();
		final JButton buttonTransfer0 = new JButton();
		
		drawMoney.addActionListener(new ActionListener() { //ȡ�֧��
			public void actionPerformed(ActionEvent e) {
				textDraw.setVisible(false);
				textDeposit.setVisible(false);
				textTransfer.setVisible(false);
				textTransfer0.setVisible(false);
				buttonDraw.setVisible(false);
				buttonDeposit.setVisible(false);
				buttonABC.setVisible(false);
				buttonB.setVisible(false);
				buttonTransfer.setVisible(false);
				buttonTransfer0.setVisible(false);
				l1.setText("");
				l2.setText("");
				l3.setText("");
				
				l1.setVisible(true);
				l1.setText("������Ҫ֧���Ľ�");
				l2.setVisible(false);
				textDraw.setVisible(true);
				pane2.add(textDraw);
				l3.setVisible(false);
				buttonDraw.setVisible(true);
				buttonDraw.setText("ȷ��֧��");
				pane3.add(buttonDraw);
				buttonDraw.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textDraw.setVisible(false);
						textDeposit.setVisible(false);
						textTransfer.setVisible(false);
						textTransfer0.setVisible(false);
						buttonDraw.setVisible(false);
						buttonDeposit.setVisible(false);
						buttonABC.setVisible(false);
						buttonB.setVisible(false);
						buttonTransfer.setVisible(false);
						buttonTransfer0.setVisible(false);
						l1.setText("");
						l2.setText("");
						l3.setText("");
						
						if(Integer.parseInt(textDraw.getText().toString()) <= user.getCurrentMoney()){
							user.setCurrentMoney(user.getCurrentMoney() - Integer.parseInt(textDraw.getText().toString()));
							l1.setText("֧���ɹ�");
							l2.setVisible(true);
							l2.setText("�˻����Ϊ��" + user.getCurrentMoney() + "Ԫ");
						} else {
							JOptionPane.showMessageDialog(frame, "���㣬�뼰ʱ��ֵ");
						}
					}
				});
			}
		});

		query.addActionListener(new ActionListener() { //��ѯ
			public void actionPerformed(ActionEvent e) {
				textDraw.setVisible(false);
				textDeposit.setVisible(false);
				textTransfer.setVisible(false);
				textTransfer0.setVisible(false);
				buttonDraw.setVisible(false);
				buttonDeposit.setVisible(false);
				buttonABC.setVisible(false);
				buttonB.setVisible(false);
				buttonTransfer.setVisible(false);
				buttonTransfer0.setVisible(false);
				l1.setText("");
				l2.setText("");
				l3.setText("");
				
				l2.setVisible(true);
				l2.setText("�˻����Ϊ��" + user.getCurrentMoney() + "Ԫ");
			}
		});

		deposit.addActionListener(new ActionListener() { //����ֵ
			public void actionPerformed(ActionEvent e) {
				textDraw.setVisible(false);
				textDeposit.setVisible(false);
				textTransfer.setVisible(false);
				textTransfer0.setVisible(false);
				buttonDraw.setVisible(false);
				buttonDeposit.setVisible(false);
				buttonABC.setVisible(false);
				buttonB.setVisible(false);
				buttonTransfer.setVisible(false);
				buttonTransfer0.setVisible(false);
				l1.setText("");
				l2.setText("");
				l3.setText("");
				
				l1.setVisible(true);
				l1.setText("�������ֵ��");
				l2.setVisible(false);
				textDeposit.setVisible(true);
				pane2.add(textDeposit);
				l3.setVisible(false);
				buttonDeposit.setVisible(true);
				buttonDeposit.setText("��ֵ");
				pane3.add(buttonDeposit);
				buttonDeposit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						l1.setText("��ѡ���ֵ��ʽ��");
						textDeposit.setVisible(false);
						buttonABC.setText("�й�ũҵ����");
						buttonABC.setForeground(Color.blue);
						pane2.add(buttonABC);
						buttonABC.setVisible(true);
						buttonDeposit.setVisible(false);
						buttonB.setText("�й���������");
						buttonB.setForeground(Color.blue);
						pane3.add(buttonB);
						buttonB.setVisible(true);
						buttonABC.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								textDraw.setVisible(false);
								textDeposit.setVisible(false);
								textTransfer.setVisible(false);
								textTransfer0.setVisible(false);
								buttonDraw.setVisible(false);
								buttonDeposit.setVisible(false);
								buttonABC.setVisible(false);
								buttonB.setVisible(false);
								buttonTransfer.setVisible(false);
								buttonTransfer0.setVisible(false);
								l1.setText("");
								l2.setText("");
								l3.setText("");
								
								l1.setText("��ֵ�ɹ�");
								user.setCurrentMoney(user.getCurrentMoney() + Integer.parseInt(textDeposit.getText().toString()));
								l2.setVisible(true);
								l2.setText("�˻����Ϊ��" + user.getCurrentMoney() + "Ԫ");
							}
						});
						buttonB.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								textDraw.setVisible(false);
								textDeposit.setVisible(false);
								textTransfer.setVisible(false);
								textTransfer0.setVisible(false);
								buttonDraw.setVisible(false);
								buttonDeposit.setVisible(false);
								buttonABC.setVisible(false);
								buttonB.setVisible(false);
								buttonTransfer.setVisible(false);
								buttonTransfer0.setVisible(false);
								l1.setText("");
								l2.setText("");
								l3.setText("");
								
								l1.setText("��ֵ�ɹ�");
								user.setCurrentMoney(user.getCurrentMoney() + Integer.parseInt(textDeposit.getText().toString()));
								l2.setVisible(true);
								l2.setText("�˻����Ϊ��" + user.getCurrentMoney() + "Ԫ");
							}
						});
					}
				});
			}
		});
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDraw.setVisible(false);
				textDeposit.setVisible(false);
				textTransfer.setVisible(false);
				textTransfer0.setVisible(false);
				buttonDraw.setVisible(false);
				buttonDeposit.setVisible(false);
				buttonABC.setVisible(false);
				buttonB.setVisible(false);
				buttonTransfer.setVisible(false);
				buttonTransfer0.setVisible(false);
				l1.setText("");
				l2.setText("");
				l3.setText("");
				
				mainFrame.setVisible(false);
				loginFrame.setVisible(true);
			}
		});
		
		transfer.addActionListener(new ActionListener() { //ת��
			public void actionPerformed(ActionEvent e) {
				textDraw.setVisible(false);
				textDeposit.setVisible(false);
				textTransfer.setVisible(false);
				textTransfer0.setVisible(false);
				buttonDraw.setVisible(false);
				buttonDeposit.setVisible(false);
				buttonABC.setVisible(false);
				buttonB.setVisible(false);
				buttonTransfer.setVisible(false);
				buttonTransfer0.setVisible(false);
				l1.setText("");
				l2.setText("");
				l3.setText("");
				
				l1.setVisible(true);
				l1.setText("������Ҫת���ʺţ�");
				l2.setVisible(false);
				pane2.add(textTransfer);
				textTransfer.setVisible(true);
				l3.setVisible(false);
				buttonTransfer.setText("ȷ��");
				pane3.add(buttonTransfer);
				buttonTransfer.setVisible(true);
				buttonTransfer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(textTransfer.getText().equals("")){
							JOptionPane.showMessageDialog(null, "������Ҫת���ʺ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
						} else {
							boolean cardflag = false;
							User useri = new User();
							for(int i =0; i<atm.getUsers().length; i++){
								if(atm.getUsers()[i].getBankCard().equals(textTransfer.getText()) && !atm.getUsers()[i].getBankCard().equals(user.getBankCard())){
									cardflag = true;//ֻҪ�����ֵ����ƥ���������е�����һ��
									useri = atm.getUsers()[i];
									break;
								}
							}
							if(cardflag){
								l1.setText("������Ҫת�Ľ�");
								textTransfer.setVisible(false);
								pane2.add(textTransfer0);
								textTransfer0.setVisible(true);
								buttonTransfer.setVisible(false);
								buttonTransfer0.setText("ȷ��");
								pane3.add(buttonTransfer0);
								buttonTransfer0.setVisible(true);
								buttonTransfer0.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(textTransfer0.getText().equals("")){
											JOptionPane.showMessageDialog(frame, "������ת�ʽ��");
										}else{
											textDraw.setVisible(false);
											textDeposit.setVisible(false);
											textTransfer.setVisible(false);
											textTransfer0.setVisible(false);
											buttonDraw.setVisible(false);
											buttonDeposit.setVisible(false);
											buttonABC.setVisible(false);
											buttonB.setVisible(false);
											buttonTransfer.setVisible(false);
											buttonTransfer0.setVisible(false);
											l1.setText("");
											l2.setText("");
											l3.setText("");
											
											user.setCurrentMoney(user.getCurrentMoney() - Integer.parseInt(textTransfer0.getText().toString()));
											l1.setVisible(true);
											l1.setText("ת�˳ɹ�");
											l2.setVisible(true);
											l2.setText("�˻����Ϊ��" + user.getCurrentMoney() + "Ԫ");
										}
									}
								});
							}else{
								JOptionPane.showMessageDialog(frame, "�ʺ���������������");
							}
						}
					}
				});
			}
		});
		
		other.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDraw.setVisible(false);
				textDeposit.setVisible(false);
				textTransfer.setVisible(false);
				textTransfer0.setVisible(false);
				buttonDraw.setVisible(false);
				buttonDeposit.setVisible(false);
				buttonABC.setVisible(false);
				buttonB.setVisible(false);
				buttonTransfer.setVisible(false);
				buttonTransfer0.setVisible(false);
				l1.setText("");
				l2.setText("");
				l3.setText("");
			}
		});
		return frame;

	}
}
