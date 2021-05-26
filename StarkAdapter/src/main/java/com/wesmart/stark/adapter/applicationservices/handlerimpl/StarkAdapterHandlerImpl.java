package com.wesmart.stark.adapter.applicationservices.handlerimpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.wesmart.stark.adapter.application.handler.StarkAdapterHandler;
import com.wesmart.stark.adapter.application.usecaseproviders.Authorization;
import com.wesmart.stark.adapter.application.usecaseproviders.Cancellation;
import com.wesmart.stark.adapter.application.usecaseproviders.Capture;
import com.wesmart.stark.adapter.application.usecaseproviders.Refund;
import com.wesmart.stark.adapter.application.uts.UtsRequester;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureMessage;
import com.wesmart.stark.adapter.applicationservices.entities.RefundMessage;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component("starkAdapterHandlerImpl")
public class StarkAdapterHandlerImpl implements StarkAdapterHandler {

	private final UtsRequester utsRequester;
	private final Authorization authorization;
	private final Capture capture;
	private final Refund refund;
	private final Cancellation cancellation;

	@Autowired
	public StarkAdapterHandlerImpl(UtsRequester utsRequester, Authorization authorization, Capture capture, Refund refund,
	                               Cancellation cancellation) {

		this.utsRequester = utsRequester;
		this.authorization = authorization;
		this.capture = capture;
		this.refund = refund;
		this.cancellation = cancellation;
	}

	@Override public String doAuthorization(final AuthorizationMessage authorizationMessage) {

		try {
			JsonNode creditCardJson = utsRequester.getCreditCardInfo(authorizationMessage.getCreditCard().getToken());
			var jsonObject = new JSONObject(authorization.doAuthorization(authorizationMessage, creditCardJson));
			return jsonObject.toString();
		} catch (Exception e) {
			log.error(e.getMessage());
			return createJsonObjectError(e).toString();
		}
	}

	@Override public String doCapture(final CaptureMessage captureMessage) {

		try {
			JsonNode creditCardJson = utsRequester.getCreditCardInfo(captureMessage.getCreditCard().getToken());
			var jsonObject = new JSONObject(capture.doCapture(captureMessage, creditCardJson));
			return jsonObject.toString();
		} catch (Exception e) {
			log.error(e.getMessage());
			return createJsonObjectError(e).toString();
		}
	}

	@Override public String doRefund(final RefundMessage refundMessage) {

		try {
			JsonNode creditCardJson = utsRequester.getCreditCardInfo(refundMessage.getCreditCard().getToken());
			var jsonObject = new JSONObject(refund.doRefund(refundMessage, creditCardJson));
			return jsonObject.toString();
		} catch (Exception e) {
			log.error(e.getMessage());
			return createJsonObjectError(e).toString();
		}
	}

	@Override public String doCancellation(final CancellationMessage cancellationMessage) {

		try {
			JsonNode creditCardJson = utsRequester.getCreditCardInfo(cancellationMessage.getCreditCard().getToken());
			var jsonObject = new JSONObject(cancellation.doCancellation(cancellationMessage, creditCardJson));
			return jsonObject.toString();
		} catch (Exception e) {
			log.error(e.getMessage());
			return createJsonObjectError(e).toString();
		}
	}

	private JSONObject createJsonObjectError(Exception e) {

		var errorJson = new JSONObject();
		errorJson.put("message", "Error in the Adapter");
		errorJson.put("status", "ERROR");
		errorJson.put("transactionCode", "ERROR");
		errorJson.put("errorMessage", e.getMessage());
		return errorJson;
	}

}
