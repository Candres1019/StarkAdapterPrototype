package com.wesmart.stark.adapter.applicationservices.handlerimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesmart.stark.adapter.application.in.handler.StarkAdapterHandler;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureMessage;
import com.wesmart.stark.adapter.applicationservices.entities.RefundMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * StarkAdapterHandlerImplTest class to test the StarkAdapterHandlerImpl class
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@SpringBootTest
class StarkAdapterHandlerImplTest {

	@Autowired
	private StarkAdapterHandler starkAdapterHandler;

	private String authString;
	private String captString;
	private String refundString;
	private String cancellationString;

	/**
	 * Method to prepare String Object to be sent in the tests
	 */
	@BeforeEach public void prepareObj() {

		this.authString =
				"{\"correlationId\":\"a560358a-1078-49e5-b426-fc397de8617b\","
						+ "\"authorizationId\":\"a560358a-d3a0-b426-d3a0-fc397de8617b\","
						+ "\"transaction\":{\"id\":\"2aa2727e-9218-44d2-a3f3-909bb7285a4f\","
						+ "\"type\":\"authorization\",\"currency\":\"COP\",\"status\":\"InVerification\","
						+ "\"total\":119000,\"tax\":19000,\"taxBase\":100000,\"creationDate\":1621486800000,"
						+ "\"modificationDate\":1621486800000,\"paymentOrder\":{\"id\":\"18261179-d3a0-4cfa-9ce9-06be03a94307\","
						+ "\"reference\":\"JAGSGB3-3131\",\"description\":\"Test payment order\",\"billingAddress\":\"Cl 5 No 1B - 72\","
						+ "\"creationDate\":1621486800000,\"customer\":{\"id\":1019151395,"
						+ "\"idType\":\"CC\",\"name\":\"CustomerName\",\"lastName\":\"CustomerLastName\","
						+ "\"email\":\"customer@mail.com\",\"phone\":\"+57 3174414419\",\"address\":\"Cl 5 No 1B - 72\","
						+ "\"creationDate\":1621486800000,\"modificationDate\":1621486800000},\"seller\":{\"id\":1,\"name\":"
						+ "\"Real Madrid\",\"nit\":\"712361-21\",\"merchantCategoryCode\":\"5423\",\"phone\":\"+3 123131312\","
						+ "\"email\":\"real.madrid@mail.com\",\"address\":\"La castellana\",\"creationDate\":1621486800000,"
						+ "\"modificationDate\":1621486800000}}},\"creditCard\":{\"id\":123456789,\"type\":\"VISA\","
						+ "\"maskedNumber\":\"1234-56**-****-4321\",\"token\":\"123456789923818231132\","
						+ "\"cardHolder\":\"CUSTOMER C CUSTOMER C\",\"expirationDate\":\"2621486800000\"}}";

		this.captString =
				"{\"correlationId\":\"a560358a-1078-49e5-b426-fc397de8617b\","
						+ "\"captureId\":\"fc397de8617b-b426-49e5-1078-a560358a\",\"authorizationTraceabilityId\":\"a560358a-1078-49e5-b426-fc397de8617b\","
						+ "\"authorizationCode\":\"fc397de8617b-b426-49e5-1078-a560358a\",\"transaction\":{\"id\":\"2aa2727e-9218-44d2-a3f3-909bb7285a4f\","
						+ "\"type\":\"capture\",\"currency\":\"COP\",\"status\":\"InVerification\",\"total\":119000,\"tax\":19000,\"taxBase\":100000,"
						+ "\"paymentOrder\":{\"id\":\"18261179-d3a0-4cfa-9ce9-06be03a94307\",\"reference\":\"JAGSGB3-3131\",\"description\":\"Test payment order\","
						+ "\"billingAddress\":\"Cl 5 No 1B - 72\"}},\"creditCard\":{\"id\":123456789,\"type\":\"VISA\",\"maskedNumber\":\"0000-00**-****-0000\","
						+ "\"token\":\"123456789923818231132\",\"cardHolder\":\"CUSTOMER C CUSTOMER C\",\"expirationDate\":\"2621486800000\"}}";

		this.refundString =
				"{\"correlationId\":\"a560358a-1078-49e5-b426-fc397de8617b\","
						+ "\"refundId\":\"fc397de8617b-b426-49e5-1078-a560358a\",\"captureTraceabilityId\":\"a560358a-1078-49e5-b426-fc397de8617b\","
						+ "\"refundCode\":\"fc397de8617b-b426-49e5-1078-a560358a\",\"maxRefundDate\":\"2621486800000\",\"transaction\":"
						+ "{\"id\":\"2aa2727e-9218-44d2-a3f3-909bb7285a4f\",\"type\":\"refund\",\"currency\":\"COP\",\"status\":\"InVerification\","
						+ "\"total\":119000,\"tax\":19000,\"taxBase\":100000,\"paymentOrder\":{\"id\":\"18261179-d3a0-4cfa-9ce9-06be03a94307\"}},\"creditCard\":"
						+ "{\"id\":123456789,\"type\":\"VISA\",\"maskedNumber\":\"0000-00**-****-0000\",\"token\":\"123456789923818231132\",\"cardHolder\":"
						+ "\"CUSTOMER C CUSTOMER C\",\"expirationDate\":\"2621486800000\"}}";

		this.cancellationString =
				"{\"correlationId\":\"a560358a-1078-49e5-b426-fc397de8617b\",\"cancellationId\":\"fc397de8617b-b426-49e5-1078-a560358a\","
						+ "\"authorizationTraceabilityId\":\"a560358a-1078-49e5-b426-fc397de8617b\",\"authorizationCode\":\"fc397de8617b-b426-49e5-1078-a560358a\","
						+ "\"transaction\":{\"id\":\"2aa2727e-9218-44d2-a3f3-909bb7285a4f\",\"type\":\"refund\",\"currency\":\"COP\",\"status\":\"InVerification\","
						+ "\"total\":119000,\"tax\":19000,\"taxBase\":100000,\"paymentOrder\":{\"id\":\"18261179-d3a0-4cfa-9ce9-06be03a94307\"}},\"creditCard\":"
						+ "{\"id\":123456789,\"type\":\"VISA\",\"maskedNumber\":\"0000-00**-****-0000\",\"token\":\"123456789923818231132\",\"cardHolder\":"
						+ "\"CUSTOMER C CUSTOMER C\",\"expirationDate\":\"2621486800000\"}}";
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
	void doAuthorizationAndFailBecauseServerNotActive() throws JsonProcessingException, JSONException {

		AuthorizationMessage authorizationMessage = new ObjectMapper()
				.readValue(new JSONObject(authString).toString(), AuthorizationMessage.class);
		Assertions.assertTrue(starkAdapterHandler.doAuthorization(authorizationMessage).contains("Failed to connect to"));
	}

	/**
	 * Method to test the doCapture method sending a custom captureMessage this method should respond with a Failed connection
	 */
	@Test
	void doCaptureAndFailBecauseServerNotActive() throws JsonProcessingException, JSONException {

		CaptureMessage captureMessage = new ObjectMapper()
				.readValue(new JSONObject(captString).toString(), CaptureMessage.class);
		Assertions.assertTrue(starkAdapterHandler.doCapture(captureMessage).contains("Failed to connect to"));
	}

	/**
	 * Method to test the doRefund method sending a custom refundMessage this method should respond with a Failed connection
	 */
	@Test
	void doRefundAndFailBecauseServerNotActive() throws JsonProcessingException, JSONException {

		RefundMessage refundMessage = new ObjectMapper()
				.readValue(new JSONObject(refundString).toString(), RefundMessage.class);
		Assertions.assertTrue(starkAdapterHandler.doRefund(refundMessage).contains("Failed to connect to"));
	}

	/**
	 * Method to test the doCancellation method sending a custom cancellationMessage this method should respond with a Failed connection
	 */
	@Test
	void doCancellationAndFailBecauseServerNotActive() throws JsonProcessingException, JSONException {

		CancellationMessage cancellationMessage = new ObjectMapper()
				.readValue(new JSONObject(cancellationString).toString(), CancellationMessage.class);
		Assertions.assertTrue(starkAdapterHandler.doCancellation(cancellationMessage).contains("Failed to connect to"));
	}
}
