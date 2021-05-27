package com.wesmart.stark.adapter.applicationservices.handlerimpl;

import com.wesmart.stark.adapter.application.handler.StarkAdapterHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StarkAdapterHandlerImplTest {

	@Autowired
	private StarkAdapterHandler starkAdapterHandler;

	@Test
	void doAuthorizationAndFailBecauseServerIsNotActive() {

		Assertions.assertTrue(starkAdapterHandler.doAuthorization(null).contains("AUTH_ERROR"));
	}

	@Test
	void doCaptureAndFailBecauseServerIsNotActive() {

		Assertions.assertTrue(starkAdapterHandler.doCapture(null).contains("CAPT_ERROR"));
	}

	@Test
	void doRefundAndFailBecauseServerIsNotActive() {

		Assertions.assertTrue(starkAdapterHandler.doRefund(null).contains("REND_ERROR"));
	}

	@Test
	void doCancellationAndFailBecauseServerIsNotActive() {

		Assertions.assertTrue(starkAdapterHandler.doCancellation(null).contains("CNL_ERROR"));
	}
}
