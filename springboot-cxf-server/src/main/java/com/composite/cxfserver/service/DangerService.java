package com.composite.cxfserver.service;


import com.composite.cxfserver.base.ResponseBean;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @program: datascreen
 * @description: 危险作业票信息
 * @author: ChengQi
 * @create: 2019-04-19 17:06
 **/
@WebService
public interface DangerService {

    /**
     * @Description: 接收危险作业信息
     * @Param: [json]
     * @return: com.zhonghuaanyuan.datascreenprovider.dto.ResultDTO
     * @Author: ChengQi
     * @Date: 2019/4/19
     */
    //action定义一个soapAction="access"用于找到这个方法以执行
    //operationName指定与此方法相匹配的wsdl:operation的名称
    @WebMethod(action = "acceptDangerAction", operationName = "acceptDangerOperation")
    //定义返回值的名称
    @WebResult(name = "acceptDangerResult")
    ResponseBean acceptDanger(@WebParam String json);

    /**
     * @Description: 更新危险作业信息
     * @Param: [json]
     * @return: com.zhonghuaanyuan.datascreenprovider.dto.ResultDTO
     * @Author: ChengQi
     * @Date: 2019/4/19
     */
    //action定义一个soapAction="access"用于找到这个方法以执行
    //operationName指定与此方法相匹配的wsdl:operation的名称
    @WebMethod(action = "updateDangerAction", operationName = "updateDangerOperation")
    //定义返回值的名称
    @WebResult(name = "updateDangerResult")
    ResponseBean updateDanger(@WebParam String json);

    /**
     * @Description: 删除危险作业信息
     * @Param: [json]
     * @return: com.zhonghuaanyuan.datascreenprovider.dto.ResultDTO
     * @Author: ChengQi
     * @Date: 2019/4/19
     */
    //action定义一个soapAction="access"用于找到这个方法以执行
    //operationName指定与此方法相匹配的wsdl:operation的名称
    @WebMethod(action = "deleteDangerAction", operationName = "deleteDangerOperation")
    //定义返回值的名称
    @WebResult(name = "deleteDangerResult")
    ResponseBean deleteDanger(@WebParam String json);

}
