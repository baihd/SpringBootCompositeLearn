#password模式
###获取token
>http://localhost:8050/oauth/token?username=admin&password=123456&grant_type=password&scope=all&client_id=client&client_secret=secret
###刷新token
>http://localhost:8050/oauth/token?grant_type=refresh_token&refresh_token=2fe04605-4437-4f18-8b7b-0e1415453b19&client_id=client&client_secret=secret
