package com.wesmart.stark.adapter.applicationservices.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wesmart.stark.adapter.applicationservices.utils.CaptureResponseDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * CaptureResponse entity class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@JsonDeserialize(using = CaptureResponseDeserializer.class)
public class CaptureResponse {

	/**
	 * The CaptureResponse correlationId
	 *
	 * @param - correlationId of the CaptureResponse
	 * @return - correlationId of the CaptureResponse
	 */
	private String correlationId;

	/**
	 * The CaptureResponse captureId
	 *
	 * @param - captureId of the CaptureResponse
	 * @return - captureId of the CaptureResponse
	 */
	private String captureId;

	/**
	 * The CaptureResponse authorizationTraceabilityId
	 *
	 * @param - authorizationTraceabilityId of the CaptureResponse
	 * @return - authorizationTraceabilityId of the CaptureResponse
	 */
	private String authorizationTraceabilityId;

	/**
	 * The CaptureResponse captureCode
	 *
	 * @param - captureCode of the CaptureResponse
	 * @return - captureCode of the CaptureResponse
	 */
	@NonNull
	private String captureCode;

	/**
	 * The CaptureResponse responseCode
	 *
	 * @param - responseCode of the CaptureResponse
	 * @return - responseCode of the CaptureResponse
	 */
	@NonNull
	private String responseCode;

	/**
	 * The CaptureResponse errorMessage
	 *
	 * @param - errorMessage of the CaptureResponse
	 * @return - errorMessage of the CaptureResponse
	 */
	@NonNull
	private String errorMessage;

	/**
	 * The CaptureResponse message
	 *
	 * @param - message of the CaptureResponse
	 * @return - message of the CaptureResponse
	 */
	@NonNull
	private String message;

}
