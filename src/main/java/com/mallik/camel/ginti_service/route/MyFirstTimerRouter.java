package com.mallik.camel.ginti_service.route;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyFirstTimerRouter extends RouteBuilder {
	
	@Autowired 
	private GetCurrentTimeBean getCurrentTimeBean;
	
	@Autowired
	private SimpleLoggingProcessingComponent loggingComponent;

	@Override
	public void configure() throws Exception {
		// time -> transform -> log
		// timer end point
		// whenever there is a message on timer end point then send it to log using .to
		//2024-08-14T10:46:43.165+05:30  INFO 27712 --- [ginti-service] [st-timer-mallik] first-timer-mallik                       
		//: Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
		from("timer:first-timer-mallik")  //timer is a channel which triggers a message null into the body
		.log("${body}")
		.transform().constant("My Constant Message")
		.log("${body}")
		//.transform().constant("Time now is "+ LocalDateTime.now())
		//.bean("getCurrentTimeBean")
		.bean(getCurrentTimeBean, "getCurrentTime")
		.log("${body}")
		.bean(loggingComponent)
		.log("${body}")
		.process(new SimpleLoggingProcessor())
		.to("log:first-timer-mallik")
		; //logs the received message

	}

}

@Component
class GetCurrentTimeBean {
	public String getCurrentTime() {
		return "Time now is "+ LocalDateTime.now();
	}
}

@Component
class SimpleLoggingProcessingComponent {
	
	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
	
	public void process(String message) {
		logger.info("SimpleLoggingProcessingComponent  {}", message);
	}
}
