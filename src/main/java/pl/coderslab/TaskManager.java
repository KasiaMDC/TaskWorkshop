package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    String[][] tasks;

    public TaskManager() {
        tasks = new String[0][];
    }

    public void setTasks(String[][] tasks) {
        this.tasks = tasks;
    }

    //add one task to the list of tasks
    public void addTask(String[] task) {

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length-1] = new String [4];
        for (int i = 0; i < task.length; i++) {
            tasks[tasks.length - 1][i] = task[i];
        }

    }

    // returns 1 task
    public String[] getTask(int index) {
        String[] arr = new String[]{tasks[index][4]};
        return arr;
    }

    public void removeTask(int index) {
        tasks = ArrayUtils.remove(tasks, index);
        System.out.println("Value was successfully deleted");
    }

    public void listTasks() {
        for (int i = 0; i < tasks.length; i++) {
            for (int j = 0; j < tasks[i].length; j++) {
            }
            System.out.println(Arrays.toString(tasks[i]));
        }
    }

    public int getTaskCount() {
        return tasks.length;
    }
}
