package app.dao;

import app.entity.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Profile("jdbcSystem")
public class JDBCUserDaoImpl implements IDao<User>{

    public JDBCUserDaoImpl() {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(id SERIAL PRIMARY KEY, name varchar NOT NULL);");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public User create(User user){
        try {
            PreparedStatement preparedStatement = getStatement("Insert into  users (name) values  (?)");
            preparedStatement.setString(1,user.getName());
            int rez = preparedStatement.executeUpdate();
            closeConnection(getConnection());
            if (rez == 0) {
                return null;
            } else {
                return user;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean delete(String name){
        try {
            PreparedStatement preparedStatement = getStatement("Delete from users where name = ?");
            preparedStatement.setString(1,name);
            int rez = preparedStatement.executeUpdate();
            closeConnection(getConnection());
            if (rez == 0) {
                return false;
            } else {
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Collection<User> getAll(){
        Map<String, User> users = new HashMap();
        try {
            ResultSet resultSet = getStatement("Select * from users").executeQuery();
            while (resultSet.next()) {
                users.put(setUserInfo(resultSet).getName(),setUserInfo(resultSet));
            }
            closeConnection(getConnection());
            return users.values();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users.values();
    }
    @Override
    public User get(String name){
        try {
            PreparedStatement preparedStatement =  getStatement("Select id, name from users where name = ?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                closeConnection(getConnection());
                return null;
            } else {
                User user = setUserInfo(resultSet);
                closeConnection(getConnection());
                return user;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    private User setUserInfo(ResultSet resultSet){
        try {
            String userName = resultSet.getString(2);
            User user = new User(userName);
            user.setId(resultSet.getInt(1));
            return user;
        }catch (SQLException e){
            e.printStackTrace();
        }return null;
    }

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(BD);
            return connection;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private PreparedStatement getStatement(String sql){
        try {
            return getConnection().prepareStatement(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    private void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
