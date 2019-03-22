package com.composite.cxfclient.helloServer;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;


@WebService(targetNamespace = "http://helloServer.cxfclient.composite.com", serviceName = "HelloService")
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public class HelloService {

    //action定义一个soapAction="access"用于找到这个方法以执行
    //operationName指定与此方法相匹配的wsdl:operation的名称
    @WebMethod(action = "getValueAction", operationName = "getValueOperation")
    //定义返回值的名称
    @WebResult(name = "resultValue")
    public String getValue(@WebParam(name = "name", targetNamespace = "http://helloServer.cxfclient.composite.com") String name) {
        return "Hello:" + name;
    }

}
