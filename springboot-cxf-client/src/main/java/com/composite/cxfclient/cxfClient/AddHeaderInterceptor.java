package com.composite.cxfclient.cxfClient;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.List;

public class AddHeaderInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
    private String userName;
    private String password;

    public AddHeaderInterceptor(String userName, String password) {
        super(Phase.PREPARE_SEND);
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {
        List<Header> headers = soapMessage.getHeaders();

        //创建Document对象
        Document document = DOMUtils.createDocument();
        Element element = document.createElement("authHeader");

        //配置服务器Head信息的用户密码
        Element userNameElement = document.createElement("userName");
        userNameElement.setTextContent(userName);
        Element passwordElement = document.createElement("password");
        passwordElement.setTextContent(password);

        element.appendChild(userNameElement);
        element.appendChild(passwordElement);
        headers.add(new Header(new QName(""), element));
    }
}
