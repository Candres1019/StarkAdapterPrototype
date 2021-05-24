package com.fake.stark.acquirer.services;

public enum TransactionStates {

	AUTHORIZATION_APPROVED("Authorization Approved"),
	AUTHORIZATION_REJECTED_INVALID_USER("Authorization Rejected, The User is not in the system."),
	AUTHORIZATION_REJECTED_CREDIT_CARD_INVALID("Authorization Rejected, Credit Card Invalid."),
	AUTHORIZATION_ALREADY_PROCESSED("Authorization Already Processed."),
	CAPTURE_APPROVED("Capture Approved, Transaction Completed."),
	CAPTURE_REJECTED_INVALID_ID("Capture Rejected, The ID of the Transaction is not in the records."),
	CAPTURE_REJECTED_CREDIT_CARD_INVALID("Capture Rejected, Credit Card Invalid."),
	CAPTURE_REJECTED_PAYMENT_ID_INVALID("Capture Rejected, The ID of the payment is not in the records."),
	CAPTURE_ALREADY_PROCESSED("Capture Already Processed."),
	ERROR_IN_THE_SERVER("Error in the server.")
	;

	private final String name;

	TransactionStates(final String name) {
		this.name = name;
	}

	public String getName() {

		return name;
	}
}
