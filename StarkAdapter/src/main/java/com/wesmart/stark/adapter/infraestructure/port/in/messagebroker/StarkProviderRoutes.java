package com.wesmart.stark.adapter.infraestructure.port.in.messagebroker;

import org.apache.camel.builder.RouteBuilder;

public class StarkProviderRoutes extends RouteBuilder {

	@Override public void configure() throws Exception {

		from("aa.authorization.request.stark")
				.to("aa.authorization.response.stark");
	}
}
