package com.wesmart.stark.adapter.application.usecaseproviders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureResponse;
import org.springframework.stereotype.Service;

@Service
public interface Capture {

	CaptureResponse doCapture(CaptureMessage captureMessage, JsonNode creditCardJson) throws JsonProcessingException;
}
