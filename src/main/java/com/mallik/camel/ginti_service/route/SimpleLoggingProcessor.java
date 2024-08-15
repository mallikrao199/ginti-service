package com.mallik.camel.ginti_service.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SimpleLoggingProcessor implements Processor {

	
	@Override
	public void process(Exchange exchange) throws Exception {
		log.info("SimpleLoggingProcessor................{}", exchange.getMessage().getBody());

	}

}
