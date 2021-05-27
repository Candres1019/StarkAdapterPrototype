package com.wesmart.stark.adapter.applicationservices.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationMessage;

/**
 * Defines the form to map a CancellationMessage to a JSONObject
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
public class CancellationMessageSerializer extends StdSerializer<CancellationMessage> {

	/**
	 * The default constructor of a StdSerializer
	 */
	public CancellationMessageSerializer() {

		this(null);
	}

	/**
	 * The default constructor of a StdSerializer for a CancellationMessage Object
	 *
	 * @param t - Class of type CancellationMessage
	 */
	public CancellationMessageSerializer(Class<CancellationMessage> t) {

		super(t);
	}

	/**
	 * Defines the way to map(serialize) a CancellationMessage
	 *
	 * @param cancellationMessage - CancellationMessage to serialize
	 * @param jsonGenerator       - JsonGenerator
	 * @param serializerProvider  - SerializerProvider
	 * @throws IOException - IOException
	 */
	@Override public void serialize(final CancellationMessage cancellationMessage, final JsonGenerator jsonGenerator,
	                                final SerializerProvider serializerProvider) throws IOException {

		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField("id", cancellationMessage.getTransaction().getId().toString());
		jsonGenerator.writeStringField("status", cancellationMessage.getTransaction().getStatus());
		jsonGenerator.writeStringField("description", cancellationMessage.getTransaction().getPaymentOrder().getDescription());
		jsonGenerator.writeStringField("shippingAddress", cancellationMessage.getTransaction().getPaymentOrder().getBillingAddress());
		jsonGenerator.writeObjectFieldStart("payment");
		jsonGenerator.writeObjectField("id", cancellationMessage.getTransaction().getPaymentOrder().getId().toString());
		jsonGenerator.writeObjectField("amount", cancellationMessage.getTransaction().getTotal());
		jsonGenerator.writeObjectField("currency", cancellationMessage.getTransaction().getCurrency());
		jsonGenerator.writeObjectField("maxRefundDate", "");
		jsonGenerator.writeObjectFieldStart("creditCard");
		jsonGenerator.writeObjectField("type", cancellationMessage.getCreditCard().getType());
		jsonGenerator.writeObjectField("expirationDate", cancellationMessage.getCreditCard().getExpirationDate());
		jsonGenerator.writeEndObject();
		jsonGenerator.writeEndObject();
		jsonGenerator.writeEndObject();
	}
}
