package com.fake.stark.acquirer.utils.validators;

import com.fake.stark.acquirer.entities.CreditCard;
import org.springframework.stereotype.Service;

/**
 * Defines the structure for an CreditCardValidator
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public interface CreditCardValidator {

	/**
	 * Check the validity of a credit card that is for an authorization.
	 *
	 * @param creditCard    - The credit card that has been received in the request.
	 * @param oldCreditCard - The credit card that his in the records.
	 * @return - If a credit card is valid or not.
	 */
	Boolean validateCreditCardForAuthorization(CreditCard creditCard, CreditCard oldCreditCard);

	/**
	 * Check the validity of a credit card that is for an capture
	 *
	 * @param creditCard        - The credit card that has been received in the request.
	 * @param oldCreditCard     - The credit card that his in the records.
	 * @param paymentCreditCard - The credit card that is in the payment records.
	 * @return - If a credit card is valid or not.
	 */
	Boolean validateCreditCardForCapture(CreditCard creditCard, CreditCard oldCreditCard, CreditCard paymentCreditCard);

	/**
	 * Check the validity of a credit card that is for an void
	 *
	 * @param creditCard        - The credit card that has been received in the request.
	 * @param oldCreditCard     - The credit card that his in the records.
	 * @param paymentCreditCard - The credit card that is in the payment records.
	 * @return - If a credit card is valid or not.
	 */
	Boolean validateCreditCardForVoid(CreditCard creditCard, CreditCard oldCreditCard, CreditCard paymentCreditCard);

	/**
	 * Check the validity of a credit card that is for an refund
	 *
	 * @param creditCard        - The credit card that has been received in the request.
	 * @param oldCreditCard     - The credit card that his in the records.
	 * @param paymentCreditCard - The credit card that is in the payment records.
	 * @return - If a credit card is valid or not.
	 */
	Boolean validateCreditCardForRefund(CreditCard creditCard, CreditCard oldCreditCard,
	                                    CreditCard paymentCreditCard);
}
