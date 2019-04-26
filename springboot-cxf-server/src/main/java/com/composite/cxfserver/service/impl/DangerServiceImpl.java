package com.composite.cxfserver.service.impl;


import com.composite.cxfserver.base.ResponseBean;
import com.composite.cxfserver.service.DangerService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

/**
 * @program: datascreen
 * @description: 危险作业票信息实现
 * @author: ChengQi
 * @create: 2019-04-19 17:08
 **/
@Service
@WebService(
        serviceName = "DangerService",
        targetNamespace = "http://service.cxfserver.composite.com",
        endpointInterface = "com.composite.cxfserver.service.DangerService"
)
//绑定SOAP1.2
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public class DangerServiceImpl implements DangerService {

    @Override
    public ResponseBean acceptDanger(String json) {
        try {
            return ResponseBean.success("success_data");
        } catch (Exception e) {
            return ResponseBean.fail(e.getMessage());
        }
    }

    @Override
    public ResponseBean updateDanger(String json) {
        try {
            return ResponseBean.success("success_data");
        } catch (Exception e) {
            return ResponseBean.fail(e.getMessage());
        }
    }

    @Override
    public ResponseBean deleteDanger(String json) {
        try {
            return ResponseBean.success("success_data");
        } catch (Exception e) {
            return ResponseBean.fail(e.getMessage());
        }
    }
}
