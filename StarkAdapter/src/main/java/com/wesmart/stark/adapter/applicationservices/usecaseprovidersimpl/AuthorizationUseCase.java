package com.wesmart.stark.adapter.applicationservices.usecaseprovidersimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesmart.stark.adapter.application.usecaseproviders.Authorization;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationResponse;
import com.wesmart.stark.adapter.infraestructure.port.out.webserviceclient.StarkRestClient;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Defines the Authorization implementation
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizationUseCase implements Authorization {

	private final StarkRestClient starkRestClient;

	/**
	 * Make a authorization and retrieve the answer to it
	 *
	 * @param authorizationMessage - Authorization message to be send to the service.
	 * @param creditCardJson       - Credit card information to be send to the service.
	 * @return - The authorization response made by the service.
	 * @throws JsonProcessingException - JsonProcessingException
	 */
	@Override public AuthorizationResponse doAuthorization(final AuthorizationMessage authorizationMessage, final JsonNode creditCardJson)
			throws JsonProcessingException {

		var authorizationMessageSerialized = new ObjectMapper().writeValueAsString(authorizationMessage);
		var authorizationMessageJson = new JSONObject(authorizationMessageSerialized);
		authorizationMessageJson.getJSONObject("payment").getJSONObject("creditCard")
		                        .put("number", creditCardJson.get("number").asText());
		var authorizationResponseJson = starkRestClient.doAuthorization(authorizationMessageJson);
		var authorizationResponse = new ObjectMapper().readValue(authorizationResponseJson.toString(),
		                                                         AuthorizationResponse.class);
		authorizationResponse.setTraceabilityId(authorizationMessage.getCorrelationId());
		authorizationResponse.setCorrelationId(authorizationMessage.getCorrelationId());
		return authorizationResponse;
	}
}
