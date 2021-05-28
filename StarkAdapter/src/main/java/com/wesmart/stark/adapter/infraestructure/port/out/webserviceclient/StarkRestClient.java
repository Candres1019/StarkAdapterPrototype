package com.wesmart.stark.adapter.infraestructure.port.out.webserviceclient;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Stark Rest Client to make the connection to the stark acquirer
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
public class StarkRestClient {

	private static final String SERVER_URL = "http://localhost:8002/";
	private static final String MEDIA_TYPE = "application/json";
	private static final String ERROR_MESSAGE = "errorMessage";
	private final JSONObject errorJson;

	/**
	 * Instantiates a new StarkRestClient service
	 */
	public StarkRestClient() {

		errorJson = new JSONObject();
		errorJson.put("message", "Transaction Error");
		errorJson.put("status", "ERROR");
	}

	/**
	 * Method to make a POST request to the Stark Acquirer in the path "/authorization"
	 *
	 * @param authorization - AuthorizationMessage to be send to the acquirer in JSON format
	 * @return - The response of the Acquirer after processing the petition
	 */
	public JSONObject doAuthorization(final JSONObject authorization) {

		var request = new Request.Builder()
				.url(SERVER_URL + "authorization")
				.post(RequestBody.create(MediaType.parse(MEDIA_TYPE), authorization.toString()))
				.build();
		var httpClient = new OkHttpClient();
		try {
			var response = httpClient.newCall(request).execute();
			return new JSONObject(response.body().string());
		} catch (Exception e) {
			errorJson.put(ERROR_MESSAGE, e.getMessage());
			errorJson.put("authorizationCode", "ERROR");
			return errorJson;
		}
	}

	/**
	 * Method to make a POST request to the Stark Acquirer in the path "/capture"
	 *
	 * @param capture - CaptureMessage to be send to the acquirer in JSON format
	 * @return - The response of the Acquirer after processing the petition
	 */
	public JSONObject doCapture(final JSONObject capture) {

		var request = new Request.Builder()
				.url(SERVER_URL + "capture")
				.post(RequestBody.create(MediaType.parse(MEDIA_TYPE), capture.toString()))
				.build();
		var httpClient = new OkHttpClient();
		try {
			var response = httpClient.newCall(request).execute();
			return new JSONObject(response.body().string());
		} catch (Exception e) {
			errorJson.put(ERROR_MESSAGE, e.getMessage());
			errorJson.put("captureCode", "ERROR");
			return errorJson;
		}
	}

	/**
	 * Method to make a POST request to the Stark Acquirer in the path "/void"
	 *
	 * @param cancellation - CancellationMessage to be send to the acquirer in JSON format
	 * @return - The response of the Acquirer after processing the petition
	 */
	public JSONObject doCancellation(final JSONObject cancellation) {

		var request = new Request.Builder()
				.url(SERVER_URL + "void")
				.post(RequestBody.create(MediaType.parse(MEDIA_TYPE), cancellation.toString()))
				.build();
		var httpClient = new OkHttpClient();
		try {
			var response = httpClient.newCall(request).execute();
			return new JSONObject(response.body().string());
		} catch (Exception e) {
			errorJson.put(ERROR_MESSAGE, e.getMessage());
			errorJson.put("voidCode", "ERROR");
			return errorJson;
		}
	}

	/**
	 * Method to make a POST request to the Stark Acquirer in the path "/refund"
	 *
	 * @param refund - RefundMessage to be send to the acquirer in JSON format
	 * @return - The response of the Acquirer after processing the petition
	 */
	public JSONObject doRefund(final JSONObject refund) {

		var request = new Request.Builder()
				.url(SERVER_URL + "refund")
				.post(RequestBody.create(MediaType.parse(MEDIA_TYPE), refund.toString()))
				.build();
		var httpClient = new OkHttpClient();
		try {
			var response = httpClient.newCall(request).execute();
			return new JSONObject(response.body().string());
		} catch (Exception e) {
			errorJson.put(ERROR_MESSAGE, e.getMessage());
			errorJson.put("refundCode", "ERROR");
			return errorJson;
		}
	}

}
