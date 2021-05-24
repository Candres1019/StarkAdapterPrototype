package com.wesmart.stark.adapter.application.usecaseproviders;

import com.fasterxml.jackson.databind.JsonNode;
import com.wesmart.stark.adapter.applicationservices.entities.RefundMessage;
import com.wesmart.stark.adapter.applicationservices.entities.RefundResponse;
import org.springframework.stereotype.Service;

@Service
public interface Refund {

	RefundResponse doRefund(RefundMessage refundMessage, JsonNode creditCardJson);
}
