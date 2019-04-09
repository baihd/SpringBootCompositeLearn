# ELK日志分析系统

## 一、安装JDK环境
### A.安装jdk1.8
#### 1.创建目录:/usr/lib/jvm
`sudo mkdir /usr/lib/jvm`  
#### 2.解压到 /usr/lib/jvm/ 下
`sudo tar xvzf jdk-8u91-linux-x64.tar.gz -C /usr/lib/jvm/`
#### 3.配置环境变量
`sudo vim /etc/profie`
##### 在文档最下部追加
```
export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_91
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$PATH
```
#### 4.立即生效配置
`source /etc/profile`
#### 5.查看java版本
`java -version`

## 二、安装低版本的ELK
### A.安装LogStash
#### 1.解压logstash-2.3.3.tar.gz
`sudo tar xvzf logstash-2.3.3.tar.gz`
#### 2.在logstash-2.3.3目录下创建logstash-test.conf配置文件，内容如下：
```
input { stdin { } }
output {
   stdout { codec=> rubydebug }
}
```  
*Logstash使用input和output定义收集日志时的输入和输出的相关配置，
本例中input定义了一个叫"stdin"的input，output定义一个叫"stdout"的output。
无论我们输入什么字符，Logstash都会按照某种格式来返回我们输入的字符，
其中output被定义为"stdout"并使用了codec参数来指定logstash输出格式。*
#### 3.启动
`./bin/logstash agent -f logstash-test.conf`
#### 4.退出
*启动后，在屏幕上输入什么内容，就会在console里显示出来。
说明安装成功。使用Ctrl+C退出进程。*

### B.安装elasticsearch
`sudo tar xvzf elasticsearch-2.3.3.tar.gz`
#### 1.修改配置文件，允许远程访问：
`sudo vim elasticsearch-2.3.3/config/elasticsearch.yml`  
##### 修改network
`network.host: 0.0.0.0`   
#### 2.后台启动
`./bin/elasticsearch -d`
#### 3.查找ES进程
`ps -ef | grep elastic`
#### 4.安装elasticsearch插件head：
`cd elasticsearch-2.3.3`  
`./bin/plugin install mobz/elasticsearch-head`
#### 5.测试elasticsearch与logstash是否能链接成功：
*在logstash-2.3.3安装目录下创建一个用于测试logstash使用elasticsearch作为logstash的后端的测试文件logstash-es-simple.conf，
 该文件中定义了stdout和elasticsearch作为output，这样的“多重输出”即保证输出结果显示到屏幕上，同时也输出到elastisearch中，内容如下：*
`sudo vim logstash-es-simple.conf`
```
input { stdin { } }
output {
   elasticsearch {hosts => "localhost" }
   stdout { codec=> rubydebug }
}
```  
*hosts为elasticsearch的主机，这里两者在同一机器上*
#### 6.启动logStash
`./bin/logstash agent -f logstash-es-simple.conf`
#### 7.elasticsearch访问地址：
>http://127.0.0.1:9200/_plugin/head/

### C.安装kibana：
`tar xvzf kibana-4.5.1-linux-x64.tar.gz`
#### 1.修改kibana.yml
`cd kibana-4.5.1-linux-x64`  
`sudo vim kibana.yml`  
```
#kibana端口
server.port: 5601 
#对外服务的主机
server.host: "0.0.0.0"  
elasticsearch.url: "http://localhost:9200"
#在elasticsearch中添加.kibana索引
kibana.index: ".kibana  
```
#### 2.后台启动
`./bin/kibana &`
#### 3.查看是否启动
`sudo netstat -apn|grep 5601` 
#### 4.关闭
`sudo kill -9 PID`
#### 5.kibana访问地址
>http://127.0.0.1:5601  

*登录后，配置一个索引，默认Kibana的数据被指向Elasticsearch，
使用默认的logstash-*的索引名称，并且是基于时间的，点击“Create”即可。
点击“Discover”选项卡，可以搜索和浏览elasticsearch中的数据，
默认搜索最近15分钟的数据，也可以自定义。至此，ELK平台已经部署完成。*

