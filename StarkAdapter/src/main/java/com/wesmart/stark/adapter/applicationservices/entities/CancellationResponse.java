package com.wesmart.stark.adapter.applicationservices.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wesmart.stark.adapter.applicationservices.utils.CancellationResponseDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * CancellationResponse entity class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@JsonDeserialize(using = CancellationResponseDeserializer.class)
public class CancellationResponse {

	/**
	 * The CancellationResponse correlationId
	 *
	 * @param - correlationId of the CancellationResponse
	 * @return - correlationId of the CancellationResponse
	 */
	private String correlationId;

	/**
	 * The CancellationResponse authorizationTraceabilityId
	 *
	 * @param - authorizationTraceabilityId of the CancellationResponse
	 * @return - authorizationTraceabilityId of the CancellationResponse
	 */
	private String authorizationTraceabilityId;

	/**
	 * The CancellationResponse cancellationCode
	 *
	 * @param - cancellationCode of the CancellationResponse
	 * @return - cancellationCode of the CancellationResponse
	 */
	@NonNull
	private String cancellationCode;

	/**
	 * The CancellationResponse responseCode
	 *
	 * @param - responseCode of the CancellationResponse
	 * @return - responseCode of the CancellationResponse
	 */
	@NonNull
	private String responseCode;

	/**
	 * The CancellationResponse errorMessage
	 *
	 * @param - errorMessage of the CancellationResponse
	 * @return - errorMessage of the CancellationResponse
	 */
	@NonNull
	private String errorMessage;

	/**
	 * The CancellationResponse message
	 *
	 * @param - message of the CancellationResponse
	 * @return - message of the CancellationResponse
	 */
	@NonNull
	private String message;

}
