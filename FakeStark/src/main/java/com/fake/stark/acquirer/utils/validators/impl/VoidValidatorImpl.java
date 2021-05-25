package com.fake.stark.acquirer.utils.validators.impl;

import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.persistence.Persistence;
import com.fake.stark.acquirer.services.TransactionStates;
import com.fake.stark.acquirer.utils.validators.CreditCardValidator;
import com.fake.stark.acquirer.utils.validators.VoidValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("voidValidatorImpl")
public class VoidValidatorImpl implements VoidValidator {

	private static final String COMPLETED_DESCRIPTION = "Completed";
	private final Persistence persistence;
	private final CreditCardValidator creditCardValidator;

	@Autowired
	public VoidValidatorImpl(@Qualifier("myBatisPersistence") final Persistence persistence,
							 @Qualifier("creditCardValidatorImpl") final CreditCardValidator creditCardValidator) {

		this.persistence = persistence;
		this.creditCardValidator = creditCardValidator;
	}

	@Override public TransactionStates validateVoid(final PurchaseOrder purchaseOrder) {

		var transactionStates = TransactionStates.VOID_APPROVED;
		var oldPurchaseOrder = persistence.getPurchaseOrderById(purchaseOrder.getId());
		if (oldPurchaseOrder == null) {
			transactionStates = TransactionStates.VOID_REJECTED_INVALID_ID;
		} else {
			var creditCard = persistence
					.getCreditCardByUser(oldPurchaseOrder.getPayment().getCreditCard().getUser().getIdentification());
			var payment = persistence.getPaymentById(purchaseOrder.getPayment().getId());
			if (payment == null) {
				transactionStates = TransactionStates.VOID_REJECTED_PAYMENT_ID_INVALID;
			} else if (creditCardValidator
					.validateCreditCardForVoid(creditCard, purchaseOrder.getPayment().getCreditCard(), payment.getCreditCard())) {
				transactionStates = TransactionStates.VOID_REJECTED_CREDIT_CARD_INVALID;
			} else if (oldPurchaseOrder.getDescription().contains(COMPLETED_DESCRIPTION)) {
				transactionStates = this.getCompletedTransactionReasonVoid(oldPurchaseOrder.getStatus());
			}
		}
		return transactionStates;
	}

	private TransactionStates getCompletedTransactionReasonVoid(String status) {

		if (status.contains("CAPTURE")) {
			return TransactionStates.VOID_ALREADY_PROCESSED_C;
		} else if (status.contains("VOID")) {
			return TransactionStates.VOID_ALREADY_PROCESSED;
		} else {
			return TransactionStates.VOID_ALREADY_PROCESSED_R;
		}
	}
}
