#安装依赖包jdk8：
sudo mkdir /usr/lib/jvm

tar xvzf jdk-8u91-linux-x64.tar.gz -C /usr/lib/jvm/

vim ~/.bashrc

在文档最下部追加

export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_91
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$PATH

执行：source ~/.bashrc

执行java -version和java，有相应数据即安装完成。

#安装Logstash：
tar xvzf logstash-2.3.3.tar.gz

在logstash-2.3.3目录下创建logstash-test.conf配置文件，内容如下：

cat logstash-test.conf
  
input { stdin { } }
output {
   stdout { codec=> rubydebug }
}

Logstash使用input和output定义收集日志时的输入和输出的相关配置，本例中input定义了一个叫"stdin"的input，output定义一个叫"stdout"的output。无论我们输入什么字符，Logstash都会按照某种格式来返回我们输入的字符，其中output被定义为"stdout"并使用了codec参数来指定logstash输出格式。

使用如下命令启动：

./bin/logstash agent -f logstash-test.conf

启动后，你在屏幕上输入什么内容，就会在console里显示出来。

说明安装成功。使用Ctrl+C退出进程。

#安装elasticsearch：

tar xvzf elasticsearch-2.3.3.tar.gz

修改配置文件，允许远程访问：

cd elasticsearch-2.3.3/config

vim elasticsearch.yml

./bin/elasticsearch -d #-d为后台启动

#安装elasticsearch插件head：

cd elasticsearch-2.3.3

./bin/plugin install mobz/elasticsearch-head

测试elasticsearch与logstash是否能链接成功：

在logstash-2.3.3安装目录下创建一个用于测试logstash使用elasticsearch作为logstash的后端的测试文件logstash-es-simple.conf，该文件中定义了stdout和elasticsearch作为output，这样的“多重输出”即保证输出结果显示到屏幕上，同时也输出到elastisearch中，内容如下：

cat logstash-es-simple.conf
  
input { stdin { } }
output {
   elasticsearch {hosts => "localhost" }
   stdout { codec=> rubydebug }
}
  
hosts为elasticsearch的主机，这里两者在同一机器上

启动：

./bin/logstash agent -f logstash-es-simple.conf

#安装kibana：

tar xvzf kibana-4.5.1-linux-x64.tar.gz

启动：

cd kibana-4.5.1-linux-x64
./bin/kibana

#配置logstash作为Indexer：

将logstash配置为索引器，并将logstash的日志数据存储到elasticsearch。本案例是索引本地系统日志。

在logstash工作目录中创建配置文件logstash-indexer.conf，内容如下：

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
  elasticsearch {hosts => "localhost" }
}
