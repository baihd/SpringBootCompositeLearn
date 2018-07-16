package com.composite.dao.Impl;

import com.composite.dao.UserDao;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    private static final String INDEX = "user_index";

    private static final String TYPE = "user_type";

    @Autowired
    private TransportClient client;

    @Override
    public IndexResponse insertUser(Map<String, Object> insertMap) throws IOException {
        XContentBuilder content = XContentFactory.jsonBuilder().startObject();
        content.field("name", "name1");
        content.field("age", "28");
        content.field("datetime", new Date());
        content.endObject();
        return this.client.prepareIndex(INDEX, TYPE).setSource(content).setId("1").get();
    }

    @Override
    public DeleteResponse deleteResponse(Map<String, Object> deleteMap) {
        return this.client.prepareDelete(INDEX, TYPE, "1").get();
    }

    @Override
    public UpdateResponse updateUser(Map<String, Object> updateMap) throws Exception {
        UpdateRequest update = new UpdateRequest(INDEX, TYPE, "1");
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
        builder.field("name", "name2");
        builder.field("age", "28");
        builder.field("datetime", new Date());
        builder.endObject();
        update.doc(builder);
        return this.client.update(update).get();
    }

    @Override
    public SearchResponse searchUser(Map<String, Object> searchMap) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchQuery("name", "name1"));
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
        rangeQuery.from(10);
        rangeQuery.to(80);
        boolQuery.must(rangeQuery);

        SearchRequestBuilder builder = this.client.prepareSearch(INDEX)
                .setTypes(TYPE)
                .setSearchType(SearchType.QUERY_AND_FETCH)
                .setQuery(boolQuery)
                .setFrom(0)
                .setSize(10);
        return builder.get();
    }
}
