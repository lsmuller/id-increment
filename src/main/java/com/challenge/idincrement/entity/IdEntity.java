package com.challenge.idincrement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.tomcat.util.codec.binary.Base64;

@Entity
public class IdEntity {

	@Id
	private String apiKey;
	private String email;
	private String password;
	private String tableName;
	private Integer currentId;

	public static class Builder {
		private String email;
		private String password;
		private String tableName;
		private Integer currentId;

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder withCurrentId(Integer currentId) {
			this.currentId = currentId;
			return this;
		}

		public Builder withTableName(String tableName) {
			this.tableName = tableName;
			return this;
		}


		public IdEntity build(){
			IdEntity idEntity = new IdEntity();
			idEntity.email = this.email;
			idEntity.password = this.password;
			idEntity.currentId = this.currentId != null ? this.currentId : 0;
			idEntity.tableName = this.tableName;
			idEntity.apiKey  = Base64.encodeBase64String((this.email + ":" + password + ":" + tableName).getBytes());
			return idEntity;
		}

	}

	private IdEntity() {
	}

	public String getApiKey() {
		return apiKey;
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

	public Integer getCurrentId() {
		return currentId;
	}

	public void setCurrentId(Integer currentId) {
		this.currentId = currentId;
	}

}
