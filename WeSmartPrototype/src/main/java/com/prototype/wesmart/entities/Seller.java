package com.prototype.wesmart.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Seller entity class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seller {

	/**
	 * The Seller id
	 *
	 * @param - id of the Seller
	 * @return - id of the Seller
	 */
	private Integer id;

	/**
	 * The Seller name
	 *
	 * @param - name of the Seller
	 * @return - name of the Seller
	 */
	private String name;

	/**
	 * The Seller nit
	 *
	 * @param - nit of the Seller
	 * @return - nit of the Seller
	 */
	private String nit;

	/**
	 * The Seller merchantCategoryCode
	 *
	 * @param - merchantCategoryCode of the Seller
	 * @return - merchantCategoryCode of the Seller
	 */
	private String merchantCategoryCode;

	/**
	 * The Seller phone
	 *
	 * @param - phone of the Seller
	 * @return - phone of the Seller
	 */
	private String phone;

	/**
	 * The Seller email
	 *
	 * @param - email of the Seller
	 * @return - email of the Seller
	 */
	private String email;

	/**
	 * The Seller address
	 *
	 * @param - address of the Seller
	 * @return - address of the Seller
	 */
	private String address;

	/**
	 * The Seller creationDate
	 *
	 * @param - creationDate of the Seller
	 * @return - creationDate of the Seller
	 */
	private Date creationDate;

	/**
	 * The Seller modificationDate
	 *
	 * @param - modificationDate of the Seller
	 * @return - modificationDate of the Seller
	 */
	private Date modificationDate;

}
