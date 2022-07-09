package com.shopdoo.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
		@Test
		public void testEncoderPassword() {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String rawPassword = "P@ssw0rd";
			String encodedPassword = passwordEncoder.encode(rawPassword);
			
			System.out.println(encodedPassword);
			
			boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
			
			assertThat(matches).isTrue();
		}
}
