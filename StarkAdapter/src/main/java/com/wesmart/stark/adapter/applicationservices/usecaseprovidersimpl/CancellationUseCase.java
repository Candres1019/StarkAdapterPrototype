package com.wesmart.stark.adapter.applicationservices.usecaseprovidersimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesmart.stark.adapter.application.in.usecaseproviders.Cancellation;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationResponse;
import com.wesmart.stark.adapter.infraestructure.port.out.webserviceclient.StarkRestClient;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Defines the Cancellation implementation
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CancellationUseCase implements Cancellation {

	private final StarkRestClient starkRestClient;

	/**
	 * Make a cancellation and retrieve the answer to it
	 *
	 * @param cancellationMessage - Cancellation message to be send to the service.
	 * @param creditCardJson      - Credit card information to be send to the service.
	 * @return - The cancellation response made by the service.
	 * @throws JsonProcessingException - JsonProcessingException
	 */
	@Override public CancellationResponse doCancellation(final CancellationMessage cancellationMessage, final JsonNode creditCardJson)
			throws JsonProcessingException {

		String cancellationMessageSerialized = new ObjectMapper().writeValueAsString(cancellationMessage);
		JSONObject cancellationMessageJson = new JSONObject(cancellationMessageSerialized);
		cancellationMessageJson.getJSONObject("payment").getJSONObject("creditCard")
		                       .put("number", creditCardJson.get("number").asText());
		JSONObject cancellationResponseJSON = starkRestClient.doCancellation(cancellationMessageJson);
		CancellationResponse cancellationResponse = new ObjectMapper()
				.readValue(cancellationResponseJSON.toString(), CancellationResponse.class);
		cancellationResponse.setCorrelationId(cancellationMessage.getCorrelationId());
		cancellationResponse.setAuthorizationTraceabilityId(cancellationMessage.getAuthorizationTraceabilityId());
		return cancellationResponse;
	}
}
