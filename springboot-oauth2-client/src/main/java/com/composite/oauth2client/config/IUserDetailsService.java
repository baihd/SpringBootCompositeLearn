package com.composite.oauth2client.config;

import com.composite.oauth2client.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class IUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, Object> userMap = userDao.findUserByName(username);
        if (userMap != null) {
            String password = userMap.get("password").toString();
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            return new User(username, password, grantedAuthorityList);
        } else {
            throw new UsernameNotFoundException("username: " + username + " do not exist!");
        }
    }


}
