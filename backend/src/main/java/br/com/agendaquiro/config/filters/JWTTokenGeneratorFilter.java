package br.com.agendaquiro.config.filters;


import br.com.agendaquiro.config.PathMappings;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

    public static String JWT_HEADER = "Authorization";
    private String jwtKey;

    public JWTTokenGeneratorFilter(String jwtKey) {
        this.jwtKey = jwtKey;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            SecretKey key = Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder().setIssuer("agenda-quiro-backend").setSubject("JWT-Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(60)))
                    .signWith(key).compact();
            response.setHeader(JWT_HEADER, jwt);
        }
        chain.doFilter(request, response);
    }

    /**
     * Vai criar o JWT token apenas quando for no /auth
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals(PathMappings.AUTH_MAPPING);
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}

