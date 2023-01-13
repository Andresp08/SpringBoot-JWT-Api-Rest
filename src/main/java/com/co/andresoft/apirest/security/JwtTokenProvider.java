package com.co.andresoft.apirest.security;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.co.andresoft.apirest.exception.BlogAppException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private String jwtExpirationInMs;


	public String generarTokenAcceso(Authentication authentication) {
		String username = authentication.getName();

		long jwtExpirationInMs = Long.parseLong(this.jwtExpirationInMs);
		Instant fechaActual = Instant.now();
		Instant fechaExpiracion = fechaActual.plusMillis(jwtExpirationInMs);

		return 	Jwts.builder()
				.setSubject(username)
				.setIssuedAt(Date.from(Instant.now()))
				.setExpiration(Date.from(fechaExpiracion))
				.signWith(SignatureAlgorithm.HS256, jwtSecret)
				.compact();
	}

	public String obtenerUsernameDelJwt(String token) {

		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		if (claims.getExpiration().before(Date.from(Instant.now()))) {
			throw new JwtException("Token está expirado");
		}

		return claims.getSubject(); // username del token
	}

	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "Firma JWT no valida");
		} catch (MalformedJwtException ex) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT no valido");
		} catch (ExpiredJwtException ex) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT caducado");
		} catch (UnsupportedJwtException ex) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT no compatible");
		} catch (IllegalArgumentException ex) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "La cadena claims JWT está vacia");
		}
	}

}
