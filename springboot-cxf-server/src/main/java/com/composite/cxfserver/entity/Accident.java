package com.composite.cxfserver.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @program: datascreen
 * @description: 检维修数据传输对象
 * @author: ChengQi
 * @create: 2019-04-17 16:19
 **/
@XmlRootElement(name = "Accident")
public class Accident implements Serializable {

    private static final long serialVersionUID = -2209656199432121127L;

    //隐患编码
    private String id;

    //企业编号
    private String entId;

    //隐患级别
    private String pitfallsLevel;

    //隐患来源
    private String pitfallsSource;

    //隐患危害
    private String hazard;

    //隐患分类
    private String pitfallsType;

    //隐患描述
    private String description;

    //计划完成时间
    private String planEndTime;

    //实际完成时间
    private String realEndTime;

    //隐患状态
    private String status;

    public Accident(String id, String entId, String pitfallsLevel, String pitfallsSource,
                    String hazard, String pitfallsType, String description, String planEndTime,
                    String realEndTime, String status) {
        this.id = id;
        this.entId = entId;
        this.pitfallsLevel = pitfallsLevel;
        this.pitfallsSource = pitfallsSource;
        this.hazard = hazard;
        this.pitfallsType = pitfallsType;
        this.description = description;
        this.planEndTime = planEndTime;
        this.realEndTime = realEndTime;
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

    public String getPitfallsLevel() {
        return pitfallsLevel;
    }

    public void setPitfallsLevel(String pitfallsLevel) {
        this.pitfallsLevel = pitfallsLevel;
    }

    public String getPitfallsSource() {
        return pitfallsSource;
    }

    public void setPitfallsSource(String pitfallsSource) {
        this.pitfallsSource = pitfallsSource;
    }

    public String getHazard() {
        return hazard;
    }

    public void setHazard(String hazard) {
        this.hazard = hazard;
    }

    public String getPitfallsType() {
        return pitfallsType;
    }

    public void setPitfallsType(String pitfallsType) {
        this.pitfallsType = pitfallsType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(String planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getRealEndTime() {
        return realEndTime;
    }

    public void setRealEndTime(String realEndTime) {
        this.realEndTime = realEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AccidentDTO{" +
                "id='" + id + '\'' +
                ", entId='" + entId + '\'' +
                ", pitfallsLevel='" + pitfallsLevel + '\'' +
                ", pitfallsSource='" + pitfallsSource + '\'' +
                ", hazard='" + hazard + '\'' +
                ", pitfallsType='" + pitfallsType + '\'' +
                ", description='" + description + '\'' +
                ", planEndTime=" + planEndTime +
                ", realEndTime=" + realEndTime +
                ", status='" + status + '\'' +
                '}';
    }
}
