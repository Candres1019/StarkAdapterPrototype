package com.fake.stark.acquirer.services;

public enum TransactionStates {

	AUTHORIZATION_APPROVED("Authorization Approved"),
	AUTHORIZATION_REJECTED_INVALID_USER("Authorization Rejected, The User is not in the system."),
	AUTHORIZATION_REJECTED_CREDIT_CARD_INVALID("Authorization Rejected, Credit Card Invalid."),
	AUTHORIZATION_ALREADY_PROCESSED("Authorization: Transaction Already Processed."),

	CAPTURE_APPROVED("Capture Approved, Transaction Completed."),
	CAPTURE_REJECTED_INVALID_ID("Capture Rejected, The ID of the Transaction is not in the records."),
	CAPTURE_REJECTED_CREDIT_CARD_INVALID("Capture Rejected, Credit Card Invalid."),
	CAPTURE_REJECTED_PAYMENT_ID_INVALID("Capture Rejected, The ID of the payment is not in the records."),
	CAPTURE_ALREADY_PROCESSED_V("Capture: Void Transaction already processed."),
	CAPTURE_ALREADY_PROCESSED_R("Capture: Refund Transaction already processed."),
	CAPTURE_ALREADY_PROCESSED("Capture: Transaction Already Processed."),

	VOID_APPROVED("Void Approved, Transaction Completed."),
	VOID_REJECTED_INVALID_ID("Void Rejected, The ID of the Transaction is not in the records."),
	VOID_REJECTED_PAYMENT_ID_INVALID("Void Rejected, The ID of the payment is not in the records."),
	VOID_REJECTED_CREDIT_CARD_INVALID("Void Rejected, Credit Card Invalid."),
	VOID_ALREADY_PROCESSED_C("Void: Capture Transaction already processed."),
	VOID_ALREADY_PROCESSED_R("Void: Refund Transaction already processed."),
	VOID_ALREADY_PROCESSED("Void: Transaction Already Processed."),

	REFUND_APPROVED("Refund Approved, Transaction Completed."),
	REFUND_REJECTED_INVALID_ID("Refund Rejected, The ID of the Transaction is not in the records."),
	REFUND_REJECTED_PAYMENT_ID_INVALID("Refund Rejected, The ID of the payment is not in the records."),
	REFUND_REJECTED_CREDIT_CARD_INVALID("Refund Rejected, Credit Card Invalid."),
	REFUND_REJECTED_MAX_REFUND_DATE_EXPIRED("Refund Rejected,  The maximum time for refund has already passed"),
	REFUND_IN_AUTHORIZATION_APPROVAL("Refund: The transaction still in authorization."),
	REFUND_ALREADY_PROCESSED_V("Refund: Void transaction already processed."),
	REFUND_ALREADY_PROCESSED("Refund: Transaction Already Processed."),

	ERROR_IN_THE_SERVER("Error in the server.");

	private final String name;

	TransactionStates(final String name) {

		this.name = name;
	}

	public String getName() {

		return name;
	}
}
