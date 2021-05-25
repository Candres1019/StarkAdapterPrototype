package com.fake.stark.acquirer.utils.validators.impl;

import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.persistence.Persistence;
import com.fake.stark.acquirer.services.TransactionStates;
import com.fake.stark.acquirer.utils.validators.AuthorizationValidator;
import com.fake.stark.acquirer.utils.validators.CreditCardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("authorizationValidatorImpl")
public class AuthorizationValidatorImpl implements AuthorizationValidator {

	private static final String COMPLETED_DESCRIPTION = "Completed";
	private final Persistence persistence;
	private final CreditCardValidator creditCardValidator;

	@Autowired
	public AuthorizationValidatorImpl(@Qualifier("myBatisPersistence") final Persistence persistence,
									  @Qualifier("creditCardValidatorImpl") final CreditCardValidator creditCardValidator) {

		this.persistence = persistence;
		this.creditCardValidator = creditCardValidator;
	}

	@Override public TransactionStates validateAuthorization(final PurchaseOrder purchaseOrder) {

		var transactionStates = TransactionStates.AUTHORIZATION_APPROVED;
		var user = persistence.getUserById(purchaseOrder.getPayment().getCreditCard().getUser().getIdentification());
		var oldPurchaseOrder = persistence.getPurchaseOrderById(purchaseOrder.getId());
		if (user == null) {
			transactionStates = TransactionStates.AUTHORIZATION_REJECTED_INVALID_USER;
		} else {
			var creditCard = persistence.getCreditCardByUser(user.getIdentification());
			var payment = persistence.getPaymentById(purchaseOrder.getPayment().getId());
			if (creditCardValidator.validateCreditCardForAuthorization(creditCard, purchaseOrder.getPayment().getCreditCard())) {
				transactionStates = TransactionStates.AUTHORIZATION_REJECTED_CREDIT_CARD_INVALID;
			} else if (oldPurchaseOrder != null && oldPurchaseOrder.getDescription().contains(COMPLETED_DESCRIPTION)) {
				transactionStates = TransactionStates.AUTHORIZATION_ALREADY_PROCESSED;
			} else if (null != payment) {
				transactionStates = TransactionStates.AUTHORIZATION_ALREADY_PROCESSED;
			}
		}
		return transactionStates;
	}
}
