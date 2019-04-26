
package com.composite.cxfserver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>acceptAccidentOperationResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="acceptAccidentOperationResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="acceptAccidentResult" type="{http://service.cxfserver.composite.com/}responseBean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "acceptAccidentOperationResponse", propOrder = {
    "acceptAccidentResult"
})
public class AcceptAccidentOperationResponse {

    protected ResponseBean acceptAccidentResult;

    /**
     * 获取acceptAccidentResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ResponseBean }
     *     
     */
    public ResponseBean getAcceptAccidentResult() {
        return acceptAccidentResult;
    }

    /**
     * 设置acceptAccidentResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseBean }
     *     
     */
    public void setAcceptAccidentResult(ResponseBean value) {
        this.acceptAccidentResult = value;
    }

}
