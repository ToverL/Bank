package com.oracle.vo;
@Table("root")
public class Root {
@Identity()
private	String number;
private	String pwd;
private    String name;
	public Root(String number, String pwd, String name) {
	super();
	this.number = number;
	this.pwd = pwd;
	this.name = name;
}
	public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
	public Root() {
	super();
}
	public Root(String name) {
		super();
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Root(String number, String pwd) {
		super();
		this.number = number;
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "Root [number=" + number + ", pwd=" + pwd + ", name=" + name + "]";
	}
	
	
}
