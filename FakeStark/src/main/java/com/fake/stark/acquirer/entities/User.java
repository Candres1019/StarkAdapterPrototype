package com.fake.stark.acquirer.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User entity Class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	/**
	 * The user Identification
	 *
	 * @param identification of the user
	 * @return identification of the user
	 */
	private Integer identification;

	/**
	 * The user Name
	 *
	 * @param name of the user
	 * @return name of the user
	 */
	private String name;

	/**
	 * The user Phone
	 *
	 * @param phone of the user
	 * @return phone of the user
	 */
	private String phone;

	/**
	 * The Address Phone
	 *
	 * @param address of the user
	 * @return address of the user
	 */
	private String address;

	/**
	 * The user Email
	 *
	 * @param email of the user
	 * @return email of the user
	 */
	private String email;

}
