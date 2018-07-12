package com.composite.event.event2;

public class ConcreteObserver2 implements Observer {
    @Override
    public void update(Observable observable) {
        System.out.println("观察者2观察到" + observable.getClass().getSimpleName() + "发生变化");
        System.out.println("观察者2做出响应");
    }
}
