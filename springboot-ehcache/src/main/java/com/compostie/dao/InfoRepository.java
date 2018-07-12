package com.compostie.dao;

import com.compostie.entity.Info;
import org.springframework.data.repository.CrudRepository;

public interface InfoRepository extends CrudRepository<Info, Long> {
}
