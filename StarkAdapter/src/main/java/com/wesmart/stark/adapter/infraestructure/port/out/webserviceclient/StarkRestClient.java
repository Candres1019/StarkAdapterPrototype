package com.wesmart.stark.adapter.infraestructure.port.out.webserviceclient;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
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

	private static final String MEDIA_TYPE = "application/json";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String ERROR_CODE = "ERROR";
	private final JSONObject errorJson;
	@Value("${stark.acquirer.url}")
	private String serverUrl;

	/**
	 * Instantiates a new StarkRestClient service
	 */
	public StarkRestClient() {

		errorJson = new JSONObject();
		errorJson.put("message", "Transaction Error");
		errorJson.put("status", ERROR_CODE);
	}

	/**
	 * Method to make a POST request to the Stark Acquirer in the path "/authorization"
	 *
	 * @param authorization - AuthorizationMessage to be send to the acquirer in JSON format
	 * @return - The response of the Acquirer after processing the petition
	 */
	public JSONObject doAuthorization(final JSONObject authorization) {

		Request request = new Request.Builder()
				.url(serverUrl + "authorization")
				.post(RequestBody.create(MediaType.parse(MEDIA_TYPE), authorization.toString()))
				.build();
		OkHttpClient httpClient = new OkHttpClient();
		try {
			Response response = httpClient.newCall(request).execute();
			return new JSONObject(response.body().string());
		} catch (Exception e) {
			errorJson.put(ERROR_MESSAGE, e.getMessage());
			errorJson.put("authorizationCode", ERROR_CODE);
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

		Request request = new Request.Builder()
				.url(serverUrl + "capture")
				.post(RequestBody.create(MediaType.parse(MEDIA_TYPE), capture.toString()))
				.build();
		OkHttpClient httpClient = new OkHttpClient();
		try {
			Response response = httpClient.newCall(request).execute();
			return new JSONObject(response.body().string());
		} catch (Exception e) {
			errorJson.put(ERROR_MESSAGE, e.getMessage());
			errorJson.put("captureCode", ERROR_CODE);
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

		Request request = new Request.Builder()
				.url(serverUrl + "void")
				.post(RequestBody.create(MediaType.parse(MEDIA_TYPE), cancellation.toString()))
				.build();
		OkHttpClient httpClient = new OkHttpClient();
		try {
			Response response = httpClient.newCall(request).execute();
			return new JSONObject(response.body().string());
		} catch (Exception e) {
			errorJson.put(ERROR_MESSAGE, e.getMessage());
			errorJson.put("voidCode", ERROR_CODE);
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

		Request request = new Request.Builder()
				.url(serverUrl + "refund")
				.post(RequestBody.create(MediaType.parse(MEDIA_TYPE), refund.toString()))
				.build();
		OkHttpClient httpClient = new OkHttpClient();
		try {
			Response response = httpClient.newCall(request).execute();
			return new JSONObject(response.body().string());
		} catch (Exception e) {
			errorJson.put(ERROR_MESSAGE, e.getMessage());
			errorJson.put("refundCode", ERROR_CODE);
			return errorJson;
		}
	}

}
