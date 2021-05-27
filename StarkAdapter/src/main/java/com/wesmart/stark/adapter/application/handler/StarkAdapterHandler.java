package com.wesmart.stark.adapter.application.handler;

import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureMessage;
import com.wesmart.stark.adapter.applicationservices.entities.RefundMessage;
import org.springframework.stereotype.Service;

/**
 * Defines the structure for a stark handler
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public interface StarkAdapterHandler {

	/**
	 * Make an authorization given an authorization message
	 *
	 * @param authorizationMessage - The authorization message to be processed
	 * @return - The response of the server after processing the authorization
	 */
	String doAuthorization(AuthorizationMessage authorizationMessage);

	/**
	 * Make a capture given a capture message
	 *
	 * @param captureMessage - The capture message to be processed
	 * @return - The response of the server after processing the capture
	 */
	String doCapture(CaptureMessage captureMessage);

	/**
	 * Make a refund given a capture message
	 *
	 * @param refundMessage - The capture refund to be processed
	 * @return - The response of the server after processing the refund
	 */
	String doRefund(RefundMessage refundMessage);

	/**
	 * Make a cancellation given a capture message
	 *
	 * @param cancellationMessage - The cancellation message to be processed
	 * @return - The response of the server after processing the cancellation
	 */
	String doCancellation(CancellationMessage cancellationMessage);

}
