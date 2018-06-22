package com.compostie.controller;

import com.compostie.entity.Info;
import com.compostie.service.InfoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @Autowired
    private InfoService infoService;

    @RequestMapping("/test")
    public String test() {

        //存入两条数据.
        Info info = new Info();
        info.setName("张三");
        info.setPassword("123456");
        Info info2 = infoService.save(info);

        //不走缓存.
        System.out.println(infoService.findById(info2.getId()));
        //走缓存.
        System.out.println(infoService.findById(info2.getId()));


        info = new Info();
        info.setName("李四");
        info.setPassword("123456");
        Info info3 = infoService.save(info);

        //不走缓存.
        System.out.println(infoService.findById(info3.getId()));
        //走缓存.
        System.out.println(infoService.findById(info3.getId()));

        System.out.println("============修改数据=====================");
        //修改数据.
        Info updated = new Info();
        updated.setName("李四-updated");
        updated.setPassword("123456");
        updated.setId(info3.getId());
        try {
            System.out.println(infoService.update(updated));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        //走缓存.
        System.out.println(infoService.findById(updated.getId()));

        return "ok";
    }

}
