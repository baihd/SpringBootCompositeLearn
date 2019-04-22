package com.composite.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

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
