#一.Docker安装
##1.获取最新版本的Docker安装包
`wget -qO- https://get.docker.com/ | sh`
##2.输入当前用户的密码后,就会下载脚本并且安装Docker及依赖包

#Docker命令
##1.打开
`sudo service docker start`
##2.关闭
`sudo service docker stop`
##3.重启
`sudo service docker restart`
##4.查看状态
`sudo service docker status`

#Docker容器生命周期管理
##1.创建容器
`sudo docker create [options] image`
##2.创建容器并运行命令
`sudo docker run [options] image`  
##3.启动容器
`sudo docker start container`
##4.停止容器
`sudo docker stop container`
##5.重启容器
`sudo docker restart container`
##6.杀掉运行中的容器
`sudo docker kill -s KILL container`
##7.删除容器
`sudo docker rm [options] container`
###强制删除容器db01、db02
`sudo docker rm -f db01 db02`
###移除容器nginx01对容器db01的连接，连接名db
`sudo docker rm -l db`
###删除容器nginx01,并删除容器挂载的数据卷
`sudo docker rm -v nginx01`
##8.暂停/恢复容器
`sudo docker pause container`  
`sudo docker unpause container`
##9.在运行的容器中执行命令
`sudo docker exec [options] container`

#容器操作
##1.列出容器
`sudo docker ps [options]`
##2.获取容器/镜像的元数据
`sudo docker inspect [options] name|id`
##3.查看容器中运行的进程信息
`sudo docker top [options] container`
##4.连接到正在运行中的容器
`sudo docker attach [options] container`
##5.从服务器获取实时事件
`sudo docker events [options]`
##6.获取容器日志
`sudo docker logs [options] container`
##7.阻塞运行直到容器停止，然后打印出它的退出代码
`sudo docker wait [options] container`
##8.将文件系统作为一个tar归档文件导出到stdout
`sudo docker export [options] container`
##9.列出容器端口映射
`sudo docker port [options] container`

#容器rootfs命令
##1.从容器创建新的镜像
`sudo docker commit [options] container`
##2.拷贝容器与主机之间的数据
`sudo docker cp [options] container:src_path dest_path|`  
`sudo docker cp [options] src_path|- container:dest_path`
##3.检查容器里文件结构的更改
`sudo docker diff [options] container`

#镜像仓库
##1.登陆镜像仓库
`sudo docker login [options] [server]`
##2.登出镜像仓库
`sudo docker logout [options] [server]`
##3.拉取或者更新指定镜像
`sudo docker pull [options] name`
##4.上传镜像到镜像仓库
`sudo docker push [options] name`
##5.查找镜像
`sudo docker search [options] term`

#本地镜像管理
##1.列出本地镜像
`sudo docker images [options] [repository[:tag]]`
##2.删除本地镜像
`sudo docker rmi [options] image [image...]`
##3.标记本地镜像，将其归入某一仓库
`sudo docker tag [options] image[:tag] [registryhost/][username/]name[:tag]`
##4.创建镜像
`sudo docker build [options] path`
##5.查看镜像创建历史
`sudo docker history [options] image`
##6.将指定镜像保存成tar归档文件
`sudo docker save [options] image [image...]`
##7.从归档文件中创建镜像
`sudo docker import [options] file|url|- [repository[:tag]]`

#信息
##1.显示Docker系统信息
`sudo docker info [options]`
##2.显示Docker版本信息
`sudo docker version [options]`

#docker发布jar包
##1.打包
`mvn clean package`
##2.创建镜像
`sudo docker build -t myImage -f Dockerfile`
##3.显示镜像
`sudo docker images`
##4.创建容器并运行命令
`sudo docker run -p 8080:8080 myImage`
##5.显示所有容器
`sudo docker ps -a`
##6.显示容器日志
`sudo docker logs container`
##7.停止容器
`sudo docker stop container`
##8.删除容器
`sudo docker rm container`
##9.删除镜像
`sudo docker rmi myImage`

#二.Docker搭建私有仓库
##1.编辑文件daemon.json
```
sudo vim /etc/docker/daemon.json
增加一行:
{ "insecure-registries": ["IP:5000"] }
```
##2.下载仓库镜像
`sudo docker pull registry`
##3.将宿主机端口映射到容器，容器的5000端口不能更改
`sudo docker run -d -p 5000:5000 registry`
##4.下载镜像
`sudo docker pull busybox`
##5.为镜像打tag，注明ip和端口
`sudo docker tag busybox IP:5000/busybox`
##6.上传私有镜像到私有仓库
`sudo docker push IP:5000/busybox`
##7.查看信息
>http://IP:5000/v2/_catalog

#三.Docker搭建Swarm集群
```
docker1:10.0.16.150
dokcer2:10.0.16.143
其中docker1同时充当swarm manager管理集群
```
##1.在各个docker主机上安装swarm，启动dockerDaemon
```
sudo docker pull swarm
sudo docker run --rm swarm -v
sudo docker service docker stop
sudo dockerd -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock &
sudo netstat -tunlp|grep docker
```
##2.使用swarm命令在dockerHub上注册
```
sudo docker run --rm swarm create
b56a34630dd87e502289eff4871ea0ea
会产生一个token用来标识和管理集群
```
##3.将各个节点加入swarm集群
```
sudo docker run -d swarm join --addr=10.0.16.150:2375 token://b56a34630dd87e502289eff4871ea0ea
sudo docker run -d swarm join --addr=10.0.16.143:2375 token://b56a34630dd87e502289eff4871ea0ea
```
##4.配置管理节点启用manager服务
```
在docker1上执行
sudo docker run -d -p 12375:2375 swarm manage token://b56a34630dd87e502289eff4871ea0ea
```
##5.查看docker进程
```
sudo netstat -tunlp |grep docker
```
##6.在任何一个节点上查看集群上的所有节点列表
```
sudo docker run --rm swarm list token://b56a34630dd87e502289eff4871ea0ea
```
##7.查看集群节点的信息
```
sudo docker –H 10.0.16.143:2376 info
```


#在使用docker的过程中如果报出以下错误：
```
1.无法连接到docker daemon(docker守护进程)
docker: Cannot connect to the Docker daemon. Is the docker daemon running on this host?.
See 'docker run --help'.
2.重启docker服务失败
  sudo service docker restart
  docker stop/waiting
  start: Job failed to start
3.在容器内部无法ping通百度、谷歌等外网
  unknown host www.baidu.com
```
##解决
```
#1.查询本机的DNS Server
nm-tool | grep DNS
#2.设置/etc/docker/daemon.json文件
{"dns": ["8.8.8.8"]}
#3.重启docker 服务
sudo service docker restart


```






