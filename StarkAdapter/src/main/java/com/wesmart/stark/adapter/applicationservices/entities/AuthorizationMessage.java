package com.wesmart.stark.adapter.applicationservices.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wesmart.entities.CreditCard;
import com.wesmart.entities.Transaction;
import com.wesmart.stark.adapter.applicationservices.utils.AuthorizationMessageSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AuthorizationMessage entity class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = AuthorizationMessageSerializer.class)
public class AuthorizationMessage {

	/**
	 * The AuthorizationMessage correlationId
	 *
	 * @param - correlationId of the AuthorizationMessage
	 * @return - correlationId of the AuthorizationMessage
	 */
	private String correlationId;

	/**
	 * The AuthorizationMessage authorizationId
	 *
	 * @param - authorizationId of the AuthorizationMessage
	 * @return - authorizationId of the AuthorizationMessage
	 */
	private String authorizationId;

	/**
	 * The AuthorizationMessage transaction
	 *
	 * @param - transaction of the AuthorizationMessage
	 * @return - transaction of the AuthorizationMessage
	 */
	private Transaction transaction;

	/**
	 * The AuthorizationMessage creditCard
	 *
	 * @param - creditCard of the AuthorizationMessage
	 * @return - creditCard of the AuthorizationMessage
	 */
	private CreditCard creditCard;

}
