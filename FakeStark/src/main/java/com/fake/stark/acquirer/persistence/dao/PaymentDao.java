package com.fake.stark.acquirer.persistence.dao;

import com.fake.stark.acquirer.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Defines the structure for PaymentDao service
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Mapper
public interface PaymentDao {

	/**
	 * Get the payment in the DB that match with the id sent
	 *
	 * @param id - Id of the payment to be searched
	 * @return - The payment found in the DB that has been found using the id
	 */
	Payment getPaymentById(@Param("id") String id);

	/**
	 * Insert in the DB a Payment
	 *
	 * @param payment - Payment to be inserted in the DB
	 */
	void insertPayment(@Param("payment") Payment payment);

	/**
	 * Update a payment in the DB
	 *
	 * @param payment - Payment with the info to be updated
	 */
	void updatePayment(@Param("payment") Payment payment);

}
