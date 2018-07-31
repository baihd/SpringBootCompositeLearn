package com.composite.handwritingcode.config;

import java.util.List;

public class MapperBean {
    //接口名
    private String interfaceName;
    //接口下所有方法
    private List<Function> list;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<Function> getList() {
        return list;
    }

    public void setList(List<Function> list) {
        this.list = list;
    }
}
