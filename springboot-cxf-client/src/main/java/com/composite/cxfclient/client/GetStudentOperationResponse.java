
package com.composite.cxfclient.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getStudentOperationResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getStudentOperationResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="resultStudent" type="{http://service.cxf.composite.com/}student" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getStudentOperationResponse", propOrder = {
    "resultStudent"
})
public class GetStudentOperationResponse {

    protected Student resultStudent;

    /**
     * 获取resultStudent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Student }
     *     
     */
    public Student getResultStudent() {
        return resultStudent;
    }

    /**
     * 设置resultStudent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Student }
     *     
     */
    public void setResultStudent(Student value) {
        this.resultStudent = value;
    }

}
