package com.oracle.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.oracle.dao.RootDao;
import com.oracle.vo.Root;
import com.oracle.vo.User;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class Delete {

	JFrame frmDelete;
	private JTextField textField;
	private Root root = null;
	private User user = null;
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
					Delete window = new Delete();
					window.frmDelete.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Delete() {
		initialize();
	}

	public Delete(Root root) {
		this.root = root;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDelete = new JFrame();
		frmDelete.setTitle("Delete");
		frmDelete.getContentPane().setBackground(Color.PINK);
		frmDelete.getContentPane().setLayout(null);

		final JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 24));
		textArea.setBounds(34, 125, 616, 314);
		frmDelete.getContentPane().add(textArea);

		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				user = new User();
				user.setUserno(textField.getText());
				flag = RootDao.FindUser(user);
				if (!flag) {
					JOptionPane.showMessageDialog(null, "查无此人！请检查后重新输入！", "错误提示", JOptionPane.ERROR_MESSAGE);
					textField.setText("");
					textArea.setText("");
				} else {
					textField.setText("");
					textArea.setText(user.Show());
				}
			}
		});
		button.setForeground(new Color(0, 0, 0));
		button.setFont(new Font("宋体", Font.PLAIN, 33));
		button.setBackground(new Color(250, 240, 230));
		button.setBounds(34, 75, 191, 40);
		frmDelete.getContentPane().add(button);

		JLabel label = new JLabel("\u8D26\u53F7\uFF1A");
		label.setForeground(new Color(0, 255, 255));
		label.setFont(new Font("宋体", Font.PLAIN, 28));
		label.setBounds(279, 72, 125, 41);
		frmDelete.getContentPane().add(label);

		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(367, 77, 205, 34);
		frmDelete.getContentPane().add(textField);

		JLabel lblDelete = new JLabel("Delete");
		lblDelete.setForeground(new Color(173, 255, 47));
		lblDelete.setFont(new Font("Times New Roman", Font.PLAIN, 47));
		lblDelete.setBounds(255, 10, 147, 55);
		frmDelete.getContentPane().add(lblDelete);

		JButton button_1 = new JButton("\u9500\u6237");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (flag) {
						if (RootDao.DeleteUser(root,user)) {
							textField.setText("");
							textArea.setText("");
							JOptionPane.showMessageDialog(null, "账户删除成功！", "删除成功", JOptionPane.INFORMATION_MESSAGE);
							flag=false;
							frmDelete.dispose();
						}
					} else {
						JOptionPane.showMessageDialog(null, "账户输入有误！\n请重新输入！", "错误提示", JOptionPane.ERROR_MESSAGE);
						textField.setText("");
						textArea.setText("");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setForeground(new Color(0, 0, 0));
		button_1.setFont(new Font("宋体", Font.PLAIN, 33));
		button_1.setBackground(new Color(250, 240, 230));
		button_1.setBounds(243, 465, 191, 40);
		frmDelete.getContentPane().add(button_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\workspace\\Bank\\imge\\206_164547_45847.jpg"));
		lblNewLabel.setBounds(0, 0, 699, 515);
		frmDelete.getContentPane().add(lblNewLabel);
		frmDelete.setBounds(100, 100, 715, 554);
		frmDelete.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

}
