package org.roadking.services;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserSerivce {
	
	private Logger log = Logger.getLogger(UserSerivce.class);

	public void getAuthentication(String username){
	
		
		log.info("Security ContextHodler checked");
		
		return ;
	}
}
