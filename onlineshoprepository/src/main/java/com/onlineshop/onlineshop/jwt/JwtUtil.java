package com.onlineshop.onlineshop.jwt;

import com.onlineshop.onlineshop.exceptions.UnAuthorizedRequestException;
import com.onlineshop.onlineshop.utils.Role;
import io.jsonwebtoken.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtUtil {

    private String SECRET_KEY = "secret";
    public static final Date EXPIRATION_TIME = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10);

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        List<Role> roles = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        roles.add(Role.valueOf(authorities.iterator().next().toString()));
        return createToken(userDetails.getUsername(), roles);
    }

    private String createToken(String subject, List<Role> roles) {
        Map<String, Object> claims = Jwts.claims().setSubject(subject);
        claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).collect(Collectors.toList()));
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token) throws UnAuthorizedRequestException{

        // is jwt signed ?
        if (!Jwts.parser().isSigned(token)) {
            throw new UnAuthorizedRequestException("TOKEN_UNSIGNED");
        }
        // is jwt signed with good key?
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        } catch (Exception ex) {
            throw new UnAuthorizedRequestException("INVALID_TOKEN_KEY");
        }
        // is jwt expirated ?
        if (extractExpiration(token).before(new Date())) {
            throw new UnAuthorizedRequestException("TOKEN_EXPIRED");
        }

        return true;
    }
}