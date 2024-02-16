package org.example.springexamples.event.handler;

import org.example.springexamples.event.MyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class AnotherMyEventHandler {
	Logger log = LoggerFactory.getLogger(AnotherMyEventHandler.class);

	@EventListener
	@Order(Ordered.LOWEST_PRECEDENCE - 1)
	public void handle(MyEvent event) {
		log.info("event number is : '{}', thread: '{}'", event.getNumber(), Thread.currentThread().getName());
	}
}
