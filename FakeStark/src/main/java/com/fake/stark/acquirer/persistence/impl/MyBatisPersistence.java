package com.fake.stark.acquirer.persistence.impl;

import java.util.List;

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

@Component("myBatisPersistence")
public class MyBatisPersistence implements Persistence {

	private final UserDao userDAO;
	private final CreditCardDao creditCardDao;
	private final PaymentDao paymentDao;
	private final PurchaseOrderDao purchaseOrderDao;

	@Autowired
	public MyBatisPersistence(final UserDao userDAO, final CreditCardDao creditCardDao,
							  final PaymentDao paymentDao, final PurchaseOrderDao purchaseOrderDao) {

		this.userDAO = userDAO;
		this.creditCardDao = creditCardDao;
		this.paymentDao = paymentDao;
		this.purchaseOrderDao = purchaseOrderDao;
	}

	@Override public User getUserById(final Integer id) {

		return userDAO.getUserById(id);
	}

	@Override public CreditCard getCreditCardByUser(final Integer identification) {

		return creditCardDao.getCreditCardByUser(identification);
	}

	@Override public Payment getPaymentById(final String id) {

		return paymentDao.getPaymentById(id);
	}

	@Override public void insertPayment(final Payment payment) {

		paymentDao.insertPayment(payment);
	}

	@Override public void insertPurchaseOrder(final PurchaseOrder purchaseOrder) {

		purchaseOrderDao.insertPurchaseOrder(purchaseOrder);
	}

	@Override public PurchaseOrder getPurchaseOrderById(final String id) {

		return purchaseOrderDao.getPurchaseOrderById(id);
	}

	@Override public List<Payment> getAll() {

		return paymentDao.getAll();
	}

	@Override public void updatePayment(final Payment payment) {

		paymentDao.updatePayment(payment);
	}

	@Override public void updatePurchaseOrder(final PurchaseOrder purchaseOrder) {
		purchaseOrderDao.updatePurchaseOrder(purchaseOrder);
	}
}
