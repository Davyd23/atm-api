package com.atm.security.service;

import com.atm.security.repository.AtmUserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtmUserDetailService implements UserDetailsService {

    private final AtmUserDetailRepository userDetailRepository;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDetails userDetails = userDetailRepository.getByUsername(userName);
        if (userDetails == null) {
            throw new UsernameNotFoundException("Could not find user: " + userName);
        }
        return userDetails;
    }
}
