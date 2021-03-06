package com.fake.stark.acquirer.utils.validators.impl;

import com.fake.stark.acquirer.entities.CreditCard;
import com.fake.stark.acquirer.entities.Payment;
import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.entities.User;
import com.fake.stark.acquirer.persistence.Persistence;
import com.fake.stark.acquirer.services.TransactionStates;
import com.fake.stark.acquirer.utils.validators.AuthorizationValidator;
import com.fake.stark.acquirer.utils.validators.CreditCardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Defines the AuthorizationValidator implementation
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Component("authorizationValidatorImpl")
public class AuthorizationValidatorImpl implements AuthorizationValidator {

	private static final String COMPLETED_DESCRIPTION = "Completed";
	private final Persistence persistence;
	private final CreditCardValidator creditCardValidator;

	/**
	 * Instantiates a new AuthorizationValidatorImpl service
	 *
	 * @param persistence         - The persistence service that provides the connection to the data base
	 * @param creditCardValidator - The service that validates if a credit card is valid.
	 */
	@Autowired
	public AuthorizationValidatorImpl(@Qualifier("myBatisPersistence") final Persistence persistence,
	                                  @Qualifier("creditCardValidatorImpl") final CreditCardValidator creditCardValidator) {

		this.persistence = persistence;
		this.creditCardValidator = creditCardValidator;
	}

	/**
	 * Check the validity of an purchase order of type authorization
	 *
	 * @param purchaseOrder - The PurchaseOrder to be processed
	 * @return The state of the PurchaseOrder after the validation
	 */
	@Override public TransactionStates validateAuthorization(final PurchaseOrder purchaseOrder) {

		TransactionStates transactionStates = TransactionStates.AUTHORIZATION_APPROVED;
		User user = persistence.getUserById(purchaseOrder.getPayment().getCreditCard().getUser().getIdentification());
		PurchaseOrder oldPurchaseOrder = persistence.getPurchaseOrderById(purchaseOrder.getId());
		if (user == null) {
			transactionStates = TransactionStates.AUTHORIZATION_REJECTED_INVALID_USER;
		} else {
			CreditCard creditCard = persistence.getCreditCardByUser(user.getIdentification());
			Payment payment = persistence.getPaymentById(purchaseOrder.getPayment().getId());
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
