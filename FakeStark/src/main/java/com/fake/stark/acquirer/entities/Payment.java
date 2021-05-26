package com.fake.stark.acquirer.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Payment entity class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	/**
	 * The Payment id
	 *
	 * @param id of the Payment
	 * @return id of the Payment
	 */
	private String id;

	/**
	 * The Payment amount
	 *
	 * @param amount of the Payment
	 * @return amount of the Payment
	 */
	private Double amount;

	/**
	 * The Payment currency
	 *
	 * @param currency of the Payment
	 * @return currency of the Payment
	 */
	private String currency;

	/**
	 * The Payment maxRefundDate
	 *
	 * @param maxRefundDate of the Payment
	 * @return maxRefundDate of the Payment
	 */
	private Date maxRefundDate;

	/**
	 * The Payment creditCard
	 *
	 * @param creditCard of the Payment
	 * @return creditCard of the Payment
	 */
	private CreditCard creditCard;

}
