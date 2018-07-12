package com.composite.event.event3;


import java.util.EventObject;

//事件状态对象,事件带有事件源,一般以Event或者EventObject命名结尾
//作为参数,一般存在于listener的方法之中
public class HomeworkEventObject extends EventObject {
    public HomeworkEventObject(Object source) {
        super(source);
    }

    public HomeworkEventObject(Teacher teacher) {
        super(teacher);
    }

    public Teacher getTeacher() {
        return (Teacher) super.getSource();
    }
}
