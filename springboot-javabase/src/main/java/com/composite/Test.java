package com.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Test {
    List<String> list = Collections.synchronizedList(new ArrayList<>());

    public List<String> getList() {
        return list;
    }
}
