package com.composite.cxf.dao;

import com.composite.cxf.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * dao层数据操作接口
 */
@Repository
public interface StudentDao {

    Student getStudentById(@Param("id") Integer id);
}
