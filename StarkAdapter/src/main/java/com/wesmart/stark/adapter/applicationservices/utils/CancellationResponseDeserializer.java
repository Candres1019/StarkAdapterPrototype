package com.wesmart.stark.adapter.applicationservices.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationResponse;

public class CancellationResponseDeserializer extends StdDeserializer<CancellationResponse> {

	public CancellationResponseDeserializer() {

		this(null);
	}

	public CancellationResponseDeserializer(Class<?> vc) {

		super(vc);
	}

	@Override public CancellationResponse deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext)
			throws IOException {

		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		String cancellationCode = node.get("voidCode").asText();
		String responseCode = node.get("status").asText();
		String errorMessage = node.get("errorMessage").asText();
		String message = node.get("message").asText();
		return new CancellationResponse(cancellationCode, responseCode, errorMessage, message);
	}

}
