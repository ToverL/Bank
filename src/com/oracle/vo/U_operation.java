package com.oracle.vo;

import java.sql.Timestamp;

@Table("u_operation")
public class U_operation {
	@Identity(true)
	private int u_opno;
	private String userno;//�û���
	private String u_operation;//����
	private String content;//����
	private String dealer;//������
	private Timestamp u_date;//����ʱ��
	public int getU_opno() {
		return u_opno;
	}
	public U_operation() {
		super();
	}
	public void setU_opno(int u_opno) {
		this.u_opno = u_opno;
	}
	public String getUserno() {
		return userno;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	public String getU_operation() {
		return u_operation;
	}
	public void setU_operation(String u_operation) {
		this.u_operation = u_operation;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDealer() {
		return dealer;
	}
	public void setDealer(String dealer) {
		this.dealer = dealer;
	}
	public Timestamp getU_date() {
		return u_date;
	}
	public void setU_date(Timestamp u_date) {
		this.u_date = u_date;
	}
	public U_operation(String userno, String u_operation, String content, String dealer) {
		super();
		this.userno = userno;
		this.u_operation = u_operation;
		this.content = content;
		this.dealer = dealer;
		Timestamp date=new Timestamp(System.currentTimeMillis());
		this.u_date=date;
	}
	@Override
	public String toString() {
		return "U_operation [u_opno=" + u_opno + ", userno=" + userno + ", u_operation=" + u_operation + ", content="
				+ content + ", dealer=" + dealer + ", u_date=" + u_date + "]";
	}
	public String Show1() {
		return "�˺ţ�" + userno + "\t������" + u_operation + "\t�������ݣ�"
				+ content + "\t�����ˣ�" + dealer + "\t����ʱ�䣺" + u_date + "       \n";
	}
	
}
