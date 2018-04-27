package com.oracle.GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import com.oracle.dao.CommonDao;
import com.oracle.dao.DbDao;
import com.oracle.vo.User;
import javax.swing.ImageIcon;

public class User_SetPwd {

	JFrame frmUser_SetPwd;
	private JPasswordField passwordField;
	private User user;

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
					User_SetPwd window = new User_SetPwd();
					window.frmUser_SetPwd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public User_SetPwd() {
		initialize();
	}

	public User_SetPwd(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUser_SetPwd = new JFrame();
		frmUser_SetPwd.getContentPane().setBackground(Color.PINK);
		frmUser_SetPwd.getContentPane().setForeground(Color.BLACK);
		frmUser_SetPwd.setTitle("SetPwd");
		frmUser_SetPwd.setBounds(100, 100, 581, 465);
		frmUser_SetPwd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("\u4FEE\u6539\u5BC6\u7801\u2460");
		lblNewLabel.setBounds(138, 43, 241, 55);
		lblNewLabel.setForeground(new Color(238, 130, 238));
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 47));

		JLabel label = new JLabel("\u5F53\u524D\u5BC6\u7801\uFF1A");
		label.setBounds(79, 157, 152, 41);
		label.setFont(new Font("宋体", Font.PLAIN, 28));
		frmUser_SetPwd.getContentPane().setLayout(null);
		frmUser_SetPwd.getContentPane().add(lblNewLabel);
		frmUser_SetPwd.getContentPane().add(label);

		passwordField = new JPasswordField();
		passwordField.setBounds(218, 157, 210, 41);
		frmUser_SetPwd.getContentPane().add(passwordField);

		JButton button = new JButton("\u4E0B\u4E00\u6B65");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user.getPwd().equals(passwordField.getText())) {

					frmUser_SetPwd.dispose();
					User_SetPwd2 window = new User_SetPwd2(user);
					window.frmUser_SetPwd2.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "当前密码输入错误！\n请重新输入！", "错误提示", JOptionPane.ERROR_MESSAGE);
					passwordField.setText("");
				}

			}
		});
		JRootPane rootpane=frmUser_SetPwd.getRootPane();	
		rootpane.setDefaultButton(button);
		button.setForeground(new Color(0, 0, 0));
		button.setFont(new Font("宋体", Font.PLAIN, 24));
		button.setBackground(new Color(255, 248, 220));
		button.setBounds(225, 238, 120, 41);
		frmUser_SetPwd.getContentPane().add(button);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("E:\\workspace\\Bank\\imge\\LL (12).jpg"));
		lblNewLabel_1.setBounds(0, 0, 565, 426);
		frmUser_SetPwd.getContentPane().add(lblNewLabel_1);
	}
}



