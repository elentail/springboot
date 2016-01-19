package org.roadking.bootstrap;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.roadking.model.User;
import org.roadking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


// Execute Logic only once after Application loaded

@Component
public class ApplicationLoader  implements ApplicationListener<ContextRefreshedEvent> {
	
    private Logger log = Logger.getLogger(ApplicationLoader.class);
    
    @Autowired
    private UserRepository userRepository;
    

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		
		User root = new User();
		root.setName("root");
		root.setAuthority("master");
		userRepository.save(root);
		log.info("Saved USER : " + root.getName());
	}

}
