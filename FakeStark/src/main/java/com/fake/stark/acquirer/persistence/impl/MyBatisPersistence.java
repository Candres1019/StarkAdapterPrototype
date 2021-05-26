package com.fake.stark.acquirer.persistence.impl;

import com.fake.stark.acquirer.entities.CreditCard;
import com.fake.stark.acquirer.entities.Payment;
import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.entities.User;
import com.fake.stark.acquirer.persistence.Persistence;
import com.fake.stark.acquirer.persistence.dao.CreditCardDao;
import com.fake.stark.acquirer.persistence.dao.PaymentDao;
import com.fake.stark.acquirer.persistence.dao.PurchaseOrderDao;
import com.fake.stark.acquirer.persistence.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Defines the persistence implementation
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Component("myBatisPersistence")
public class MyBatisPersistence implements Persistence {

	private final UserDao userDAO;
	private final CreditCardDao creditCardDao;
	private final PaymentDao paymentDao;
	private final PurchaseOrderDao purchaseOrderDao;

	/**
	 * Instantiates a new MyBatisPersistence service, that uses the framework MyBatis
	 *
	 * @param userDAO          - Mapper of the User in DB
	 * @param creditCardDao    - Mapper of the CreditCard in DB
	 * @param paymentDao       - Mapper of the Payment in DB
	 * @param purchaseOrderDao - Mapper of the PurchaseOrder in DB
	 */
	@Autowired
	public MyBatisPersistence(final UserDao userDAO, final CreditCardDao creditCardDao,
	                          final PaymentDao paymentDao, final PurchaseOrderDao purchaseOrderDao) {

		this.userDAO = userDAO;
		this.creditCardDao = creditCardDao;
		this.paymentDao = paymentDao;
		this.purchaseOrderDao = purchaseOrderDao;
	}

	/**
	 * Get the user in the DB that match with the id sent
	 *
	 * @param id - id identification of the user to be searched
	 * @return - The user that has been found using the id
	 */
	@Override public User getUserById(final Integer id) {

		return userDAO.getUserById(id);
	}

	/**
	 * Get the credit card in the DB of a user, using his id
	 *
	 * @param identification - The identification of the user
	 * @return - The credit card of the user
	 */
	@Override public CreditCard getCreditCardByUser(final Integer identification) {

		return creditCardDao.getCreditCardByUser(identification);
	}

	/**
	 * Get the payment in the DB that match with the id sent
	 *
	 * @param id - Id of the payment to be searched
	 * @return - The payment found in the DB that has been found using the id
	 */
	@Override public Payment getPaymentById(final String id) {

		return paymentDao.getPaymentById(id);
	}

	/**
	 * Get the PurchaseOrder in the DB that match with the id sent
	 *
	 * @param id - Id of the purchase order to be searched
	 * @return - The PurchaseOrder found in the DB that has been found using the id
	 */
	@Override public PurchaseOrder getPurchaseOrderById(final String id) {

		return purchaseOrderDao.getPurchaseOrderById(id);
	}

	/**
	 * Insert in the DB a Payment
	 *
	 * @param payment - Payment to be inserted in the DB
	 */
	@Override public void insertPayment(final Payment payment) {

		paymentDao.insertPayment(payment);
	}

	/**
	 * Insert in the DB a PurchaseOrder
	 *
	 * @param purchaseOrder - The PurchaseOrder to be inserted in the DB
	 */
	@Override public void insertPurchaseOrder(final PurchaseOrder purchaseOrder) {

		purchaseOrderDao.insertPurchaseOrder(purchaseOrder);
	}

	/**
	 * Update a payment in the DB
	 *
	 * @param payment - Payment with the info to be updated
	 */
	@Override public void updatePayment(final Payment payment) {

		paymentDao.updatePayment(payment);
	}

	/**
	 * Update a PurchaseOrder in the DB
	 *
	 * @param purchaseOrder - PurchaseOrder with the info to be updated
	 */
	@Override public void updatePurchaseOrder(final PurchaseOrder purchaseOrder) {

		purchaseOrderDao.updatePurchaseOrder(purchaseOrder);
	}
}
