FROM mysql/mysql-server
MAINTAINER test
# Contents of /docker-entrypoint-initdb.d are run on mysqld startup
ADD  mysql/ /docker-entrypoint-initdb.d/