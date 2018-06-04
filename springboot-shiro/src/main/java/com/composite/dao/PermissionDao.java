package com.composite.dao;

import com.composite.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PermissionDao {
    List<Permission> getByMap(Map<String, Object> map);

    Permission getById(int id);

    int create(Permission permission);

    int update(Permission permission);

    int delete(int id);

    List<Permission> getList();

    List<Permission> getByUserId(int userId);
}
