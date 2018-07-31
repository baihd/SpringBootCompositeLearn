package com.composite.handwritingcode;

import com.composite.handwritingcode.bean.User;
import com.composite.handwritingcode.config.MySqlSession;
import com.composite.handwritingcode.mapper.UserMapper;

public class TestMybatis {
    public static void main(String[] args) {
        MySqlSession sqlSession = new MySqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById("1");
        System.out.println(user.toString());
    }
}
