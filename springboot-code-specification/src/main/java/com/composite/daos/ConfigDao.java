package com.composite.daos;

import com.composite.beans.Config;

import java.util.Collection;

public interface ConfigDao {
    Collection<Config> getAll();

    long add(Config config);

    boolean delete(long id);
}
