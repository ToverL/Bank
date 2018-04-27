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

public class Sum {

	JFrame frmSum;
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
					Sum window = new Sum();
					window.frmSum.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sum() {
		initialize();
	}

	public Sum(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmSum = new JFrame();
		frmSum.getContentPane().setBackground(Color.PINK);
		frmSum.getContentPane().setLayout(null);

		JLabel lblSelect = new JLabel("\u4F59\u989D");
		lblSelect.setBackground(Color.WHITE);
		lblSelect.setForeground(new Color(255, 0, 255));
		lblSelect.setFont(new Font("宋体", Font.PLAIN, 47));
		lblSelect.setBounds(196, 34, 109, 55);
		frmSum.getContentPane().add(lblSelect);

		JLabel label = new JLabel("\u5F53\u524D\u4F59\u989D\uFF1A");
		label.setForeground(new Color(0, 191, 255));
		label.setFont(new Font("宋体", Font.PLAIN, 31));
		label.setBounds(24, 171, 161, 55);
		frmSum.getContentPane().add(label);

		JButton button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSum.dispose();
			}
		});
		JRootPane rootpane=frmSum.getRootPane();	
		rootpane.setDefaultButton(button);
		button.setForeground(new Color(0, 0, 0));
		button.setFont(new Font("宋体", Font.PLAIN, 33));
		button.setBackground(new Color(255, 248, 220));
		button.setBounds(157, 315, 191, 40);
		frmSum.getContentPane().add(button);

		JLabel label_2 = new JLabel(user.getSum() + "¥");
		label_2.setForeground(new Color(0, 0, 0));
		label_2.setFont(new Font("宋体", Font.PLAIN, 31));
		label_2.setBounds(173, 171, 211, 55);
		frmSum.getContentPane().add(label_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\workspace\\Bank\\imge\\LL (17).jpg"));
		lblNewLabel.setBounds(0, 0, 496, 448);
		frmSum.getContentPane().add(lblNewLabel);

		frmSum.setTitle("Sum");
		frmSum.setBounds(100, 100, 512, 487);
		frmSum.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

	}

	}
