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

import com.oracle.dao.RootDao;
import com.oracle.dao.UserDao;
import com.oracle.vo.Root;
import com.oracle.vo.User;
import javax.swing.ImageIcon;

public class R_Sslect_U {

	JFrame  frmR_Select_U;
	private JTextField textField;
	Root root;
	private boolean flag = false;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
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
					R_Sslect_U window = new R_Sslect_U();
					window.frmR_Select_U.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public R_Sslect_U() {
		initialize();
	}
	public R_Sslect_U(Root root) {
		this.root=root;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmR_Select_U = new JFrame();
		frmR_Select_U.getContentPane().setBackground(Color.PINK);
		frmR_Select_U.getContentPane().setLayout(null);
		
		JLabel lblSelect = new JLabel("\u7528\u6237\u4FE1\u606F\u67E5\u8BE2");
		lblSelect.setBackground(Color.WHITE);
		lblSelect.setForeground(new Color(0, 206, 209));
		lblSelect.setFont(new Font("宋体", Font.PLAIN, 47));
		lblSelect.setBounds(210, 20, 288, 55);
		frmR_Select_U.getContentPane().add(lblSelect);
		
		JLabel label = new JLabel("\u8D26\u53F7\uFF1A");
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("宋体", Font.PLAIN, 28));
		label.setBounds(251, 105, 86, 41);
		frmR_Select_U.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 26));
		textField.setColumns(10);
		textField.setBounds(332, 104, 215, 48);
		frmR_Select_U.getContentPane().add(textField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(106, 256, 597, 470);
		frmR_Select_U.getContentPane().add(scrollPane);
		
		final JTextArea textArea = new JTextArea();
		textArea.setForeground(new Color(0, 0, 0));
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setFont(new Font("隶书", Font.PLAIN, 17));
		
		JButton button_1 = new JButton("\u6240\u6709\u7528\u6237\u4FE1\u606F");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textArea.setText(RootDao.User_Information());		
			}
		});
		button_1.setForeground(Color.BLUE);
		button_1.setFont(new Font("宋体", Font.PLAIN, 26));
		button_1.setBackground(new Color(255, 248, 220));
		button_1.setBounds(24, 165, 191, 40);
		frmR_Select_U.getContentPane().add(button_1);
		
		JButton button = new JButton("\u7528\u6237\u8BE6\u7EC6\u8D44\u6599");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setUserno(textField.getText());
				 flag = RootDao.FindUser(user);
				 
				if (!flag) {
					JOptionPane.showMessageDialog(null, "查无此人！请检查后重新输入！", "错误提示", JOptionPane.ERROR_MESSAGE);
					textField.setText("");
					textArea.setText("");
				} else {
					textField.setText("");
					StringBuilder str= new StringBuilder("\n用户操作流水\n");
					str.append(UserDao.Accounts(user));
					textArea.setText(user.Show()+str);
				}
				
			}
		});
		button.setForeground(Color.BLUE);
		button.setFont(new Font("宋体", Font.PLAIN, 26));
		button.setBackground(new Color(255, 248, 220));
		button.setBounds(24, 104, 191, 40);
		frmR_Select_U.getContentPane().add(button);
		
		JLabel lblList = new JLabel("        \u67E5\u8BE2\u6E05\u5355");
		lblList.setBackground(new Color(255, 200, 0));
		lblList.setForeground(new Color(255, 99, 71));
		lblList.setFont(new Font("宋体", Font.PLAIN, 47));
		lblList.setBounds(126, 202, 559, 44);
		frmR_Select_U.getContentPane().add(lblList);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\workspace\\Bank\\imge\\161232b8vbsjjthbh0mty0.jpg"));
		lblNewLabel.setBounds(0, 0, 731, 736);
		frmR_Select_U.getContentPane().add(lblNewLabel);
	
		
		frmR_Select_U.setTitle("SelectUser");
		frmR_Select_U.setBounds(100, 100, 747, 775);
		frmR_Select_U.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
	}
}
