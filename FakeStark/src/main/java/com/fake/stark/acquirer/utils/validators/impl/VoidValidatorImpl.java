package com.fake.stark.acquirer.utils.validators.impl;

import com.fake.stark.acquirer.entities.CreditCard;
import com.fake.stark.acquirer.entities.Payment;
import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.persistence.Persistence;
import com.fake.stark.acquirer.services.TransactionStates;
import com.fake.stark.acquirer.utils.validators.CreditCardValidator;
import com.fake.stark.acquirer.utils.validators.VoidValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Defines the VoidValidator implementation
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Component("voidValidatorImpl")
public class VoidValidatorImpl implements VoidValidator {

	private static final String COMPLETED_DESCRIPTION = "Completed";
	private final Persistence persistence;
	private final CreditCardValidator creditCardValidator;

	/**
	 * Instantiates a new VoidValidatorImpl service
	 *
	 * @param persistence         - The persistence service that provides the connection to the data base
	 * @param creditCardValidator - The service that validates if a credit card is valid.
	 */
	@Autowired
	public VoidValidatorImpl(@Qualifier("myBatisPersistence") final Persistence persistence,
	                         @Qualifier("creditCardValidatorImpl") final CreditCardValidator creditCardValidator) {

		this.persistence = persistence;
		this.creditCardValidator = creditCardValidator;
	}

	/**
	 * Check the validity of an purchase order of type void
	 *
	 * @param purchaseOrder - The PurchaseOrder to be processed
	 * @return The state of the PurchaseOrder after the validation
	 */
	@Override public TransactionStates validateVoid(final PurchaseOrder purchaseOrder) {

		TransactionStates transactionStates = TransactionStates.VOID_APPROVED;
		PurchaseOrder oldPurchaseOrder = persistence.getPurchaseOrderById(purchaseOrder.getId());
		if (oldPurchaseOrder == null) {
			transactionStates = TransactionStates.VOID_REJECTED_INVALID_ID;
		} else {
			CreditCard creditCard = persistence
					.getCreditCardByUser(oldPurchaseOrder.getPayment().getCreditCard().getUser().getIdentification());
			Payment payment = persistence.getPaymentById(purchaseOrder.getPayment().getId());
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

	/**
	 * Check the reason of a completed transaction.
	 *
	 * @param status - The State of a transaction.
	 * @return - The transaction state reason
	 */
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
