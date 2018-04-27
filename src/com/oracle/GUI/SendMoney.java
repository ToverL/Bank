package com.oracle.GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.oracle.dao.UserDao;
import com.oracle.vo.User;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class SendMoney extends JFrame implements Runnable {

	JFrame frmSendMoney;
	private JTextField textField;
	private JLabel time;
	User user;
	private JTextField textField_1;
	private JTextField textField_2;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SendMoney window = new SendMoney();
					window.frmSendMoney.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SendMoney() {
		initialize();
	}

	public SendMoney(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSendMoney = new JFrame();
		frmSendMoney.getContentPane().setBackground(Color.PINK);
		frmSendMoney.getContentPane().setLayout(null);

		JLabel lblSelect = new JLabel("\u8F6C\u8D26");
		lblSelect.setBackground(Color.WHITE);
		lblSelect.setForeground(new Color(0, 255, 255));
		lblSelect.setFont(new Font("宋体", Font.PLAIN, 47));
		lblSelect.setBounds(228, 22, 109, 55);
		frmSendMoney.getContentPane().add(lblSelect);

		JLabel label = new JLabel("\u8F6C\u8D26\u91D1\u989D\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 28));
		label.setBounds(98, 352, 147, 41);
		frmSendMoney.getContentPane().add(label);

		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(240, 351, 187, 48);
		frmSendMoney.getContentPane().add(textField);

		JLabel label_1 = new JLabel("\u00A5");
		label_1.setFont(new Font("宋体", Font.PLAIN, 28));
		label_1.setBounds(437, 354, 33, 41);
		frmSendMoney.getContentPane().add(label_1);

		JButton button = new JButton("\u786E\u8BA4\u8F6C\u8D26");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty() || textField_1.getText().isEmpty()
						|| textField_2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "请填写完整信息！", "错误提示！", JOptionPane.ERROR_MESSAGE);
				} else if(user.getUserno().equals(textField_1.getText())){
					JOptionPane.showMessageDialog(null, "不可给本人转账！", "错误提示！", JOptionPane.ERROR_MESSAGE);
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
				}else{
					double money = Double.valueOf(textField.getText());
					if (money < 0) {
						JOptionPane.showMessageDialog(null, "请正确输入金额！", "错误提示！", JOptionPane.ERROR_MESSAGE);
						textField.setText("");
					} else {
						User user2 = new User();
						user2.setUserno(textField_1.getText());
						user2.setName(textField_2.getText());
						int flag = UserDao.SendMoney(user, user2, money);
						if (flag == 1) {
							JOptionPane.showMessageDialog(null, "余额不足！", "转账失败", JOptionPane.ERROR_MESSAGE);
							textField.setText("");
						} else if (flag == 2) {
							JOptionPane.showMessageDialog(null, "收款人信息填写有误！", "转账失败", JOptionPane.ERROR_MESSAGE);
							textField.setText("");
							textField_1.setText("");
							textField_2.setText("");
						} else if (flag == 3) {
							JOptionPane.showMessageDialog(null, "系统发生故障！", "转账失败", JOptionPane.ERROR_MESSAGE);
							textField.setText("");
							textField_1.setText("");
							textField_2.setText("");
							frmSendMoney.dispose();
						} else {
							frmSendMoney.dispose();
							JOptionPane.showMessageDialog(null, "转账成功！\n成功转入账户" + user2.getName() + "\n" + money + "¥",
									"转账成功", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		JRootPane rootpane=frmSendMoney.getRootPane();	
		rootpane.setDefaultButton(button);
		button.setForeground(new Color(0, 0, 0));
		button.setFont(new Font("宋体", Font.PLAIN, 33));
		button.setBackground(new Color(255, 248, 220));
		button.setBounds(189, 427, 191, 40);
		frmSendMoney.getContentPane().add(button);

		JLabel label_2 = new JLabel("\u6536\u6B3E\u4EBA\u8D26\u53F7\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 28));
		label_2.setBounds(53, 191, 170, 41);
		frmSendMoney.getContentPane().add(label_2);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(236, 194, 209, 34);
		frmSendMoney.getContentPane().add(textField_1);

		JLabel label_3 = new JLabel("\u6536\u6B3E\u4EBA\u59D3\u540D\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 28));
		label_3.setBounds(53, 253, 170, 41);
		frmSendMoney.getContentPane().add(label_3);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(236, 256, 209, 34);
		frmSendMoney.getContentPane().add(textField_2);

		JLabel label_4 = new JLabel("当前余额：");
		label_4.setFont(new Font("宋体", Font.PLAIN, 31));
		label_4.setBounds(109, 87, 161, 55);
		frmSendMoney.getContentPane().add(label_4);

		JLabel label_5 = new JLabel(user.getSum() + "¥");
		label_5.setForeground(Color.YELLOW);
		label_5.setFont(new Font("宋体", Font.PLAIN, 31));
		label_5.setBounds(258, 87, 211, 55);
		frmSendMoney.getContentPane().add(label_5);

		time = new JLabel("结束还剩  120s");
		time.setForeground(Color.RED);
		time.setFont(new Font("STZhongsong", Font.PLAIN, 20));
		time.setBounds(408, 435, 161, 35);
		frmSendMoney.getContentPane().add(time);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\workspace\\Bank\\imge\\LL (23).jpg"));
		lblNewLabel.setBounds(0, 0, 591, 495);
		frmSendMoney.getContentPane().add(lblNewLabel);

		frmSendMoney.setTitle("SendMoney");
		frmSendMoney.setBounds(100, 100, 607, 534);
		frmSendMoney.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	@Override
	public void run() {
		for (int i = 120; i >= 0; i--) {
			try {
				time.setText("结束还剩  " + i + "s");
				Thread.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		frmSendMoney.dispose();
	}

}
