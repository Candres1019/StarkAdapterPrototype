package com.prototype.wesmart.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Customer entity class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	/**
	 * The id correlationId
	 *
	 * @param - id of the Customer
	 * @return - id of the Customer
	 */
	private Integer id;

	/**
	 * The Customer idType
	 *
	 * @param - idType of the Customer
	 * @return - idType of the Customer
	 */
	private String idType;

	/**
	 * The Customer name
	 *
	 * @param - name of the Customer
	 * @return - name of the Customer
	 */
	private String name;

	/**
	 * The Customer lastName
	 *
	 * @param - lastName of the Customer
	 * @return - lastName of the Customer
	 */
	private String lastName;

	/**
	 * The Customer email
	 *
	 * @param - email of the Customer
	 * @return - email of the Customer
	 */
	private String email;

	/**
	 * The Customer phone
	 *
	 * @param - phone of the Customer
	 * @return - phone of the Customer
	 */
	private String phone;

	/**
	 * The Customer address
	 *
	 * @param - address of the Customer
	 * @return - address of the Customer
	 */
	private String address;

	/**
	 * The Customer creationDate
	 *
	 * @param - creationDate of the Customer
	 * @return - creationDate of the Customer
	 */
	private Date creationDate;

	/**
	 * The Customer modificationDate
	 *
	 * @param - modificationDate of the Customer
	 * @return - modificationDate of the Customer
	 */
	private Date modificationDate;

}
