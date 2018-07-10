package com.composite.event.event3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//事件源产生事件
public class Teacher {
    private String name;
    private List<String> homeworks;
    private Set<HomeworkListener> homeworkListenerList;

    public String getName() {
        return this.name;
    }

    public Teacher(String name) {
        this.name = name;
        this.homeworks = new ArrayList<>();
        this.homeworkListenerList = new HashSet<>();
    }

    public void setHomework(String homework) {
        System.out.printf("%s布置了作业%s \n", this.name, homework);
        homeworks.add(homework);
        HomeworkEventObject event = new HomeworkEventObject(this);
        /*
         * 在观察者模式中，我们直接调用Observable的notifyObservers来通知被观察者
         * 现在我们只能自己通知了~~
         */
        for (HomeworkListener listener : homeworkListenerList) {
            listener.update(event, homework);
        }
    }

    public void addObserver(HomeworkListener homeworkListener) {
        homeworkListenerList.add(homeworkListener);
    }
}
