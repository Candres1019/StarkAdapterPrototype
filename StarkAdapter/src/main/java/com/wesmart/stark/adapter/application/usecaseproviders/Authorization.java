package com.wesmart.stark.adapter.application.usecaseproviders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationResponse;
import org.springframework.stereotype.Service;

/**
 * Defines the structure for an authorization
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public interface Authorization {

	/**
	 * Make an authorization and retrieve the answer to it
	 *
	 * @param authorizationMessage - Authorization message to be send to the service.
	 * @param creditCardJson       - Credit card information to be send to the service.
	 * @return - The authorization response made by the service.
	 * @throws JsonProcessingException - JsonProcessingException
	 */
	AuthorizationResponse doAuthorization(AuthorizationMessage authorizationMessage, JsonNode creditCardJson)
			throws JsonProcessingException;

}
