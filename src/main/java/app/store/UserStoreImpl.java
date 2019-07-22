package app.store;

import app.entity.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserStoreImpl implements IStore<User> {
    @Override
    public void addInfo(Map<Integer, User> users) {
        try {
            Writer writer = new FileWriter("Users.txt");

            for (User user : users.values()) {
                writer.write(" " + user.getId());
                writer.write("," + user.getName());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Map<Integer, User> getInfo(){
            Map<Integer, User> users = new HashMap();
            try {
                File file = new File("Users.txt");
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (bufferedReader.read() != -1) {
                    String[] string = bufferedReader.readLine().split(",");
                    User user = new User(string[1]);
                    user.setId(string[0]);
                    users.put(user.getId(), user);
                }
                return users;
            } catch(Exception e){
            e.printStackTrace();
        }
        return users;
    }
    @Override
    public int getMaxId() {
        Map<Integer, User> users = getInfo();
        int maxId = -1;
        for(User user : users.values()){
            if (user.getId() > maxId){
                maxId = user.getId();
            }
        }
        maxId++;
        return maxId;
    }
}
