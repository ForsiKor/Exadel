package app.console;

import app.entity.*;
import app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Scanner;

@Component
public class Console {
    @Autowired
    private IService<User> userService;
    @Autowired
    private IService<Task> taskService;

    private static void out() {
        System.out.println("1.Create new User");
        System.out.println("2.Create new Task");
        System.out.println("3.Output List Users");
        System.out.println("4.Output List Tasks");
        System.out.println("5.Delete User");
        System.out.println("6.Delete Task");
        System.out.println("7.Exit");
    }

    public void menu() throws IOException{
        int count = 0;
        while (count != 7) {
            Task task;
            User user;
            out();
            BufferedReader choiceReader = new BufferedReader(new InputStreamReader(System.in));
            String choice = choiceReader.readLine();
            switch (Integer.parseInt(choice)) {
                case 1:
                    user = inputUser();
                    while ((user = userService.add(user)) == null) {
                        System.out.println("A user with the same name already exists. Pleas input new user");
                        user = inputUser();
                    }
                    System.out.println("User: " + user.getName() + " with id: " + user.getId() + " successfully added");
                    break;
                case 2:
                    task = inputTask();
                    user = inputUser();
                    if (userService.get(user.getName()) == null) {
                        user = userService.add(user);
                        task.setUserId(user.getId());
                        task.setDeadline(inputData());
                        task = taskService.add(task);
                        System.out.println("User: " + user.getName() + " with id: " + user.getId() + " successfully added");
                        System.out.println("Task: " + task.getName() + " with id: " + task.getId() + " successfully added");
                    } else {
                        user = userService.get(user.getName());
                        task.setUserId(user.getId());
                        task.setDeadline(inputData());
                        while ((task = taskService.add(task)) == null) {
                            System.out.println("Task with same name are exists, please enter new task: ");
                            task = inputTask();
                            task.setUserId(user.getId());
                            task.setDeadline(inputData());
                        }
                        System.out.println("Task: " + task.getName() + " with id: " + task.getId() + " successfully added");
                    }
                    break;
                case 3:
                    if (userService.getAll().size() != 0) {
                        for (User user2 : userService.getAll()) {
                            outUserInfo(user2, taskService.getAll());
                        }
                    } else {
                        System.out.println("User list is empty!");
                    }
                    break;
                case 4:
                    if (taskService.getAll().size() != 0) {
                        for (Task task2 : taskService.getAll()) {
                            outTaskInfo(task2, userService.getAll());
                        }
                    } else {
                        System.out.println("Task list is empty!");
                    }
                    break;
                case 5:
                    user = inputUser();
                    if (userService.get(user.getName()) == null) {
                        System.out.println("A user with the same name don't exists");
                    } else {
                        userService.delete(user.getName());
                        System.out.println("\nUser: " + user.getName() + " with his tasks, successfully deleted");
                    }
                    break;
                case 6:
                    task = inputTask();
                    if (!taskService.delete(task.getName())) {
                        System.out.println("A task with the same name don't exists");
                    } else {
                        System.out.println("Task: " + task.getName() + " successfully deleted");
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

    private String inputData() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String deadline;
        while (true) {
            try {
                System.out.println("Enter task deadline in format: dd.MM.yyyy ");
                deadline = reader.readLine();
                break;
            } catch (Exception e) {
                System.out.println("Date error. Please try again: ");
            }
        }
        return deadline;
    }

    private Task inputTask() {
        Scanner nameScanner = new Scanner(System.in);
        String taskName;
        System.out.println("Enter name of task: ");
        taskName = nameScanner.nextLine();

        while (taskName.length() == 0) {
            System.out.println("Empty string, please enter new task name:");
            taskName = nameScanner.nextLine();
        }
        taskName = taskName.replace(",", " ");
        return new Task(taskName);
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

    public User inputUser() {
        Scanner nameScanner = new Scanner(System.in);
        String userName;

        System.out.println("Enter name of User: ");
        userName = nameScanner.nextLine();

        while (userName.length() == 0) {
            System.out.println("Empty string, please enter new user name:");
            userName = nameScanner.nextLine();
        }
        userName = userName.replace(",", " ");
        return new User(userName);
    }
}
