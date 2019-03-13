package com.composite.cxf.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.composite.cxf.service.DemoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String getId(Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }

    @Override
    public String getIds(String ids) {
        Map<String, Object> map = new HashMap<>();
        map.put("ids", ids);
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }
}
