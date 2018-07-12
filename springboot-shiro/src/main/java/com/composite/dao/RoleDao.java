package com.composite.dao;

import com.composite.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleDao {
    List<Role> getByMap(Map<String, Object> map);

    Role getById(int id);

    int create(Role role);

    int update(Role role);

    int delete(int id);
}
