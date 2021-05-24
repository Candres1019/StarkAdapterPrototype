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
	public ResponseEntity<?> doAuthorization(@RequestBody PurchaseOrder purchaseOrder) {

		try {
			return new ResponseEntity<>(transactionProcessor.processAuthorizationTransaction(purchaseOrder), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/capture")
	public ResponseEntity<?> doCapture(@RequestBody PurchaseOrder purchaseOrder) {

		try {
			return new ResponseEntity<>(transactionProcessor.processCaptureTransaction(purchaseOrder), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/all")
	public ResponseEntity<?> ddsadada() {

		try {
			return new ResponseEntity<>(transactionProcessor.processAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
