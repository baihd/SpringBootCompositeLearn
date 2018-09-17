package com.composite.oauth2server.config;

import com.composite.oauth2server.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class IUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, Object> userMap = userDao.findUserByName(username);
        if (userMap != null) {
            String password = userMap.get("password").toString();
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            grantedAuthorityList.add(new IGrantedAuthority("MY_ROLE1", "MY_MENU1"));
            return new User(username, passwordEncoder.encode(password), grantedAuthorityList);
        } else {
            throw new UsernameNotFoundException("username: " + username + " do not exist!");
        }
    }


}
