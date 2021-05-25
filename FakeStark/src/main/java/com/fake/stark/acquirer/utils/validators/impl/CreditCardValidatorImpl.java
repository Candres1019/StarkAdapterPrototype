package com.fake.stark.acquirer.utils.validators.impl;

import com.fake.stark.acquirer.entities.CreditCard;
import com.fake.stark.acquirer.utils.validators.CreditCardValidator;
import org.springframework.stereotype.Component;

@Component("creditCardValidatorImpl")
public class CreditCardValidatorImpl implements CreditCardValidator {

	@Override public Boolean validateCreditCardForAuthorization(final CreditCard creditCard, final CreditCard oldCreditCard) {

		if (creditCard == null) {
			return true;
		} else if (!creditCard.getNumber().equals(oldCreditCard.getNumber())) {
			return true;
		} else {
			return creditCard.getExpirationDate().getTime() < System.currentTimeMillis();
		}
	}

	@Override
	public Boolean validateCreditCardForCapture(final CreditCard creditCard, final CreditCard oldCreditCard,
												final CreditCard paymentCreditCard) {

		return validateCaptureVoidCreditCardRefund(creditCard, oldCreditCard, paymentCreditCard);
	}

	@Override public Boolean validateCreditCardForVoid(final CreditCard creditCard, final CreditCard oldCreditCard,
													   final CreditCard paymentCreditCard) {

		return validateCaptureVoidCreditCardRefund(creditCard, oldCreditCard, paymentCreditCard);
	}

	@Override public Boolean validateCreditCardForRefund(final CreditCard creditCard, final CreditCard oldCreditCard,
														 final CreditCard paymentCreditCard) {

		return validateCaptureVoidCreditCardRefund(creditCard, oldCreditCard, paymentCreditCard);
	}

	private Boolean validateCaptureVoidCreditCardRefund(final CreditCard creditCard, final CreditCard oldCreditCard,
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
