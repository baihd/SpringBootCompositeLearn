FROM java:8
#VOLUME指定了临时文件目录为/tmp。其效果是在主机/var/lib/docker目录下创建了一个临时文件，
#并链接到容器的/tmp。改步骤是可选的，如果涉及到文件系统的应用就很有必要了。
#/tmp目录用来持久化到Docker数据文件夹，因为Spring Boot使用的内嵌Tomcat容器默认使用/tmp作为工作目录。
VOLUME /tmp
#向镜像中增加文件
ADD springboot-docker-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
#对外连接端口号
EXPOSE 8080
#"-Djava.security.egd=file:/dev/./urandom"解决阿里云的tomcat启动慢的问题
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]