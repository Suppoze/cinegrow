package hu.zsoki.cinegrow.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String CLIENT_ROLE = "Client";

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;

    @NonNull
    @Value("username") // Environment variable
    private String clientUsername;

    @NonNull
    @Value("password") // Environment variable
    private String clientPassword;

    @Autowired
    public SpringSecurityConfig(RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                                AccessDeniedHandler accessDeniedHandler) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    // TODO: https://stackoverflow.com/questions/48446708/securing-spring-boot-api-with-api-key-and-secret?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/", "/view", "/search").hasRole(CLIENT_ROLE)
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(clientUsername).password(clientPassword).roles(CLIENT_ROLE);
    }
}
