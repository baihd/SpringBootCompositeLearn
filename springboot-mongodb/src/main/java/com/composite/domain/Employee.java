package com.composite.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

//指定集合
@Document(collection = "employeeD")
//复合索引，加复合索引后通过复合索引字段查询将大大提高速度
@CompoundIndexes({@CompoundIndex(name = "nameAge_index", def = "{'name':-1,'age':1}")})
public class Employee {
    @Id
    private ObjectId id;
    private String name;
    private Integer age;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
