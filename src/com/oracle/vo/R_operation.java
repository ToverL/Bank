package com.oracle.vo;

import java.sql.Date;
import java.sql.Timestamp;

@Table("r_operation")
public class R_operation {
	@Override
	public String toString() {
		return "R_operation [r_opno=" + r_opno + ", number=" + number + ", r_name=" + r_name + ", r_operation="
				+ r_operation + ", op_user=" + op_user + ", r_date=" + r_date + "]";
	}
	
	public String Show() {
		return "管理员账号：" + number + "\t管理员姓名：" + r_name + "\t操作："
				+ r_operation + "\t操作用户：" + op_user + "\t操作时间：" + r_date+"        \n";
	}
	@Identity(true)
	private int r_opno;
	private String number;
	private String r_name;
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	private String r_operation;
	public R_operation() {
		super();
	}
	private String op_user;
	private Timestamp r_date;
	public int getR_opno() {
		return r_opno;
	}
	public void setR_opno(int r_opno) {
		this.r_opno = r_opno;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getR_operation() {
		return r_operation;
	}
	public void setR_operation(String r_operation) {
		this.r_operation = r_operation;
	}
	public String getOp_user() {
		return op_user;
	}
	public void setOp_user(String op_user) {
		this.op_user = op_user;
	}
	public Timestamp getR_date() {
		return r_date;
	}
	public void setR_date(Timestamp r_date) {
		this.r_date = r_date;
	}
	public R_operation(String number,String r_name,String r_operation, String op_user) {
		super();
		this.number = number;
		this.r_name =r_name;
		this.r_operation = r_operation;
		this.op_user = op_user;
		Timestamp date = new Timestamp(System.currentTimeMillis());
		this.r_date=date;
	}
}
