package com.arj.tiketkufinalproject.Security;

import com.arj.tiketkufinalproject.Entity.UsersEntity;
import com.arj.tiketkufinalproject.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepo ui;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsersEntity> userinfo = ui.findByEmail(username);
        return userinfo.map(UserInfoUserDetails::new).orElseThrow(()->new UsernameNotFoundException("Username Doesn't Exsist" + username));

    }
}
