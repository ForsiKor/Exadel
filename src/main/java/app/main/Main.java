package app.main;

import app.console.Console;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        Console application = context.getBean("console", Console.class);
        try {
            application.menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("EXIT");
    }
}


/*
<bean id="userDbDao" class="app.dao.JDBCUserDaoImpl"> </bean>
<bean id="taskDbDao" class="app.dao.JDBCTaskDaoImpl"> </bean>
<bean id="userDbService" class="app.service.JDBCServiceImpl">
<constructor-arg index = "0" ref="userDbDao"/>
</bean>
<bean id="taskDbService" class="app.service.JDBCServiceImpl">
<constructor-arg index = "0" ref="taskDbDao"/>
</bean>
<bean id="userFileDao" class="app.dao.UserDaoImpl"> </bean>
<bean id="taskFileDao" class="app.dao.TaskDaoImpl"> </bean>
<bean id="FileService" class="app.service.FileServiceImpl">
<constructor-arg index = "0" ref="taskFileDao"/>
</bean>
<bean id="userFileService" class="app.service.FileServiceImpl">
<constructor-arg index = "0" ref="userFileDao"/>
</bean>
<bean id="console" class="app.console.Console">
<constructor-arg index = "0" ref="ServiceImpl"/>

</bean>*/