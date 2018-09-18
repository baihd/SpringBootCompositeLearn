package com.composite.oauth2server.config;

import com.composite.oauth2server.filter.KaptchaAuthenticationFilter;
import com.composite.oauth2server.handler.FailureHandler;
import com.composite.oauth2server.handler.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("IUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private FailureHandler failureHandler;

    @Autowired
    private SuccessHandler successHandler;

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
    public void configure(WebSecurity web) throws Exception {
        //完全绕过spring security的所有filter
        web.ignoring().antMatchers("/**/*.js", "/**/*.css");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                //在认证用户名之前认证验证码，如果验证码错误，将不执行用户名和密码的认证
                .addFilterBefore(new KaptchaAuthenticationFilter("/login", "/login?error=verifyCode"), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/getKaptchaImage").permitAll()
                .antMatchers(HttpMethod.POST,"/removeToken").permitAll()
                //任何用户都可以访问以"/login"开头的URL
                .antMatchers("/login").permitAll()
                //尚未匹配的任何URL都要求用户进行身份验证
                .anyRequest().authenticated()
                //指定登录页的路径
                .and().formLogin()
                .loginPage("/login")
                //.successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()
                .logout()
                //指定登出的url
                .logoutUrl("/logout")
                //指定登出成功之后跳转的url
                .logoutSuccessUrl("/login")
                .permitAll();
    }


}
