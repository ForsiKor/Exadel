package app.dao;

import java.sql.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;

import app.entity.Task;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jdbcSystem")
public class JDBCTaskDaoImpl implements IDao<Task> {

    public JDBCTaskDaoImpl(){
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(id SERIAL PRIMARY KEY, name varchar NOT NULL);");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS tasks(Id SERIAL PRIMARY KEY, name VARCHAR NOT NULL, " +
                    "deadeLine VARCHAR NOT NULL, userId INTEGER NOT NULL, " +
                    "FOREIGN KEY (userId) REFERENCES users (Id) ON DELETE CASCADE);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Task create(Task task) {
        try {
            PreparedStatement preparedStatement = getStatement("Insert into  tasks (name, deadLine, userId) values  (?,?,?)");
            preparedStatement.setString(1, task.getName());
            preparedStatement.setDate(2, Date.valueOf(task.getDeadline()));
            preparedStatement.setInt(3, task.getUserId());
            int rez = preparedStatement.executeUpdate();
            closeConnection(getConnection());
            if (rez == 0) {
                return null;
            } else {
                return task;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean delete(String name) {
        try {
            PreparedStatement preparedStatement = getStatement("Delete from tasks where name = ?");
            preparedStatement.setString(1,name);
            int rez = preparedStatement.executeUpdate();
            closeConnection(getConnection());
            if (rez == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Task get(String name) {
        try {
            PreparedStatement preparedStatement =  getStatement("Select id, name, deadline, userId from tasks where name = ?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                closeConnection(getConnection());
                return null;
            } else {
                Task task = setTaskInfo(resultSet);
                closeConnection(getConnection());
                return task;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Task> getAll() {
        Map<String, Task> tasks = new HashMap();
        try {
            ResultSet resultSet = getStatement("Select  from tasks").executeQuery();
            while (resultSet.next()) {
                Task task = setTaskInfo(resultSet);
                tasks.put(task.getName(),task);
            }
            closeConnection(getConnection());
            return tasks.values();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks.values();
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(BD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Task setTaskInfo(ResultSet resultSet){
        try {
            String taskName = resultSet.getString("name");
            String taskDeadLine = resultSet.getString("deadline");
            Task task = new Task(taskName, taskDeadLine);
            task.setId(resultSet.getInt("id"));
            task.setUserId(resultSet.getInt("userid"));
            return task;
        }catch (SQLException e){
            e.printStackTrace();
        }return null;
    }

    private PreparedStatement getStatement(String sql) {
        try {
            return getConnection().prepareStatement(sql);
        } catch (SQLException e) {
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

