package com.fake.stark.acquirer.utils.validators.impl;

import com.fake.stark.acquirer.entities.CreditCard;
import com.fake.stark.acquirer.utils.validators.CreditCardValidator;
import org.springframework.stereotype.Component;

/**
 * Defines the CreditCardValidator implementation
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Component("creditCardValidatorImpl")
public class CreditCardValidatorImpl implements CreditCardValidator {

	/**
	 * Check the validity of a credit card that is for an authorization.
	 *
	 * @param creditCard    - The credit card that has been received in the request.
	 * @param oldCreditCard - The credit card that his in the records.
	 * @return - If a credit card is valid or not.
	 */
	@Override public Boolean validateCreditCardForAuthorization(final CreditCard creditCard, final CreditCard oldCreditCard) {

		if (creditCard == null) {
			return true;
		} else if (!creditCard.getNumber().equals(oldCreditCard.getNumber())) {
			return true;
		} else {
			return creditCard.getExpirationDate().getTime() < System.currentTimeMillis();
		}
	}

	/**
	 * Check the validity of a credit card that is for an capture
	 *
	 * @param creditCard        - The credit card that has been received in the request.
	 * @param oldCreditCard     - The credit card that his in the records.
	 * @param paymentCreditCard - The credit card that is in the payment records.
	 * @return - If a credit card is valid or not.
	 */
	@Override public Boolean validateCreditCardForCapture(final CreditCard creditCard, final CreditCard oldCreditCard,
	                                                      final CreditCard paymentCreditCard) {

		return validateCaptureVoidRefundCreditCard(creditCard, oldCreditCard, paymentCreditCard);
	}

	/**
	 * Check the validity of a credit card that is for an void
	 *
	 * @param creditCard        - The credit card that has been received in the request.
	 * @param oldCreditCard     - The credit card that his in the records.
	 * @param paymentCreditCard - The credit card that is in the payment records.
	 * @return - If a credit card is valid or not.
	 */
	@Override public Boolean validateCreditCardForVoid(final CreditCard creditCard, final CreditCard oldCreditCard,
	                                                   final CreditCard paymentCreditCard) {

		return validateCaptureVoidRefundCreditCard(creditCard, oldCreditCard, paymentCreditCard);
	}

	/**
	 * Check the validity of a credit card that is for an refund
	 *
	 * @param creditCard        - The credit card that has been received in the request.
	 * @param oldCreditCard     - The credit card that his in the records.
	 * @param paymentCreditCard - The credit card that is in the payment records.
	 * @return - If a credit card is valid or not.
	 */
	@Override public Boolean validateCreditCardForRefund(final CreditCard creditCard, final CreditCard oldCreditCard,
	                                                     final CreditCard paymentCreditCard) {

		return validateCaptureVoidRefundCreditCard(creditCard, oldCreditCard, paymentCreditCard);
	}

	/**
	 * Check the validity of a credit card that is the same for Void, Capture and refund
	 *
	 * @param creditCard        - The credit card that has been received in the request.
	 * @param oldCreditCard     - The credit card that his in the records.
	 * @param paymentCreditCard - The credit card that is in the payment records.
	 * @return - If a credit card is valid or not.
	 */
	private Boolean validateCaptureVoidRefundCreditCard(final CreditCard creditCard, final CreditCard oldCreditCard,
	                                                    final CreditCard paymentCreditCard) {

		if (creditCard == null) {
			return true;
		} else if (!creditCard.getNumber().equals(oldCreditCard.getNumber())) {
			return true;
		} else if (!paymentCreditCard.getNumber().equals(oldCreditCard.getNumber())) {
			return true;
		} else {
			return creditCard.getExpirationDate().getTime() < System.currentTimeMillis();
		}
	}
}
