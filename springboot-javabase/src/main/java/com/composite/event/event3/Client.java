package com.composite.event.event3;

public class Client {
    public static void main(String[] args) {
        Student student1 = new Student("张三");
        Student student2 = new Student("李四");
        Teacher teacher1 = new Teacher("zuikc");
        teacher1.addObserver(student1);
        teacher1.addObserver(student2);
        teacher1.setHomework("事件机制第二天作业");
    }
}
