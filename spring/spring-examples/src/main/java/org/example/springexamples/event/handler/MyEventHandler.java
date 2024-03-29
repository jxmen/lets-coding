package org.example.springexamples.event.handler;

import org.example.springexamples.event.MyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MyEventHandler {
	Logger log = LoggerFactory.getLogger(MyEventHandler.class);

	@EventListener
	@Async
	public void handle(MyEvent event) {
		log.info("event number is : '{}', thread: '{}'", event.getNumber(), Thread.currentThread().getName());
	}
}
