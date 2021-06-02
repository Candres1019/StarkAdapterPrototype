package com.fake.stark.acquirer.utils.validators.impl;

import com.fake.stark.acquirer.entities.CreditCard;
import com.fake.stark.acquirer.entities.Payment;
import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.persistence.Persistence;
import com.fake.stark.acquirer.services.TransactionStates;
import com.fake.stark.acquirer.utils.validators.CreditCardValidator;
import com.fake.stark.acquirer.utils.validators.RefundValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Defines the RefundValidatorImpl implementation
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Component("refundValidatorImpl")
public class RefundValidatorImpl implements RefundValidator {

	private final Persistence persistence;
	private final CreditCardValidator creditCardValidator;

	/**
	 * Instantiates a new RefundValidatorImpl service
	 *
	 * @param persistence         - The persistence service that provides the connection to the data base
	 * @param creditCardValidator - The service that validates if a credit card is valid.
	 */
	public RefundValidatorImpl(@Qualifier("myBatisPersistence") final Persistence persistence,
	                           @Qualifier("creditCardValidatorImpl") final CreditCardValidator creditCardValidator) {

		this.persistence = persistence;
		this.creditCardValidator = creditCardValidator;
	}

	/**
	 * Check the validity of an purchase order of type refund
	 *
	 * @param purchaseOrder - The PurchaseOrder to be processed
	 * @return The state of the PurchaseOrder after the validation
	 */
	@Override public TransactionStates validateRefund(final PurchaseOrder purchaseOrder) {

		TransactionStates transactionStates = TransactionStates.REFUND_APPROVED;
		PurchaseOrder oldPurchaseOrder = persistence.getPurchaseOrderById(purchaseOrder.getId());
		if (oldPurchaseOrder == null) {
			transactionStates = TransactionStates.REFUND_REJECTED_INVALID_ID;
		} else {
			CreditCard creditCard = persistence
					.getCreditCardByUser(oldPurchaseOrder.getPayment().getCreditCard().getUser().getIdentification());
			Payment payment = persistence.getPaymentById(purchaseOrder.getPayment().getId());
			if (payment == null) {
				transactionStates = TransactionStates.REFUND_REJECTED_PAYMENT_ID_INVALID;
			} else if (creditCardValidator
					.validateCreditCardForRefund(creditCard, purchaseOrder.getPayment().getCreditCard(), payment.getCreditCard())) {
				transactionStates = TransactionStates.REFUND_REJECTED_CREDIT_CARD_INVALID;
			} else if (oldPurchaseOrder.getStatus().contains("AUTHORIZATION") || oldPurchaseOrder.getStatus().contains("VOID")
					|| oldPurchaseOrder.getStatus().contains("REFUND")) {
				transactionStates = this.getCompletedTransactionReasonRefund(oldPurchaseOrder.getStatus());
			} else if (oldPurchaseOrder.getPayment().getMaxRefundDate().getTime() < System.currentTimeMillis()) {
				transactionStates = TransactionStates.REFUND_REJECTED_MAX_REFUND_DATE_EXPIRED;
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
	private TransactionStates getCompletedTransactionReasonRefund(String status) {

		if (status.contains("AUTHORIZATION")) {
			return TransactionStates.REFUND_IN_APPROVAL;
		} else if (status.contains("VOID")) {
			return TransactionStates.REFUND_ALREADY_PROCESSED_V;
		} else {
			return TransactionStates.REFUND_ALREADY_PROCESSED;
		}
	}
}
