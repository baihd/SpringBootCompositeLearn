#Ubuntu安装FastDFS

##一、下载文件
fastdfs,libfastcommon,fastdfs-nginx-module

##二、创建必要文件夹
###1.下载tar.gz软件包并安装
`sudo mkdir /opt/soft/software`
###2.上传文件测试文件夹
`sudo mkdir /opt/soft/software/testfile`
###3.fastdfs根文件
`sudo mkdir /opt/soft/data`
###4.tracker文件配置路径
`sudo mkdir /opt/soft/data/fastdfs/track`
###5.storage配置路径
`sudo mkdir /opt/soft/data/fastdfs/storage`
###6.client配置路径
`sudo mkdir /opt/soft/data/fastdfs/clientlog`

##三、安装libfastcommon与fastdfs
###1.安装libfastcommon
####进入libfastcommon目录
`cd libfastcommon`
####编译文件
`sudo ./make.sh`
####安装
`sudo ./make.sh install`

###2.安装fastdfs
####进入fastdfs目录
`cd fastdfs`
####编译文件
`sudo ./make.sh`
####安装
`sudo ./make.sh install`

##四、配置tracker,storage,clent,http服务
###以上安装成功后,进入/etc/fdfs/目录
###1.配置tracker服务
`sudo cp tracker.conf.sample tracker.conf`  
`sudo vim tracker.conf`
####设置tracker的数据文件和日志目录
`base_path=/opt/soft/data/fastdfs/track`
####设置http端口号，默认为8080
`http.server_port=80`

###2.配置storage服务
`sudo cp storage.conf.sample storage.conf`  
`sudo vim storage.conf`
####修改组名
`group_name=group1`
####设置storage数据文件和日志目录
`base_path=/opt/soft/data/fastdfs/storage`
####实际文件存储路径
`store_path0=/opt/soft/data/fastdfs/storage`
####改为本地ip
`tracker_server=10.0.16.50:22122`
####设置端口号，如果没有冲突尽量默认
`http.server_port=8888`
 
###3.配置client服务
`sudo cp client.conf.sample client.conf`  
`sudo vim client.conf`
####设置客户端存储路径
`base_path=/opt/soft/data/fastdfs/clientlog`
####改为本地ip
`tracker_server=10.0.16.50:22122`
####tracker服务器的http端口号，必须和tracker的设置对应起来
`http.tracker_server_port=80`
####include http.conf这句，原配置文件中有2个#，删掉一个
`#include http.conf`

###4.配置http服务
`sudo cp /opt/soft/software/fastdfs/conf/http.conf /etc/fdfs/http.conf`  
`cd /etc/fdfs`  
`sudo vim http.conf`  
####修改
`http.anti_steal.token_check_fail=/opt/soft/fastdfs/data/fastdfs/httppic/anti-steal.jpg`

##五、启动tracker和storage服务
###启动
`/usr/bin/fdfs_trackerd /etc/fdfs/tracker.conf`  
`/usr/bin/fdfs_storaged /etc/fdfs/storage.conf`  
###查看tracker和storage服务
`sudo netstat -unltp|grep fdfs`

##六、测试上传文件功能
`cd /opt/soft/software/testfile`  
`sudo vim test.txt`  
`fdfs_test /etc/fdfs/client.conf upload /opt/soft/software/testfile/test.txt`  
###复制url地址到浏览器
`http://10.0.16.50/group1/M00/00/00/CgAQMltGuHqAF6kmAAAAFa5qxQs243.txt`

##七、安装nginx和fastdfs-nginx-module,pcre,zlib
###在安装Nginx之前，需要安装如下（gcc/pcre/zlib/openssl）插件
###1.openssl安装：
`sudo apt-get install openssl libssl-dev`
###2.pcre安装： 
`sudo apt-get install libpcre3 libpcre3-dev`  
`sudo apt-get install openssl libssl-dev`
###3.zlib安装：
`sudo apt-get install zlib1g-dev`
###4.gcc安装：
`sudo apt-get install build-essential`

