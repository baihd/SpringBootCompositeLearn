package com.compostie.service;

import com.compostie.entity.Info;
import javassist.NotFoundException;

public interface InfoService {
    void delete(Long id);

    Info update(Info info) throws NotFoundException;

    Info findById(Long id);

    Info save(Info info);
}
