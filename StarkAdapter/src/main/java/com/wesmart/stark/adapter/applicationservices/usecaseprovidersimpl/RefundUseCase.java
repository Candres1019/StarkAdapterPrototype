package com.wesmart.stark.adapter.applicationservices.usecaseprovidersimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesmart.stark.adapter.application.usecaseproviders.Refund;
import com.wesmart.stark.adapter.applicationservices.entities.RefundMessage;
import com.wesmart.stark.adapter.applicationservices.entities.RefundResponse;
import com.wesmart.stark.adapter.infraestructure.port.out.webserviceclient.StarkRestClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RefundUseCase implements Refund {

	StarkRestClient starkRestClient;

	@Autowired
	public RefundUseCase(StarkRestClient starkRestClient) {

		this.starkRestClient = starkRestClient;
	}

	@Override public RefundResponse doRefund(final RefundMessage refundMessage, final JsonNode creditCardJson)
			throws JsonProcessingException {

		var refundMessageSerialized = new ObjectMapper().writeValueAsString(refundMessage);
		var refundMessageJson = new JSONObject(refundMessageSerialized);
		refundMessageJson.getJSONObject("payment").getJSONObject("creditCard")
		                 .put("number", creditCardJson.get("number").asText());
		var refundResponseJson = starkRestClient.doRefund(refundMessageJson);
		var refundResponse = new ObjectMapper().readValue(refundResponseJson.toString(), RefundResponse.class);
		refundResponse.setCorrelationId(refundMessage.getCorrelationId());
		refundResponse.setCaptureTraceabilityId(refundMessage.getCaptureTraceabilityId());
		return refundResponse;
	}
}
