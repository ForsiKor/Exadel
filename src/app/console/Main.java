package app.console;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import app.entity.Task;
import app.entity.User;
import app.service.Service;

public class Main {
    public  Date parseDate(String s) throws  Exception{
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = format.parse(s);
        return  date;
    }

      public   void menu() {
          System.out.println("1.Create new User");
          System.out.println("2.Create new Task");
          System.out.println("3.Output List Users");
          System.out.println("4.Output List Tasks");
          System.out.println("5.Remove User");
          System.out.println("6.Remove Task");
          System.out.println("7.Exit");
      }

    public  void main(String[] args) throws  Exception {
        ArrayList<Task> listTasks = new ArrayList<Task>();
        ArrayList<User> listUsers = new ArrayList<User>();
        boolean flag = true;
        do {
            menu();
            BufferedReader choice = new BufferedReader(new InputStreamReader(System.in));
            switch (Integer.parseInt(choice.readLine())) {
                case 1:
                    System.out.println("Enter name users");
                    BufferedReader userEnter = new BufferedReader(new InputStreamReader(System.in));
                    String s = userEnter.readLine();
                    if (!"out".equals(s)) {
                        Service.saveUser(s);
                    }
                    while (!"out".equals(s)) {
                        s = null;
                        s = userEnter.readLine();
                        if (!"out".equals(s)) {
                            Service.saveUser(s);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter id");
                    System.out.println("Enter name");
                    System.out.println("Enter date");
                    System.out.println("Enter user");
                    BufferedReader id = new BufferedReader(new InputStreamReader(System.in));
                    BufferedReader name = new BufferedReader(new InputStreamReader(System.in));
                    BufferedReader date = new BufferedReader(new InputStreamReader(System.in));
                    BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
                    try {
                        Service.saveTask(Integer.parseInt(id.readLine()),name.readLine(), parseDate(date.readLine()),
                                listUsers.get(Integer.parseInt(user.readLine())));
                    } catch (Exception e){
                        System.out.println("Have't this user");
                        System.out.println("This task don't create");
                    }
                    break;
                case 3:
                    for (User users : listUsers) {
                        System.out.println(users.getName());
                    }
                    System.out.println();
                    break;
                case 4:
                    for (Task task : listTasks) {
                        System.out.println("ID:" + task.getId());
                        System.out.println("Name:" + task.getName());
                        System.out.println("Deadline:" + task.getDeadline());
                        System.out.println("User:" + task.getUser().getName());
                        System.out.println();
                    }
                    System.out.println();
                    break;
                case 5:
                    System.out.println("What user to delete?");
                    BufferedReader remove = new BufferedReader(new InputStreamReader(System.in));
                    listUsers.remove(Integer.parseInt(remove.readLine()));
                    break;
                case 6:
                    System.out.println("What task to delete?");
                    BufferedReader removetasks = new BufferedReader(new InputStreamReader(System.in));
                    listTasks.remove(Integer.parseInt(removetasks.readLine()));
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
        } while (flag);
    }
}