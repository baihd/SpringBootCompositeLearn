package com.composite.service.impl;

import com.composite.domain.Employee;
import com.composite.service.EmpService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    @Qualifier("masterMongoTemplate")
    private MongoTemplate masterMongoTemplate;

    @Autowired
    @Qualifier("slaveMongoTemplate")
    private MongoTemplate slaveMongoTemplate;


    @Override
    public List<Employee> getEmployeeList() {
        Query query = new Query();
        //is查询
        query.addCriteria(Criteria.where("name").is("zhangsan"));
        //正则查询以A开头
        //query.addCriteria(Criteria.where("name").regex("^z"));
        //GT（大于）和LT（小于）运算符
        query.addCriteria(Criteria.where("age").gt(10).lt(50));
        //结果排序
        query.with(new Sort(Sort.Direction.ASC, "age"));
        //分页
        query.with(PageRequest.of(0, 20));

        List<Employee> employeeList = masterMongoTemplate.find(query, Employee.class);
        return employeeList;
    }

    @Override
    public String postEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setName("zhangsan");
        employee.setAge(30);
        employeeList.add(employee);
        masterMongoTemplate.insert(employeeList, Employee.class);
        return "success";
    }

    @Override
    public String putEmployee() {
        //修改全部age为10的数据中的name和age
        Query query = Query.query(Criteria.where("age").is(30));
        Update update = Update.update("name", "zhangsan").set("age", 20);
        UpdateResult updateResult = masterMongoTemplate.updateMulti(query, update, Employee.class);
        System.out.println(updateResult.getMatchedCount());
        System.out.println(updateResult.getModifiedCount());
        //如果找到匹配的文档就正常更新，否则，就以这个条件和更新文档为基础创建一个新的文档。
        Query query2 = Query.query(Criteria.where("age").is(10));
        Update update2 = Update.update("name", "zhangsan").set("age", 10);
        UpdateResult updateResult2 = masterMongoTemplate.upsert(query, update, Employee.class);
        System.out.println(updateResult2.getMatchedCount());
        System.out.println(updateResult2.getModifiedCount());
        return "success";
    }

    @Override
    public String deleteEmployee() {
        Query query = Query.query(Criteria.where("name").is("zhangsan1"));
        DeleteResult deleteResult = masterMongoTemplate.remove(query, Employee.class);
        System.out.println(deleteResult.getDeletedCount());
        return "success";
    }
}
