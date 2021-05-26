package com.fake.stark.acquirer.persistence.dao;

import com.fake.stark.acquirer.entities.CreditCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Defines the structure for CreditCardDao service
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Mapper
public interface CreditCardDao {

	/**
	 * Get the credit card in the DB of a user, using his id
	 *
	 * @param identification - The identification of the user
	 * @return - The credit card of the user
	 */
	CreditCard getCreditCardByUser(@Param("identification") Integer identification);

}
