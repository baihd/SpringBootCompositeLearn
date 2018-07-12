package com.composite.event.event3;

import java.util.EventListener;

public interface HomeworkListener extends EventListener{
    void update(HomeworkEventObject object, Object arg);

}
