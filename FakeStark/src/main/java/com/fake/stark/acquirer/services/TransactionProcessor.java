package com.fake.stark.acquirer.services;

import com.fake.stark.acquirer.entities.PurchaseOrder;
import org.springframework.stereotype.Service;

@Service
public interface TransactionProcessor {

	PurchaseOrder processAuthorizationTransaction(PurchaseOrder purchaseOrder);

	PurchaseOrder processCaptureTransaction(PurchaseOrder purchaseOrder);

	PurchaseOrder processVoidTransaction(PurchaseOrder purchaseOrder);

	PurchaseOrder processRefundTransaction(PurchaseOrder purchaseOrder);

}
