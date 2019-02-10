package com.challenge.idincrement.model;

public class ApiKey {
	private String apiKey;

	//Needed for deserialization
	public ApiKey() {
	}

	public ApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public String toString() {
		return "ApiKey{" + "apiKey='" + apiKey + '\'' + '}';
	}
}
