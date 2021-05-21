package com.wesmart.stark.adapter.applicationservices.handlerimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.wesmart.stark.adapter.application.handler.StarkAdapterHandler;
import com.wesmart.stark.adapter.application.usecaseproviders.Authorization;
import com.wesmart.stark.adapter.application.usecaseproviders.Capture;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureMessage;
import com.wesmart.stark.adapter.applicationservices.uts.UtsRequester;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("starkAdapterHandlerImpl")
public class StarkAdapterHandlerImpl implements StarkAdapterHandler {

	UtsRequester utsRequester;

	Authorization authorization;

	Capture capture;

	@Autowired
	public StarkAdapterHandlerImpl(UtsRequester utsRequester, Authorization authorization, Capture capture) {

		this.utsRequester = utsRequester;
		this.authorization = authorization;
		this.capture = capture;
	}

	@Override public String doAuthorization(final AuthorizationMessage authorizationMessage) {

		try {
			JsonNode creditCardJson = utsRequester.getCreditCardInfo(authorizationMessage.getCreditCard().getToken());
			var jsonObject = new JSONObject(authorization.doAuthorization(authorizationMessage, creditCardJson));
			return jsonObject.toString();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override public String doCapture(final CaptureMessage captureMessage) {

		try {
			JsonNode creditCardJson = utsRequester.getCreditCardInfo(captureMessage.getCreditCard().getToken());
			var jsonObject = new JSONObject(capture.doCapture(captureMessage, creditCardJson));
			return jsonObject.toString();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "null";
	}
}
