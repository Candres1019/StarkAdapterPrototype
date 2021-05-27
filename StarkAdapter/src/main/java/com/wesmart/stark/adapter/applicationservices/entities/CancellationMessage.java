package com.wesmart.stark.adapter.applicationservices.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wesmart.entities.CreditCard;
import com.wesmart.entities.Transaction;
import com.wesmart.stark.adapter.applicationservices.utils.CancellationMessageSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CancellationMessage entity class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = CancellationMessageSerializer.class)
public class CancellationMessage {

	/**
	 * The CancellationMessage correlationId
	 *
	 * @param - correlationId of the CancellationMessage
	 * @return - correlationId of the CancellationMessage
	 */
	private String correlationId;

	/**
	 * The CancellationMessage cancellationId
	 *
	 * @param - cancellationId of the CancellationMessage
	 * @return - cancellationId of the CancellationMessage
	 */
	private String cancellationId;

	/**
	 * The CancellationMessage authorizationTraceabilityId
	 *
	 * @param - authorizationTraceabilityId of the CancellationMessage
	 * @return - authorizationTraceabilityId of the CancellationMessage
	 */
	private String authorizationTraceabilityId;

	/**
	 * The CancellationMessage authorizationCode
	 *
	 * @param - authorizationCode of the CancellationMessage
	 * @return - authorizationCode of the CancellationMessage
	 */
	private String authorizationCode;

	/**
	 * The CancellationMessage transaction
	 *
	 * @param - transaction of the CancellationMessage
	 * @return - transaction of the CancellationMessage
	 */
	private Transaction transaction;

	/**
	 * The CancellationMessage creditCard
	 *
	 * @param - creditCard of the CancellationMessage
	 * @return - creditCard of the CancellationMessage
	 */
	private CreditCard creditCard;

}
