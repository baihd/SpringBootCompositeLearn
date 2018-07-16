#一、安装依赖包jdk8：
###1.如果没有则创建目录:/usr/lib/jvm
`sudo mkdir /usr/lib/jvm`  
###2.解压到 /usr/lib/jvm/ 下
`sudo tar xvzf jdk-8u91-linux-x64.tar.gz -C /usr/lib/jvm/`
###3.配置环境变量
`sudo vim /etc/profie`
####在文档最下部追加
```
export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_91
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$PATH
```
####立即生效配置
`source /etc/profile`
####查看java版本
`java -version`

#二、安装Logstash：
###1.解压logstash-2.3.3.tar.gz
`sudo tar xvzf logstash-2.3.3.tar.gz`
###2.在logstash-2.3.3目录下创建logstash-test.conf配置文件，内容如下：
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
####3.启动：
`./bin/logstash agent -f logstash-test.conf`
####4.退出
*启动后，在屏幕上输入什么内容，就会在console里显示出来。
说明安装成功。使用Ctrl+C退出进程。*

#三、安装elasticsearch：
`sudo tar xvzf elasticsearch-2.3.3.tar.gz`
###1.修改配置文件，允许远程访问：
`sudo vim elasticsearch-2.3.3/config/elasticsearch.yml`  
####修改network
`network.host: 0.0.0.0`   
###2.后台启动
`./bin/elasticsearch -d`
###3.查找ES进程
`ps -ef | grep elastic`
#四、安装elasticsearch插件head：
`cd elasticsearch-2.3.3`  
`./bin/plugin install mobz/elasticsearch-head`
###1.测试elasticsearch与logstash是否能链接成功：
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
###2.启动：
`./bin/logstash agent -f logstash-es-simple.conf`
###3.访问地址：
>http://127.0.0.1:9200/_plugin/head/

#五、安装kibana：
`tar xvzf kibana-4.5.1-linux-x64.tar.gz`
###1.修改kibana.yml
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
###2.后台启动：
`./bin/kibana &`
###3.查看是否启动
`sudo netstat -apn|grep 5601` 
###4.关闭 
`sudo kill -9 PID`
###5.访问地址：
>http://127.0.0.1:5601  

*登录后，配置一个索引，默认Kibana的数据被指向Elasticsearch，
使用默认的logstash-*的索引名称，并且是基于时间的，点击“Create”即可。
点击“Discover”选项卡，可以搜索和浏览elasticsearch中的数据，
默认搜索最近15分钟的数据，也可以自定义。至此，ELK平台已经部署完成。*

###4.配置logstash作为Indexer：

####1.将logstash配置为索引器，并将logstash的日志数据存储到elasticsearch。
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