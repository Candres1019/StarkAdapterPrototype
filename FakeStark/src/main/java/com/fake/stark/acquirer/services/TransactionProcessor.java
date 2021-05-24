package com.fake.stark.acquirer.services;

import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public interface TransactionProcessor {

	String processAuthorizationTransaction(PurchaseOrder purchaseOrder) throws JsonProcessingException;

	String processCaptureTransaction(PurchaseOrder purchaseOrder) throws JsonProcessingException;

	Object processAll();
}
