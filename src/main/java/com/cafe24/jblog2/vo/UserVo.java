package com.cafe24.jblog2.vo;

public class UserVo {
	private long no;
	private String id;
	private String password;
	private String name;
	private String regdate;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", id=" + id + ", password=" + password 
				+ ", name=" + name + ", regdate=" + regdate + "]";
	}
	
}
