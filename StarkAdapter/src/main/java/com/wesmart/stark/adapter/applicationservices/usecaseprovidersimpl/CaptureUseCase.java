package com.wesmart.stark.adapter.applicationservices.usecaseprovidersimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesmart.stark.adapter.application.in.usecaseproviders.Capture;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureResponse;
import com.wesmart.stark.adapter.infraestructure.port.out.webserviceclient.StarkRestClient;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Defines the Capture implementation
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CaptureUseCase implements Capture {

	private final StarkRestClient starkRestClient;

	/**
	 * Make a capture and retrieve the answer to it
	 *
	 * @param captureMessage - Capture message to be send to the service.
	 * @param creditCardJson - Credit card information to be send to the service.
	 * @return - The authorization response made by the service.
	 * @throws JsonProcessingException - JsonProcessingException
	 */
	@Override public CaptureResponse doCapture(final CaptureMessage captureMessage, final JsonNode creditCardJson)
			throws JsonProcessingException {

		String captureMessageSerialized = new ObjectMapper().writeValueAsString(captureMessage);
		JSONObject captureMessageJson = new JSONObject(captureMessageSerialized);
		captureMessageJson.getJSONObject("payment").getJSONObject("creditCard")
		                  .put("number", creditCardJson.get("number").asText());
		JSONObject captureResponseJson = starkRestClient.doCapture(captureMessageJson);
		CaptureResponse captureResponse = new ObjectMapper().readValue(captureResponseJson.toString(), CaptureResponse.class);
		captureResponse.setCorrelationId(captureMessage.getCorrelationId());
		captureResponse.setCaptureId(captureMessage.getCaptureId());
		captureResponse.setAuthorizationTraceabilityId(captureMessage.getAuthorizationTraceabilityId());
		return captureResponse;
	}
}
