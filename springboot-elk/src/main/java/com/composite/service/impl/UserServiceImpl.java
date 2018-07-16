package com.composite.service.impl;

import com.composite.dao.UserDao;
import com.composite.service.UserService;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public String insertUser(Map<String, Object> insertMap) throws IOException {
        IndexResponse indexResponse = userDao.insertUser(insertMap);
        return indexResponse.getId();
    }

    @Override
    public String deleteUser(Map<String, Object> deleteMap) {
        DeleteResponse result = userDao.deleteResponse(deleteMap);
        return result.getId();
    }

    @Override
    public String updateUser(Map<String, Object> updateMap) throws Exception {
        UpdateResponse result = userDao.updateUser(updateMap);
        return result.getId();
    }

    @Override
    public List<Map<String, Object>> searchUser(Map<String, Object> searchMap) {
        SearchResponse response = userDao.searchUser(searchMap);
        List<Map<String, Object>> result = new ArrayList<>();
        for (SearchHit hit : response.getHits()) {
            result.add(hit.getSource());
        }
        return result;
    }
}
