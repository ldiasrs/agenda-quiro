package br.com.agendaquiro.config.filters;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class APIKeyAuthValidatorFilter extends OncePerRequestFilter {

    private String apiKeyHeaderName;
    private String apiKeyValue;

    public APIKeyAuthValidatorFilter(String apiKeyHeaderName, String apiKeyValue) {
        this.apiKeyHeaderName = apiKeyHeaderName;
        this.apiKeyValue = apiKeyValue;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getHeader(apiKeyHeaderName);
        if (!apiKey.equals(apiKeyValue)) {
            throw new BadCredentialsException("The API key was not found or not the expected value.");
        } else {
            SecurityContextHolder.getContext().setAuthentication(
                    new ApiKeyAuthenticationToken(apiKey, AuthorityUtils.NO_AUTHORITIES));
        }
        filterChain.doFilter(request, response);
    }
}
