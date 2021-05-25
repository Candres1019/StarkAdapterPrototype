package com.fake.stark.acquirer.utils.jsonmappers;

import java.io.IOException;

import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class PurchaseOrderSerializer extends StdSerializer<PurchaseOrder> {

	private static final String ID_FIELD = "id";
	private static final String DAYS_UNTIL_EXPIRATION = "daysUntilExpiration";

	public PurchaseOrderSerializer() {

		this(null);
	}

	public PurchaseOrderSerializer(Class<PurchaseOrder> t) {

		super(t);
	}

	@Override
	public void serialize(final PurchaseOrder purchaseOrder, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider)
			throws IOException {

		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField(ID_FIELD, purchaseOrder.getId());
		if (purchaseOrder.getStatus().contains("AUTHORIZATION")) {
			jsonGenerator.writeStringField("authorizationCode", "AUTH" + System.currentTimeMillis());
			jsonGenerator.writeStringField(DAYS_UNTIL_EXPIRATION, "None");
		} else if (purchaseOrder.getStatus().contains("CAPTURE")) {
			jsonGenerator.writeStringField("captureCode", "CAPT" + System.currentTimeMillis());
			jsonGenerator.writeStringField(DAYS_UNTIL_EXPIRATION,
										   String.valueOf(purchaseOrder.getPayment().getMaxRefundDate().getTime()));
		} else if (purchaseOrder.getStatus().contains("REFUND")) {
			jsonGenerator.writeStringField("refundCode", "REND" + System.currentTimeMillis());
			jsonGenerator.writeStringField(DAYS_UNTIL_EXPIRATION, "None");
		} else {
			jsonGenerator.writeStringField("voidCode", "VOID" + System.currentTimeMillis());
			jsonGenerator.writeStringField(DAYS_UNTIL_EXPIRATION, "None");
		}
		if (!purchaseOrder.getDescription().contains("Rejected") && !purchaseOrder.getDescription().contains("Error")) {
			jsonGenerator.writeStringField("errorMessage", "None");
		} else {
			jsonGenerator.writeStringField("errorMessage", purchaseOrder.getDescription());
		}
		jsonGenerator.writeStringField("status", purchaseOrder.getStatus());
		jsonGenerator.writeStringField("message", purchaseOrder.getDescription());
		jsonGenerator.writeEndObject();
	}

}
