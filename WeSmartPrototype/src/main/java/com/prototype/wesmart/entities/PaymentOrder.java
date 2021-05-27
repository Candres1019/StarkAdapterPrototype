package com.prototype.wesmart.entities;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PaymentOrder entity class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrder {

	/**
	 * The PaymentOrder id
	 *
	 * @param - id of the PaymentOrder
	 * @return - id of the PaymentOrder
	 */
	private UUID id;

	/**
	 * The PaymentOrder reference
	 *
	 * @param - reference of the PaymentOrder
	 * @return - reference of the PaymentOrder
	 */
	private String reference;

	/**
	 * The PaymentOrder description
	 *
	 * @param - description of the PaymentOrder
	 * @return - description of the PaymentOrder
	 */
	private String description;

	/**
	 * The PaymentOrder billingAddress
	 *
	 * @param - billingAddress of the PaymentOrder
	 * @return - billingAddress of the PaymentOrder
	 */
	private String billingAddress;

	/**
	 * The PaymentOrder creationDate
	 *
	 * @param - creationDate of the PaymentOrder
	 * @return - creationDate of the PaymentOrder
	 */
	private Date creationDate;

	/**
	 * The PaymentOrder customer
	 *
	 * @param - customer of the PaymentOrder
	 * @return - customer of the PaymentOrder
	 */
	private Customer customer;

	/**
	 * The PaymentOrder seller
	 *
	 * @param - seller of the PaymentOrder
	 * @return - seller of the PaymentOrder
	 */
	private Seller seller;

}
