package com.wesmart.stark.adapter.applicationservices.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wesmart.stark.adapter.applicationservices.utils.CaptureResponseDeserializer;

@JsonDeserialize(using = CaptureResponseDeserializer.class)
public class CaptureResponse {

	private String correlationId;

	private String captureId;

	private String authorizationTraceabilityId;

	private String captureCode;

	private String responseCode;

	private String errorMessage;

	private String message;

	public CaptureResponse() {

	}

	public CaptureResponse(final String captureCode, final String responseCode, final String errorMessage, final String message) {

		this.captureCode = captureCode;
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

	public String getCaptureId() {

		return captureId;
	}

	public void setCaptureId(final String captureId) {

		this.captureId = captureId;
	}

	public String getAuthorizationTraceabilityId() {

		return authorizationTraceabilityId;
	}

	public void setAuthorizationTraceabilityId(final String authorizationTraceabilityId) {

		this.authorizationTraceabilityId = authorizationTraceabilityId;
	}

	public String getCaptureCode() {

		return captureCode;
	}

	public void setCaptureCode(final String captureCode) {

		this.captureCode = captureCode;
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
