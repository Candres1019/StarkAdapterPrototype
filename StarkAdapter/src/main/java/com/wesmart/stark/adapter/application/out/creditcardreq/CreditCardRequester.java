package com.wesmart.stark.adapter.application.out.creditcardreq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

/**
 * Defines the structure for an uts requester
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public interface CreditCardRequester {

	/**
	 * Make a petition to the uts app and retrieve the information of a credit card
	 *
	 * @param token - The token of the credit card in order to search it in the records
	 * @return - JsonNode with the information obtained in the uts app
	 * @throws JsonProcessingException - JsonProcessingException
	 */
	JsonNode getCreditCardInfo(String token) throws JsonProcessingException;

}
