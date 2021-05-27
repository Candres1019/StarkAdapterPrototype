package com.prototype.wesmart.entities;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Transaction entity class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	/**
	 * The Transaction id
	 *
	 * @param - id of the Transaction
	 * @return - id of the Transaction
	 */
	private UUID id;

	/**
	 * The Transaction type
	 *
	 * @param - type of the Transaction
	 * @return - type of the Transaction
	 */
	private String type;

	/**
	 * The Transaction currency
	 *
	 * @param - currency of the Transaction
	 * @return - currency of the Transaction
	 */
	private String currency;

	/**
	 * The Transaction status
	 *
	 * @param - status of the Transaction
	 * @return - status of the Transaction
	 */
	private String status;

	/**
	 * The Transaction total
	 *
	 * @param - total of the Transaction
	 * @return - total of the Transaction
	 */
	private Double total;

	/**
	 * The Transaction tax
	 *
	 * @param - tax of the Transaction
	 * @return - tax of the Transaction
	 */
	private Double tax;

	/**
	 * The Transaction taxBase
	 *
	 * @param - taxBase of the Transaction
	 * @return - taxBase of the Transaction
	 */
	private Double taxBase;

	/**
	 * The Transaction creationDate
	 *
	 * @param - creationDate of the Transaction
	 * @return - creationDate of the Transaction
	 */
	private Date creationDate;

	/**
	 * The Transaction modificationDate
	 *
	 * @param - modificationDate of the Transaction
	 * @return - modificationDate of the Transaction
	 */
	private Date modificationDate;

	/**
	 * The Transaction paymentOrder
	 *
	 * @param - paymentOrder of the Transaction
	 * @return - paymentOrder of the Transaction
	 */
	private PaymentOrder paymentOrder;

}
