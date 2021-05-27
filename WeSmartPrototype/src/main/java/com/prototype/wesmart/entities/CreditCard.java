package com.prototype.wesmart.entities;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CreditCard entity class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {

	/**
	 * The CreditCard id
	 *
	 * @param - id of the CreditCard
	 * @return - id of the CreditCard
	 */
	private Integer id;

	/**
	 * The CreditCard type
	 *
	 * @param - type of the CreditCard
	 * @return - type of the CreditCard
	 */
	private String type;

	/**
	 * The CreditCard maskedNumber
	 *
	 * @param - maskedNumber of the CreditCard
	 * @return - maskedNumber of the CreditCard
	 */
	private String maskedNumber;

	/**
	 * The CreditCard token
	 *
	 * @param - token of the CreditCard
	 * @return - token of the CreditCard
	 */
	private String token;

	/**
	 * The CreditCard cardHolder
	 *
	 * @param - cardHolder of the CreditCard
	 * @return - cardHolder of the CreditCard
	 */
	private String cardHolder;

	/**
	 * The CreditCard expirationDate
	 *
	 * @param - expirationDate of the CreditCard
	 * @return - expirationDate of the CreditCard
	 */
	private Date expirationDate;

	/**
	 * The CreditCard creationDate
	 *
	 * @param - creationDate of the CreditCard
	 * @return - creationDate of the CreditCard
	 */
	private Date creationDate;

	/**
	 * The CreditCard modificationDate
	 *
	 * @param - modificationDate of the CreditCard
	 * @return - modificationDate of the CreditCard
	 */
	private Date modificationDate;

	/**
	 * The CreditCard customer
	 *
	 * @param - customer of the CreditCard
	 * @return - customer of the CreditCard
	 */
	private Customer customer;

}
