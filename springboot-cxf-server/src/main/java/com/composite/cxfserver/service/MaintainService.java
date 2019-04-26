package com.composite.cxfserver.service;

import com.composite.cxfserver.base.ResponseBean;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @program: datascreen
 * @description: 检维修工单
 * @author: ChengQi
 * @create: 2019-04-17 16:17
 **/
@WebService
public interface MaintainService {

    /**
     * @Description: 接收检维修工单信息
     * @Param: [json]
     * @return: com.zhonghuaanyuan.datascreenprovider.dto.ResultDTO
     * @Author: ChengQi
     * @Date: 2019/4/19
     */
    //action定义一个soapAction="access"用于找到这个方法以执行
    //operationName指定与此方法相匹配的wsdl:operation的名称
    @WebMethod(action = "acceptMaintainAction", operationName = "acceptMaintainOperation")
    //定义返回值的名称
    @WebResult(name = "acceptMaintainResult")
    ResponseBean acceptMaintain(@WebParam String json);

    /**
     * @Description: 修改检维修工单数据
     * @Param: [json]
     * @return: com.zhonghuaanyuan.datascreenprovider.dto.ResultDTO
     * @Author: ChengQi
     * @Date: 2019/4/19
     */
    //action定义一个soapAction="access"用于找到这个方法以执行
    //operationName指定与此方法相匹配的wsdl:operation的名称
    @WebMethod(action = "updateMaintainAction", operationName = "updateMaintainOperation")
    //定义返回值的名称
    @WebResult(name = "updateMaintainResult")
    ResponseBean updateMaintain(@WebParam String json);

    /**
     * @Description: 删除检维修工单数据
     * @Param: [json]
     * @return: com.zhonghuaanyuan.datascreenprovider.dto.ResultDTO
     * @Author: ChengQi
     * @Date: 2019/4/19
     */
    //action定义一个soapAction="access"用于找到这个方法以执行
    //operationName指定与此方法相匹配的wsdl:operation的名称
    @WebMethod(action = "deleteMaintainAction", operationName = "deleteMaintainOperation")
    //定义返回值的名称
    @WebResult(name = "deleteMaintainResult")
    ResponseBean deleteMaintain(@WebParam String json);


}
