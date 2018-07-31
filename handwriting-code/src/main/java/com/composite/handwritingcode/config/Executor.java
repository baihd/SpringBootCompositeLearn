package com.composite.handwritingcode.config;

public interface Executor {
    <T> T query(String statement, Object parameter);
}
