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
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static br.com.agendaquiro.config.PathMappings.getFullPath;


public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

    public static String JWT_KEY_THAT_SHOULD_BE_ON_ENV =
            "eyJhdXRob3JpdGllcyI6IkFETUlOIiwidXNlcm5hbWUiOiJhZG1pbiJ9";
    public static String JWT_HEADER = "Authorization";

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            SecretKey key = Keys.hmacShaKeyFor(JWT_KEY_THAT_SHOULD_BE_ON_ENV.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder().setIssuer("pedidos-entregas-api").setSubject("JWT-Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + 300000))
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
        return !request.getServletPath().equals(getFullPath(PathMappings.AUTH_MAPPING));
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}

