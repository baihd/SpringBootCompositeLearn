package com.composite.cxfserver.service.impl;

import com.composite.cxfserver.base.ResponseBean;
import com.composite.cxfserver.service.AccidentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

/**
 * @program: datascreen
 * @description: 隐患实现类
 * @author: ChengQi
 * @create: 2019-04-17 14:45
 **/
@Service
@WebService(
        serviceName = "AccidentService",
        targetNamespace = "http://service.cxfserver.composite.com",
        endpointInterface = "com.composite.cxfserver.service.AccidentService"
)
//绑定SOAP1.2
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public class AccidentServiceImpl implements AccidentService {

    private final static Logger logger = LoggerFactory.getLogger(AccidentServiceImpl.class);

    /**
     * @Description: 接收隐患信息
     * @Param: [jsonStr：json格式字符串]
     * @return: com.zhonghuaanyuan.datascreenprovider.dto.ResultDTO
     * @Author: ChengQi
     * @Date: 2019/4/18
     */
    @Override
    public ResponseBean acceptAccident(String jsonStr) {
        try {
            return ResponseBean.success("success_data");
        } catch (Exception e) {
            logger.error("acceptAccident error:", e);
            return ResponseBean.fail(e.getMessage());
        }
    }

    /**
     * @Description: 修改隐患信息
     * @Param: [json]
     * @return: com.zhonghuaanyuan.datascreenprovider.dto.ResultDTO
     * @Author: ChengQi
     * @Date: 2019/4/18
     */
    @Override
    public ResponseBean updateAccident(String json) {
        try {
            return ResponseBean.success("success_data");
        } catch (Exception e) {
            logger.error("updateAccident error:", e);
            return ResponseBean.fail(e.getMessage());
        }
    }

    //删除隐患信息
    @Override
    public ResponseBean deleteAccident(String json) {
        try {
            return ResponseBean.success("success_data");
        } catch (Exception e) {
            logger.error("deleteAccident error:", e);
            return ResponseBean.fail(e.getMessage());
        }
    }
}
