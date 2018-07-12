package com.composite.event.event4;

import java.util.EventObject;

/**
 * 定义事件对象，必须继承EventObject
 */
public class DoorEvent extends EventObject {
    private static final long serialVersionUID = 1L;
    // 表示门的状态，有“开”和“关”两种
    private String doorState = "";

    public DoorEvent(Object source, String doorState) {
        super(source);
        this.doorState = doorState;
    }

    public String getDoorState() {
        return doorState;
    }

    public void setDoorState(String doorState) {
        this.doorState = doorState;
    }
}
