## MongoDB安装:
* 1.下载地址：
>https://www.mongodb.com/download-center#community
* 2.解压：
    * tar -zxvf  mongodb-linux-x86_64-ubuntu1604-4.0.5.tgz
* 3.重命名：
    * mv mongodb-linux-x86_64-ubuntu1604-4.0.5 /usr/local/mongodb
* 4.配置系统文件profile：
    * sudo vim /etc/profile
    * 插入下列内容并生效：source /etc/profile：
```
export MONGODB_HOME=/usr/local/mongodb  
export PATH=$PATH:$MONGODB_HOME/bin
```
* 5.mongodb启动配置：
    * 进入到bin目录，增加配置文件，并插入下列内容：
```
cd /usr/local/mongodb/bin  
sudo vim mongodb.conf
```
```
dbpath = /usr/local/mongodb/data/db #数据文件存放目录  
logpath = /usr/local/mongodb/logs/mongodb.log #日志文件存放目录  
port = 27017  #端口  
fork = true  #以守护程序的方式启用，即在后台运行  
auth=false #不开启认证功能
```
* 6.启动：
```
cd /usr/local/mongodb/bin
./mongod -f mongodb.conf  #也可以用-config 代替-f
#如果已经在第四步配置了系统环境变量，则可以直接输入
mongod -config mongodb.conf
```

## mongodb命令
* 进入mongodb：
    * `mongo`
* 显示数据库列表： 
    * `show dbs`
* 切换当前数据库：  
    * `use myDB`
* 显示当前数据库中的集合（类似关系数据库中的表table）：  
    * `show collections`
* 显示所有用户：  
    * `show users`  
* 显示数据库操作命令：   
    * `db.help()`  
* 显示集合操作命令：  
    * `db.myCollection.help()`
* 创建Collection：  
    * `db.createCollection('myCollection')`
* 插入数据：    
    * `db.myCollection.insert({_id:1, name: 'zhangsan', sage: 20})`
    * `db.myCollection.save({_id:1, name: 'zhangsan', sage: 22})`
* 查找数据：   
    * `db.myCollection.find(criteria, filterDisplay)`
* 查询所有记录。相当于：`select * from student`  
    * `db.myCollection.find()`
* 查询name='lisi'的记录。相当于：`select * from student where name='lisi'`  
    * `db.myCollection.find({name: 'lisi'})`
* 查询指定列name、sage数据。相当于：`select name,sage from student`
    * name:1表示返回name列，默认_id字段也是返回的，可以添加_id:0（意为不返回_id）写成{name: 1, sage: 1,_id:0}，就不会返回默认的_id字段
    * `db.myCollection.find({},{name:1, sage:1})`
    * and与条件查询。相当于：`select * from student where name = 'zhangsan' and sage = 22`
    * `db.myCollection.find({name: 'zhangsan', sage: 22})`
    * or条件查询。相当于：`select * from student where sage = 22 or sage = 25`
    * `db.myCollection.find({$or: [{sage: 22}, {sage: 25}]})`
 
* 修改数据
    * `db.myCollection.update(criteria, objNew, upsert, multi);`
    * 相当于：`update student set sage =30 where sname = 'lisi'`
    * `db.myCollection.update({name: 'lisi'}, {$set: {sage: 30}}, false, true)`

* 删除数据  
    * 相当于：`delete from student where sname='chenliu';`
    * `db.myCollection.remove({sname: 'chenliu'})`

## 可视化工具
* robo3t

## curl语句
* `curl -v -X GET http://127.0.0.1:8065/employees/`
* `curl -v -X POST http://127.0.0.1:8065/employees/`
* `curl -v -X PUT http://127.0.0.1:8065/employees/`
* `curl -v -X DELETE http://127.0.0.1:8065/employees/`