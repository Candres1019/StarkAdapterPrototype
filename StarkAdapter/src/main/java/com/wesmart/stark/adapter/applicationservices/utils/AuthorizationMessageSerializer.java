package com.wesmart.stark.adapter.applicationservices.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationMessage;

/**
 * Defines the form to map an AuthorizationMessage to a JSONObject
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
public class AuthorizationMessageSerializer extends StdSerializer<AuthorizationMessage> {

	/**
	 * The default constructor of a StdSerializer
	 */
	public AuthorizationMessageSerializer() {

		this(null);
	}

	/**
	 * The default constructor of a StdSerializer for an AuthorizationMessage Object
	 *
	 * @param t - Class of type AuthorizationMessage
	 */
	public AuthorizationMessageSerializer(Class<AuthorizationMessage> t) {

		super(t);
	}

	/**
	 * Defines the way to map(serialize) a AuthorizationMessage
	 *
	 * @param authorizationMessage - AuthorizationMessage to serialize
	 * @param jsonGenerator        - JsonGenerator
	 * @param serializerProvider   - SerializerProvider
	 * @throws IOException - IOException
	 */
	@Override public void serialize(final AuthorizationMessage authorizationMessage, final JsonGenerator jsonGenerator,
	                                final SerializerProvider serializerProvider) throws IOException {

		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField("id", authorizationMessage.getTransaction().getId().toString());
		jsonGenerator.writeStringField("status", authorizationMessage.getTransaction().getStatus());
		jsonGenerator.writeStringField("description", authorizationMessage.getTransaction().getPaymentOrder().getDescription());
		jsonGenerator.writeStringField("shippingAddress", authorizationMessage.getTransaction().getPaymentOrder().getBillingAddress());
		jsonGenerator.writeObjectFieldStart("payment");
		jsonGenerator.writeObjectField("id", authorizationMessage.getTransaction().getPaymentOrder().getId().toString());
		jsonGenerator.writeObjectField("amount", authorizationMessage.getTransaction().getTotal());
		jsonGenerator.writeObjectField("currency", authorizationMessage.getTransaction().getCurrency());
		jsonGenerator.writeObjectField("maxRefundDate", "null");
		jsonGenerator.writeObjectFieldStart("creditCard");
		jsonGenerator.writeObjectField("type", authorizationMessage.getCreditCard().getType());
		jsonGenerator.writeObjectField("expirationDate", authorizationMessage.getCreditCard().getExpirationDate());
		jsonGenerator.writeObjectFieldStart("user");
		jsonGenerator.writeObjectField("identification", authorizationMessage.getTransaction().getPaymentOrder().getCustomer().getId());
		jsonGenerator.writeObjectField("name", authorizationMessage.getTransaction().getPaymentOrder().getCustomer().getName());
		jsonGenerator.writeObjectField("phone", authorizationMessage.getTransaction().getPaymentOrder().getCustomer().getPhone());
		jsonGenerator.writeObjectField("email", authorizationMessage.getTransaction().getPaymentOrder().getCustomer().getEmail());
		jsonGenerator.writeEndObject();
		jsonGenerator.writeEndObject();
		jsonGenerator.writeEndObject();
		jsonGenerator.writeEndObject();
	}
}
