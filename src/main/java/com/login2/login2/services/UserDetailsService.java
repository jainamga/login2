package com.login2.login2.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
	  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	}