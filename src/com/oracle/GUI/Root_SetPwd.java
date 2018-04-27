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

import com.oracle.vo.Root;
import javax.swing.ImageIcon;

public class Root_SetPwd {

	JFrame frmRoot_SetPwd;
	private JPasswordField passwordField;
	private Root root;

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
					Root_SetPwd window = new Root_SetPwd();
					window.frmRoot_SetPwd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Root_SetPwd() {
		initialize();
	}

	public Root_SetPwd(Root root) {
		this.root = root;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRoot_SetPwd = new JFrame();
		frmRoot_SetPwd.getContentPane().setBackground(Color.PINK);
		frmRoot_SetPwd.getContentPane().setForeground(Color.BLACK);
		frmRoot_SetPwd.setTitle("SetPwd");
		frmRoot_SetPwd.setBounds(100, 100, 581, 465);
		frmRoot_SetPwd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("\u4FEE\u6539\u5BC6\u7801\u2460");
		lblNewLabel.setBounds(138, 43, 241, 55);
		lblNewLabel.setForeground(new Color(218, 112, 214));
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 47));

		JLabel label = new JLabel("\u5F53\u524D\u5BC6\u7801\uFF1A");
		label.setBounds(79, 157, 152, 41);
		label.setFont(new Font("宋体", Font.PLAIN, 28));
		frmRoot_SetPwd.getContentPane().setLayout(null);
		frmRoot_SetPwd.getContentPane().add(lblNewLabel);
		frmRoot_SetPwd.getContentPane().add(label);

		passwordField = new JPasswordField();
		passwordField.setBounds(218, 157, 210, 41);
		frmRoot_SetPwd.getContentPane().add(passwordField);

		JButton button = new JButton("\u4E0B\u4E00\u6B65");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (root.getPwd().equals(passwordField.getText())) {

					frmRoot_SetPwd.dispose();
					Root_SetPwd2 window = new Root_SetPwd2(root);
					window.frmRoot_SetPwd2.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "当前密码输入错误！\n请重新输入！", "错误提示", JOptionPane.ERROR_MESSAGE);
					passwordField.setText("");
				}

			}
		});
		JRootPane rootpane=frmRoot_SetPwd.getRootPane();	
		rootpane.setDefaultButton(button);
		button.setForeground(new Color(0, 0, 0));
		button.setFont(new Font("宋体", Font.PLAIN, 24));
		button.setBackground(new Color(255, 248, 220));
		button.setBounds(225, 238, 120, 41);
		frmRoot_SetPwd.getContentPane().add(button);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("E:\\workspace\\Bank\\imge\\LL (12).jpg"));
		lblNewLabel_1.setBounds(0, -1, 566, 429);
		frmRoot_SetPwd.getContentPane().add(lblNewLabel_1);
	}
}



