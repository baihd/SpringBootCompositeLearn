#Elasticsearch操作
##删除索引
`curl -XDELETE http://localhost:9200/_all`  
`curl -XDELETE http://localhost:9200/*`  
`curl -XDELETE http://localhost:9200/index1,index2`  
###删除多余副本
`curl -XPUT "http://localhost:9200/_settings" -d'{"number_of_replicas" : 0}'`