#### 6.配置logstash作为Indexer：
##### 将logstash配置为索引器，并将logstash的日志数据存储到elasticsearch。
*本案例是索引本地系统日志*
*在logstash工作目录中创建配置文件logstash-indexer.conf，内容如下：*
```
input {
  file {
    type =>"syslog"
    path => ["/var/log/messages", "/var/log/syslog" ]
  }
  syslog {
    type =>"syslog"
    port =>"5544"
  }
}
output {
  stdout { codec=> rubydebug }
  elasticsearch {
    hosts => "localhost" 
    index => "logstash-%{+YYYY.MM}"
  }
}
```

## 三、安装高版本的ELK
### A.安装elasticsearch5.X以上
`sudo tar zxvf elasticsearch-6.3.1.tar.gz`
#### 1.修改配置文件，允许远程访问 
`sudo vim elasticsearch-6.3.1/config/elasticsearch.yml` 
#### 2.修改network 
`network.host: 0.0.0.0` 

### B.安装elasticsearch-head插件
`sudo unzip node-v8.11.3-linux-x64.zip`
#### 1.修改目录名称
`sudo mv node-v8.11.3-linux-x64.zip node`
#### 2.将node和npm设置为全局
`sudo ln /opt/soft/node/bin/node /usr/local/bin/node`  
`sudo ln /opt/soft/node/bin/npm /usr/local/bin/npm`
#### 3.安装grunt和grunt-cli
`sudo npm install -g grunt`   
`sudo npm install -g grunt-cli`
#### 4.解压elasticsearch-head
`sudo unzip elasticsearch-head-master.zip`
#### 5.安装head
`cd elasticsearch-head`  
`npm install`  
#### 6.配置head
##### 6.1.修改app.js
`vim elasticsearch-head/_site/app.js`  
##### 6.2.修改localhost为elasticsearch的ip
`this.base_uri = this.config.base_uri || this.prefs.get("app-base_uri") || "http://localhost:9200";`
##### 6.3.修改Gruntfile.js
```
connect: {
    server: {
        options: {
          hostname: "*",                                         
          port: 9100,
          base: '.',
          keepalive: true
        }   
    }   
}
```
#### 7.配置elasticsearch
`vim elasticsearch.yml`
##### 增加以下两行
`http.cors.enabled: true`  
`http.cors.allow-origin: "*"`  
#### 8.启动elasticsearch和head插件
`cd /opt/soft/elasticsearch-6.3.1/bin/`  
`./elasticsearch`  
`./elasticsearch -d`  
`cd /opt/soft/elasticsearch-head`  
`npm start`  
`nohup npm start &`  
`nohup grunt server &`
#### 9.访问
>http://127.0.0.1:9100
#### 10.停止elasticsearch
`ps -ef|grep elastic`  
`kill -9 端口号`
#### 11.停止head
`netstat -tunlp|grep 9100`  
`kill -9 端口号`

### C.安装logstash-6.3.1
`sudo tar zxvf logstash-6.3.1.tar.gz`  
#### 1.启动：
`./bin/logstash -f logstash-test.conf`
`nohup ./bin/logstash -f ./configs &`
#### 2.停止
`ps -ef |grep logstash`  
`kill -9 端口号`


### D.安装kibana-6.3.1-linux-x86_64
`sudo tar zxvf kibana-6.3.1-linux-x86_64.tar.gz`
#### 1.修改配置  
`sudo vim /opt/soft/kibana-6.3.1-linux-x86_64/config/kibana.yml`
`server.host: "10.0.16.150"`  
`elasticsearch.url: "http://10.0.16.150:9200"`  
#### 2.启动
`cd kibana-6.3.1-linux-x86_64/bin`  
`./kibana`  
`nohup ./bin/kibana &`  
#### 3.停止
`netstat -anltp|grep 5601`  
`kill -9 端口号`

