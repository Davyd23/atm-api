package com.atm.security.repository;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailRepository {

    public UserDetails getByUsername(String username);

    public UserDetails getByAuthorization(String authorization);

    public boolean addAuthorization(String authorization, UserDetails userDetails);
}
