package com.wesmart.stark.adapter.application.usecaseproviders;

import com.fasterxml.jackson.databind.JsonNode;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationResponse;
import org.springframework.stereotype.Service;

@Service
public interface Cancellation {

	CancellationResponse doCancellation(CancellationMessage cancellationMessage, JsonNode creditCardJson);
}
