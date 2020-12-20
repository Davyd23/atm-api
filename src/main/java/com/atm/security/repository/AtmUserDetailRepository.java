package com.atm.security.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AtmUserDetailRepository implements UserDetailRepository {
    private final Map<String, UserDetails> userHolder;
    private final Map<String, UserDetails> authorizationHolder;

    public AtmUserDetailRepository(@Value("${user.default.username}") String defaultUsername,
                                   @Value("${user.default.password}") String defaultPassword) {
        userHolder = new HashMap<String, UserDetails>();
        authorizationHolder = new HashMap<String, UserDetails>();

        // Setup default user
        UserDetails userDetails = new User(defaultUsername, defaultPassword, Collections.<GrantedAuthority>emptyList());
        userHolder.put(defaultUsername, userDetails);
    }

    public UserDetails getByUsername(String username) {
        return userHolder.get(username);
    }

    public UserDetails getByAuthorization(String authorization) {
        return authorizationHolder.get(authorization);
    }

    /*
    * This methods receives the authorization  and an UserDetails object
    * return value is a boolean based on the success of the data being stored
    * */
    public boolean addAuthorization(String authorization, UserDetails userDetails) {
        authorizationHolder.put(authorization, userDetails);
        return true;
    }
}
