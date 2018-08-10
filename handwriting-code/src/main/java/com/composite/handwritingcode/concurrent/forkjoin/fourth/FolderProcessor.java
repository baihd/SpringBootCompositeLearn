package com.composite.handwritingcode.concurrent.forkjoin.fourth;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderProcessor extends RecursiveTask<List<String>> {
    private static final long serialVersionUID = 1L;
    private String path;
    private String extension;

    public FolderProcessor(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    @Override
    protected List<String> compute() {
        List<String> list = new ArrayList<>();
        List<FolderProcessor> tasks = new ArrayList<>();
        File file = new File(path);
        File[] content = file.listFiles();
        if (content != null) {
            for (int i = 0; i < content.length; i++) {
                if (content[i].isDirectory()) {
                    FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(), extension);
                    //采用异步方式执行任务
                    task.fork();
                    tasks.add(task);
                } else if (checkFile(content[i].getName())) {
                    list.add(content[i].getAbsolutePath());
                }
            }
            if (tasks.size() > 50) {
                System.out.printf("%s: %d tasks run.\n", file.getAbsolutePath(), tasks.size());
            }
            addResultFromTasks(list, tasks);
        }
        return list;
    }

    private boolean checkFile(String name) {
        return name.endsWith(extension);
    }

    //等待所有的任务运行结束
    private void addResultFromTasks(List<String> list, List<FolderProcessor> tasks) {
        for (FolderProcessor item : tasks) {
            list.addAll(item.join());
        }
    }

}
