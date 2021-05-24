package com.wesmart.stark.adapter.applicationservices.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wesmart.stark.adapter.applicationservices.utils.CaptureResponseDeserializer;
import com.wesmart.stark.adapter.applicationservices.utils.RefundMessageSerializer;
import com.wesmart.stark.adapter.applicationservices.utils.RefundResponseDeserializer;

@JsonDeserialize(using = RefundResponseDeserializer.class)
public class RefundResponse {

	private String correlationId;

	private String captureTraceabilityId;

	private String refundCode;

	private String responseCode;

	private String errorMessage;

	private String message;

	public RefundResponse() {

	}

	public RefundResponse(final String refundCode, final String responseCode, final String errorMessage, final String message) {

		this.refundCode = refundCode;
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

	public String getCaptureTraceabilityId() {

		return captureTraceabilityId;
	}

	public void setCaptureTraceabilityId(final String captureTraceabilityId) {

		this.captureTraceabilityId = captureTraceabilityId;
	}

	public String getRefundCode() {

		return refundCode;
	}

	public void setRefundCode(final String refundCode) {

		this.refundCode = refundCode;
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
