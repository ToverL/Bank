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

public class GetMoney  extends JFrame implements Runnable {

	JFrame  frmGetMoney;
	private JTextField textField;
	User user;
	private JLabel time;
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
					GetMoney window = new GetMoney();
					window.frmGetMoney.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GetMoney() {
		initialize();
	}
	public GetMoney(User user) {
		this.user=user;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGetMoney = new JFrame();
		frmGetMoney.getContentPane().setBackground(new Color(0, 0, 0));
		frmGetMoney.getContentPane().setLayout(null);
		
		JLabel lblSelect = new JLabel("取款");
		lblSelect.setBackground(Color.WHITE);
		lblSelect.setForeground(new Color(127, 255, 212));
		lblSelect.setFont(new Font("宋体", Font.PLAIN, 47));
		lblSelect.setBounds(297, 26, 109, 55);
		frmGetMoney.getContentPane().add(lblSelect);
		
		JLabel label = new JLabel("取款金额：");
		label.setForeground(new Color(0, 255, 255));
		label.setFont(new Font("宋体", Font.PLAIN, 28));
		label.setBounds(162, 528, 147, 48);
		frmGetMoney.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(305, 528, 187, 48);
		frmGetMoney.getContentPane().add(textField);
		
		JLabel label_1 = new JLabel("\u00A5");
		label_1.setFont(new Font("宋体", Font.PLAIN, 28));
		label_1.setBounds(502, 531, 33, 41);
		frmGetMoney.getContentPane().add(label_1);
		
		JButton button = new JButton("确认取款");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double money = -1;
				if(!textField.getText().isEmpty())
					 money= Double.valueOf(textField.getText());
				if(money<0){
					JOptionPane.showMessageDialog(null, "请正确输入金额！", "错误提示！", JOptionPane.ERROR_MESSAGE);
					textField.setText("");
				}else{
					if(UserDao.getMoney(user, money)){
						frmGetMoney.dispose();
						JOptionPane.showMessageDialog(null, "取款成功！\n成功取出"+money+"¥", "取款成功", JOptionPane.INFORMATION_MESSAGE);	
					}else{
						JOptionPane.showMessageDialog(null, "余额不足！", "错误提示！", JOptionPane.ERROR_MESSAGE);
						textField.setText("");
					}
				}
			}
		});
		JRootPane rootpane=frmGetMoney.getRootPane();	
		rootpane.setDefaultButton(button);
		button.setForeground(new Color(0, 0, 0));
		button.setFont(new Font("宋体", Font.PLAIN, 33));
		button.setBackground(new Color(250, 240, 230));
		button.setBounds(260, 597, 173, 40);
		frmGetMoney.getContentPane().add(button);
		
		JButton button_1 = new JButton("100¥");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("100");
			}
		});
		button_1.setForeground(new Color(0, 0, 0));
		button_1.setFont(new Font("宋体", Font.PLAIN, 33));
		button_1.setBackground(new Color(250, 240, 230));
		button_1.setBounds(70, 222, 152, 40);
		frmGetMoney.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("5000¥");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("5000");
			}
		});
		button_2.setForeground(new Color(0, 0, 0));
		button_2.setFont(new Font("宋体", Font.PLAIN, 33));
		button_2.setBackground(new Color(250, 240, 230));
		button_2.setBounds(460, 297, 153, 40);
		frmGetMoney.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("200¥");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("200");
			}
		});
		button_3.setForeground(new Color(0, 0, 0));
		button_3.setFont(new Font("宋体", Font.PLAIN, 33));
		button_3.setBackground(new Color(250, 240, 230));
		button_3.setBounds(70, 297, 152, 40);
		frmGetMoney.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("10000¥");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("10000");
			}
		});
		button_4.setForeground(new Color(0, 0, 0));
		button_4.setFont(new Font("宋体", Font.PLAIN, 33));
		button_4.setBackground(new Color(250, 240, 230));
		button_4.setBounds(460, 372, 153, 40);
		frmGetMoney.getContentPane().add(button_4);
		
		JButton button_5 = new JButton("500¥");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("500");
			}
		});
		button_5.setForeground(new Color(0, 0, 0));
		button_5.setFont(new Font("宋体", Font.PLAIN, 33));
		button_5.setBackground(new Color(250, 240, 230));
		button_5.setBounds(70, 368, 152, 40);
		frmGetMoney.getContentPane().add(button_5);
		
		JButton button_6 = new JButton("20000¥");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("20000");
			}
		});
		button_6.setForeground(new Color(0, 0, 0));
		button_6.setFont(new Font("宋体", Font.PLAIN, 33));
		button_6.setBackground(new Color(250, 240, 230));
		button_6.setBounds(460, 443, 153, 40);
		frmGetMoney.getContentPane().add(button_6);
		
		JButton button_7 = new JButton("1000¥");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("1000");
			}
		});
		button_7.setForeground(new Color(0, 0, 0));
		button_7.setFont(new Font("宋体", Font.PLAIN, 33));
		button_7.setBackground(new Color(250, 240, 230));
		button_7.setBounds(70, 442, 152, 40);
		frmGetMoney.getContentPane().add(button_7);
		
		JButton button_9 = new JButton("2000¥");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("2000");
			}
		});
		button_9.setForeground(new Color(0, 0, 0));
		button_9.setFont(new Font("宋体", Font.PLAIN, 33));
		button_9.setBackground(new Color(250, 240, 230));
		button_9.setBounds(461, 222, 152, 40);
		frmGetMoney.getContentPane().add(button_9);
		
		JLabel label_2 = new JLabel("当前余额：");
		label_2.setForeground(new Color(255, 255, 255));
		label_2.setFont(new Font("宋体", Font.PLAIN, 31));
		label_2.setBounds(132, 107, 161, 55);
		frmGetMoney.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel(user.getSum()+"¥");
		label_3.setForeground(Color.YELLOW);
		label_3.setFont(new Font("宋体", Font.PLAIN, 31));
		label_3.setBounds(281, 107, 211, 55);
		frmGetMoney.getContentPane().add(label_3);
		
		time = new JLabel("结束还剩  30s");
		time.setForeground(Color.RED);
		time.setFont(new Font("STZhongsong", Font.PLAIN, 20));
		time.setBounds(480, 603, 193, 35);
		frmGetMoney.getContentPane().add(time);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setIcon(new ImageIcon("E:\\workspace\\Bank\\imge\\QQ图片20141118203057.jpg"));
		lblNewLabel.setBounds(0, -2, 700, 650);
		frmGetMoney.getContentPane().add(lblNewLabel);
		
	
	
		frmGetMoney.setTitle("GetMoney");
		frmGetMoney.setBounds(100, 100, 715, 686);
		frmGetMoney.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	@Override
	public void run() {
		for(int i=30;i>=0;i--){
			try {
				time.setText("结束还剩  "+i+"s");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		frmGetMoney.dispose();
	}
}
