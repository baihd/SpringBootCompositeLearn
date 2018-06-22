package com.compostie.service.impl;

import com.compostie.dao.InfoRepository;
import com.compostie.entity.Info;
import com.compostie.service.InfoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    //这里的单引号不能少，否则会报错，被识别是一个对象;
    public static final String CACHE_KEY = "'demoInfo'";

    @Autowired
    private InfoRepository infoRepository;

    /**
     * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
     */
    public static final String DEMO_CACHE_NAME = "demo";

    @CacheEvict(value = DEMO_CACHE_NAME, key = "'demoInfo_'+#id")
    @Override
    public void delete(Long id) {
        infoRepository.delete(id);
    }

    /**
     * 在支持Spring Cache的环境下，对于使用@Cacheable标注的方法，
     * Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，
     * 如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回，
     * 否则才会执行并将返回结果存入指定的缓存中。
     *
     * @param info
     * @return
     * @throws NotFoundException
     * @CachePut 也可以声明一个方法支持缓存功能。
     * 与@Cacheable不同的是使用@CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，
     * 而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中。
     * @CachePut 也可以标注在类上和方法上。使用@CachePut时我们可以指定的属性跟@Cacheable是一样的。
     */
    @CachePut(value = DEMO_CACHE_NAME, key = "'demoInfo_'+#info.getId()")
    @Override
    public Info update(Info info) throws NotFoundException {
        Info info1 = infoRepository.findOne(info.getId());
        if (info1 == null) {
            throw new NotFoundException("No find");
        }
        info1.setName(info.getName());
        info1.setPassword(info.getPassword());
        return info1;
    }

    @Cacheable(value = DEMO_CACHE_NAME, key = "'demoInfo_'+#id")
    @Override
    public Info findById(Long id) {
        return infoRepository.findOne(id);
    }

    @CacheEvict(value = DEMO_CACHE_NAME, key = CACHE_KEY)
    @Override
    public Info save(Info info) {
        return infoRepository.save(info);
    }
}
