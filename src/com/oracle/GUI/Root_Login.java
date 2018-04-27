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
import com.oracle.vo.Root;
import javax.swing.ImageIcon;

public class Root_Login {

	JFrame frmRoot_Login;
	private JTextField textField;
	private JPasswordField passwordField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Root_Login window = new Root_Login();
					window.frmRoot_Login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Root_Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRoot_Login = new JFrame();
		frmRoot_Login.getContentPane().setBackground(Color.PINK);
		frmRoot_Login.getContentPane().setForeground(Color.BLACK);
		frmRoot_Login.setTitle("Root_Login");
		frmRoot_Login.setBounds(100, 100, 581, 465);
		frmRoot_Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Root Login");
		lblNewLabel.setBounds(163, 34, 221, 55);
		lblNewLabel.setForeground(Color.ORANGE);
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
		frmRoot_Login.getContentPane().setLayout(null);
		frmRoot_Login.getContentPane().add(lblNewLabel);
		frmRoot_Login.getContentPane().add(lblNewLabel_1);
		frmRoot_Login.getContentPane().add(textField);
		frmRoot_Login.getContentPane().add(label);
		
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Root root = new Root();
				root.setNumber(textField.getText());
				root.setPwd(passwordField.getText());
				try {
					if(DbDao.R_Login(root)){
					JOptionPane.showMessageDialog(null, "密码输入正确！", "登录成功", JOptionPane.INFORMATION_MESSAGE);
					frmRoot_Login.dispose();
					Root_Opreation window = new Root_Opreation(root);
					window.frmRoot_Opreation.setVisible(true);
					new Thread(window).start();
					}
					else{
						JOptionPane.showMessageDialog(null, "用户名或密码不正确！请检查后重新输入！", "错误提示", JOptionPane.ERROR_MESSAGE); 
						textField.setText("");
						passwordField.setText("");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		
		JRootPane rootpane=frmRoot_Login.getRootPane();	
		rootpane.setDefaultButton(btnNewButton);
		
		btnNewButton.setBackground(new Color(255, 248, 220));
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 28));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(208, 269, 125, 41);
		frmRoot_Login.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(174, 206, 210, 31);
		frmRoot_Login.getContentPane().add(passwordField);
		
		JButton btnUser = new JButton("user");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRoot_Login.dispose();;
				Login window  = new Login();
				window.frmLogin.setVisible(true);
			}
		});
		btnUser.setForeground(Color.BLUE);
		btnUser.setFont(new Font("宋体", Font.PLAIN, 11));
		btnUser.setBackground(new Color(255, 248, 220));
		btnUser.setBounds(479, 396, 76, 20);
		
		frmRoot_Login.getContentPane().add(btnUser);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("E:\\workspace\\Bank\\imge\\1355220198423.jpg"));
		lblNewLabel_2.setBounds(0, 0, 565, 426);
		frmRoot_Login.getContentPane().add(lblNewLabel_2);
	}
}
