package com.jasper.pigrakker.service;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = getUserService(userRequest);

        if (delegate != null) {
            return delegate.loadUser(userRequest);
        } else {
            throw new OAuth2AuthenticationException("Cannot handle OAuth2 user authentication for provider " + userRequest.getClientRegistration().getClientName());
        }
    }

    private OAuth2UserService<OAuth2UserRequest, OAuth2User> getUserService(OAuth2UserRequest userRequest) {
        // Use the provider name to determine the appropriate user service to use
        String provider = userRequest.getClientRegistration().getClientName();

        switch (provider) {
            case "google":
                return new GoogleOAuth2UserService();
//            case "facebook":
//                return new FacebookOAuth2UserService();
            // Add other providers here as needed
            default:
                return null;
        }
    }
}