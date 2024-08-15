package com.mallik.camel.inbound_service.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class InBoundActiveMqSenderRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("timer:active-mq-timer?period=10000")
		.transform().constant("My message for Active MQ")
		.log("${body}")
		.to("activemq:active-mq-queue");
	}

}
