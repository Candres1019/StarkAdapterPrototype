package com.wesmart.stark.adapter.application.in.usecaseproviders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureResponse;
import org.springframework.stereotype.Service;

/**
 * Defines the structure for a capture
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public interface Capture {

	/**
	 * Make a capture an retrieve the answer to it
	 *
	 * @param captureMessage - Capture message to be send to the service.
	 * @param creditCardJson - Credit card information to be send to the service.
	 * @return - The authorization response made by the service.
	 * @throws JsonProcessingException - JsonProcessingException
	 */
	CaptureResponse doCapture(CaptureMessage captureMessage, JsonNode creditCardJson) throws JsonProcessingException;
}
