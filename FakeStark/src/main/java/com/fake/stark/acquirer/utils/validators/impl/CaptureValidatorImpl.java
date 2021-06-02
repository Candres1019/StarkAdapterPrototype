package com.fake.stark.acquirer.utils.validators.impl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.fake.stark.acquirer.entities.CreditCard;
import com.fake.stark.acquirer.entities.Payment;
import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.persistence.Persistence;
import com.fake.stark.acquirer.services.TransactionStates;
import com.fake.stark.acquirer.utils.validators.CaptureValidator;
import com.fake.stark.acquirer.utils.validators.CreditCardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Defines the CaptureValidator implementation
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Component("captureValidatorImpl")
public class CaptureValidatorImpl implements CaptureValidator {

	private static final String COMPLETED_DESCRIPTION = "Completed";
	private static final Integer MAX_REFUND_DAYS = 2;
	private final Persistence persistence;
	private final CreditCardValidator creditCardValidator;

	/**
	 * Instantiates a new CaptureValidatorImpl service
	 *
	 * @param persistence         - The persistence service that provides the connection to the data base
	 * @param creditCardValidator - The service that validates if a credit card is valid.
	 */
	@Autowired
	public CaptureValidatorImpl(@Qualifier("myBatisPersistence") final Persistence persistence,
	                            @Qualifier("creditCardValidatorImpl") final CreditCardValidator creditCardValidator) {

		this.persistence = persistence;

		this.creditCardValidator = creditCardValidator;
	}

	/**
	 * Check the validity of an purchase order of type capture
	 *
	 * @param purchaseOrder - The PurchaseOrder to be processed
	 * @return The state of the PurchaseOrder after the validation
	 */
	@Override public TransactionStates validateCapture(final PurchaseOrder purchaseOrder) {

		TransactionStates transactionStates = TransactionStates.CAPTURE_APPROVED;
		PurchaseOrder oldPurchaseOrder = persistence.getPurchaseOrderById(purchaseOrder.getId());
		purchaseOrder.getPayment().setMaxRefundDate(new Date(0L));
		if (oldPurchaseOrder == null) {
			transactionStates = TransactionStates.CAPTURE_REJECTED_INVALID_ID;
		} else {
			CreditCard creditCard = persistence
					.getCreditCardByUser(oldPurchaseOrder.getPayment().getCreditCard().getUser().getIdentification());
			Payment payment = persistence.getPaymentById(purchaseOrder.getPayment().getId());
			if (payment == null) {
				transactionStates = TransactionStates.CAPTURE_REJECTED_PAYMENT_ID_INVALID;
			} else if (creditCardValidator
					.validateCreditCardForCapture(creditCard, purchaseOrder.getPayment().getCreditCard(), payment.getCreditCard())) {
				transactionStates = TransactionStates.CAPTURE_REJECTED_CREDIT_CARD_INVALID;
			} else if (oldPurchaseOrder.getDescription().contains(COMPLETED_DESCRIPTION)) {
				transactionStates = this.getCompletedTransactionReasonCapture(oldPurchaseOrder.getStatus());
			} else {
				purchaseOrder.getPayment()
				             .setMaxRefundDate(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(MAX_REFUND_DAYS)));
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
	private TransactionStates getCompletedTransactionReasonCapture(String status) {

		if (status.contains("CAPTURE")) {
			return TransactionStates.CAPTURE_ALREADY_PROCESSED;
		} else if (status.contains("VOID")) {
			return TransactionStates.CAPTURE_ALREADY_PROCESSED_V;
		} else {
			return TransactionStates.CAPTURE_ALREADY_PROCESSED_R;
		}
	}
}
