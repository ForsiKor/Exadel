package app.store;

import app.entity.Task;

import java.io.*;
import java.util.*;


public class TaskStoreImpl implements IStore<Task>{
    @Override
    public void addInfo(Map<Integer, Task> tasks) {
        try {
            Writer writer = new FileWriter("Tasks.txt");

            for (Task task : tasks.values()) {
                writer.write(" " + task.getId() + ",");
                writer.write(task.getName() + ",");
                writer.write(task.getDeadline() + ",");
                writer.write(task.getUserId() + "\n");
            }
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public int getMaxId(){
        Map<Integer, Task> tasks = getInfo();
        int maxId = -1;
        for (Task task : tasks.values()){
            if (task.getId() > maxId){
                maxId = task.getId();
            }
        }
        maxId++;
        return maxId;
    }
    @Override
    public Map<Integer, Task> getInfo() {
        Map<Integer, Task> tasks = new HashMap();
        try {
            if(! (new File("Tasks.txt").isFile())){
                File file = new File("Tasks.txt");
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader("Tasks.txt"));

            while (reader.read() != -1) {
                String[] string = reader.readLine().split(",");

                String taskName = string[1];
                String taskDeadLine = string[2];

                Task task = new Task(taskName, taskDeadLine);
                task.setId(Integer.parseInt(string[0]));
                task.setUserId(Integer.parseInt(string[3]));
                tasks.put(task.getId(), task);
            }} catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
