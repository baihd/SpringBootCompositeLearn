package com.composite.cxfserver.service;


import com.composite.cxfserver.base.ResponseBean;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @program: datascreen
 * @description: 隐患接口
 * @author: ChengQi
 * @create: 2019-04-17 14:18
 **/
@WebService
public interface AccidentService {

    /**
     * @Description: 接收隐患信息
     * @Param: [json]
     * @return: com.zhonghuaanyuan.datascreenprovider.dto.ResultDTO
     * @Author: ChengQi
     * @Date: 2019/4/18
     */
    //action定义一个soapAction="access"用于找到这个方法以执行
    //operationName指定与此方法相匹配的wsdl:operation的名称
    @WebMethod(action = "acceptAccidentAction", operationName = "acceptAccidentOperation")
    //定义返回值的名称
    @WebResult(name = "acceptAccidentResult")
    ResponseBean acceptAccident(@WebParam String json);


    /**
     * @Description: 修改隐患信息
     * @Param: [json]
     * @return: com.zhonghuaanyuan.datascreenprovider.dto.ResultDTO
     * @Author: ChengQi
     * @Date: 2019/4/18
     */
    //action定义一个soapAction="access"用于找到这个方法以执行
    //operationName指定与此方法相匹配的wsdl:operation的名称
    @WebMethod(action = "updateAccidentAction", operationName = "updateAccidentOperation")
    //定义返回值的名称
    @WebResult(name = "updateAccidentResult")
    ResponseBean updateAccident(@WebParam String json);


    /**
     * @Description: 删除隐患信息
     * @Param: [json]
     * @return: com.zhonghuaanyuan.datascreenprovider.dto.ResultDTO
     * @Author: ChengQi
     * @Date: 2019/4/18
     */
    //action定义一个soapAction="access"用于找到这个方法以执行
    //operationName指定与此方法相匹配的wsdl:operation的名称
    @WebMethod(action = "deleteAccidentAction", operationName = "deleteAccidentOperation")
    //定义返回值的名称
    @WebResult(name = "deleteAccidentResult")
    ResponseBean deleteAccident(@WebParam String json);


}
