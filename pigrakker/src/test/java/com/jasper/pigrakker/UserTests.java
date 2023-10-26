package com.jasper.pigrakker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.jasper.pigrakker.model.User;
import com.jasper.pigrakker.repository.RoleRepository;
import com.jasper.pigrakker.repository.UserRepository;
import com.jasper.pigrakker.service.SecurityUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootTest(classes = MockitoExtension.class )
public class UserTests {

	@Mock
	private UserRepository userRepository;

	@Mock
	private RoleRepository roleRepository;
	@InjectMocks
	private SecurityUserDetailsService userService;

	private PasswordEncoder passwordEncoder;

	@BeforeEach
	public void setUp() {
		passwordEncoder = new BCryptPasswordEncoder();
	}

	@Test
	public void shouldReturnTrueWhenUserRegisteredSuccessfully() {
		// given
		User user = new User();
		user.setUsername("user");
		user.setPassword(passwordEncoder.encode("password"));
		user.setEmail("user@example.com");

		// when
		when(userRepository.save(user)).thenReturn(user);

		// then
		assertThat(userService.save(user)).isNull();
	}

	@Test
	public void shouldThrowUserServiceExceptionWhenUsernameAlreadyExists() {
		// given
		User user = new User();
		user.setUsername("user");
		user.setPassword(passwordEncoder.encode("password"));
		user.setEmail("user@example.com");

		// when
		when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

		// then
		assertThatThrownBy(() -> userService.save(user))
				.isInstanceOf(UsernameNotFoundException.class)
				.hasMessage("Username or email is already taken.");
	}

	@Test
	public void shouldThrowUserServiceExceptionWhenEmailAlreadyExists() {
		// given
		User user = new User();
		user.setUsername("user");
		user.setPassword(passwordEncoder.encode("password"));
		user.setEmail("user@example.com");

		// when
		when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

		// then
		assertThatThrownBy(() -> userService.save(user))
				.isInstanceOf(UsernameNotFoundException.class)
				.hasMessage("Username or email is already taken.");
	}
}