package com.composite.dao;

import com.composite.entity.Event;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EventDao {
    List<Event> getByMap(Map<String, Object> map);

    Event getById(int id);

    int create(Event event);

    int update(Event event);

    int delete(int id);
}
