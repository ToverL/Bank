package com.oracle.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.oracle.dao.RootDao;
import com.oracle.vo.Root;
import com.oracle.vo.User;

import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Enroll {

	JFrame frmEnroll;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	Root root=null;
	User user=null;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Root getRoot() {
		return root;
	}

	public void setRoot(Root root) {
		this.root = root;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Enroll window = new Enroll();
					window.frmEnroll.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Enroll(Root root) {
		this.root=root;
		initialize();
	}
	public Enroll() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEnroll = new JFrame();
		frmEnroll.setTitle("Enroll");
		frmEnroll.getContentPane().setBackground(Color.PINK);
		frmEnroll.getContentPane().setLayout(null);
		
		JLabel lblEnroll = new JLabel("\u6CE8\u518C");
		lblEnroll.setForeground(Color.CYAN);
		lblEnroll.setFont(new Font("STSong", Font.PLAIN, 47));
		lblEnroll.setBounds(235, 31, 120, 55);
		frmEnroll.getContentPane().add(lblEnroll);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 28));
		label.setBounds(69, 128, 125, 41);
		frmEnroll.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(207, 131, 209, 34);
		frmEnroll.getContentPane().add(textField);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 28));
		label_1.setBounds(69, 201, 125, 41);
		frmEnroll.getContentPane().add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(207, 211, 210, 31);
		frmEnroll.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(206, 288, 210, 31);
		frmEnroll.getContentPane().add(passwordField_1);
		
		JLabel label_2 = new JLabel("\u91CD\u590D\u5BC6\u7801\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 28));
		label_2.setBounds(68, 278, 157, 41);
		frmEnroll.getContentPane().add(label_2);
		
		JButton button = new JButton("\u5F00\u6237");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(!passwordField.getText().equals(passwordField_1.getText())){
					JOptionPane.showMessageDialog(null, "两次密码输入不相同！", "注册失败！", JOptionPane.ERROR_MESSAGE); 
					textField.setText("");
					passwordField.setText("");
					passwordField_1.setText("");
				}else{
						User user = new User();
						user.setName(textField.getText());
						user.setPwd(passwordField.getText());
						RootDao.U_Enroll(root, user);
					    JOptionPane.showMessageDialog(null, "恭喜您注册成功！\n"+"您的账号为："+user.getUserno()+"\n安全保存并请牢记！！", "注册成功", JOptionPane.INFORMATION_MESSAGE);
						frmEnroll.dispose();	
				}
			}
		});
		JRootPane rootpane=frmEnroll.getRootPane();	
		rootpane.setDefaultButton(button);
		button.setForeground(new Color(47, 79, 79));
		button.setFont(new Font("宋体", Font.PLAIN, 24));
		button.setBackground(new Color(255, 248, 220));
		button.setBounds(240, 376, 120, 41);
		frmEnroll.getContentPane().add(button);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\workspace\\Bank\\imge\\LL (2).png"));
		lblNewLabel.setBounds(2, 1, 609, 458);
		frmEnroll.getContentPane().add(lblNewLabel);
		frmEnroll.setBounds(100, 100, 629, 500);
		frmEnroll.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

	}
}
