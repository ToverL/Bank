package com.oracle.GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.xml.bind.SchemaOutputResolver;

import com.oracle.dao.RootDao;
import com.oracle.vo.Root;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class Select {

	JFrame  frmSelect;
	private JTextField textField;
	Root root;
	
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
					Select window = new Select();
					window.frmSelect.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Select() {
		initialize();
	}
	public Select(Root root) {
		this.root=root;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSelect = new JFrame();
		frmSelect.getContentPane().setBackground(Color.PINK);
		frmSelect.getContentPane().setLayout(null);
		
		JLabel lblSelect = new JLabel("\u4E2A\u4EBA\u64CD\u4F5C\u6D41\u6C34");
		lblSelect.setBackground(Color.WHITE);
		lblSelect.setForeground(new Color(0, 206, 209));
		lblSelect.setFont(new Font("����", Font.PLAIN, 47));
		lblSelect.setBounds(210, 20, 288, 55);
		frmSelect.getContentPane().add(lblSelect);
		
		JLabel label = new JLabel("\u8FD1");
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("����", Font.PLAIN, 28));
		label.setBounds(243, 103, 40, 41);
		frmSelect.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("����", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(276, 100, 128, 48);
		frmSelect.getContentPane().add(textField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(126, 256, 559, 470);
		frmSelect.getContentPane().add(scrollPane);
		
		final JTextArea textArea = new JTextArea();
		textArea.setForeground(new Color(0, 0, 0));
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setFont(new Font("����", Font.PLAIN, 17));
		
		JButton button_1 = new JButton("\u67E5\u8BE2\u5168\u90E8");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textArea.setText(RootDao.Accounts(root));
			}
		});
		button_1.setForeground(new Color(0, 0, 205));
		button_1.setFont(new Font("����", Font.PLAIN, 33));
		button_1.setBackground(new Color(255, 248, 220));
		button_1.setBounds(24, 165, 191, 40);
		frmSelect.getContentPane().add(button_1);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "��������Ҫ��ѯ������~", "������ʾ��", JOptionPane.ERROR_MESSAGE);
				}else{
					
					int time = Integer.parseInt(textField.getText()) ;
					String buf=RootDao.Accounts(root, time);
					if(buf.isEmpty()){
						JOptionPane.showMessageDialog(null, "��������ˮ������\n����Ĳ�ѯʱ��~", "��ʾ��", JOptionPane.ERROR_MESSAGE);
						textField.setText("");
					}else{
						textArea.setText(buf);
						textField.setText("");
					}	
				}
				
			}
		});
		button.setForeground(new Color(0, 0, 205));
		button.setFont(new Font("����", Font.PLAIN, 33));
		button.setBackground(new Color(255, 248, 220));
		button.setBounds(24, 104, 191, 40);
		frmSelect.getContentPane().add(button);
		
		JLabel label_1 = new JLabel("\u5929\u8BB0\u5F55");
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setFont(new Font("����", Font.PLAIN, 28));
		label_1.setBounds(414, 103, 84, 41);
		frmSelect.getContentPane().add(label_1);
		
		JLabel lblList = new JLabel("        \u67E5\u8BE2\u6E05\u5355");
		lblList.setBackground(new Color(255, 200, 0));
		lblList.setForeground(new Color(255, 228, 196));
		lblList.setFont(new Font("����", Font.PLAIN, 47));
		lblList.setBounds(126, 202, 559, 44);
		frmSelect.getContentPane().add(lblList);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\workspace\\Bank\\imge\\161227e6obex8z6kxzab7v.jpg"));
		lblNewLabel.setBounds(0, 0, 732, 737);
		frmSelect.getContentPane().add(lblNewLabel);
		
	
		
		
		frmSelect.setTitle("Select");
		frmSelect.setBounds(100, 100, 747, 775);
		frmSelect.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
