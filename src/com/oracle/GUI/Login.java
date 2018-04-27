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
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import com.oracle.dao.DbDao;
import com.oracle.vo.User;
import javax.swing.ImageIcon;

public class Login {

	JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.getContentPane().setBackground(Color.PINK);
		frmLogin.getContentPane().setForeground(Color.BLACK);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 581, 465);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(218, 27, 120, 55);
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 47));
		
		JLabel lblNewLabel_1 = new JLabel("\u8D26\u53F7\uFF1A");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(37, 134, 125, 41);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 28));
		
		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
		label.setForeground(Color.WHITE);
		label.setBounds(36, 196, 125, 41);
		label.setFont(new Font("宋体", Font.PLAIN, 28));
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setBounds(175, 137, 209, 34);
		textField.setColumns(10);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.getContentPane().add(lblNewLabel);
		frmLogin.getContentPane().add(lblNewLabel_1);
		frmLogin.getContentPane().add(textField);
		frmLogin.getContentPane().add(label);
		
		JButton btnNewButton = new JButton("admin");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
				Root_Login window = new Root_Login();
				window.frmRoot_Login.setVisible(true);
				
				
			}
		});
		btnNewButton.setBackground(new Color(255, 248, 220));
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 11));
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setBounds(479, 396, 76, 20);
		frmLogin.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(174, 206, 210, 31);
		frmLogin.getContentPane().add(passwordField);
		
		JButton button = new JButton("\u767B\u5F55");
		button.setIcon(null);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setUserno(textField.getText());
				user.setPwd(passwordField.getText());
				try {
					if(DbDao.U_Login(user)){
					JOptionPane.showMessageDialog(null, "密码输入正确！", "登录成功", JOptionPane.INFORMATION_MESSAGE);
					frmLogin.dispose();
					User_Opreation window = new User_Opreation(user);
					window.frmUser_Opreation.setVisible(true);
					new Thread(window).start();
					}
					else{
						JOptionPane.showMessageDialog(null, "用户名或密码不正确！请检查后重新输入！", "错误提示", JOptionPane.ERROR_MESSAGE); 
						textField.setText("");
						passwordField.setText("");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}				
			}
		});
		JRootPane rootpane=frmLogin.getRootPane();	
		rootpane.setDefaultButton(button);
		
		button.setForeground(new Color(0, 0, 0));
		button.setFont(new Font("宋体", Font.PLAIN, 24));
		button.setBackground(new Color(255, 248, 220));
		button.setBounds(218, 266, 120, 41);
		frmLogin.getContentPane().add(button);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("E:\\workspace\\Bank\\imge\\161232b8vbsjjthbh0mty0.jpg"));
		lblNewLabel_2.setBounds(0, 0, 565, 426);
		frmLogin.getContentPane().add(lblNewLabel_2);
	}
}
