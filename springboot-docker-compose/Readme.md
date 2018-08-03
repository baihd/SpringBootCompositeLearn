#Docker-Compose.yml详解
##*一份标准配置文件应该包含version、services、networks三大部分*
##*其中最关键的就是services和networks两个部分*
##一.services书写规则
###1.image:指定服务的镜像名称或镜像id
```
image: redis
image: ubuntu:14.04
```
###2.build:基于Dockerfile自动构建镜像
```
build:
    context: .
    dockerfile: app.dockerfile
```
####*如果同时指定了image和build，那么Compose会构建镜像并且把镜像命名为image后面的名字*
###3.command:覆盖容器启动后默认执行的命令
```
command: mvn clean spring-boot:run -Dspring-boot.run.profiles=docker
```
####*-Dspring-boot.run.profiles=docker表示使用application-docker.properties文件配置信息进行启动*
###4.container_name:控制容器命名
```
container_name: app
```
###5.depends_on:解决容器的依赖、启动先后的问题
```
depends_on:
  - db
  - redis
```
###6.environment:设置镜像变量
```
environment:
  MYSQL_DATABASE: dockerdb
  MYSQL_USER: root
  MYSQL_PASSWORD: root
  MYSQL_ROOT_PASSWORD: root
```
###7.env_file:从文件中获取环境变量，可指定一个文件路径或路径列表
```
env_file: .env
env_file:
  - ./common.env   # 共用
  - ./apps/web.env # web用
  - /opt/secrets.env # 密码用
```
###8.external_links:使Compose能够连接这些不在docker-compose.yml中定义的容器
####*注意:外部容器中必须至少有一个容器是连接到与项目内的服务的同一个网络里面*
```
external_links:
 - redis_1
 - project_db_1:mysql
 - project_db_1:postgresql
```
###9.links:链接到其它服务中的容器
```
links:
  - db
  - db:database
  - redis
```
###10.extra_hosts:添加主机名
```
extra_hosts:
 - "somehost:162.242.195.82"
 - "otherhost:50.31.209.229"
```
###11.ports:映射端口
```
#SHORT语法
ports:
 - host:container
```
```
#LONG语法
ports:
    #容器内的端口
  - target: 80
    #公开的端口
    published: 8080
    #端口协议（tcp或udp）
    protocol: tcp
    #host:用在每个节点还是哪个发布的主机端口或使用ingress:用于集群模式端口进行平衡负载
    mode: host
```
###12.volumes:挂载一个目录或者一个已存在的数据卷容器
```
volumes:
  #只是指定一个路径，Docker会自动在创建一个数据卷（这个路径是容器内部的）
  - /var/lib/mysql
  #使用绝对路径挂载数据卷
  - /opt/data:/var/lib/mysql
  #以Compose配置文件为中心的相对路径作为数据卷挂载到容器
  - ./cache:/tmp/cache
  #使用用户的相对路径（~/ 表示的目录是 /home/<用户目录>/或者/root/）
  - ~/configs:/etc/configs/:ro
  #已经存在的命名的数据卷
  - datavolume:/var/lib/mysql
```
###13.volumes_from:从其它容器或者服务挂载数据卷
```
volumes_from:
  - service_name
  - service_name:ro
  - container:container_name
  - container:container_name:rw
```
###14.networks:通常应用于集群服务，解决网络隔离问题
```
networks:
  default:
    driver: bridge
```
###15.dns:配置dns服务器
```
dns: 8.8.8.8
dns:
  - 8.8.8.8
  - 9.9.9.9
```
###16.dns_search:配置DNS的搜索域名
```
dns_search: example.com
dns_search:
  - dc1.example.com
  - dc2.example.com
```