package com.composite.oauth2client.config;

import com.composite.oauth2client.filter.ExtraAuthenticationFilter;
import com.composite.oauth2client.handler.ILoginFailureHandler;
import com.composite.oauth2client.handler.ILoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("IUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ILoginSuccessHandler successHandler;

    @Autowired
    private ILoginFailureHandler failureHandler;

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
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/**/*.js", "/**/*.css");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .addFilterBefore(new ExtraAuthenticationFilter("/redirect", "/register"), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                //任何用户都可以访问以"/login"开头的URL
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/user/**").permitAll()
                //尚未匹配的任何URL都要求用户进行身份验证
                .anyRequest().authenticated()
                //指定登录页的路径
                .and().formLogin()
                .loginPage("/login")
                .successHandler(successHandler)
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
