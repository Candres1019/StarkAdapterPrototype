package com.wesmart.stark.adapter.application.handler;

import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureMessage;
import org.springframework.stereotype.Service;

@Service
public interface StarkAdapterHandler {

	String doAuthorization(AuthorizationMessage authorizationMessage);

	String doCapture(CaptureMessage captureMessage);

}
