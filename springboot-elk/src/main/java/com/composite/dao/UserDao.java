package com.composite.dao;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;

import java.io.IOException;
import java.util.Map;

public interface UserDao {
    IndexResponse insertUser(Map<String, Object> insertMap) throws IOException;

    DeleteResponse deleteResponse(Map<String,Object> deleteMap);

    UpdateResponse updateUser(Map<String,Object> updateMap) throws Exception;

    SearchResponse searchUser(Map<String,Object> searchMap);
}
