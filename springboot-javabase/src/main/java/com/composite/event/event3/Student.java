package com.composite.event.event3;

//监听器监听事件
public class Student implements HomeworkListener {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void update(HomeworkEventObject object, Object arg) {
        Teacher teacher = object.getTeacher();
        System.out.printf("学生%s观察到（实际是被通知）%s布置了作业《%s》 \n", this.name, teacher.getName(), arg);
    }
}
