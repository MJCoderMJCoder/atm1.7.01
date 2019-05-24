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
		 * 初始化（取款机）信息
		 */
		ATM atm = new ATM(); //新建一台取款机对象
		atm.setCurrentMoney(10000); //初始化取款机内有现金一万

		//int id, String name, String password, String bankCard,double currentMoney
		User[] users = new User[4];
		users[0] = new User(1, "1", "1", "001", 1000);
		users[1] = new User(2, "2", "2", "002", 10000);
		users[2] = new User(3, "3", "3", "003", 500);
		users[3] = new User(4, "4", "4", "004", 9000);
		atm.setUsers(users); //初始化取款机合法的用户


		/*
		 * 进入UI界面
		 */
		new ATMui(atm);
	}

	JFrame loginFrame;
	JFrame mainFrame;
	ATM atm;
	User user;

	public ATMui(ATM atm){
		loginFrame = login(); //调用进入登录页面的方法login()
		mainFrame = mainUI(); //进入主界面的方法main();
		this.atm = atm; //进入页面之后需要用到取款机里面的数据，所以进行设置
	}

	public JFrame login() {
		final JFrame frame = new JFrame("登录");
		loginFrame = frame; //局部变量的窗体，就是全局变量的登录窗体
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null); //居中
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		JPanel panel = new JPanel(new GridLayout(4, 1));
		frame.add(panel);

		JPanel titlePanel = new JPanel();
		panel.add(titlePanel);
		JLabel title = new JLabel("登录界面");
		title.setFont(new Font("宋体", Font.BOLD, 25));
		titlePanel.add(title);

		JPanel namePanel = new JPanel();
		panel.add(namePanel);
		JLabel nameLabel = new JLabel("请输入昵称：");
		final JTextField nameText = new JTextField(10);
		final JLabel nameMsg = new JLabel("  注 册 昵 称   ");
		nameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(nameText.getText().equals("")){
					nameMsg.setText("昵称不能为空");
					nameMsg.setForeground(Color.red);
				}else{
					nameMsg.setText("  注 册 昵 称   ");
					nameMsg.setForeground(Color.black);
				}
			}
		});
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		namePanel.add(nameMsg);

		JPanel passwordPanel = new JPanel(); //密码面板
		panel.add(passwordPanel);
		JLabel passwordLabel = new JLabel("请输入密码：");
		final JPasswordField password = new JPasswordField(10);
		final JLabel passwordMsg = new JLabel("  找 回 密 码   ");
		password.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(password.getText().equals("")){
					passwordMsg.setText("密码不能为空");
					passwordMsg.setForeground(Color.red);
				}else{
					passwordMsg.setText("  找 回 密 码   ");
					passwordMsg.setForeground(Color.black);
				}
			}
		});
		passwordPanel.add(passwordLabel);
		passwordPanel.add(password);
		passwordPanel.add(passwordMsg);

		JPanel buttonPanel = new JPanel();
		panel.add(buttonPanel);
		JButton login = new JButton("登录");
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isPass = true;
				for(int i =0;i<atm.getUsers().length;i++){
					if(nameText.getText().equals(atm.getUsers()[i].getName())&&
							password.getText().equals(atm.getUsers()[i].getPassword())){//输进去的值等于数组中的某个对象的值
						JOptionPane.showMessageDialog(frame, "登录成功", "提示",JOptionPane.INFORMATION_MESSAGE);
						loginFrame.setVisible(false);
						user = atm.getUsers()[i];
						isPass = false;
						mainFrame.setVisible(true);
					}
				}
				if(isPass && (!nameText.getText().equals("")) && (!password.getText().equals(""))){
					JOptionPane.showMessageDialog(frame, "用户名或者密码错误", "提示",JOptionPane.INFORMATION_MESSAGE);
				}else{
				}
			}
		});
		JButton reset = new JButton("重置");
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
		final JFrame frame = new JFrame("游戏币");
		frame.setSize(450,160);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		final JPanel mainPanel = new JPanel(); //含有全部按钮的主界面
		mainPanel.setLayout(new GridLayout(3, 3));
		frame.add(mainPanel);

		JPanel drawPanel = new JPanel();
		mainPanel.add(drawPanel);
		JButton drawMoney = new JButton("支付"); //取款、支付
		drawPanel.add(drawMoney);

		JPanel pane1 = new JPanel();
		mainPanel.add(pane1);
		final JLabel l1 = new JLabel();
		pane1.add(l1);

		JPanel queryPanel = new JPanel();
		mainPanel.add(queryPanel);
		JButton query = new JButton("查询");
		queryPanel.add(query);

		JPanel depPanel = new JPanel();
		mainPanel.add(depPanel);
		JButton deposit = new JButton("充值"); //存款、充值
		depPanel.add(deposit);

		final JPanel pane2 = new JPanel();
		mainPanel.add(pane2);
		final JLabel l2 = new JLabel();
		pane2.add(l2);

		JPanel exitPanel = new JPanel();
		mainPanel.add(exitPanel);
		JButton exit = new JButton("退出");
		exitPanel.add(exit);

		JPanel tranPanel = new JPanel();
		mainPanel.add(tranPanel);
		JButton transfer = new JButton("转帐");
		tranPanel.add(transfer);

		final JPanel pane3 = new JPanel();
		mainPanel.add(pane3);
		final JLabel l3 = new JLabel();
		pane3.add(l3);

		JPanel otherPanel = new JPanel();
		mainPanel.add(otherPanel);
		JButton other = new JButton("清屏");
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
		
		drawMoney.addActionListener(new ActionListener() { //取款、支付
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
				l1.setText("请输入要支付的金额：");
				l2.setVisible(false);
				textDraw.setVisible(true);
				pane2.add(textDraw);
				l3.setVisible(false);
				buttonDraw.setVisible(true);
				buttonDraw.setText("确定支付");
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
							l1.setText("支付成功");
							l2.setVisible(true);
							l2.setText("账户余额为：" + user.getCurrentMoney() + "元");
						} else {
							JOptionPane.showMessageDialog(frame, "余额不足，请及时充值");
						}
					}
				});
			}
		});

		query.addActionListener(new ActionListener() { //查询
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
				l2.setText("账户余额为：" + user.getCurrentMoney() + "元");
			}
		});

		deposit.addActionListener(new ActionListener() { //存款、充值
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
				l1.setText("请输入充值金额：");
				l2.setVisible(false);
				textDeposit.setVisible(true);
				pane2.add(textDeposit);
				l3.setVisible(false);
				buttonDeposit.setVisible(true);
				buttonDeposit.setText("充值");
				pane3.add(buttonDeposit);
				buttonDeposit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						l1.setText("请选择充值方式：");
						textDeposit.setVisible(false);
						buttonABC.setText("中国农业银行");
						buttonABC.setForeground(Color.blue);
						pane2.add(buttonABC);
						buttonABC.setVisible(true);
						buttonDeposit.setVisible(false);
						buttonB.setText("中国工商银行");
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
								
								l1.setText("充值成功");
								user.setCurrentMoney(user.getCurrentMoney() + Integer.parseInt(textDeposit.getText().toString()));
								l2.setVisible(true);
								l2.setText("账户余额为：" + user.getCurrentMoney() + "元");
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
								
								l1.setText("充值成功");
								user.setCurrentMoney(user.getCurrentMoney() + Integer.parseInt(textDeposit.getText().toString()));
								l2.setVisible(true);
								l2.setText("账户余额为：" + user.getCurrentMoney() + "元");
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
		
		transfer.addActionListener(new ActionListener() { //转帐
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
				l1.setText("请输入要转的帐号：");
				l2.setVisible(false);
				pane2.add(textTransfer);
				textTransfer.setVisible(true);
				l3.setVisible(false);
				buttonTransfer.setText("确定");
				pane3.add(buttonTransfer);
				buttonTransfer.setVisible(true);
				buttonTransfer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(textTransfer.getText().equals("")){
							JOptionPane.showMessageDialog(null, "请输入要转的帐号", "提示", JOptionPane.INFORMATION_MESSAGE);
						} else {
							boolean cardflag = false;
							User useri = new User();
							for(int i =0; i<atm.getUsers().length; i++){
								if(atm.getUsers()[i].getBankCard().equals(textTransfer.getText()) && !atm.getUsers()[i].getBankCard().equals(user.getBankCard())){
									cardflag = true;//只要输入的值可以匹配上数组中的任意一个
									useri = atm.getUsers()[i];
									break;
								}
							}
							if(cardflag){
								l1.setText("请输入要转的金额：");
								textTransfer.setVisible(false);
								pane2.add(textTransfer0);
								textTransfer0.setVisible(true);
								buttonTransfer.setVisible(false);
								buttonTransfer0.setText("确定");
								pane3.add(buttonTransfer0);
								buttonTransfer0.setVisible(true);
								buttonTransfer0.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(textTransfer0.getText().equals("")){
											JOptionPane.showMessageDialog(frame, "请输入转帐金额");
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
											l1.setText("转账成功");
											l2.setVisible(true);
											l2.setText("账户余额为：" + user.getCurrentMoney() + "元");
										}
									}
								});
							}else{
								JOptionPane.showMessageDialog(frame, "帐号有误，请重新输入");
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
