package com.challenge.idincrement.model;

public class User {

	private String email;
	private String password;
	private String tableName;

	public User() {
	}

	public User(String email, String password, String tableName) {
		this.email = email;
		this.password = password;
		this.tableName = tableName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
