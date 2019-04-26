package com.composite.cxfserver.client;

import com.composite.cxfserver.base.ResponseBean;
import com.composite.cxfserver.service.AccidentService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class WebServiceCXFClient {

    public static void main(String[] args) {
        JaxWsProxyFactoryBean ps = new JaxWsProxyFactoryBean();
        ps.setAddress("http://127.0.0.1:8050/webservice/api/v1/accident?wsdl");
        ps.setServiceClass(AccidentService.class);
        //ps.getOutInterceptors().add(new AddHeaderInterceptor("zhangsan", "123456"));
        AccidentService accidentService = (AccidentService) ps.create();
        ResponseBean responseBean = accidentService.acceptAccident("");
        System.out.println(responseBean);
    }

    /*public static void main(String[] args) throws IOException {
        //创建WSDL的URL，注意不是服务地址
        URL url = new URL("http://127.0.0.1:8082/webservice/api/v1/student/get?wsdl");
        //创建服务名称
        //1.namespaceURI-命名空间地址(wsdl文档中的targetNamespace)
        //2.localPart-服务视图名(wsdl文档中服务名称，例如<wsdl:service name="StudentServiceImplService">)
        QName qname = new QName("http://service.cxf.composite.com/", "StudentServiceImplService");

        //创建服务视图
        //参数解释：
        //1.wsdlDocumentLocation - wsdl地址
        //2.serviceName - 服务名称
        Service service = Service.create(url, qname);
        //获取服务实现类
        //参数解释:serviceEndpointInterface - 服务端口(wsdl文档中服务端口的name属性，例如<wsdl:port name="MobileCodeWSSoap" binding="tns:MobileCodeWSSoap">)
        StudentService studentService = service.getPort(StudentService.class);
        //调用查询方法
        Student student = studentService.getStudentOperation(1);
        Students students = studentService.getAllStudentOperation("{'id':[1]}");
        System.out.println(student.toString());
        System.out.println(students.toString());
    }*/
}
