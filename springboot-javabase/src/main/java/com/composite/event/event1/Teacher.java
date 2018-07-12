package com.composite.event.event1;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Teacher extends Observable {
    private String name;
    private List<String> homeworks;

    public String getName() {
        return this.name;
    }

    public Teacher(String name) {
        this.name = name;
        homeworks = new ArrayList<>();
    }

    public void setHomework(String homework) {
        System.out.printf("%s布置了作业%s\n", this.name, homework);
        homeworks.add(homework);
        setChanged();
        notifyObservers(homework);
    }

}
