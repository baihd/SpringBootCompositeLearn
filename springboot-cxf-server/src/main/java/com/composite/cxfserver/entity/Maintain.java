package com.composite.cxfserver.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @program: datascreen
 * @description: 检维修工单
 * @author: ChengQi
 * @create: 2019-04-19 16:31
 **/
@XmlRootElement(name = "Maintain")
public class Maintain implements Serializable {
    private static final long serialVersionUID = 6986153901410597683L;

    //检维修编号
    private String id;

    //企业编号
    private String entId;

    //设备名称
    private String deviceName;

    //设备位置
    private String devicePos;

    //故障描述
    private String description;

    //检修负责人
    private String principal;

    //检修计划开始时间
    private String startTime;

    //检修计划结束时间
    private String endTime;

    //状态
    private String status;

    public Maintain(String id, String entId, String deviceName, String devicePos, String description,
                    String principal, String startTime, String endTime, String status) {
        this.id = id;
        this.entId = entId;
        this.deviceName = deviceName;
        this.devicePos = devicePos;
        this.description = description;
        this.principal = principal;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDevicePos() {
        return devicePos;
    }

    public void setDevicePos(String devicePos) {
        this.devicePos = devicePos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MaintainDTO{" +
                "id='" + id + '\'' +
                ", entId='" + entId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", devicePos='" + devicePos + '\'' +
                ", description='" + description + '\'' +
                ", principal='" + principal + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
