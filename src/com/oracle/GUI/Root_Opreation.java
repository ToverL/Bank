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
import javax.swing.ImageIcon;

public class Root_Opreation extends JFrame implements Runnable {

	static JLabel date;
	static JLabel time;
	Root root=null;
	public Root getRoot() {
		return root;
	}

	public void setRoot(Root root) {
		this.root = root;
	}


	JFrame frmRoot_Opreation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Root_Opreation window = new Root_Opreation();
					window.frmRoot_Opreation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Root_Opreation(Root root) {
		this.root=root;
		initialize();
	}
	public Root_Opreation() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRoot_Opreation = new JFrame();
		frmRoot_Opreation.getContentPane().setBackground(Color.PINK);
		frmRoot_Opreation.getContentPane().setLayout(null);
		
		JButton button = new JButton("\u5F00\u6237");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enroll window = new Enroll(root);
				window.frmEnroll.setVisible(true);
			}
		});
		button.setForeground(new Color(0, 0, 0));
		button.setFont(new Font("宋体", Font.PLAIN, 40));
		button.setBackground(new Color(255, 248, 220));
		button.setBounds(157, 159, 305, 44);
		frmRoot_Opreation.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u9500\u6237");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete window = new Delete(root);
				window. frmDelete.setVisible(true);
			}
		});
		button_1.setForeground(new Color(0, 0, 0));
		button_1.setFont(new Font("宋体", Font.PLAIN, 40));
		button_1.setBackground(new Color(255, 248, 220));
		button_1.setBounds(157, 213, 305, 44);
		frmRoot_Opreation.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u4E2A\u4EBA\u64CD\u4F5C\u6D41\u6C34");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Select window  = new Select(root);
				window.frmSelect.setVisible(true);
			}
		});
		button_2.setForeground(new Color(0, 0, 0));
		button_2.setFont(new Font("宋体", Font.PLAIN, 40));
		button_2.setBackground(new Color(255, 248, 220));
		button_2.setBounds(157, 334, 305, 44);
		frmRoot_Opreation.getContentPane().add(button_2);
		
		JLabel lblNewLabel = new JLabel("\u7BA1\u7406\u5458\uFF1A");
		lblNewLabel.setText(lblNewLabel.getText()+root.getName());
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 35));
		lblNewLabel.setForeground(new Color(102, 205, 170));
		lblNewLabel.setBounds(171, 28, 301, 52);
		frmRoot_Opreation.getContentPane().add(lblNewLabel);
		
		JButton button_3 = new JButton("\u7528\u6237\u4FE1\u606F\u67E5\u8BE2");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				R_Sslect_U window = new R_Sslect_U(root);
				window.frmR_Select_U.setVisible(true);
			}
		});
		button_3.setForeground(new Color(0, 0, 0));
		button_3.setFont(new Font("宋体", Font.PLAIN, 40));
		button_3.setBackground(new Color(255, 248, 220));
		button_3.setBounds(157, 267, 305, 48);
		frmRoot_Opreation.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("\u5B89\u5168\u9000\u51FA");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRoot_Opreation.dispose();
				Root_Login window = new Root_Login();
				window.frmRoot_Login.setVisible(true);
			}
		});
		button_4.setForeground(new Color(0, 0, 0));
		button_4.setFont(new Font("宋体", Font.PLAIN, 40));
		button_4.setBackground(new Color(255, 248, 220));
		button_4.setBounds(157, 459, 305, 44);
		frmRoot_Opreation.getContentPane().add(button_4);
		
		
		date = new JLabel();
		date.setBounds(433, 596, 180, 22);
		frmRoot_Opreation.getContentPane().add(date);
		date.setFont(new Font("微软雅黑", Font.PLAIN, 13));

		time = new JLabel();
		time.setBounds(475, 552, 106, 47);
		frmRoot_Opreation.getContentPane().add(time);
		time.setFont(new Font("Arial", Font.PLAIN, 25));
		
		JButton button_5 = new JButton("\u4FEE\u6539\u5BC6\u7801");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Root_SetPwd window = new Root_SetPwd(root);
				window.frmRoot_SetPwd.setVisible(true);
			}
		});
		button_5.setForeground(new Color(0, 0, 0));
		button_5.setFont(new Font("SimSun", Font.PLAIN, 40));
		button_5.setBackground(new Color(255, 248, 220));
		button_5.setBounds(157, 394, 305, 44);
		frmRoot_Opreation.getContentPane().add(button_5);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("E:\\workspace\\Bank\\imge\\LL (1).png"));
		lblNewLabel_1.setBounds(0, 0, 650, 628);
		frmRoot_Opreation.getContentPane().add(lblNewLabel_1);
		
		
		frmRoot_Opreation.setTitle("Admin");
		frmRoot_Opreation.setBackground(Color.WHITE);
		frmRoot_Opreation.setBounds(100, 100, 666, 667);
		frmRoot_Opreation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * 时钟线程
	 */
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
