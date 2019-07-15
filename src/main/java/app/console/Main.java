package main.java.app.console;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

import main.java.app.entity.Task;
import main.java.app.entity.User;
import main.java.app.service.Service;


public class Main {

    public static void main(String[] args) throws Exception {
        Main app = new Main();
        app.menu();
    }

    private static void out() {
        System.out.println("1.Create new User");
        System.out.println("2.Create new Task");
        System.out.println("3.Output List Users");
        System.out.println("4.Output List Tasks");
        System.out.println("5.Delete User");
        System.out.println("6.Delete Task");
        System.out.println("7.Exit");
    }

    private void menu() throws Exception {
        int count = 0;
        Service service = new Service();
        while (count != 7) {
            String userName;
            String taskName;
            out();
            BufferedReader choiceReader = new BufferedReader(new InputStreamReader(System.in));
            String choice = choiceReader.readLine();
            switch (Integer.parseInt(choice)) {
                case 1:
                    userName = inputUser();
                    while (!service.addUser(new User(userName))) {
                        System.out.println("Please input new user");
                        userName = inputUser();
                    }
                    break;
                case 2:
                    taskName = inputTask();
                    userName = inputUser();
                    while (!service.addTask(new Task(taskName, inputData()), userName)) {
                        System.out.println("Please input new task");
                        taskName = inputTask();
                        userName = inputUser();
                    }
                    break;
                case 3:
                    for (User users : service.getAllUsers()) {
                        outUserInfo(users, service.getAllTasks());
                    }
                    System.out.println();
                    break;
                case 4:
                    for (Task task : service.getAllTasks()) {
                        outTaskInfo(task, service.getAllUsers());
                    }
                    System.out.println();
                    break;
                case 5:
                    userName = inputUser();
                    if (service.getUser(userName) == null) {
                        System.out.println("A user with the same name don't exists");
                    } else {
                        System.out.print("Delete user task:");
                        for (Task task : service.getAllTasks()) {
                            if (task.getUserId() == service.getUser(userName).getId()) {
                                System.out.print(" " + task.getName());
                                service.deleteTask(task.getName());
                            }
                        }
                        service.deleteUser(userName);
                        System.out.println("\nUser: " + userName + " successfully deleted");
                    }
                    break;
                case 6:
                    taskName = inputTask();
                    if (!service.deleteTask(taskName)) {
                        System.out.println("Task with this name don't exists");
                    } else {
                        System.out.println("Task: " + taskName + " successfully deleted");
                    }
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter correct data");
            }
        }
    }

    private Date inputData() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date deadline;
        while (true) {
            try {
                System.out.println("Enter task deadline in format: dd.MM.yyyy ");
                deadline = dateFormat.parse(reader.readLine());
                break;
            } catch (Exception e) {
                System.out.println("Date error. Please try again: ");
            }
        }
        return deadline;
    }

    private String inputTask() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String task;
        System.out.println("Please enter task: ");
        task = reader.readLine();
        task = task.replace(",", " ");
        return task;
    }

    private String inputUser() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userName;
        System.out.println("Please enter user: ");
        userName = reader.readLine();
        userName = userName.replace(",", " ");
        return userName;
    }

    private void outTaskInfo(Task task, Collection<User> users) {
        System.out.println("Task id: " + task.getId());
        System.out.println("Name of task: " + task.getName());
        System.out.println("Task deadline: " + task.getDeadline());
        System.out.print("Task user: ");
        for (User user : users) {
            if (user.getId() == task.getUserId()) {
                System.out.println(user.getName() + "\n");
                break;
            }
        }
    }

    private void outUserInfo(User user, Collection<Task> tasks) {
        System.out.println("User name: " + user.getName());
        System.out.println("User id: " + user.getId());
        System.out.print("User tasks: ");
        for (Task task : tasks) {
            if (user.getId() == task.getUserId()) {
                System.out.print(task.getName() + "   ");
            }
        }
        System.out.println("\n");
    }
}