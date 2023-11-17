
package com.jasper.pigrakker.config;

import com.jasper.pigrakker.service.CustomOidcUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    private GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
            boolean isAdmin = authorities.stream()
                    .anyMatch(authority -> {
                        if (authority instanceof OAuth2UserAuthority) {
                            Map<String, Object> attributes = ((OAuth2UserAuthority) authority).getAttributes();
                            String email = (String) attributes.get("email");
                            return email != null && email.matches("jasperdeklijn@gmail.com"); // Check the email domain
                        }
                        return false;
                    });
            authorities.forEach((authority) -> {
                GrantedAuthority mappedAuthority;

                if (authority instanceof OidcUserAuthority) {
                    OidcUserAuthority userAuthority = (OidcUserAuthority) authority;
                    if (isAdmin) {
                        mappedAuthority = new OidcUserAuthority("ROLE_ADMIN", userAuthority.getIdToken(), userAuthority.getUserInfo());
                    } else {
                        mappedAuthority = new OidcUserAuthority("ROLE_USER", userAuthority.getIdToken(), userAuthority.getUserInfo());
                    }
                } else if (authority instanceof OAuth2UserAuthority) {
                    OAuth2UserAuthority userAuthority = (OAuth2UserAuthority) authority;
                    if (isAdmin) {
                        mappedAuthority = new OAuth2UserAuthority("ROLE_ADMIN", userAuthority.getAttributes());
                    } else {
                        mappedAuthority = new OAuth2UserAuthority("ROLE_USER", userAuthority.getAttributes());
                    }
                } else {
                    mappedAuthority = authority;
                }

                mappedAuthorities.add(mappedAuthority);
            });

            return mappedAuthorities;
        };
    }
    @Bean
    public OidcUserService getService()
    {
        return new CustomOidcUserService();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http

                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/order/**").authenticated()
                        .anyRequest().permitAll()
                )
                .oauth2Client(withDefaults())
                .oauth2Login((oauth2Login) -> oauth2Login
                        .userInfoEndpoint((userInfo) -> userInfo
                                .userAuthoritiesMapper(grantedAuthoritiesMapper())
                                .oidcUserService(getService())
                        )
                )
                .formLogin(withDefaults())
                .logout(out ->
                        out.logoutRequestMatcher(new
                                        AntPathRequestMatcher("/logout"))
                                .permitAll()
                )
                .build();
    }


}
