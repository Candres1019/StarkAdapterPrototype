package com.wesmart.stark.adapter.application.usecaseproviders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.wesmart.stark.adapter.applicationservices.entities.RefundMessage;
import com.wesmart.stark.adapter.applicationservices.entities.RefundResponse;
import org.springframework.stereotype.Service;

/**
 * Defines the structure for a refund
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public interface Refund {

	/**
	 * Make an refund an retrieve the answer to it
	 *
	 * @param refundMessage  - Refund message to be send to the service.
	 * @param creditCardJson - Credit card information to be send to the service.
	 * @return - The authorization response made by the service.
	 * @throws JsonProcessingException - JsonProcessingException
	 */
	RefundResponse doRefund(RefundMessage refundMessage, JsonNode creditCardJson) throws JsonProcessingException;

}