### E.安装filebeat-6.3.1-linux-x86_64
`sudo tar zxvf filebeat-6.3.1-linux-x86_64.tar.gz`
#### 1.修改配置  
`sudo vim filebeat.yml`
```
filebeat.inputs:
- type: log
  #Change to true to enable this input configuration.
  enabled: true
  #Paths that should be crawled and fetched. Glob based paths.
  paths:
    - /opt/soft/logs/*.log
    
#output.elasticsearch:
  # Array of hosts to connect to.
  #hosts: ["localhost:9200"]
    
output.logstash:
  # The Logstash hosts
  hosts: ["localhost:5044"]
```
#### 2.启动
`./filebeat -e -c filebeat.yml -d "publish"`  
`nohup ./filebeat -e -c filebeat.yml >/dev/null 2>&1 &`
#### 3.停止
`ps -ef |grep filebeat`  
`kill -9 端口号`

#### 4.filebeat在logstash中的配置
```
input {
  beats{
      port => "5044"
      type => "logs"
  }
}
filter{
  mutate {
    remove_field => ["host","[beat][hostname]","[beat][name]","count","fields","input_type","offset","type","beat","@version"]
  }
}
output {
  stdout { codec=> rubydebug }
  elasticsearch {
    hosts => "http://127.0.0.1:9200/"
    index => "logstash-%{+YYYY.MM}"
  }
}
```

## 四、使用Docker安装ELK+Filebeat
### A.前提条件
#### 1.Docker至少得分配3GB的内存；
#### 2.Elasticsearch至少需要单独2G的内存；
`sudo vim /etc/sysctl.conf`
##### 增加
`vm.max_map_count=262144`
##### 查看
`sudo sysctl -p`
#### 3.防火墙开放相关端口；

### B.下载镜像
`sudo docker pull sebp/elk`

### C.启动docker容器
`sudo docker run -p 5601:5601 -p 9200:9200 -p 5044:5044 -it --name elk sebp/elk`
* -p 指定容器和宿主机映射端口 
* 5601：kibana服务端口 HTTP (web访问) 
* 9200：Elasticsearch 开发端口 HTTP,保存数据到Elasticsearch中使用 
* 5044：logstash 收集日志端口 TCP 

### D.进入ELK容器
`sudo docker exec -it elk /bin/bash`
#### 1.修改配置文件
`vi /etc/logstash/conf.d/02-beats-input.conf`
#### 2.将以下三行删除掉
```
ssl => true
ssl_certificate => "/pki/tls/certs/logstash.crt"
ssl_key => "/pki/tls/private/logstash.key"
```
* 意思是否使用证书，如果需要使用证书，将logstash.crt拷贝到客户端，然后在filebeat.yml里面添加路径即可

### E.重启docker容器
`sudo docker restart (containerId)`

### F.配置Filebeat
```
filebeat.inputs:
 - type: log
   enabled: true
   paths:
    - /opt/soft/logs/server.*.log
   fields_under_root: true
   fields:
    alilogtype: server_log
    serverip: 10.0.16.150
   multiline.pattern: '^\['
   multiline.negate: true
   multiline.match: after
   
 - type: log
   enabled: true
   paths:
    - /opt/soft/logs/business.*.log
   fields_under_root: true
   fields:
    alilogtype: business_log
    serverip: 10.0.16.150
   multiline.pattern: '^\['
   multiline.negate: true
   multiline.match: after

#-------------------------- Elasticsearch output ------------------------------
#output.elasticsearch:
 #hosts: ["localhost:9200"]
 
#----------------------------- Logstash output --------------------------------
output.logstash:
 hosts: ["localhost:5044"]

```
#### 1. 启动
`nohup ./filebeat -e -c filebeat.yml >/dev/null 2>&1 &`
#### 2.停止
`ps -ef |grep filebeat`
`kill -9 端口号`