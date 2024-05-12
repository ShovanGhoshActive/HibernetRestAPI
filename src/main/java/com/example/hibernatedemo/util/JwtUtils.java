package com.example.hibernatedemo.util;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${jwt.secret}")
	private String appCookie;

	@Value("${jwt.expiration}")
	private int jwtEexpiration;
	
	@Value("${app.secret}")
	private String appSecret;

	public ResponseCookie generateCookie(UserDetailsImpl userPrincipal) {
		String jwt = generateTokenFromUsername(userPrincipal.getUsername());
		ResponseCookie cookie = ResponseCookie.from(appCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true)
				.build();
		return cookie;
	}

	public ResponseCookie cookieClean() {
		ResponseCookie cookie = ResponseCookie.from(appCookie, null).path("/api").build();
		return cookie;
	}

//	private Key key() {
//		return Keys.secretKeyFor(SignatureAlgorithm.HS256);
//	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(appSecret));
	}

	public String getUserName(String token) {
		return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("Invalid token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("token expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("token unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("empty: {}", e.getMessage());
		}

		return false;
	}

	public String generateTokenFromUsername(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtEexpiration))
				.signWith(key(), SignatureAlgorithm.HS256).compact();
	}

	public String getJwtFromCookies(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, appCookie);
		if (cookie != null) {
			return cookie.getValue();
		} else {
			return null;
		}
	}
}
