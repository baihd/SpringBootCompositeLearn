package com.composite.dockercompose.dao;

import com.composite.dockercompose.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Integer> {
}
