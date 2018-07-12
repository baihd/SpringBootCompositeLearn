package com.composite.event.event4;

/**
 * 该类为门1监听接口的实现，做具体的开门，关门动作
 */
public class DoorListener1 implements DoorListener {
    @Override
    public void doorEvent(DoorEvent event) {
        if (event.getDoorState() != null && event.getDoorState().equals("open")) {
            System.out.println("门1打开");
        } else {
            System.out.println("门1关闭");
        }
    }
}
