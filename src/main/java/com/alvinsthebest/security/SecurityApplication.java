package com.alvinsthebest.security;

import com.alvinsthebest.security.repo.RoleRepo;
import com.alvinsthebest.security.repo.UserRepo;
import com.alvinsthebest.security.repo.model.Role;
import com.alvinsthebest.security.repo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Bean
	public void createUsers(){
		Role userRole = new Role("ROLE_USER");
		Role adminRole = new Role("ROLE_ADMIN");
		roleRepo.save(userRole);
		roleRepo.save(adminRole);

		User aang = new User();
		aang.setUsername("aang");
		aang.setPassword(passwordEncoder.encode("air"));
		aang.setInfo("Hello hotman!");
		aang.setRoles(Arrays.asList(adminRole));
		userRepo.save(aang);

		User zuko = new User();
		zuko.setUsername("zuko");
		zuko.setPassword(passwordEncoder.encode("fire"));
		zuko.setInfo("I will catch the avatar.");
		zuko.setRoles(Arrays.asList(userRole));
		userRepo.save(zuko);
	}

}
