package com.etrans.itstock.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	// @Autowired
	// private UserRepository userRepository;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		// seedData();
	}

	private void seedData() {
		// User user1 = userRepository.save(new User("Admin","admin", "admin"));
		// System.out.println(user1);
	}

}
