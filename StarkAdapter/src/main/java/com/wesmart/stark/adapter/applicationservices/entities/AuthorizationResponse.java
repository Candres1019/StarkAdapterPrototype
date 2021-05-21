package com.wesmart.stark.adapter.applicationservices.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wesmart.stark.adapter.applicationservices.utils.AuthorizationResponseDeserializer;

@JsonDeserialize(using = AuthorizationResponseDeserializer.class)
public class AuthorizationResponse {

	private String correlationId;

	private String traceabilityId;

	private String authorizationCode;

	private String responseCode;

	private String errorMessage;

	private String message;

	public AuthorizationResponse() {

	}

	public AuthorizationResponse(final String authorizationCode, final String responseCode, final String errorMessage,
								 final String message) {

		this.authorizationCode = authorizationCode;
		this.responseCode = responseCode;
		this.errorMessage = errorMessage;
		this.message = message;
	}

	public String getCorrelationId() {

		return correlationId;
	}

	public void setCorrelationId(final String correlationId) {

		this.correlationId = correlationId;
	}

	public String getTraceabilityId() {

		return traceabilityId;
	}

	public void setTraceabilityId(final String traceabilityId) {

		this.traceabilityId = traceabilityId;
	}

	public String getAuthorizationCode() {

		return authorizationCode;
	}

	public void setAuthorizationCode(final String authorizationCode) {

		this.authorizationCode = authorizationCode;
	}

	public String getResponseCode() {

		return responseCode;
	}

	public void setResponseCode(final String responseCode) {

		this.responseCode = responseCode;
	}

	public String getErrorMessage() {

		return errorMessage;
	}

	public void setErrorMessage(final String errorMessage) {

		this.errorMessage = errorMessage;
	}

	public String getMessage() {

		return message;
	}

	public void setMessage(final String message) {

		this.message = message;
	}
}
