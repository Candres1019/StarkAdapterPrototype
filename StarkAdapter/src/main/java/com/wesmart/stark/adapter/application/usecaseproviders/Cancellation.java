package com.wesmart.stark.adapter.application.usecaseproviders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationResponse;
import org.springframework.stereotype.Service;

/**
 * Defines the structure for a cancellation
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public interface Cancellation {

	/**
	 * Make a cancellation and retrieve the answer to it
	 *
	 * @param cancellationMessage - Cancellation message to be send to the service.
	 * @param creditCardJson      - Credit card information to be send to the service.
	 * @return - The cancellation response made by the service.
	 * @throws JsonProcessingException - JsonProcessingException
	 */
	CancellationResponse doCancellation(CancellationMessage cancellationMessage, JsonNode creditCardJson) throws JsonProcessingException;
}
