package br.com.agendaquiro.config;


import br.com.agendaquiro.config.filters.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Arrays;

import static java.util.Collections.singletonList;

/**
 *
 * -- FILTERS
 * Filters que podemo ser configurados antes e depois de cada request
 * addFilterBefore
 * addFilterAfter
 * addFilterAt
 *
 * Veja classes GenericFilterBean and OncePerRequestFilter
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private String jwtKey;
    private DataSource dataSource;

    public SecurityConfig(@Value("${api.security.jwt.key}") String jwtKey, DataSource dataSource) {
        this.jwtKey = jwtKey;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        defineCorsConfig(http);
        disableCsrf(http);
        defineAthorizationConfig(http);
        addLogFilters(http);
        disableCorsSessionIdToken(http);
        defineJWTAccessTokenConfiguration(http);
    }


    private void disableCorsSessionIdToken(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * User Authentication
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return defineInMemoryUserDetailsConfig();
    }


    @Bean
    public PasswordEncoder definePasswordEncoder(){
        //define um encoder que ira criar uma hash da senha para comparacao e store
        //na teoria essa hash nao pode mais recriar a senha original
        return new BCryptPasswordEncoder();
    }

    private UserDetailsService defineInMemoryUserDetailsConfig() {
        JdbcDaoImpl service = new JdbcDaoImpl();
        service.setDataSource(dataSource);
        return service;
    }

    private void defineAthorizationConfig(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //permite acesso ao /info sem autenticacao
                .antMatchers(PathMappings.HOME).permitAll()
//                .antMatchers(getFullPath(PathMappings.ADMIN_MAPPING)).hasAnyRole( "ADMIN")
                //o resto do acesso dos pedidosentrega precisam de autenticacao
                .antMatchers("/app").authenticated()
                .antMatchers(PathMappings.AUTH_MAPPING).authenticated()
                .antMatchers(PathMappings.CALENDAR_FREE_SLOTS).authenticated()
                .antMatchers("/agenda").hasAnyAuthority("ROLE_MANAGER")
                //Habilita o form login do spring
                //.and().formLogin()
                .and().httpBasic();
    }

    private void addLogFilters(HttpSecurity http) {
        http
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class);
    }

    private void defineJWTAccessTokenConfiguration(HttpSecurity http) {
        http
                .addFilterBefore(new JWTTokenValidatorFilter(jwtKey), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(jwtKey), BasicAuthenticationFilter.class);
    }

    private HttpSecurity disableCsrf(HttpSecurity http) throws Exception {
        /**
         * https://en.wikipedia.org/wiki/Cross-site_request_forgery
         * In a CSRF attack, the attacker's goal is to cause an innocent victim to unknowingly
         * submit a maliciously crafted web request to a website that the victim has privileged
         * access to. This web request can be crafted to include URL parameters,
         * cookies and other data that appear normal to the web server processing the request.
         * At risk are web applications that perform actions based on input from trusted and authenticated
         * users without requiring the user to authorize the specific action. A user who is authenticated
         * by a cookie saved in the user's web browser could unknowingly send an HTTP request to a site that
         * trusts the user and thereby causes an unwanted action.
         */
        return http.csrf().disable();
    }

    /**
     * https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
     *
     * Cross-Origin Resource Sharing (CORS) is an HTTP-header based mechanism that allows a server
     * to indicate any origins (br.com.agendaquiro.domain, scheme, or port) other than its own from which a browser should
     * permit loading of resources. CORS also relies on a mechanism by which browsers make a "preflight"
     * request to the server hosting the cross-origin resource, in order to check that the server will
     * permit the actual request. In that preflight, the browser sends headers that indicate the HTTP
     * method and headers that will be used in the actual request.
     *
     * An example of a cross-origin request: the front-end JavaScript code served from
     * https://domain-a.com uses XMLHttpRequest to make a request for https://domain-b.com/data.json.
     *
     * For security reasons, browsers restrict cross-origin HTTP requests initiated from scripts.
     * For example, XMLHttpRequest and the Fetch API follow the same-origin policy.
     * This means that a web application using those APIs can only request resources from the same
     * origin the application was loaded from unless the response from other origins includes the right
     * CORS headers.
     */
    private HttpSecurity defineCorsConfig(HttpSecurity http) throws Exception {
        http.cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                var configuration = new CorsConfiguration();
                //Apenas requisicoes vinda dessa URL serao aceitas
                configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://localhost:3000"));
                //Todos os metodos http POST, GET, DELETE etc serao aceitos
                configuration.setAllowedMethods((singletonList("*")));
                //Todos os cabecalhos do http serao aceitos
                configuration.setAllowedHeaders((singletonList("*")));
                //O cliente vai cachear essa config de cors por 3600 segundos = 1h at?? verificar novamente
                configuration.setMaxAge(3600L);
                //Aceita credinciais de seguran??a
                configuration.setAllowCredentials(true);
                configuration.setExposedHeaders(Arrays.asList("Authorization"));
                return configuration;
            }
        });
        return http;
    }
}
