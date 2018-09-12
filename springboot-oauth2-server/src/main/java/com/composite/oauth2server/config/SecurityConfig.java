package com.composite.oauth2server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("IUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                //任何用户都可以访问以"/**/*.js","/signup", 或者 "/about"开头的URL。
                .antMatchers("/**/*.js", "/**/*.css").permitAll()
                //尚未匹配的任何URL都要求用户进行身份验证
                .anyRequest().authenticated()
                //指定登录页的路径
                .and().formLogin().loginPage("/login")
                //指定登录成功后跳转到/index页面
                .defaultSuccessUrl("/index")
                //指定登录失败后跳转到/login?error页面
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                //指定登出的url
                .logoutUrl("/logout")
                //指定登出成功之后跳转的url
                .logoutSuccessUrl("/login")
                .permitAll();
    }


}
