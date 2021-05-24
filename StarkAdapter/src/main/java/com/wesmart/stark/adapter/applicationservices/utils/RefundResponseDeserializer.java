package com.wesmart.stark.adapter.applicationservices.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.wesmart.stark.adapter.applicationservices.entities.RefundResponse;

public class RefundResponseDeserializer extends StdDeserializer<RefundResponse> {

	public RefundResponseDeserializer() {

		this(null);
	}

	public RefundResponseDeserializer(Class<?> vc) {

		super(vc);
	}

	@Override public RefundResponse deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext)
			throws IOException {

		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		String refundCode = node.get("refundCode").asText();
		String responseCode = node.get("status").asText();
		String errorMessage = node.get("errorMessage").asText();
		String message = node.get("message").asText();
		return new RefundResponse(refundCode, responseCode, errorMessage, message);
	}
}
