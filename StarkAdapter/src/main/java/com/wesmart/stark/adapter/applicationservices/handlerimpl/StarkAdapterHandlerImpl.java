package com.wesmart.stark.adapter.applicationservices.handlerimpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesmart.stark.adapter.application.in.handler.StarkAdapterHandler;
import com.wesmart.stark.adapter.application.in.usecaseproviders.Authorization;
import com.wesmart.stark.adapter.application.in.usecaseproviders.Cancellation;
import com.wesmart.stark.adapter.application.in.usecaseproviders.Capture;
import com.wesmart.stark.adapter.application.in.usecaseproviders.Refund;
import com.wesmart.stark.adapter.application.out.creditcardreq.CreditCardRequester;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureMessage;
import com.wesmart.stark.adapter.applicationservices.entities.RefundMessage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Defines the StarkAdapterHandler implementation
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component("starkAdapterHandlerImpl")
public class StarkAdapterHandlerImpl implements StarkAdapterHandler {

	private final CreditCardRequester utsRequester;
	private final Authorization authorization;
	private final Capture capture;
	private final Refund refund;
	private final Cancellation cancellation;

	/**
	 * Make an authorization given an authorization message
	 *
	 * @param authorizationMessage - The authorization message to be processed
	 * @return - The response of the server after processing the authorization
	 */
	@Override public String doAuthorization(final AuthorizationMessage authorizationMessage) {

		try {
			JsonNode creditCardJson = utsRequester.getCreditCardInfo(authorizationMessage.getCreditCard().getToken());
			return new ObjectMapper().writeValueAsString(authorization.doAuthorization(authorizationMessage, creditCardJson));
		} catch (Exception e) {
			log.error(e.getMessage());
			return createJsonObjectError(e, "AUTH").toString();
		}
	}

	/**
	 * Make a capture given a capture message
	 *
	 * @param captureMessage - The capture message to be processed
	 * @return - The response of the server after processing the capture
	 */
	@Override public String doCapture(final CaptureMessage captureMessage) {

		try {
			JsonNode creditCardJson = utsRequester.getCreditCardInfo(captureMessage.getCreditCard().getToken());
			return new ObjectMapper().writeValueAsString(capture.doCapture(captureMessage, creditCardJson));
		} catch (Exception e) {
			log.error(e.getMessage());
			return createJsonObjectError(e, "CAPT").toString();
		}
	}

	/**
	 * Make a refund given a capture message
	 *
	 * @param refundMessage - The capture refund to be processed
	 * @return - The response of the server after processing the refund
	 */
	@Override public String doRefund(final RefundMessage refundMessage) {

		try {
			JsonNode creditCardJson = utsRequester.getCreditCardInfo(refundMessage.getCreditCard().getToken());
			return new ObjectMapper().writeValueAsString(refund.doRefund(refundMessage, creditCardJson));
		} catch (Exception e) {
			log.error(e.getMessage());
			return createJsonObjectError(e, "REND").toString();
		}
	}

	/**
	 * Make a cancellation given a capture message
	 *
	 * @param cancellationMessage - The cancellation message to be processed
	 * @return - The response of the server after processing the cancellation
	 */
	@Override public String doCancellation(final CancellationMessage cancellationMessage) {

		try {
			JsonNode creditCardJson = utsRequester.getCreditCardInfo(cancellationMessage.getCreditCard().getToken());
			return new ObjectMapper().writeValueAsString(cancellation.doCancellation(cancellationMessage, creditCardJson));
		} catch (Exception e) {
			log.error(e.getMessage());
			return createJsonObjectError(e, "CNL").toString();
		}
	}

	/**
	 * Create a JSONObject if an error occur during the execution process
	 *
	 * @param e - Exception caught in the process
	 * @return - The JSONObject that represents the error
	 */
	private JSONObject createJsonObjectError(Exception e, String codeError) {

		JSONObject errorJson = new JSONObject();
		errorJson.put("message", "Error in the adapter, when processing the Transaction");
		errorJson.put("status", "ERROR");
		errorJson.put("transactionCode", codeError + "_ERROR");
		errorJson.put("errorMessage", e.getMessage());
		return errorJson;
	}

}
