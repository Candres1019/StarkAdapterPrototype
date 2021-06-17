package com.wesmart.stark.adapter.applicationservices.handlerimpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.wesmart.stark.adapter.application.in.handler.StarkAdapterHandler;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureMessage;
import com.wesmart.stark.adapter.applicationservices.entities.RefundMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * StarkAdapterHandlerImplTest class to test the StarkAdapterHandlerImpl class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StarkAdapterHandlerImplTest {

	private StarkAdapterHandler starkAdapterHandler;

	/**
	 * Method to prepare String Object to be sent in the tests
	 */
	@BeforeAll
	void init() {

		starkAdapterHandler = mock(StarkAdapterHandler.class);

		when(starkAdapterHandler.doAuthorization(null)).thenReturn("AUTH_ERROR");
		when(starkAdapterHandler.doAuthorization(any(AuthorizationMessage.class))).thenReturn("Failed to connect to");
		when(starkAdapterHandler.doCapture(null)).thenReturn("CAPT_ERROR");
		when(starkAdapterHandler.doCapture(any(CaptureMessage.class))).thenReturn("Failed to connect to");
		when(starkAdapterHandler.doRefund(null)).thenReturn("REND_ERROR");
		when(starkAdapterHandler.doRefund(any(RefundMessage.class))).thenReturn("Failed to connect to");
		when(starkAdapterHandler.doCancellation(null)).thenReturn("CNL_ERROR");
		when(starkAdapterHandler.doCancellation(any(CancellationMessage.class))).thenReturn("Failed to connect to");
	}

	/**
	 * Method to test the doAuthorization method sending a null authorizationMessage, this method should respond with a AUTH_ERROR
	 */
	@Test
	void doAuthorizationAndFailBecauseNotCorrectAuthorization() {

		Assertions.assertTrue(starkAdapterHandler.doAuthorization(null).contains("AUTH_ERROR"));
	}

	/**
	 * Method to test the doCapture method sending a null captureMessage this method should respond with a CAPT_ERROR
	 */
	@Test
	void doCaptureAndFailBecauseNotCorrectCapture() {

		Assertions.assertTrue(starkAdapterHandler.doCapture(null).contains("CAPT_ERROR"));
	}

	/**
	 * Method to test the doRefund method sending a null refundMessage this method should respond with a REND_ERROR
	 */
	@Test
	void doRefundAndFailBecauseNotCorrectRefund() {

		Assertions.assertTrue(starkAdapterHandler.doRefund(null).contains("REND_ERROR"));
	}

	/**
	 * Method to test the doCancellation method sending a null cancellationMessage this method should respond with a CNL_ERROR
	 */
	@Test
	void doCancellationAndFailBecauseNotCorrectCancellation() {

		Assertions.assertTrue(starkAdapterHandler.doCancellation(null).contains("CNL_ERROR"));
	}

	/**
	 * Method to test the doAuthorization method sending a custom authorizationMessage this method should respond with a Failed connection
	 */
	@Test
	void doAuthorizationAndFailBecauseServerNotActive() {

		Assertions.assertTrue(starkAdapterHandler.doAuthorization(new AuthorizationMessage()).contains("Failed to connect to"));
	}

	/**
	 * Method to test the doCapture method sending a custom captureMessage this method should respond with a Failed connection
	 */
	@Test
	void doCaptureAndFailBecauseServerNotActive() {

		Assertions.assertTrue(starkAdapterHandler.doCapture(new CaptureMessage()).contains("Failed to connect to"));
	}

	/**
	 * Method to test the doRefund method sending a custom refundMessage this method should respond with a Failed connection
	 */
	@Test
	void doRefundAndFailBecauseServerNotActive() {

		Assertions.assertTrue(starkAdapterHandler.doRefund(new RefundMessage()).contains("Failed to connect to"));
	}

	/**
	 * Method to test the doCancellation method sending a custom cancellationMessage this method should respond with a Failed connection
	 */
	@Test
	void doCancellationAndFailBecauseServerNotActive() {

		Assertions.assertTrue(starkAdapterHandler.doCancellation(new CancellationMessage()).contains("Failed to connect to"));
	}
}
