package com.composite.services;

import com.composite.beans.Config;

import java.util.Collection;

public interface ConfigService {

    Collection<Config> getAll();

    long add(Config config);

    boolean delete(long id);

    boolean someOpration(long id);

    void update(Config config);

}
