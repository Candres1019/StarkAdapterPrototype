package com.wesmart.stark.adapter.applicationservices.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wesmart.entities.CreditCard;
import com.wesmart.entities.Transaction;
import com.wesmart.stark.adapter.applicationservices.utils.CaptureMessageSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CaptureMessage entity class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = CaptureMessageSerializer.class)
public class CaptureMessage {

	/**
	 * The CaptureMessage correlationId
	 *
	 * @param - correlationId of the CaptureMessage
	 * @return - correlationId of the CaptureMessage
	 */
	private String correlationId;

	/**
	 * The CaptureMessage captureId
	 *
	 * @param - captureId of the CaptureMessage
	 * @return - captureId of the CaptureMessage
	 */
	private String captureId;

	/**
	 * The CaptureMessage authorizationTraceabilityId
	 *
	 * @param - authorizationTraceabilityId of the CaptureMessage
	 * @return - authorizationTraceabilityId of the CaptureMessage
	 */
	private String authorizationTraceabilityId;

	/**
	 * The CaptureMessage authorizationCode
	 *
	 * @param - authorizationCode of the CaptureMessage
	 * @return - authorizationCode of the CaptureMessage
	 */
	private String authorizationCode;

	/**
	 * The CaptureMessage transaction
	 *
	 * @param - transaction of the CaptureMessage
	 * @return - transaction of the CaptureMessage
	 */
	private Transaction transaction;

	/**
	 * The CaptureMessage creditCard
	 *
	 * @param - creditCard of the CaptureMessage
	 * @return - creditCard of the CaptureMessage
	 */
	private CreditCard creditCard;

}
