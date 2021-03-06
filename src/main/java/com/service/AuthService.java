package com.service;

import static java.time.Instant.now;
import static java.util.Date.from;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.AuthenticationResponse;
import com.dto.LoginRequest;
import com.dto.RefreshTokenRequest;
import com.dto.RegisterRequest;
import com.exceptions.SpringInventoryException;
import com.model.NotificationEmail;
import com.model.Users;
import com.model.VerificationToken;
import com.repository.UserRepository;
import com.repository.VerificationTokenRepository;
import com.security.JWTProvider;
import com.util.Constants;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailContentBuilder mailContentBuilder;
	private final MailService mailService;
	private final AuthenticationManager authenticationManager;
	private final JWTProvider jwtProvider;
	private final RefreshTokenService refreshTokenService;

	@Transactional
	public void signup(RegisterRequest registerRequest) {

		Users user = new Users();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(encodePassword(registerRequest.getPassword()));
		user.setCreated(now());
		user.setEnabled(false);

		userRepository.save(user);

		String token = generateVerificationToken(user);

		String message = mailContentBuilder.build(
				"Gracias por registrarse en PanaUpgrade, " + "por favor hacer click en la URL para activar su cuenta : "
						+ Constants.ACTIVATION_EMAIL + "/" + token);
		mailService.sendMail(new NotificationEmail("Por favor activar tu cuenta", user.getEmail(), message));

	}

	@Transactional(readOnly = true)
	public Users getCurrentUser() {

		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		return userRepository.findByUsername(principal.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
	}

	private String generateVerificationToken(Users user) {

		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);

		return token;
	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	public AuthenticationResponse login(LoginRequest loginRequest) {

		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String authenticationToken = jwtProvider.generateToken(authenticate);

		return AuthenticationResponse.builder()
				.authenticationToken(authenticationToken)
				.refreshToken(refreshTokenService.generateRefreshToken().getToken())
				.expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
				.username(loginRequest.getUsername())
				.build();
	}
	
	public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		
		refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
		
		String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
		
			return AuthenticationResponse.builder()
				.authenticationToken(token)
				.refreshToken(refreshTokenRequest.getRefreshToken())
				.expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
				.username(refreshTokenRequest.getUsername())
				.build();
		}

	public void verifyAccount(String token) {

		Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);
		verificationTokenOptional.orElseThrow(() -> new SpringInventoryException("Token inválido"));
		fetchUserAndEnable(verificationTokenOptional.get());
	}

	@Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) {

		String username = verificationToken.getUser().getUsername();
		Users user = userRepository.findByUsername(username)
				.orElseThrow(() -> new SpringInventoryException("Usuario no encontrado con el id - " + username));
		user.setEnabled(true);
		userRepository.save(user);
	}

	public boolean isLoggedIn() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
	}
}