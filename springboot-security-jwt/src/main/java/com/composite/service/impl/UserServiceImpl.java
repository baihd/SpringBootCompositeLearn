package com.composite.service.impl;

import com.composite.dao.IUserRepository;
import com.composite.entity.IRole;
import com.composite.entity.IUser;
import com.composite.utils.JwtTokenUtil;
import com.composite.service.IUserService;
import com.composite.utils.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private IUserRepository userRepository;

    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, IUserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    @Override
    public String login(String username, String password) {
        //使用name和password封装成为的token
        Authentication upToken = new UsernamePasswordAuthenticationToken(username, password);
        // 将token传递给Authentication进行验证
        Authentication authentication = authenticationManager.authenticate(upToken);
        //传递authentication对象，来建立安全上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public String register(IUser user) {
        String username = user.getUsername();
        if (userRepository.findByUsername(username) != null) {
            return "用户已存在";
        }
        String rawPassword = user.getPassword();
        user.setPassword(BCryptUtil.encode(rawPassword));
        List<IRole> roles = new ArrayList<>();
        IRole role = new IRole();
        role.setName("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        userRepository.insert(user);
        return "success";
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring("Bearer ".length());
        if (!jwtTokenUtil.isTokenExpired(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return "error";
    }

}
