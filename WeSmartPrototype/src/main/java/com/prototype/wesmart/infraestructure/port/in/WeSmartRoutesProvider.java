package com.prototype.wesmart.infraestructure.port.in;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * WeSmartRoutesProvider Class to consume and produce in to the ActiveMq Queue
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
public class WeSmartRoutesProvider extends RouteBuilder {

	/**
	 * Configuration Routes to consume and produce
	 *
	 * @throws Exception - Exception
	 */
	@Override public void configure() throws Exception {

		from("file:files/json/Authorization")
				.to("activemq:aa.authorization.request.stark");

		from("file:files/json/Capture")
				.to("activemq:aa.capture.request.stark");

		from("file:files/json/Refund")
				.to("activemq:aa.refund.request.stark");

		from("file:files/json/Cancellation")
				.to("activemq:aa.cancellation.request.stark");

		from("activemq:aa.authorization.response.stark")
				.to("log:aa.authorization.response.stark");

		from("activemq:aa.capture.response.stark")
				.to("log:aa.capture.response.stark");

		from("activemq:aa.refund.response.stark")
				.to("log:aa.refund.response.stark");

		from("activemq:aa.cancellation.response.stark")
				.to("log:aa.cancellation.response.stark");
	}

}
