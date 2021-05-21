package com.wesmart.stark.adapter.applicationservices.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureResponse;

public class CaptureResponseDeserializer extends StdDeserializer<CaptureResponse> {

	public CaptureResponseDeserializer() {

		this(null);
	}

	public CaptureResponseDeserializer(Class<?> vc) {

		super(vc);
	}

	@Override public CaptureResponse deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext)
			throws IOException {

		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		String authorizationCode = node.get("authorizationCode").asText();
		String responseCode = node.get("status").asText();
		String errorMessage = node.get("errorMessage").asText();
		String message = node.get("message").asText();
		return new CaptureResponse(authorizationCode, responseCode, errorMessage, message);
	}
}
