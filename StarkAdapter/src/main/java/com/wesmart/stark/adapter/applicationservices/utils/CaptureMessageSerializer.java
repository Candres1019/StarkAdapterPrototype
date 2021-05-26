package com.wesmart.stark.adapter.applicationservices.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureMessage;

public class CaptureMessageSerializer extends StdSerializer<CaptureMessage> {

	public CaptureMessageSerializer() {

		this(null);
	}

	public CaptureMessageSerializer(Class<CaptureMessage> t) {

		super(t);
	}

	@Override public void serialize(final CaptureMessage captureMessage, final JsonGenerator jsonGenerator,
	                                final SerializerProvider serializerProvider) throws IOException {

		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField("id", captureMessage.getTransaction().getId().toString());
		jsonGenerator.writeStringField("status", captureMessage.getTransaction().getStatus());
		jsonGenerator.writeStringField("description", captureMessage.getTransaction().getPaymentOrder().getDescription());
		jsonGenerator.writeStringField("shippingAddress", captureMessage.getTransaction().getPaymentOrder().getBillingAddress());
		jsonGenerator.writeObjectFieldStart("payment");
		jsonGenerator.writeObjectField("id", captureMessage.getTransaction().getPaymentOrder().getId().toString());
		jsonGenerator.writeObjectField("amount", captureMessage.getTransaction().getTotal());
		jsonGenerator.writeObjectField("currency", captureMessage.getTransaction().getCurrency());
		jsonGenerator.writeObjectField("maxRefundDate", "null");
		jsonGenerator.writeObjectFieldStart("creditCard");
		jsonGenerator.writeObjectField("type", captureMessage.getCreditCard().getType());
		jsonGenerator.writeObjectField("expirationDate", captureMessage.getCreditCard().getExpirationDate());
		jsonGenerator.writeEndObject();
		jsonGenerator.writeEndObject();
		jsonGenerator.writeEndObject();
	}
}
