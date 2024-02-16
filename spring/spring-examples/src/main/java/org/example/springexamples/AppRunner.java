package org.example.springexamples;

import org.example.springexamples.event.MyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

	@Autowired ApplicationEventPublisher eventPublisher;

	@Override
	public void run(ApplicationArguments args) {
		eventPublisher.publishEvent(new MyEvent(this, 100));
	}
}
