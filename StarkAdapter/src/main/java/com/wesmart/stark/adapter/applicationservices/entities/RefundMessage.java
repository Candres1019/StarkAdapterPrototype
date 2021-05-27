package com.wesmart.stark.adapter.applicationservices.entities;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wesmart.entities.CreditCard;
import com.wesmart.entities.Transaction;
import com.wesmart.stark.adapter.applicationservices.utils.RefundMessageSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RefundMessage entity class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = RefundMessageSerializer.class)
public class RefundMessage {

	/**
	 * The RefundMessage correlationId
	 *
	 * @param - correlationId of the RefundMessage
	 * @return - correlationId of the RefundMessage
	 */
	private String correlationId;

	/**
	 * The RefundMessage refundId
	 *
	 * @param - refundId of the RefundMessage
	 * @return - refundId of the RefundMessage
	 */
	private String refundId;

	/**
	 * The RefundMessage captureTraceabilityId
	 *
	 * @param - captureTraceabilityId of the RefundMessage
	 * @return - captureTraceabilityId of the RefundMessage
	 */
	private String captureTraceabilityId;

	/**
	 * The RefundMessage refundCode
	 *
	 * @param - refundCode of the RefundMessage
	 * @return - refundCode of the RefundMessage
	 */
	private String refundCode;

	/**
	 * The RefundMessage transaction
	 *
	 * @param - transaction of the RefundMessage
	 * @return - transaction of the RefundMessage
	 */
	private Transaction transaction;

	/**
	 * The RefundMessage creditCard
	 *
	 * @param - creditCard of the RefundMessage
	 * @return - creditCard of the RefundMessage
	 */
	private CreditCard creditCard;

	/**
	 * The RefundMessage maxRefundDate
	 *
	 * @param - maxRefundDate of the RefundMessage
	 * @return - maxRefundDate of the RefundMessage
	 */
	private Date maxRefundDate;

}
