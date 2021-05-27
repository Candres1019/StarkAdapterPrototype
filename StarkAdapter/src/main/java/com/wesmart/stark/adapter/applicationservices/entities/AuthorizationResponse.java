package com.wesmart.stark.adapter.applicationservices.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wesmart.stark.adapter.applicationservices.utils.AuthorizationResponseDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * AuthorizationResponse entity class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@JsonDeserialize(using = AuthorizationResponseDeserializer.class)
public class AuthorizationResponse {

	/**
	 * The AuthorizationResponse correlationId
	 *
	 * @param - correlationId of the AuthorizationResponse
	 * @return - correlationId of the AuthorizationResponse
	 */
	private String correlationId;

	/**
	 * The AuthorizationResponse traceabilityId
	 *
	 * @param - traceabilityId of the AuthorizationResponse
	 * @return - traceabilityId of the AuthorizationResponse
	 */
	private String traceabilityId;

	/**
	 * The AuthorizationResponse authorizationCode
	 *
	 * @param - authorizationCode of the AuthorizationResponse
	 * @return - authorizationCode of the AuthorizationResponse
	 */
	@NonNull
	private String authorizationCode;

	/**
	 * The AuthorizationResponse responseCode
	 *
	 * @param - responseCode of the AuthorizationResponse
	 * @return - responseCode of the AuthorizationResponse
	 */
	@NonNull
	private String responseCode;

	/**
	 * The AuthorizationResponse errorMessage
	 *
	 * @param - errorMessage of the AuthorizationResponse
	 * @return - errorMessage of the AuthorizationResponse
	 */
	@NonNull
	private String errorMessage;

	/**
	 * The AuthorizationResponse message
	 *
	 * @param - message of the AuthorizationResponse
	 * @return - message of the AuthorizationResponse
	 */
	@NonNull
	private String message;

}
