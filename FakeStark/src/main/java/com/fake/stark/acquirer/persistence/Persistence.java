package com.fake.stark.acquirer.persistence;

import java.util.List;

import com.fake.stark.acquirer.entities.CreditCard;
import com.fake.stark.acquirer.entities.Payment;
import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface Persistence {

	User getUserById(Integer id);

	CreditCard getCreditCardByUser(Integer identification);

	Payment getPaymentById(String id);

	void insertPayment(Payment payment);

	void insertPurchaseOrder(PurchaseOrder purchaseOrder);

	PurchaseOrder getPurchaseOrderById(String id);

	List<Payment> getAll();

	void updatePayment(Payment payment);

	void updatePurchaseOrder(PurchaseOrder purchaseOrder);
}
