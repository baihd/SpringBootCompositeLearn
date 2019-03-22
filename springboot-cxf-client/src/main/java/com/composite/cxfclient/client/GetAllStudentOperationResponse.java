
package com.composite.cxfclient.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllStudentOperationResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllStudentOperationResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="resultAllStudent" type="{http://service.cxf.composite.com/}students" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllStudentOperationResponse", propOrder = {
    "resultAllStudent"
})
public class GetAllStudentOperationResponse {

    protected Students resultAllStudent;

    /**
     * 获取resultAllStudent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Students }
     *     
     */
    public Students getResultAllStudent() {
        return resultAllStudent;
    }

    /**
     * 设置resultAllStudent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Students }
     *     
     */
    public void setResultAllStudent(Students value) {
        this.resultAllStudent = value;
    }

}
