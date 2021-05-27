package com.wesmart.stark.adapter.applicationservices.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationResponse;

/**
 * Defines the form to map a JSONObject to a CancellationResponse
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
public class CancellationResponseDeserializer extends StdDeserializer<CancellationResponse> {

	/**
	 * The default constructor of a StdDeserializer
	 */
	public CancellationResponseDeserializer() {

		this(null);
	}

	/**
	 * The default constructor of a StdDeserializer for an Object
	 *
	 * @param vc - Class of type Object
	 */
	public CancellationResponseDeserializer(Class<?> vc) {

		super(vc);
	}

	/**
	 * Defines the way to map(deserialize) a JSONObject to a CancellationResponse
	 *
	 * @param jsonParser             - JsonParser
	 * @param deserializationContext - DeserializationContext
	 * @return - The CancellationResponse Object after the deserialization of the received JSON
	 * @throws IOException - IOException
	 */
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
