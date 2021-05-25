package com.fake.stark.acquirer.utils.validators;

import com.fake.stark.acquirer.entities.CreditCard;
import org.springframework.stereotype.Service;

@Service
public interface CreditCardValidator {

	Boolean validateCreditCardForAuthorization(CreditCard creditCard, CreditCard oldCreditCard);

	Boolean validateCreditCardForCapture(CreditCard creditCard, CreditCard oldCreditCard, CreditCard paymentCreditCard);

	Boolean validateCreditCardForVoid(CreditCard creditCard, CreditCard oldCreditCard, CreditCard paymentCreditCard);

	Boolean validateCreditCardForRefund(CreditCard creditCard, CreditCard oldCreditCard,
										CreditCard paymentCreditCard);
}
