package com.wesmart.stark.adapter.application.handler;

import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureMessage;
import com.wesmart.stark.adapter.applicationservices.entities.RefundMessage;
import org.springframework.stereotype.Service;

@Service
public interface StarkAdapterHandler {

	String doAuthorization(AuthorizationMessage authorizationMessage);

	String doCapture(CaptureMessage captureMessage);

	String doRefund(RefundMessage refundMessage);

	String doCancellation(CancellationMessage cancellationMessage);

}
