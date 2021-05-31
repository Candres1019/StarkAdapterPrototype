package com.wesmart.stark.adapter.applicationservices.usecaseprovidersimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesmart.stark.adapter.application.in.usecaseproviders.Refund;
import com.wesmart.stark.adapter.applicationservices.entities.RefundMessage;
import com.wesmart.stark.adapter.applicationservices.entities.RefundResponse;
import com.wesmart.stark.adapter.infraestructure.port.out.webserviceclient.StarkRestClient;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Defines the Refund implementation
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RefundUseCase implements Refund {

	private final StarkRestClient starkRestClient;

	/**
	 * Make a refund and retrieve the answer to it
	 *
	 * @param refundMessage  - Refund message to be send to the service.
	 * @param creditCardJson - Credit card information to be send to the service.
	 * @return - The authorization response made by the service.
	 * @throws JsonProcessingException - JsonProcessingException
	 */
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
