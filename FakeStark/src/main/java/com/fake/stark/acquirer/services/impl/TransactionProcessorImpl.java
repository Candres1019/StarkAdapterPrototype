package com.fake.stark.acquirer.services.impl;

import java.util.Date;

import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.persistence.Persistence;
import com.fake.stark.acquirer.services.TransactionProcessor;
import com.fake.stark.acquirer.services.TransactionStates;
import com.fake.stark.acquirer.utils.validators.AuthorizationValidator;
import com.fake.stark.acquirer.utils.validators.CaptureValidator;
import com.fake.stark.acquirer.utils.validators.RefundValidator;
import com.fake.stark.acquirer.utils.validators.VoidValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Defines the TransactionProcessor implementation
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Log4j2
@Component("transactionProcessorImpl")
public class TransactionProcessorImpl implements TransactionProcessor {

	private final Persistence persistence;
	private final AuthorizationValidator authorizationValidator;
	private final CaptureValidator captureValidator;
	private final VoidValidator voidValidator;
	private final RefundValidator refundValidator;

	/**
	 * Instantiates a new TransactionProcessorImpl service
	 *
	 * @param persistence            - The persistence service that provides the connection to the data base
	 * @param authorizationValidator - The AuthorizationValidator service that provides validation for Authorization Transactions
	 * @param captureValidator       - The CaptureValidator service that provides validation for Capture Transactions
	 * @param voidValidator          - The VoidValidator service that provides validation for Void Transactions
	 * @param refundValidator        - The RefundValidator service that provides validation for Refund Transactions
	 */
	@Autowired
	public TransactionProcessorImpl(@Qualifier("myBatisPersistence") final Persistence persistence,
	                                @Qualifier("authorizationValidatorImpl") final AuthorizationValidator authorizationValidator,
	                                @Qualifier("captureValidatorImpl") final CaptureValidator captureValidator,
	                                @Qualifier("voidValidatorImpl") final VoidValidator voidValidator,
	                                @Qualifier("refundValidatorImpl") final RefundValidator refundValidator) {

		this.persistence = persistence;
		this.authorizationValidator = authorizationValidator;
		this.captureValidator = captureValidator;
		this.voidValidator = voidValidator;
		this.refundValidator = refundValidator;
	}

	/**
	 * Process an Transaction of type Authorization
	 *
	 * @param purchaseOrder - The purchase order to be processed
	 * @return The Purchase Order after been processed
	 */
	@Override public PurchaseOrder processAuthorizationTransaction(final PurchaseOrder purchaseOrder) {

		try {
			TransactionStates transactionStates = authorizationValidator.validateAuthorization(purchaseOrder);
			purchaseOrder.setStatus(transactionStates.name());
			purchaseOrder.setDescription(transactionStates.getName());
			if (transactionStates.equals(TransactionStates.AUTHORIZATION_APPROVED)) {
				persistence.insertPayment(purchaseOrder.getPayment());
				persistence.insertPurchaseOrder(purchaseOrder);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			purchaseOrder.setStatus(TransactionStates.ERROR_IN_THE_SERVER.name());
			purchaseOrder.setDescription("Authorization, " + TransactionStates.ERROR_IN_THE_SERVER.getName());
		}
		return purchaseOrder;
	}

	/**
	 * Process an Transaction of type Capture
	 *
	 * @param purchaseOrder - The purchase order to be processed
	 * @return The Purchase Order after been processed
	 */
	@Override public PurchaseOrder processCaptureTransaction(final PurchaseOrder purchaseOrder) {

		try {
			TransactionStates transactionStates = captureValidator.validateCapture(purchaseOrder);
			purchaseOrder.setStatus(transactionStates.name());
			purchaseOrder.setDescription(transactionStates.getName());
			if (transactionStates.equals(TransactionStates.CAPTURE_APPROVED)) {
				persistence.updatePayment(purchaseOrder.getPayment());
				persistence.updatePurchaseOrder(purchaseOrder);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			purchaseOrder.setStatus(TransactionStates.ERROR_IN_THE_SERVER.name());
			purchaseOrder.setDescription("Capture, " + TransactionStates.ERROR_IN_THE_SERVER.getName());
		}
		return purchaseOrder;
	}

	/**
	 * Process an Transaction of type Void
	 *
	 * @param purchaseOrder - The purchase order to be processed
	 * @return The Purchase Order after been processed
	 */
	@Override public PurchaseOrder processVoidTransaction(final PurchaseOrder purchaseOrder) {

		try {
			TransactionStates transactionStates = voidValidator.validateVoid(purchaseOrder);
			purchaseOrder.setStatus(transactionStates.name());
			purchaseOrder.setDescription(transactionStates.getName());
			if (transactionStates.equals(TransactionStates.VOID_APPROVED)) {
				purchaseOrder.getPayment().setMaxRefundDate(new Date(0L));
				persistence.updatePayment(purchaseOrder.getPayment());
				persistence.updatePurchaseOrder(purchaseOrder);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			purchaseOrder.setStatus(TransactionStates.ERROR_IN_THE_SERVER.name());
			purchaseOrder.setDescription("Void, " + TransactionStates.ERROR_IN_THE_SERVER.getName());
		}
		return purchaseOrder;
	}

	/**
	 * Process an Transaction of type Refund
	 *
	 * @param purchaseOrder - The purchase order to be processed
	 * @return The Purchase Order after been processed
	 */
	@Override public PurchaseOrder processRefundTransaction(final PurchaseOrder purchaseOrder) {

		try {
			TransactionStates transactionStates = refundValidator.validateRefund(purchaseOrder);
			purchaseOrder.setStatus(transactionStates.name());
			purchaseOrder.setDescription(transactionStates.getName());
			if (transactionStates.equals(TransactionStates.REFUND_APPROVED)) {
				purchaseOrder.getPayment().setMaxRefundDate(new Date(0L));
				persistence.updatePayment(purchaseOrder.getPayment());
				persistence.updatePurchaseOrder(purchaseOrder);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			purchaseOrder.setStatus(TransactionStates.ERROR_IN_THE_SERVER.name());
			purchaseOrder.setDescription("Void, " + TransactionStates.ERROR_IN_THE_SERVER.getName());
		}
		return purchaseOrder;
	}

}
