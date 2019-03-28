#一.使用悲观锁
*要使用悲观锁,首先要关闭mysql数据库的autocommit属性*
##关闭autocommit属性
###1.命令
`set global init_connect="set autocommit=0";`
###2.修改my.cnf文件
`init_connect='SET autocommit=0'`
##查看autocommit属性
`show variables like '%autocommit%';`


###注意:连接mysql用户的权限不能大于启动mysql的用户的权限


#一.mysql命令
##1.创建用户
`grant privileges on database.table to username@hostname identified by 'password';`
###privileges:权限
`all privileges,select,insert,update,delete,create,drop`
####全局层级
`grant all on *.* |revoke all on *.*`
####数据库层级
`grant all on db_name.* | revoke all on db_name.*`
####表层级
`grant all on db_name.tbl_name | revoke all on db_name.tbl_name` 

###2.查询权限
`show grants for username;`

###3.刷新权限
`flush privileges;`