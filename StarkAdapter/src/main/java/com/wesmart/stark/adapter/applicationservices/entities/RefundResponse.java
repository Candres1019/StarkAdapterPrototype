package com.wesmart.stark.adapter.applicationservices.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wesmart.stark.adapter.applicationservices.utils.RefundResponseDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * RefundResponse entity class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@JsonDeserialize(using = RefundResponseDeserializer.class)
public class RefundResponse {

	/**
	 * The RefundResponse correlationId
	 *
	 * @param - correlationId of the RefundResponse
	 * @return - correlationId of the RefundResponse
	 */
	private String correlationId;

	/**
	 * The RefundResponse captureTraceabilityId
	 *
	 * @param - captureTraceabilityId of the RefundResponse
	 * @return - captureTraceabilityId of the RefundResponse
	 */
	private String captureTraceabilityId;

	/**
	 * The RefundResponse refundCode
	 *
	 * @param - refundCode of the RefundResponse
	 * @return - refundCode of the RefundResponse
	 */
	@NonNull
	private String refundCode;

	/**
	 * The RefundResponse responseCode
	 *
	 * @param - responseCode of the RefundResponse
	 * @return - responseCode of the RefundResponse
	 */
	@NonNull
	private String responseCode;

	/**
	 * The RefundResponse errorMessage
	 *
	 * @param - errorMessage of the RefundResponse
	 * @return - errorMessage of the RefundResponse
	 */
	@NonNull
	private String errorMessage;

	/**
	 * The RefundResponse message
	 *
	 * @param - message of the RefundResponse
	 * @return - message of the RefundResponse
	 */
	@NonNull
	private String message;

}