##八、配置nginx安装，加入fastdfs-nginx-module模块
`cd nginx-1.13.3`  
`sudo ./configure --add-module=/opt/soft/software/fastdfs-nginx-module/src/`
###编译
`sudo make`
###安装
`sudo make install`
###查找安装路径
`whereis nginx`
###启动、停止nginx
`cd /usr/local/nginx/sbin/`
###启动
`./nginx` 
###此方式相当于先查出nginx进程id再使用kill命令强制杀掉进程
`./nginx -s stop`
###此方式停止步骤是待nginx进程处理任务完毕进行停止
`./nginx -s quit`
###重启
`./nginx -s reload`
###查询nginx进程
`sudo ps aux|grep nginx`
###设置开机自启动即在rc.local增加启动代码
`sudo vim /etc/rc.local`
###增加一行
`/usr/local/nginx/sbin/nginx`
###设置执行权限：
`sudo chmod 755 rc.local`

##九、配置fastdfs-nginx-module和nginx
###1.配置mod-fastdfs.conf，并拷贝到/etc/fdfs文件目录下
`tracker_server=10.0.16.50:22122`  
`url_have_group_name = true`  
`store_path0=/opt/soft/data/fastdfs/storage`  
`sudo cp mod_fastdfs.conf /etc/fdfs/`  
###2.把fastdfs下面的配置中还没有存在/etc/fdfs中的拷贝进去
`cd /opt/soft/software/fastdfs/conf`  
`sudo cp anti-steal.jpg http.conf mime.types /etc/fdfs/`
###3.关闭防火墙
`sudo ufw disable`
###4.配置Nginx,编辑nginx.config
`cd /usr/local/nginx/conf`  
`sudo vim nginx.conf`  
###5.在配置文件中加入
####8888端口值是要与/etc/fdfs/storage.conf中的http.server_port=8888相对应
`user root;`
```
location ~/group([0-9])/M00 {
    ngx_fastdfs_module;
}  
```
###6.由于我们配置了group1/M00的访问，我们需要建立一个group1文件夹，并建立M00到data的软链接
`sudo mkdir /opt/soft/fastdfs/data/fastdfs/storage/data/group1`  
`sudo ln -s /opt/soft/fastdfs/data/fastdfs/storage/data /opt/soft/fastdfs/data/fastdfs/storage/data/group1/M00`
###7.启动nginx 
`/usr/local/nginx/sbin/nginx`
####如果发现：
```
nginx: [emerg] bind() to 0.0.0.0:80 failed (98: Address already in use)
nginx: [emerg] bind() to 0.0.0.0:80 failed (98: Address already in use)
nginx: [emerg] bind() to 0.0.0.0:80 failed (98: Address already in use)
nginx: [emerg] bind() to 0.0.0.0:80 failed (98: Address already in use)
nginx: [emerg] bind() to 0.0.0.0:80 failed (98: Address already in use)
nginx: [emerg] still could not bind()
```
####说明80端口被占用，杀掉这个进程：
`sudo killall -9 nginx`
####再次启动nginx：
`sudo /usr/local/nginx/sbin/nginx`
####查看是否启动：
`sudo ps aux|grep nginx`

##十、开机启动服务
###1.启动开机检查Tracker、Storage服务是否开启：
####查看服务
`sudo netstat -unltp|grep fdfs`  
####启动服务
`/usr/bin/fdfs_trackerd /etc/fdfs/tracker.conf`  
`/usr/bin/fdfs_storaged /etc/fdfs/storage.conf`  
或  
`sudo service fdfs_trackerd start`
`sudo service fdfs_storaged start`
###2.试tracker和storage服务通信
`/usr/bin/fdfs_monitor /etc/fdfs/storage.conf`
###3.查看Nginx是否开启：
`sudo ps aux|grep nginx`
`sudo /usr/local/nginx/sbin/nginx`
###4.测试上传下载
`fdfs_test /etc/fdfs/client.conf upload /software/testfile/test.txt`

##十一、使用fastdfs-client-java包
###下载fastdfs-client-java
>https://github.com/happyfish100/fastdfs-client-java
###切换到fastdfs-client-java解压的目录下,输入命令放入到本地仓库
`mvn clean install`
###加入到pom.xml文件中
```
<dependency>
    <groupId>org.csource</groupId>
    <artifactId>fastdfs-client-java</artifactId>
    <version>1.27-SNAPSHOT</version>
</dependency>
```