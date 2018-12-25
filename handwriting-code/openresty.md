##一、安装环境
###1.创建目录/usr/servers，所有软件安装在此目录
`mkdir -p /usr/servers`

###2.安装依赖
`apt-get install libreadline-dev libncurses5-dev libpcre3-dev libssl-dev perl`

###3.下载ngx_openresty-1.7.7.2.tar.gz并解压
`wget http://openresty.org/download/ngx_openresty-1.7.7.2.tar.gz`  
`tar -xzvf ngx_openresty-1.7.7.2.tar.gz`

###4.安装LuaJIT
*bundle目录里存放着nginx核心和很多第三方模块*  
`cd /usr/servers/ngx_openresty-1.7.7.2/bundle/LuaJIT-2.1-20150120/`  
`make clean && make && make install`  
`ln -sf luajit-2.1.0-alpha /usr/local/bin/luajit`  

###5.下载ngx_cache_purge模块，该模块用于清理nginx缓存
`cd /usr/servers/ngx_openresty-1.7.7.2/bundle`  
`wget https://github.com/FRiCKLE/ngx_cache_purge/archive/2.3.tar.gz`  
`tar -xvf 2.3.tar.gz`  

###6.下载nginx_upstream_check_module模块，该模块用于ustream健康检查
`cd /usr/servers/ngx_openresty-1.7.7.2/bundle`  
`wget https://github.com/yaoweibin/nginx_upstream_check_module/archive/v0.3.0.tar.gz`  
`tar -xvf v0.3.0.tar.gz`  

###7.安装ngx_openresty
`cd /usr/servers/ngx_openresty-1.7.7.2`  
`./configure --prefix=/usr/servers --with-http_realip_module  --with-pcre  --with-luajit --add-module=./bundle/ngx_cache_purge-2.3/ --add-module=./bundle/nginx_upstream_check_module-0.3.0/ -j2
 make && make install`  
####取用户真实ip模块
`--with-http_realip_module`
####Perl兼容的达式模块  
`--with-pcre`   
####集成luajit模块 
`--with-luajit`     
####添加自定义的第三方模块，如此次的ngx_che_purge
`--add-module`  

###8.启动nginx
####检测配置是否正确
`/usr/servers/nginx/sbin/nginx -t`
####启动
`/usr/servers/nginx/sbin/nginx`
####重启
`/usr/servers/nginx/sbin/nginx -s reload`

##二、配置nginx+lua开发环境
###1.创建lua.conf 
`cd /usr/servers/nginx/conf`  
`vim lua.conf`  
```
server { 
    listen 80; 
    server_name _;
    #HelloWorld
    location /lua { 
        default_type 'text/html'; 
        content_by_lua 'ngx.say("hello world")'; 
    }
}
```
###2.编辑nginx.conf配置文件 
`vim /usr/servers/nginx/conf/nginx.conf`
####在http部分添加如下配置
```
#lua模块路径，多个之间”;”分隔，其中”;;”表示默认搜索路径，默认到/usr/servers/nginx下找
lua_package_path "/usr/servers/lualib/?.lua;;"; #lua模块 
lua_package_cpath "/usr/servers/lualib/?.so;;"; #c模块 
include lua.conf; #单独lua配置
```
###3.测试是否正常
`/usr/servers/nginx/sbin/nginx -t `
###4.重启nginx
`/usr/servers/nginx/sbin/nginx -s reload`
###5.访问如http://127.0.0.1/lua

##三、lua代码文件和缓存
####1.lua_code_cache
`默认情况下lua_code_cache是开启的，即缓存lua代码，即每次lua代码变更必须reload nginx才生效`
####2.lua代码文件
`vim /usr/servers/lua/test.lua`  
`ngx.say("hello world by lua!");`  
`vim /usr/servers/nginx/conf/lua.conf`  
```
location /lua { 
    default_type 'text/html';
    lua_code_cache off;
    content_by_lua_file /usr/servers/lua/test.lua; 
}

```