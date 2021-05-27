package com.wesmart.stark.adapter.applicationservices.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationResponse;

/**
 * Defines the form to map a JSONObject to an AuthorizationResponse
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
public class AuthorizationResponseDeserializer extends StdDeserializer<AuthorizationResponse> {

	/**
	 * The default constructor of a StdDeserializer
	 */
	public AuthorizationResponseDeserializer() {

		this(null);
	}

	/**
	 * The default constructor of a StdDeserializer for an Object
	 *
	 * @param vc - Class of type Object
	 */
	public AuthorizationResponseDeserializer(Class<?> vc) {

		super(vc);
	}

	/**
	 * Defines the way to map(deserialize) a JSONObject to an AuthorizationResponse
	 *
	 * @param jsonParser             - JsonParser
	 * @param deserializationContext - DeserializationContext
	 * @return - The AuthorizationResponse Object after the deserialization of the received JSON
	 * @throws IOException - IOException
	 */
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
