
package com.Jasper.pigrakker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("spring")
                .password(passwordEncoder.encode("secret"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
    private AuthenticationSuccessHandler successHandler() {
        SimpleUrlAuthenticationSuccessHandler simpleUrlAuthenticationSuccessHandler = new SimpleUrlAuthenticationSuccessHandler();
        simpleUrlAuthenticationSuccessHandler.setDefaultTargetUrl("/");
        return simpleUrlAuthenticationSuccessHandler;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .successHandler(successHandler())
                                .permitAll()
                )
                .logout(out ->
                        out.logoutRequestMatcher(new
                                        AntPathRequestMatcher("/logout"))
                                .permitAll()
                )
                .build();
    }

    @Bean
    public PasswordEncoder encoder() {

        return new BCryptPasswordEncoder();
    }
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }


}
