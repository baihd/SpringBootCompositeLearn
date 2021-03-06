package com.composite.cxf.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 学生实体类
 */
@XmlRootElement(name = "Student")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String sex;
    private String address;
    private Integer age;

    public Student() {
        super();
    }

    public Student(Integer id, String name, String sex, String address, Integer age) {
        super();
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", sex=" + sex
                + ", address=" + address + ", age=" + age + "]";
    }

}
