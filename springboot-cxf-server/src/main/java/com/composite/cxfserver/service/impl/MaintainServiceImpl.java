package com.composite.cxfserver.service.impl;

import com.composite.cxfserver.base.ResponseBean;
import com.composite.cxfserver.service.MaintainService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

/**
 * @program: datascreen
 * @description: 检维修工单实现类
 * @author: ChengQi
 * @create: 2019-04-19 16:17
 **/
@Service
@WebService(
        serviceName = "MaintainService",
        targetNamespace = "http://service.cxfserver.composite.com",
        endpointInterface = "com.composite.cxfserver.service.MaintainService"
)
//绑定SOAP1.2
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public class MaintainServiceImpl implements MaintainService {
    @Override
    public ResponseBean acceptMaintain(String json) {
        try {
            return ResponseBean.success("success_data");
        } catch (Exception e) {
            return ResponseBean.fail(e.getMessage());
        }
    }

    @Override
    public ResponseBean updateMaintain(String json) {
        try {
            return ResponseBean.success("success_data");
        } catch (Exception e) {
            return ResponseBean.fail(e.getMessage());
        }
    }

    @Override
    public ResponseBean deleteMaintain(String json) {
        try {
            return ResponseBean.success("success_data");
        } catch (Exception e) {
            return ResponseBean.fail(e.getMessage());
        }
    }
}
