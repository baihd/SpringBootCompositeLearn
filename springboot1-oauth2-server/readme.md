#password模式
###获取token
>http://localhost:8050/oauth/token?username=admin&password=123456&grant_type=password&scope=select&client_id=client&client_secret=123456
###刷新token
>http://localhost:8050/oauth/token?grant_type=refresh_token&refresh_token=258fa81d-0f85-47f8-adac-d15b30294973&client_id=client&client_secret=123456