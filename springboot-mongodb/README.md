##MongoDB安装:
###`sudo apt-get install mongodb`  
##MongoDB卸载
###`sudo apt-get --purge remove mongodb mongodb-clients mongodb-server`  
##查看MongoDB版本:
###`mongo -version`
##启动:
###`service mongodb start`
##关闭:
###`service mongodb stop`
##查看是否启动成功：
###`pgrep mongo -l`

##进入mongodb
###`mongo`

##显示数据库列表
###`show dbs`
##切换当前数据库
###`use myDB`
##显示当前数据库中的集合（类似关系数据库中的表table）
###`show collections`
##显示所有用户
###`show users`
##显示数据库操作命令 
###`db.help()`
##显示集合操作命令
###`db.myCollection.help()`

##创建Collection
###`db.createCollection('myCollection')`

##插入数据
###`db.myCollection.insert({_id:1, name: 'zhangsan', sage: 20})`
###`db.myCollection.save({_id:1, name: 'zhangsan', sage: 22})`

##查找数据
### `db.myCollection.find(criteria, filterDisplay)`
###查询所有记录。相当于：`select * from student`
### `db.myCollection.find()`
###查询name='lisi'的记录。相当于： `select * from student where name='lisi'`
### `db.myCollection.find({name: 'lisi'})`
###查询指定列name、sage数据。相当于：`select name,sage from student`
###name:1表示返回name列，默认_id字段也是返回的，可以添加_id:0（意为不返回_id）写成{name: 1, sage: 1,_id:0}，就不会返回默认的_id字段
### `db.myCollection.find({},{name:1, sage:1})`
###and与条件查询。相当于：`select * from student where name = 'zhangsan' and sage = 22`
### `db.myCollection.find({name: 'zhangsan', sage: 22})`
###or条件查询。相当于：`select * from student where sage = 22 or sage = 25`
### `db.myCollection.find({$or: [{sage: 22}, {sage: 25}]})`
 
##修改数据
### `db.myCollection.update(criteria, objNew, upsert, multi);`
### 相当于：`update student set sage =30 where sname = 'lisi'`
### `db.myCollection.update({name: 'lisi'}, {$set: {sage: 30}}, false, true)`

##删除数据
### 相当于：`delete from student where sname='chenliu';`
### `db.myCollection.remove({sname: 'chenliu'})`
