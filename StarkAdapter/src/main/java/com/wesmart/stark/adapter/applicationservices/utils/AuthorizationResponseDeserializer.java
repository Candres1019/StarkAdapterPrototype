package com.wesmart.stark.adapter.applicationservices.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationResponse;

public class AuthorizationResponseDeserializer extends StdDeserializer<AuthorizationResponse> {

	public AuthorizationResponseDeserializer() {

		this(null);
	}

	public AuthorizationResponseDeserializer(Class<?> vc) {

		super(vc);
	}

	@Override public AuthorizationResponse deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext)
			throws IOException {

		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		String authorizationCode = node.get("authorizationCode").asText();
		String responseCode = node.get("status").asText();
		String errorMessage = node.get("errorMessage").asText();
		String message = node.get("message").asText();
		return new AuthorizationResponse(authorizationCode, responseCode, errorMessage, message);
	}
}
