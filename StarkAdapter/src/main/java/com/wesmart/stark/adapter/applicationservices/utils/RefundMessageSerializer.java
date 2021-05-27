package com.wesmart.stark.adapter.applicationservices.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.wesmart.stark.adapter.applicationservices.entities.RefundMessage;

/**
 * Defines the form to map a RefundMessage to a JSONObject
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
public class RefundMessageSerializer extends StdSerializer<RefundMessage> {

	/**
	 * The default constructor of a StdSerializer
	 */
	public RefundMessageSerializer() {

		this(null);
	}

	/**
	 * The default constructor of a StdSerializer for a RefundMessage Object
	 *
	 * @param t - Class of type RefundMessage
	 */
	public RefundMessageSerializer(Class<RefundMessage> t) {

		super(t);
	}

	/**
	 * Defines the way to map(serialize) a RefundMessage
	 *
	 * @param refundMessage - RefundMessage to serialize
	 * @param jsonGenerator        - JsonGenerator
	 * @param serializerProvider   - SerializerProvider
	 * @throws IOException - IOException
	 */
	@Override
	public void serialize(final RefundMessage refundMessage, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider)
			throws IOException {

		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField("id", refundMessage.getTransaction().getId().toString());
		jsonGenerator.writeStringField("status", refundMessage.getTransaction().getStatus());
		jsonGenerator.writeStringField("description", refundMessage.getTransaction().getPaymentOrder().getDescription());
		jsonGenerator.writeStringField("shippingAddress", refundMessage.getTransaction().getPaymentOrder().getBillingAddress());
		jsonGenerator.writeObjectFieldStart("payment");
		jsonGenerator.writeObjectField("id", refundMessage.getTransaction().getPaymentOrder().getId().toString());
		jsonGenerator.writeObjectField("amount", refundMessage.getTransaction().getTotal());
		jsonGenerator.writeObjectField("currency", refundMessage.getTransaction().getCurrency());
		jsonGenerator.writeObjectField("maxRefundDate", refundMessage.getMaxRefundDate());
		jsonGenerator.writeObjectFieldStart("creditCard");
		jsonGenerator.writeObjectField("type", refundMessage.getCreditCard().getType());
		jsonGenerator.writeObjectField("expirationDate", refundMessage.getCreditCard().getExpirationDate());
		jsonGenerator.writeEndObject();
		jsonGenerator.writeEndObject();
		jsonGenerator.writeEndObject();
	}
}
