#Elasticsearch操作
##删除索引
`curl -XDELETE http://localhost:9200/_all`  
`curl -XDELETE http://localhost:9200/*`  
`curl -XDELETE http://localhost:9200/index1,index2`  
###删除多余副本
`curl -XPUT "http://localhost:9200/_settings" -d'{"number_of_replicas" : 0}'`

`curl -H "Content-Type: application/json" -XPUT "http://localhost:9200/_settings" -d'{"number_of_replicas" : 0}'`


curl -H "Content-Type: application/json" -XPOST 'http://localhost:9200/logstash-2018.07/external/_delete_by_query?pretty' -d '{ "query": { "match_all": {} } }'

