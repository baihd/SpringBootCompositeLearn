package com.composite.cxf.controller;

import com.composite.cxf.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @ResponseBody
    @Produces({MediaType.APPLICATION_JSON + "charset='utf-8'"})
    @RequestMapping(value = "getId/{id}", method = RequestMethod.GET)
    public String getId(@PathVariable("id") Integer id) {
        return demoService.getId(id);
    }

    @ResponseBody
    @Produces({MediaType.APPLICATION_JSON + "charset='utf-8'"})
    @Consumes({MediaType.APPLICATION_JSON})
    @RequestMapping(value = "getIds/{ids}", method = RequestMethod.GET)
    public String getIds(@PathVariable("ids") String ids) {
        return demoService.getIds(ids);
    }

}
