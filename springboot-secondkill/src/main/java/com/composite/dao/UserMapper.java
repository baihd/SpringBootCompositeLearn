package com.composite.dao;

import com.composite.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User selectByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password);

    User checkPhone(@Param("phone") String phone);
}
