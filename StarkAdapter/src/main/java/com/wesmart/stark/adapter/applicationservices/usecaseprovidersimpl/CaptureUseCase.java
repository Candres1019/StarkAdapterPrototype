package com.wesmart.stark.adapter.applicationservices.usecaseprovidersimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesmart.stark.adapter.application.usecaseproviders.Capture;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureResponse;
import com.wesmart.stark.adapter.infraestructure.port.out.webserviceclient.StarkRestClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CaptureUseCase implements Capture {

	StarkRestClient starkRestClient;

	@Autowired
	public CaptureUseCase(StarkRestClient starkRestClient) {

		this.starkRestClient = starkRestClient;
	}

	@Override public CaptureResponse doCapture(final CaptureMessage captureMessage, final JsonNode creditCardJson) {

		try {
			var captureMessageSerialized = new ObjectMapper().writeValueAsString(captureMessage);
			var captureMessageJson = new JSONObject(captureMessageSerialized);
			captureMessageJson.getJSONObject("payment").getJSONObject("creditCard")
							  .put("number", creditCardJson.get("number").asText());
			var captureResponseJson = starkRestClient.doCapture(captureMessageJson);
			var captureResponse = new ObjectMapper().readValue(captureResponseJson.toString(), CaptureResponse.class);
			captureResponse.setCorrelationId(captureMessage.getCorrelationId());
			captureResponse.setCaptureId(captureMessage.getCaptureId());
			captureResponse.setAuthorizationTraceabilityId(captureMessage.getAuthorizationTraceabilityId());
			captureResponse.setAuthorizationCode(captureMessage.getAuthorizationCode());
			return captureResponse;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
