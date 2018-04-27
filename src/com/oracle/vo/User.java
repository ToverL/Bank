package com.oracle.vo;

@Table("user")
public class User {
	@Identity(false)
	private String userno;
	private String pwd;
	private String name;
	private double sum;
	public String getUserno() {
		return userno;
	}
	public User(String userno, String pwd) {
		super();
		this.userno = userno;
		this.pwd = pwd;
	}
	public User(String userno, String pwd, String name) {
		super();
		this.userno = userno;
		this.pwd = pwd;
		this.name = name;
	}
	public User() {
		super();
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	public String getPwd() {
		return pwd;
	}
	public User(String userno, String pwd, String name, double sum) {
		super();
		this.userno = userno;
		this.pwd = pwd;
		this.name = name;
		this.sum = sum;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	

	@Override
	public String toString() {
		return "user [userno=" + userno + ", pwd=" + pwd + ", name=" + name + ", sum=" + sum + "]";
	}
	/**
	 * 用于单个用户查询
	 * @return
	 */
	public String Show(){
		return "账号：" + userno + "\n密码：" + pwd + "\n姓名：" + name + "\n余额：" + sum+"￥\n";	
	}
	/**
	 * 用于管理员查询
	 * @return
	 */
	public String Show_1(){
		return "账号：" + userno + "\t姓名：" + name + "\t余额：" + sum+"￥\n";	
	}
	
}
