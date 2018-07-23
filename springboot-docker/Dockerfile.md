#一、编译镜像
##1. 编译镜像
`sudo docker build [OPTIONS] PATH| URL| -`  
**-t 设置镜像的名称和tag，格式为name:tag**  
**-f Dockerfile的名称，默认为path/Dockerfile** 
##2.dockerignore文件
**编译开始前，DockerDaemon会读取编译目录中的.dockerignore文件，忽略其中的文件和目录**
-忽略PATH路径下一级子目录中以temp开头的文件和目录，如PATH/A/temp.txt
`*/temp*`   
-忽略PATH路径下二级子目录中以temp开头的文件和目录，如PATH/A/B/temp.txt
`*/*/temp*`  
-忽略所有md文件
`*.md`  
-除了README.md  
`!README.md`  

#二、Dockerfile指令详解
-功能描述：设置基础镜像  
`FROM`  
-设置镜像作者  
`MAINTAINER`  
-编译镜像时运行的脚本  
`RUN`  
-设置容器的启动命令
`CMD`
-设置镜像的标签
`LABEL`
-设置镜像暴露的端口
`EXPOESE`
-设置容器的环境变量
`ENV`
-编译镜像时复制文件到镜像中
`ADD`
-编译镜像时复制文件到镜像中
`COPY`
-设置容器的入口程序
`ENTRYPOINT`
-设置容器的挂载卷
`VOLUME`
-设置运行RUN COM ENTRYPOINT的用户名
`USER`
-设置运行RUN CMD ENTRYPOINT COPY ADD指令的工作目录
`WORKDIR`
-设置编译镜像时加入的参数
`ARG`
-设置镜像的ONBUILD指令
'ONBUILD'
-设置容器的退出信号量
`STOPSIGNAL`