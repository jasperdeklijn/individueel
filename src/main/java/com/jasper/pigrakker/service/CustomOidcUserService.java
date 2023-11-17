package com.jasper.pigrakker.service;

import com.jasper.pigrakker.config.GoogleOAuth2UserInfo;
import com.jasper.pigrakker.model.User;
import com.jasper.pigrakker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CustomOidcUserService extends OidcUserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        Map<String, Object> attributes = oidcUser.getAttributes();
        GoogleOAuth2UserInfo userInfo = new GoogleOAuth2UserInfo();
        userInfo.setEmail((String) attributes.get("email"));
        userInfo.setId((String) attributes.get("sub"));
        userInfo.setImageUrl((String) attributes.get("picture"));
        userInfo.setName((String) attributes.get("name"));
        updateUser(userInfo);
        return oidcUser;
    }

    private void updateUser(GoogleOAuth2UserInfo userInfo) {
        Optional<User> optionalUser = userRepository.findByEmail(userInfo.getEmail());
        User user = new User();
        if(optionalUser.isPresent()){
            user = optionalUser.get();
        }else{
            user.setEmail(userInfo.getEmail());
            user.setImageUrl(userInfo.getImageUrl());
            user.setName(userInfo.getName());

        }

        userRepository.save(user);
    }
}