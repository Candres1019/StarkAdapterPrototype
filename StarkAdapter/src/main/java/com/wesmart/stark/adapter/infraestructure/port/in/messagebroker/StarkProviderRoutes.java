package com.wesmart.stark.adapter.infraestructure.port.in.messagebroker;

import com.wesmart.stark.adapter.application.in.handler.StarkAdapterHandler;
import com.wesmart.stark.adapter.applicationservices.entities.AuthorizationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CancellationMessage;
import com.wesmart.stark.adapter.applicationservices.entities.CaptureMessage;
import com.wesmart.stark.adapter.applicationservices.entities.RefundMessage;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * StarkProviderRoutes Class to consume and produce in to the ActiveMq Queue
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
public class StarkProviderRoutes extends RouteBuilder {

	private final StarkAdapterHandler starkAdapterHandler;

	/**
	 * Instantiates a new StarkProviderRoutes service
	 *
	 * @param starkAdapterHandler - The Handler for the petitions made in the queue
	 */
	@Autowired
	public StarkProviderRoutes(@Qualifier("starkAdapterHandlerImpl") StarkAdapterHandler starkAdapterHandler) {

		this.starkAdapterHandler = starkAdapterHandler;
	}

	/**
	 * Configuration Routes to consume and produce
	 *
	 * @throws Exception - Exception
	 */
	@Override public void configure() throws Exception {

		from("activemq:aa.authorization.request.stark")
				.unmarshal().json(JsonLibrary.Jackson, AuthorizationMessage.class)
				.bean(starkAdapterHandler, "doAuthorization")
				.to("activemq:aa.authorization.response.stark");

		from("activemq:aa.capture.request.stark")
				.unmarshal().json(JsonLibrary.Jackson, CaptureMessage.class)
				.bean(starkAdapterHandler, "doCapture")
				.to("activemq:aa.capture.response.stark");

		from("activemq:aa.refund.request.stark")
				.unmarshal().json(JsonLibrary.Jackson, RefundMessage.class)
				.bean(starkAdapterHandler, "doRefund")
				.to("activemq:aa.refund.response.stark");

		from("activemq:aa.cancellation.request.stark")
				.unmarshal().json(JsonLibrary.Jackson, CancellationMessage.class)
				.bean(starkAdapterHandler, "doCancellation")
				.to("activemq:aa.cancellation.response.stark");
	}
}
