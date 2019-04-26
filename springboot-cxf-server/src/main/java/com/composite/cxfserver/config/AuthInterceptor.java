package com.composite.cxfserver.config;

import com.composite.cxfserver.dao.UserDao;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.List;
import java.util.Map;

@Component
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    @Autowired
    private UserDao userDao;

    public AuthInterceptor() {
        //拦截器在调用方法之前拦截SOAP消息
        super(Phase.PRE_INVOKE);
    }

    //拦截器操作
    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {
        System.out.println("come to auth interceptor...");
        //获取SOAP消息的所有Header
        List<Header> headers = soapMessage.getHeaders();
        if (headers == null || headers.size() < 1) {
            throw new Fault(new IllegalArgumentException("没有Header,拦截器实施拦截"));
        }
        //获取Header携带是用户和密码信息
        Header firstHeader = headers.get(0);
        Element element = (Element) firstHeader.getObject();
        NodeList userNameElement = element.getElementsByTagName("userName");
        NodeList passWordElement = element.getElementsByTagName("password");
        if (userNameElement.getLength() != 1) {
            throw new Fault(new IllegalArgumentException("用户名格式不对"));
        }
        if (passWordElement.getLength() != 1) {
            throw new Fault(new IllegalArgumentException("用户密码格式不对"));
        }
        //获取元素的文本内容
        String userName = userNameElement.item(0).getTextContent();
        String password = passWordElement.item(0).getTextContent();
        Map<String,Object> resultMap = userDao.getUser("zhangsan");
        //实际项目中, 应该去查询数据库, 该用户名,密码是否被授权调用该webservice
        if (!userName.equals("zhangsan") || !password.equals("123456")) {
            throw new Fault(new IllegalArgumentException("用户名或密码不正确"));
        }
    }
}
