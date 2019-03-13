package com.composite.cxf.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface DemoService {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    //限制id只能是0~9的数组,不超过10位
    @Path("/getId/{id:[0-9]{0,10}}")
    String getId(@PathParam("id") Integer id);


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getIds/{ids}")
    String getIds(@PathParam("ids") String ids);
}
