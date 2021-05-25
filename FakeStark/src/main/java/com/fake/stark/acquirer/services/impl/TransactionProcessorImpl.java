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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("transactionProcessorImpl")
public class TransactionProcessorImpl implements TransactionProcessor {

	private final Persistence persistence;
	private final AuthorizationValidator authorizationValidator;
	private final CaptureValidator captureValidator;
	private final VoidValidator voidValidator;
	private final RefundValidator refundValidator;

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

	@Override public PurchaseOrder processAuthorizationTransaction(final PurchaseOrder purchaseOrder) {

		try {
			var transactionStates = authorizationValidator.validateAuthorization(purchaseOrder);
			purchaseOrder.setStatus(transactionStates.name());
			purchaseOrder.setDescription(transactionStates.getName());
			if (transactionStates.equals(TransactionStates.AUTHORIZATION_APPROVED)) {
				persistence.insertPayment(purchaseOrder.getPayment());
				persistence.insertPurchaseOrder(purchaseOrder);
			}
		} catch (Exception e) {
			purchaseOrder.setStatus(TransactionStates.ERROR_IN_THE_SERVER.name());
			purchaseOrder.setDescription("Authorization, " + TransactionStates.ERROR_IN_THE_SERVER.getName());
		}
		return purchaseOrder;
	}

	@Override public PurchaseOrder processCaptureTransaction(final PurchaseOrder purchaseOrder) {

		try {
			var transactionStates = captureValidator.validateCapture(purchaseOrder);
			purchaseOrder.setStatus(transactionStates.name());
			purchaseOrder.setDescription(transactionStates.getName());
			if (transactionStates.equals(TransactionStates.CAPTURE_APPROVED)) {
				persistence.updatePayment(purchaseOrder.getPayment());
				persistence.updatePurchaseOrder(purchaseOrder);
			}
		} catch (Exception e) {
			purchaseOrder.setStatus(TransactionStates.ERROR_IN_THE_SERVER.name());
			purchaseOrder.setDescription("Capture, " + TransactionStates.ERROR_IN_THE_SERVER.getName());
		}
		return purchaseOrder;
	}

	@Override public PurchaseOrder processVoidTransaction(final PurchaseOrder purchaseOrder) {

		try {
			var transactionStates = voidValidator.validateVoid(purchaseOrder);
			purchaseOrder.setStatus(transactionStates.name());
			purchaseOrder.setDescription(transactionStates.getName());
			if (transactionStates.equals(TransactionStates.VOID_APPROVED)) {
				purchaseOrder.getPayment().setMaxRefundDate(new Date(0L));
				persistence.updatePayment(purchaseOrder.getPayment());
				persistence.updatePurchaseOrder(purchaseOrder);
			}
		} catch (Exception e) {
			purchaseOrder.setStatus(TransactionStates.ERROR_IN_THE_SERVER.name());
			purchaseOrder.setDescription("Void, " + TransactionStates.ERROR_IN_THE_SERVER.getName());
		}
		return purchaseOrder;
	}

	@Override public PurchaseOrder processRefundTransaction(final PurchaseOrder purchaseOrder) {

		try {
			var transactionStates = refundValidator.validateRefund(purchaseOrder);
			purchaseOrder.setStatus(transactionStates.name());
			purchaseOrder.setDescription(transactionStates.getName());
			if (transactionStates.equals(TransactionStates.REFUND_APPROVED)) {
				purchaseOrder.getPayment().setMaxRefundDate(new Date(0L));
				persistence.updatePayment(purchaseOrder.getPayment());
				persistence.updatePurchaseOrder(purchaseOrder);
			}
		} catch (Exception e) {
			purchaseOrder.setStatus(TransactionStates.ERROR_IN_THE_SERVER.name());
			purchaseOrder.setDescription("Void, " + TransactionStates.ERROR_IN_THE_SERVER.getName());
		}
		return purchaseOrder;
	}

}
