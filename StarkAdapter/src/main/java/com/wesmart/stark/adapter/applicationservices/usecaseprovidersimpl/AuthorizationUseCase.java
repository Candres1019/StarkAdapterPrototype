package com.wesmart.stark.adapter.applicationservices.usecaseprovidersimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesmart.stark.adapter.application.usecaseproviders.Authorization;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationResponse;
import com.wesmart.stark.adapter.infraestructure.port.out.webserviceclient.StarkRestClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationUseCase implements Authorization {

	StarkRestClient starkRestClient;

	@Autowired
	public AuthorizationUseCase(StarkRestClient starkRestClient) {

		this.starkRestClient = starkRestClient;
	}

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
