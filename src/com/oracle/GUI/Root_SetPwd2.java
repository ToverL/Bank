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

import com.oracle.dao.RootDao;
import com.oracle.vo.Root;
import javax.swing.ImageIcon;

public class Root_SetPwd2 {

	JFrame frmRoot_SetPwd2;
	private JPasswordField passwordField;
	private Root root;
	private JPasswordField passwordField_1;

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
					Root_SetPwd2 window = new Root_SetPwd2();
					window.frmRoot_SetPwd2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public Root_SetPwd2() {
		initialize();
	}

	public Root_SetPwd2 (Root root) {
		super();
		this.root=root;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRoot_SetPwd2 = new JFrame();
		frmRoot_SetPwd2.getContentPane().setBackground(Color.PINK);
		frmRoot_SetPwd2.getContentPane().setForeground(Color.BLACK);
		frmRoot_SetPwd2.setTitle("SetPwd2");
		frmRoot_SetPwd2.setBounds(100, 100, 581, 465);
		frmRoot_SetPwd2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("\u4FEE\u6539\u5BC6\u7801\u2461");
		lblNewLabel.setBounds(154, 40, 235, 55);
		lblNewLabel.setForeground(new Color(238, 130, 238));
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 47));

		JLabel label = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		label.setBounds(78, 142, 152, 41);
		label.setFont(new Font("宋体", Font.PLAIN, 28));
		frmRoot_SetPwd2.getContentPane().setLayout(null);
		frmRoot_SetPwd2.getContentPane().add(lblNewLabel);
		frmRoot_SetPwd2.getContentPane().add(label);

		passwordField = new JPasswordField();
		passwordField.setBounds(217, 142, 210, 41);
		frmRoot_SetPwd2.getContentPane().add(passwordField);

		JButton button = new JButton("\u786E\u8BA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (passwordField.getText().isEmpty() || passwordField_1.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "请正确输入新密码！", "警告", JOptionPane.ERROR_MESSAGE);

				} else {
					if (!passwordField.getText().equals(passwordField_1.getText())) {
						JOptionPane.showMessageDialog(null, "两次密码输入不相同！", "修改失败！", JOptionPane.ERROR_MESSAGE);
						passwordField.setText("");
						passwordField_1.setText("");
					} else {

						if (RootDao.SetPwd(root, passwordField.getText())) {
							frmRoot_SetPwd2.dispose();
							JOptionPane.showMessageDialog(null, "密码修改成功！\n\n请安全保存并请牢记！！", "修改成功",JOptionPane.INFORMATION_MESSAGE);
							Root_Login window = new Root_Login();
							window.frmRoot_Login.setVisible(true);
						}else{
							frmRoot_SetPwd2.setVisible(false);
							JOptionPane.showMessageDialog(null, "密码修改失败！\n\n新密码与旧密码相同！", "修改失败！", JOptionPane.ERROR_MESSAGE);
						}

					}

				}

			}
		});
		JRootPane rootpane=frmRoot_SetPwd2.getRootPane();	
		rootpane.setDefaultButton(button);
		button.setForeground(new Color(0, 0, 0));
		button.setFont(new Font("宋体", Font.PLAIN, 24));
		button.setBackground(new Color(255, 248, 220));
		button.setBounds(217, 299, 120, 41);
		frmRoot_SetPwd2.getContentPane().add(button);

		JLabel label_1 = new JLabel("\u91CD\u590D\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 28));
		label_1.setBounds(79, 208, 152, 41);
		frmRoot_SetPwd2.getContentPane().add(label_1);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(218, 208, 210, 41);
		frmRoot_SetPwd2.getContentPane().add(passwordField_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("E:\\workspace\\Bank\\imge\\LL (12).jpg"));
		lblNewLabel_1.setBounds(-1, 0, 567, 427);
		frmRoot_SetPwd2.getContentPane().add(lblNewLabel_1);
	}
}
