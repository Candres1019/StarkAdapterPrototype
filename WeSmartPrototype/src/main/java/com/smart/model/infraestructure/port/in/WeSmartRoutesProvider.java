package com.smart.model.infraestructure.port.in;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class WeSmartRoutesProvider extends RouteBuilder {

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
