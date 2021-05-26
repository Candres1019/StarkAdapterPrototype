package com.wesmart.stark.adapter.applicationservices.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationMessage;

public class CancellationMessageSerializer extends StdSerializer<CancellationMessage> {

	public CancellationMessageSerializer() {

		this(null);
	}

	public CancellationMessageSerializer(Class<CancellationMessage> t) {

		super(t);
	}

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
