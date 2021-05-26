package com.fake.stark.acquirer.persistence;

import com.fake.stark.acquirer.entities.CreditCard;
import com.fake.stark.acquirer.entities.Payment;
import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.entities.User;
import org.springframework.stereotype.Service;

/**
 * Defines the structure for persistence service
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public interface Persistence {

	/**
	 * Get the user in the DB that match with the id sent
	 *
	 * @param id - id identification of the user to be searched
	 * @return - The user that has been found using the id
	 */
	User getUserById(Integer id);

	/**
	 * Get the credit card in the DB of a user, using his id
	 *
	 * @param identification - The identification of the user
	 * @return - The credit card of the user
	 */
	CreditCard getCreditCardByUser(Integer identification);

	/**
	 * Get the payment in the DB that match with the id sent
	 *
	 * @param id - Id of the payment to be searched
	 * @return - The payment found in the DB that has been found using the id
	 */
	Payment getPaymentById(String id);

	/**
	 * Get the PurchaseOrder in the DB that match with the id sent
	 *
	 * @param id - Id of the purchase order to be searched
	 * @return - The PurchaseOrder found in the DB that has been found using the id
	 */
	PurchaseOrder getPurchaseOrderById(String id);

	/**
	 * Insert in the DB a Payment
	 *
	 * @param payment - Payment to be inserted in the DB
	 */
	void insertPayment(Payment payment);

	/**
	 * Insert in the DB a PurchaseOrder
	 *
	 * @param purchaseOrder - The PurchaseOrder to be inserted in the DB
	 */
	void insertPurchaseOrder(PurchaseOrder purchaseOrder);

	/**
	 * Update a payment in the DB
	 *
	 * @param payment - Payment with the info to be updated
	 */
	void updatePayment(Payment payment);

	/**
	 * Update a PurchaseOrder in the DB
	 *
	 * @param purchaseOrder - PurchaseOrder with the info to be updated
	 */
	void updatePurchaseOrder(PurchaseOrder purchaseOrder);
}
