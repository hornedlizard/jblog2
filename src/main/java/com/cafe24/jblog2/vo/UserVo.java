package com.cafe24.jblog2.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {
	private long no;
	@NotEmpty
	@Length(min=2, max=7)
	private String id;
	@NotEmpty
	@Pattern(regexp="^[0-9a-zA-Z]{4,12}$")
	private String password;
	@NotEmpty
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
