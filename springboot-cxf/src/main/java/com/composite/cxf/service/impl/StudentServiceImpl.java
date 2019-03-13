package com.composite.cxf.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.composite.cxf.dao.StudentDao;
import com.composite.cxf.entity.Student;
import com.composite.cxf.entity.Students;
import com.composite.cxf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.ArrayList;

/**
 * 实现webservice接口，对外暴露 soap
 */
//由Spring管理
@Component
//webservice接口的全类名
@WebService(endpointInterface = "com.composite.cxf.service.StudentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student getStudent(Integer id) {
        return studentDao.getStudentById(id);
    }

    /**
     * 没有测试正确性，不是本文重点
     */
    @Override
    public Students getAllStudent(String ids) {
        Students students = new Students(new ArrayList<>());
        // 得到json对象
        JSONObject json = JSONObject.parseObject(ids);
        // 获取对象的id列表
        JSONArray sid = json.getJSONArray("id");
        for (int i = 0; i < sid.size(); i++) {
            Integer s = sid.getInteger(0);
            if (s != null) {
                students.getStudents().add(studentDao.getStudentById(s));
            } else {
                continue;
            }
        }
        return students;
    }

}
