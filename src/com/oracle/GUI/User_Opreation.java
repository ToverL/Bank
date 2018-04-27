package com.oracle.GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.oracle.vo.Root;
import com.oracle.vo.User;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class User_Opreation extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JLabel date;
	static JLabel time;
	private User user = null;

	public Root getRoot() {
		return getRoot();
	}

	public void setRoot(User user) {
		this.user = user;
	}

	JFrame frmUser_Opreation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_Opreation window = new User_Opreation();
					window.frmUser_Opreation.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the application.
	 */
	public User_Opreation(User user) {
		this.user = user;
		initialize();
	}

	public User_Opreation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUser_Opreation = new JFrame();
		frmUser_Opreation.getContentPane().setBackground(Color.PINK);
		frmUser_Opreation.getContentPane().setLayout(null);
		JButton button = new JButton("\u5B58\u6B3E");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveMoney window = new SaveMoney(user);
				window.frmSaveMoney.setVisible(true);
				new Thread(window).start();
			}
		});
		button.setForeground(new Color(0, 0, 0));
		button.setFont(new Font("宋体", Font.PLAIN, 40));
		button.setBackground(new Color(255, 255, 224));
		button.setBounds(171, 210, 305, 47);
		frmUser_Opreation.getContentPane().add(button);

		JButton button_1 = new JButton("\u53D6\u6B3E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetMoney window = new GetMoney(user);
				window.frmGetMoney.setVisible(true);
				new Thread(window).start();;
			}
		});
		button_1.setForeground(new Color(0, 0, 0));
		button_1.setFont(new Font("宋体", Font.PLAIN, 40));
		button_1.setBackground(new Color(255, 255, 224));
		button_1.setBounds(171, 267, 305, 41);
		frmUser_Opreation.getContentPane().add(button_1);

		JButton button_2 = new JButton("\u4FEE\u6539\u5BC6\u7801");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User_SetPwd window = new User_SetPwd(user);
				window.frmUser_SetPwd.setVisible(true);
			}
		});
		button_2.setForeground(new Color(0, 0, 0));
		button_2.setFont(new Font("宋体", Font.PLAIN, 40));
		button_2.setBackground(new Color(255, 255, 224));
		button_2.setBounds(171, 369, 305, 47);
		frmUser_Opreation.getContentPane().add(button_2);

		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u767B\u5F55\uFF1A" + user.getName());
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 35));
		lblNewLabel.setForeground(new Color(51, 255, 255));
		lblNewLabel.setBounds(171, 39, 323, 52);
		frmUser_Opreation.getContentPane().add(lblNewLabel);

		JButton button_3 = new JButton("\u8F6C\u8D26");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SendMoney window = new SendMoney(user);
				window.frmSendMoney.setVisible(true);
				new Thread(window).start();
			}
		});
		button_3.setForeground(new Color(0, 0, 0));
		button_3.setFont(new Font("宋体", Font.PLAIN, 40));
		button_3.setBackground(new Color(255, 255, 224));
		button_3.setBounds(171, 318, 305, 41);
		frmUser_Opreation.getContentPane().add(button_3);

		JButton button_4 = new JButton("\u9AD8\u7EA7\u67E5\u8BE2");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				U_Select window = new U_Select(user);
				window.frmU_Select.setVisible(true);
			}
		});
		button_4.setForeground(new Color(0, 0, 0));
		button_4.setFont(new Font("宋体", Font.PLAIN, 40));
		button_4.setBackground(new Color(255, 255, 224));
		button_4.setBounds(171, 426, 305, 47);
		frmUser_Opreation.getContentPane().add(button_4);

		JButton button_5 = new JButton("\u5B89\u5168\u9000\u51FA");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmUser_Opreation.dispose();
				Login window = new Login();
				window.frmLogin.setVisible(true);
			}
		});
		button_5.setForeground(new Color(0, 0, 0));
		button_5.setFont(new Font("宋体", Font.PLAIN, 40));
		button_5.setBackground(new Color(255, 255, 224));
		button_5.setBounds(171, 483, 305, 47);
		frmUser_Opreation.getContentPane().add(button_5);

		JButton button_6 = new JButton("\u67E5\u8BE2\u4F59\u989D");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sum window = new Sum(user);
				window.frmSum.setVisible(true);
			}
		});
		button_6.setForeground(new Color(0, 0, 0));
		button_6.setFont(new Font("宋体", Font.PLAIN, 40));
		button_6.setBackground(new Color(255, 255, 224));
		button_6.setBounds(171, 159, 305, 41);
		frmUser_Opreation.getContentPane().add(button_6);

		date = new JLabel();
		date.setForeground(new Color(250, 235, 215));
		date.setBounds(482, 617, 180, 22);
		frmUser_Opreation.getContentPane().add(date);
		date.setFont(new Font("微软雅黑", Font.PLAIN, 13));

		time = new JLabel();
		time.setForeground(new Color(250, 235, 215));
		time.setBounds(520, 572, 106, 47);
		frmUser_Opreation.getContentPane().add(time);
		time.setFont(new Font("Arial", Font.PLAIN, 25));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("E:\\workspace\\Bank\\imge\\1355220204459.jpg"));
		lblNewLabel_1.setBounds(0, 0, 672, 649);
		frmUser_Opreation.getContentPane().add(lblNewLabel_1);

		frmUser_Opreation.setTitle("User");
		frmUser_Opreation.setBackground(Color.WHITE);
		frmUser_Opreation.setBounds(100, 100, 688, 688);
		frmUser_Opreation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void run() {
		while (true) {
			try {
				date.setText(new SimpleDateFormat("yyyy 年 MM 月 dd 日   EEEE").format(new Date()));
				time.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}
}
