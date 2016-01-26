package org.roadking.bootstrap;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.roadking.model.Users;
import org.roadking.model.UserRole;
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
		
		Users admin = new Users();
		admin.setUsername("admin");
		admin.setPassword("123456");
		admin.setFirstName("Foo");
		admin.setLastName("Bar");
		
		UserRole adminRole = new UserRole();
		adminRole.setUserRoleName("ROLE_ADMIN");
		adminRole.setUser(admin);
		Set<UserRole> adminRoles = new HashSet<UserRole>(0);
		adminRoles.add(adminRole);
		admin.setUserRoles(adminRoles);
		
		userRepository.save(admin);
		log.info("Saved USER : " + admin.getUsername());
	}

}
