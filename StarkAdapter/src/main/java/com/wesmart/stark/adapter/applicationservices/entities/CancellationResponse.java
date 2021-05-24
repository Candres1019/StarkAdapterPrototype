package com.wesmart.stark.adapter.applicationservices.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wesmart.stark.adapter.applicationservices.utils.CancellationResponseDeserializer;

@JsonDeserialize(using = CancellationResponseDeserializer.class)
public class CancellationResponse {

	private String correlationId;

	private String authorizationTraceabilityId;

	private String cancellationCode;

	private String responseCode;

	private String errorMessage;

	private String message;

	public CancellationResponse() {

	}

	public CancellationResponse(final String cancellationCode, final String responseCode, final String errorMessage, final String message) {

		this.cancellationCode = cancellationCode;
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

	public String getAuthorizationTraceabilityId() {

		return authorizationTraceabilityId;
	}

	public void setAuthorizationTraceabilityId(final String authorizationTraceabilityId) {

		this.authorizationTraceabilityId = authorizationTraceabilityId;
	}

	public String getCancellationCode() {

		return cancellationCode;
	}

	public void setCancellationCode(final String cancellationCode) {

		this.cancellationCode = cancellationCode;
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
