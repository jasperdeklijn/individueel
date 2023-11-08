package com.jasper.pigrakker.service;

import com.jasper.pigrakker.config.CustomUserDetails;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class GoogleOAuth2UserService implements OAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // Create a new user from the principal
        return (OAuth2User) new CustomUserDetails(userRequest.getClientRegistration().getClientName(), userRequest.getAccessToken().getTokenValue());
    }
}
