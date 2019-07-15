package main.java.app.store;

import main.java.app.entity.Task;
import main.java.app.entity.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class Store {
    public void addInfoUser(Map<String, User> users) throws Exception {
        Writer writer = new FileWriter("Users.txt");

        for (User user : users.values()){
            writer.write(" " + user.getId());
            writer.write(", " + user.getName());
            writer.write("\n");
        }
        writer.close();
    }

    public Map<String,User> getInfoUser() throws Exception {
        if(! (new File("Users.txt").isFile())){
            File file = new File("Users.txt");
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader("Users.txt"));
        Map<String,User> users = new HashMap<>();
        while (reader.read() != -1) {
            String line = reader.readLine();
            String[] string = line.split(",");
            User user = new User(string[1]);
            user.setId(string[0]);
            users.put(user.getName(),user);
        }
        return users;
    }

    public int getMaxIdUser() throws Exception{
        Map<String, User> users = new HashMap<>();
        users =getInfoUser();
        int maxId = -1;
        for (User user : users.values()){
            if(user.getId() > maxId){
                maxId = user.getId();
            }
        }
        maxId++;
        return maxId;
    }

    public void addInfo(Map<String, Task> tasks) throws Exception{
        Writer writer = new FileWriter("Tasks.txt");

        for (Task task : tasks.values()){
            writer.write(" " + task.getId() + ",");
            writer.write(task.getName() + ",");
            writer.write(task.getDeadline() + ",");
            writer.write(task.getUserId() + "\n");
        }
        writer.close();
    }

    public Map<String, Task> getInfoTask() throws Exception{
        if(!(new File("Tasks.txt").isFile())){
            File file = new File("Tasks.txt");
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader (new FileReader("Tasks.txt"));
        Map<String, Task> tasks = new HashMap<>();
        while (reader.read() != -1) {
            String[] string = reader.readLine().split(",");
            int taskId = Integer.parseInt(string[0]);
            String taskName = string[1];
            String taskDeadLine = string[2];
            Task task = new Task(taskName,taskDeadLine);
            task.setId(string[0]);
            task.setUserId(string[3]);
            tasks.put(task.getName(),task);
        }
        return tasks;
    }

    public int getMaxIdTask() throws Exception {
        Map<String, Task> tasks = new HashMap<>();
        tasks = getInfoTask();
        int maxId = -1;
        for(Task task: tasks.values()){
            if (task.getId() > maxId) {
                maxId = task.getId();
            }
        }
        maxId++;
        return maxId;
    }
}