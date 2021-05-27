package com.wesmart.stark.adapter.applicationservices.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureMessage;

/**
 * Defines the form to map a CaptureMessage to a JSONObject
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
public class CaptureMessageSerializer extends StdSerializer<CaptureMessage> {

	/**
	 * The default constructor of a StdSerializer
	 */
	public CaptureMessageSerializer() {

		this(null);
	}

	/**
	 * The default constructor of a StdSerializer for a CaptureMessage Object
	 *
	 * @param t - Class of type CaptureMessage
	 */
	public CaptureMessageSerializer(Class<CaptureMessage> t) {

		super(t);
	}

	/**
	 * Defines the way to map(serialize) a CaptureMessage
	 *
	 * @param captureMessage - CaptureMessage to serialize
	 * @param jsonGenerator        - JsonGenerator
	 * @param serializerProvider   - SerializerProvider
	 * @throws IOException - IOException
	 */
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
