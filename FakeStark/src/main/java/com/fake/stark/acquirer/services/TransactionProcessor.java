package com.fake.stark.acquirer.services;

import com.fake.stark.acquirer.entities.PurchaseOrder;
import org.springframework.stereotype.Service;

/**
 * Defines the structure for an transaction processor
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public interface TransactionProcessor {

	/**
	 * Process an Transaction of type Authorization
	 *
	 * @param purchaseOrder - The purchase order to be processed
	 * @return The Purchase Order after been processed
	 */
	PurchaseOrder processAuthorizationTransaction(PurchaseOrder purchaseOrder);

	/**
	 * Process an Transaction of type Capture
	 *
	 * @param purchaseOrder - The purchase order to be processed
	 * @return The Purchase Order after been processed
	 */
	PurchaseOrder processCaptureTransaction(PurchaseOrder purchaseOrder);

	/**
	 * Process an Transaction of type Void
	 *
	 * @param purchaseOrder - The purchase order to be processed
	 * @return The Purchase Order after been processed
	 */
	PurchaseOrder processVoidTransaction(PurchaseOrder purchaseOrder);

	/**
	 * Process an Transaction of type Refund
	 *
	 * @param purchaseOrder - The purchase order to be processed
	 * @return The Purchase Order after been processed
	 */
	PurchaseOrder processRefundTransaction(PurchaseOrder purchaseOrder);

}
