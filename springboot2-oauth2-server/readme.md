#password模式
###获取token
>http://localhost:8060/oauth/token?username=admin&password=123456&grant_type=password&scope=all&client_id=client&client_secret=secret
###刷新token
>http://localhost:8060/oauth/token?grant_type=refresh_token&refresh_token=e263d7f5-c677-4882-a5bc-e5999170ac57&client_id=client&client_secret=secret


#Spring Security
```
//等价于http.authorizeRequests().anyRequest().access("permitAll");
http.authorizeRequests().requestMatchers(AnyRequestMatcher.INSTANCE).access("permitAll");

//表示所有请求都不需要权限控制
http.authorizeRequests().anyRequest().access("permitAll");
        
//表示所有的OPTIONS请求都不需要权限认证
http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll();
        
//匹配 /oauth/*，且http method是GET，不需要权限认证
http.authorizeRequests().antMatchers(HttpMethod.GET, "/oauth/*").permitAll();

http
    //http.authorizeRequests()方法有多个子节点，每个macher按照他们的声明顺序执行
    .authorizeRequests()

    //我们指定任何用户都可以访问多个URL的模式。
    //任何用户都可以访问以"/resources/","/signup", 或者 "/about"开头的URL。
    .antMatchers("/resources/**", "/signup", "/about").permitAll()

    //以 "/admin/" 开头的URL只能让拥有 "ROLE_ADMIN"角色的用户访问。
    //请注意我们使用 hasRole 方法，没有使用 "ROLE_" 前缀。
    .antMatchers("/admin/**").hasRole("ADMIN")

    //任何以"/db/" 开头的URL需要同时具有 "ROLE_ADMIN" 和 "ROLE_DBA"权限的用户才可以访问。
    //和上面一样我们的 hasRole 方法也没有使用 "ROLE_" 前缀。
    .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")

    //任何以"/db/" 开头的URL只需要拥有 "ROLE_ADMIN" 和 "ROLE_DBA"其中一个权限的用户才可以访问。
    //和上面一样我们的 hasRole 方法也没有使用 "ROLE_" 前缀。
    .antMatchers("/db/**").hasAnyRole("ADMIN", "DBA")

    //尚未匹配的任何URL都要求用户进行身份验证
    .anyRequest().authenticated()
    .and()
    // ...
    .formLogin()
    //指定登录页的路径
    .loginPage("/api/user/login")
    //指定登录成功后跳转到/index页面
    .defaultSuccessUrl("/index")
    //指定登录失败后跳转到/login?error页面
    .failureUrl("/login?error")
    .permitAll()
    .and()
    //开启cookie储存用户信息，并设置有效期为14天，指定cookie中的密钥
    .rememberMe().tokenValiditySeconds(1209600).key("mykey")
    .and()
    .logout()
    //指定登出的url
    .logoutUrl("/api/user/logout")
    //指定登出成功之后跳转的url
    .logoutSuccessUrl("/index")
    .permitAll();*/

```
 