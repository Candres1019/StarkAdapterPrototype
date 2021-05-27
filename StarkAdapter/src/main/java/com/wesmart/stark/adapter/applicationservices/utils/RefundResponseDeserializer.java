package com.wesmart.stark.adapter.applicationservices.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.wesmart.stark.adapter.applicationservices.entities.RefundResponse;

/**
 * Defines the form to map a JSONObject to a RefundResponse
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
public class RefundResponseDeserializer extends StdDeserializer<RefundResponse> {

	/**
	 * The default constructor of a StdDeserializer
	 */
	public RefundResponseDeserializer() {

		this(null);
	}

	/**
	 * The default constructor of a StdDeserializer for an Object
	 *
	 * @param vc - Class of type Object
	 */
	public RefundResponseDeserializer(Class<?> vc) {

		super(vc);
	}

	/**
	 * Defines the way to map(deserialize) a JSONObject to a RefundResponse
	 *
	 * @param jsonParser             - JsonParser
	 * @param deserializationContext - DeserializationContext
	 * @return - The RefundResponse Object after the deserialization of the received JSON
	 * @throws IOException - IOException
	 */
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
