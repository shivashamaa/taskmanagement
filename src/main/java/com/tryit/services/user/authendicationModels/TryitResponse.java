package com.tryit.services.user.authendicationModels;

public class TryitResponse {
	private final String tryitToken;

	public TryitResponse(String tryitToken) {
		this.tryitToken = tryitToken;
	}

	public String getTryitToken() {
		return tryitToken;
	}

}
