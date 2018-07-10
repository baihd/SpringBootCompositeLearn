package com.composite.event.event2;

public class ConcreteObserver1 implements Observer {
    @Override
    public void update(Observable observable) {
        System.out.println("观察者1观察到" + observable.getClass().getSimpleName() + "发生变化");
        System.out.println("观察者1做出响应");
    }
}
