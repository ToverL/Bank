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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.oracle.dao.UserDao;
import com.oracle.vo.User;
import javax.swing.ImageIcon;

public class U_Select {

	JFrame  frmU_Select;
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
					U_Select window = new U_Select();
					window.frmU_Select.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public U_Select() {
		initialize();
	}
	public U_Select(User user) {
		this.user=user;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmU_Select = new JFrame();
		frmU_Select.getContentPane().setBackground(Color.PINK);
		frmU_Select.getContentPane().setLayout(null);
		
		JLabel lblSelect = new JLabel("\u4E2A\u4EBA\u64CD\u4F5C\u6D41\u6C34");
		lblSelect.setBackground(Color.WHITE);
		lblSelect.setForeground(new Color(0, 206, 209));
		lblSelect.setFont(new Font("宋体", Font.PLAIN, 47));
		lblSelect.setBounds(210, 20, 288, 55);
		frmU_Select.getContentPane().add(lblSelect);
		
		JLabel label = new JLabel("\u8FD1");
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("宋体", Font.PLAIN, 28));
		label.setBounds(243, 103, 40, 41);
		frmU_Select.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(276, 100, 128, 48);
		frmU_Select.getContentPane().add(textField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(126, 256, 559, 470);
		frmU_Select.getContentPane().add(scrollPane);
		
		final JTextArea textArea = new JTextArea();
		textArea.setForeground(new Color(0, 0, 0));
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setFont(new Font("隶书", Font.PLAIN, 17));
		
		JButton button_1 = new JButton("\u67E5\u8BE2\u5168\u90E8");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textArea.setText(UserDao.Accounts(user));
			}
		});
		button_1.setForeground(Color.BLUE);
		button_1.setFont(new Font("宋体", Font.PLAIN, 33));
		button_1.setBackground(new Color(255, 248, 220));
		button_1.setBounds(24, 165, 191, 40);
		frmU_Select.getContentPane().add(button_1);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "请输入您要查询的天数~", "错误提示！", JOptionPane.ERROR_MESSAGE);
				}else{
					
					int time = Integer.parseInt(textField.getText()) ;
					String buf=UserDao.Accounts(user, time);
					if(buf.isEmpty()){
						JOptionPane.showMessageDialog(null, "近期无流水操作！\n请更改查询时间~", "提示！", JOptionPane.ERROR_MESSAGE);
						textField.setText("");
					}else{
						textArea.setText(buf);
						textField.setText("");
					}	
				}
				
			}
		});
		button.setForeground(Color.BLUE);
		button.setFont(new Font("宋体", Font.PLAIN, 33));
		button.setBackground(new Color(255, 248, 220));
		button.setBounds(24, 104, 191, 40);
		frmU_Select.getContentPane().add(button);
		
		JLabel label_1 = new JLabel("\u5929\u8BB0\u5F55");
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setFont(new Font("宋体", Font.PLAIN, 28));
		label_1.setBounds(414, 103, 84, 41);
		frmU_Select.getContentPane().add(label_1);
		
		JLabel lblList = new JLabel("        \u67E5\u8BE2\u6E05\u5355");
		lblList.setBackground(new Color(255, 200, 0));
		lblList.setForeground(new Color(255, 239, 213));
		lblList.setFont(new Font("宋体", Font.PLAIN, 47));
		lblList.setBounds(126, 202, 559, 44);
		frmU_Select.getContentPane().add(lblList);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\workspace\\Bank\\imge\\161227e6obex8z6kxzab7v.jpg"));
		lblNewLabel.setBounds(0, 0, 731, 736);
		frmU_Select.getContentPane().add(lblNewLabel);
		
	
		
		
		frmU_Select.setTitle("Select");
		frmU_Select.setBounds(100, 100, 747, 775);
		frmU_Select.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
