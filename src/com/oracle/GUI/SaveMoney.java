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

public class SaveMoney extends JFrame implements Runnable{
	private JLabel time;
	JFrame frmSaveMoney;
	private JTextField textField;
	User user;

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
					SaveMoney window = new SaveMoney();
					window.frmSaveMoney.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SaveMoney() {
		initialize();
	}

	public SaveMoney(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSaveMoney = new JFrame();
		frmSaveMoney.getContentPane().setBackground(Color.PINK);
		frmSaveMoney.getContentPane().setLayout(null);

		JLabel lblSelect = new JLabel("\u5B58\u6B3E");
		lblSelect.setBackground(Color.WHITE);
		lblSelect.setForeground(new Color(0, 255, 255));
		lblSelect.setFont(new Font("宋体", Font.PLAIN, 47));
		lblSelect.setBounds(228, 22, 109, 55);
		frmSaveMoney.getContentPane().add(lblSelect);

		JLabel label = new JLabel("\u5B58\u6B3E\u91D1\u989D\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 28));
		label.setBounds(101, 191, 147, 41);
		frmSaveMoney.getContentPane().add(label);

		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(243, 190, 187, 48);
		frmSaveMoney.getContentPane().add(textField);

		JLabel label_1 = new JLabel("\u00A5");
		label_1.setFont(new Font("宋体", Font.PLAIN, 28));
		label_1.setBounds(440, 193, 33, 41);
		frmSaveMoney.getContentPane().add(label_1);

		
		JButton button = new JButton("\u786E\u8BA4\u5B58\u6B3E");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "请正确输入金额！", "错误提示！", JOptionPane.ERROR_MESSAGE);
					textField.setText("");
				} else {
					double money= Double.valueOf(textField.getText());
					if (money < 0) {
						JOptionPane.showMessageDialog(null, "请正确输入金额！", "错误提示！", JOptionPane.ERROR_MESSAGE);
						textField.setText("");
					} else {
						if (UserDao.SaveMoney(user, money)) {
							frmSaveMoney.dispose();
							JOptionPane.showMessageDialog(null, "存款成功！\n成功存入账户" + money + "¥", "存款成功",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		JRootPane rootpane=frmSaveMoney.getRootPane();	
		rootpane.setDefaultButton(button);
		button.setForeground(new Color(0, 0, 0));
		button.setFont(new Font("宋体", Font.PLAIN, 33));
		button.setBackground(new Color(255, 248, 220));
		button.setBounds(184, 282, 191, 40);
		frmSaveMoney.getContentPane().add(button);
		
		time = new JLabel("结束还剩  30s");
		time.setForeground(Color.RED);
		time.setFont(new Font("STZhongsong", Font.PLAIN, 20));
		time.setBounds(388, 450, 193, 35);
		frmSaveMoney.getContentPane().add(time);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\workspace\\Bank\\imge\\3 (2).jpg"));
		lblNewLabel.setBounds(1, 0, 590, 496);
		frmSaveMoney.getContentPane().add(lblNewLabel);

		frmSaveMoney.setTitle("SaveMoney");
		frmSaveMoney.setBounds(100, 100, 607, 534);
		frmSaveMoney.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	@Override
	public void run() {
		for(int i=30;i>=0;i--){
			try {
				time.setText("结束还剩  "+i+"s");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		frmSaveMoney.dispose();
	}
	
	
}
