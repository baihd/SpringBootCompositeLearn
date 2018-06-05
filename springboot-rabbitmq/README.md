#ubuntu安装rabbitmq  
`echo 'deb http://www.rabbitmq.com/debian/ testing main' |   sudo tee /etc/apt/sources.list.d/rabbitmq.list`
`wget -O- https://www.rabbitmq.com/rabbitmq-release-signing-key.asc | sudo apt-key add -`  
`sudo apt-get update`  
`sudo apt-get install rabbitmq-server`  
  
#启动RabbitMQ管理插件，用于web界面管理  
`sudo rabbitmq-plugins enable rabbitmq_management`  
`sudo service rabbitmq-server restart`  

#测试安装完成的RabbitMQ
`sudo rabbitmqctl status`  

#常用命令列举  
##应用管理

rabbitmqctl status //显示RabbitMQ中间件的所有信息  
rabbitmqctl stop //停止RabbitMQ应用，关闭节点  
rabbitmqctl stop_app //停止RabbitMQ应用  
rabbitmqctl start_app //启动RabbitMQ应用  
rabbitmqctl restart //重置RabbitMQ节点  
rabbitmqctl force_restart //强制重置RabbitMQ节点  

##用户管理
rabbitmqctl add_user username password //添加用户  
rabbitmqctl delete_user username //删除用户  
rabbitmqctl change_password username newpassword //修改密码  
rabbitmqctl list_users //列出所有用户  

##权限控制管理
rabbitmqctl add_vhost vhostpath //创建虚拟主机  
rabbitmqctl delete_vhost vhostpath //删除虚拟主机  
rabbitmqctl list_vhosts //列出所有虚拟主机  
rabbitmqctl set_permissions [-p vhostpath] username <conf> <write> <read> //设置用户权限  
rabbitmqctl clear_permissions [-p vhostpath] username //删除用户权限  
rabbitmqctl list_permissions [-p vhostpath] //列出虚拟机上的所有权限  
rabbitmqctl list_user_permissions username //列出用户权限  

#Web界面管理RabbitMQ  

使用rabbitmq-plugins enable rabbitmq_management来启动Management插件。 默认是可以本地登录localhost:15672，用户名：guest；密码：guest；端口默认15672。