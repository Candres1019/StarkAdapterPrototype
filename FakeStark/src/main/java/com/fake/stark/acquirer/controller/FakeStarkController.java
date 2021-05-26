package com.fake.stark.acquirer.controller;

import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.services.TransactionProcessor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fake Stark Rest Controller
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Log4j2
@RestController
public class FakeStarkController {

	private final TransactionProcessor transactionProcessor;

	/**
	 * Instantiates a new FakeStarkController service
	 *
	 * @param transactionProcessor - The transaction processor service for processing purchase orders
	 */
	@Autowired
	public FakeStarkController(@Qualifier("transactionProcessorImpl") TransactionProcessor transactionProcessor) {

		this.transactionProcessor = transactionProcessor;
	}

	/**
	 * Post Method for an authorization
	 *
	 * @param purchaseOrder - Purchase Order of type authorization
	 * @return - PurchaseOrder after been processed
	 */
	@PostMapping(value = "/authorization")
	public ResponseEntity<PurchaseOrder> doAuthorization(@RequestBody PurchaseOrder purchaseOrder) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(transactionProcessor.processAuthorizationTransaction(purchaseOrder));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/**
	 * Post Method for an capture
	 *
	 * @param purchaseOrder - Purchase Order of type capture
	 * @return - PurchaseOrder after been processed
	 */
	@PostMapping(value = "/capture")
	public ResponseEntity<PurchaseOrder> doCapture(@RequestBody PurchaseOrder purchaseOrder) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(transactionProcessor.processCaptureTransaction(purchaseOrder));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/**
	 * Post Method for an void
	 *
	 * @param purchaseOrder - Purchase Order of type void
	 * @return - PurchaseOrder after been processed
	 */
	@PostMapping(value = "/void")
	public ResponseEntity<PurchaseOrder> doVoid(@RequestBody PurchaseOrder purchaseOrder) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(transactionProcessor.processVoidTransaction(purchaseOrder));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/**
	 * Post Method for an refund
	 *
	 * @param purchaseOrder - Purchase Order of type refund
	 * @return - PurchaseOrder after been processed
	 */
	@PostMapping(value = "/refund")
	public ResponseEntity<PurchaseOrder> doRefund(@RequestBody PurchaseOrder purchaseOrder) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(transactionProcessor.processRefundTransaction(purchaseOrder));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
