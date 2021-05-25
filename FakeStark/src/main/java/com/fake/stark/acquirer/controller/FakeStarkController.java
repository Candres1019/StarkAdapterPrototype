package com.fake.stark.acquirer.controller;

import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.services.TransactionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeStarkController {

	private final TransactionProcessor transactionProcessor;

	@Autowired
	public FakeStarkController(@Qualifier("transactionProcessorImpl") TransactionProcessor transactionProcessor) {

		this.transactionProcessor = transactionProcessor;
	}

	@PostMapping(value = "/authorization")
	public ResponseEntity<PurchaseOrder> doAuthorization(@RequestBody PurchaseOrder purchaseOrder) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(transactionProcessor.processAuthorizationTransaction(purchaseOrder));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PostMapping(value = "/capture")
	public ResponseEntity<PurchaseOrder> doCapture(@RequestBody PurchaseOrder purchaseOrder) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(transactionProcessor.processCaptureTransaction(purchaseOrder));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PostMapping(value = "/void")
	public ResponseEntity<PurchaseOrder> doVoid(@RequestBody PurchaseOrder purchaseOrder) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(transactionProcessor.processVoidTransaction(purchaseOrder));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PostMapping(value = "/refund")
	public ResponseEntity<PurchaseOrder> doRefund(@RequestBody PurchaseOrder purchaseOrder) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(transactionProcessor.processRefundTransaction(purchaseOrder));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
