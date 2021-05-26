package com.wesmart.stark.adapter.applicationservices.usecaseprovidersimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesmart.stark.adapter.application.usecaseproviders.Cancellation;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationResponse;
import com.wesmart.stark.adapter.infraestructure.port.out.webserviceclient.StarkRestClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CancellationUseCase implements Cancellation {

	StarkRestClient starkRestClient;

	@Autowired
	public CancellationUseCase(StarkRestClient starkRestClient) {

		this.starkRestClient = starkRestClient;
	}

	@Override public CancellationResponse doCancellation(final CancellationMessage cancellationMessage, final JsonNode creditCardJson)
			throws JsonProcessingException {

		var cancellationMessageSerialized = new ObjectMapper().writeValueAsString(cancellationMessage);
		var cancellationMessageJson = new JSONObject(cancellationMessageSerialized);
		cancellationMessageJson.getJSONObject("payment").getJSONObject("creditCard")
		                       .put("number", creditCardJson.get("number").asText());
		var cancellationResponseJSON = starkRestClient.doCancellation(cancellationMessageJson);
		var cancellationResponse = new ObjectMapper().readValue(cancellationResponseJSON.toString(), CancellationResponse.class);
		cancellationResponse.setCorrelationId(cancellationMessage.getCorrelationId());
		cancellationResponse.setAuthorizationTraceabilityId(cancellationMessage.getAuthorizationTraceabilityId());
		return cancellationResponse;
	}
}
