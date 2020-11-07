package com.sha.springbootrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringBootRestApplication {

	// This will create encrypted, secure, ... passwor.
	// This will internary generate a random salt so you will handle different result for each call.
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApplication.class, args);
	}

}
