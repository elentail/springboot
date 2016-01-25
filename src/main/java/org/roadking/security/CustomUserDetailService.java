package org.roadking.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.roadking.model.UserRole;
import org.roadking.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		org.roadking.model.Users user =  userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRoles());
		
		return buildUserForAuthentication(user,authorities);
	}
	
	private User buildUserForAuthentication(org.roadking.model.Users user,
			List<GrantedAuthority> authorities) {
		
		return new User(user.getUsername(),user.getPassword(),true,true,true,true,authorities);
		
	}
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
		for (UserRole userRole : userRoles) {
			authorities.add(new SimpleGrantedAuthority(userRole.getUserRoleName()));
		}
		
		return authorities;
	}

}
