package com.fake.stark.acquirer.entities;

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
	 * The CreditCard number
	 *
	 * @param number of the CreditCard
	 * @return number of the CreditCard
	 */
	private String number;

	/**
	 * The CreditCard number
	 *
	 * @param type of the CreditCard
	 * @return type of the CreditCard
	 */
	private String type;

	/**
	 * The CreditCard cardHolder
	 *
	 * @param cardHolder of the CreditCard
	 * @return cardHolder of the CreditCard
	 */
	private String cardHolder;

	/**
	 * The CreditCard expirationDate
	 *
	 * @param expirationDate of the CreditCard
	 * @return expirationDate of the CreditCard
	 */
	private Date expirationDate;

	/**
	 * The CreditCard user
	 *
	 * @param user of the CreditCard
	 * @return user of the CreditCard
	 */
	private User user;

}
