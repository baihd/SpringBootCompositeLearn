package com.composite.cxfserver.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @program: datascreen
 * @description: 危险作业票数据传输类
 * @author: ChengQi
 * @create: 2019-04-19 17:09
 **/
@XmlRootElement(name = "Dangers")
public class Dangers implements Serializable {

    private static final long serialVersionUID = -1780693509693280103L;

    //作业票编号
    public String id;

    //企业编号
    public String entId;

    //申请单位或部门
    public String department;

    //动火方式
    public String fireType;

    //动火人、临时用电人
    private String operator;

    //作业等级
    private String taskLevel;

    //设备或管线名称
    private String deviceName;

    //施工项目现场负责人、现场安全负责人
    private String principal;

    //施工单位动火监护人、现场监护人
    private String guardian;

    //危害辨识、危害标识
    private String hazardIdentification;

    //安全防护装备
    private String safetyEquipment;

    //开始时间
    private String startTime;

    //结束时间
    private String endTime;

    //状态，0：进行中；1：完成
    private String status;

    //对应动火作业票
    private String fireWorkId;

    //作业内容
    private String jobContent;

    //作业高度
    private String jobHeight;

    //作业位置、堵塞或开挖路段
    private String taskPos;

    //放射源种类
    private String radiateType;

    //作业人员资格证
    private String certification;

    //指挥人员
    private String command;

    //司索人员
    private String tacklePerson;

    //起重车型
    private String liftingType;

    //起重车号
    private String iftingNo;

    //内部原有介质
    private String internalMedium;

    //断路原因
    private String reason;

    //设备管线原有介质
    private String deviceMedium;

    //设备管线压力
    private String pressure;

    //设备管线温度
    private String temperature;

    //材质
    private String material;

    //规格
    private String specification;

    //数量
    private String quantity;

    //监护方式
    private String guardianType;


}
