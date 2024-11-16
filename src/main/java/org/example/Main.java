package org.example;

import org.example.dao.UserDao;
import org.example.dataSource.DataSourceConfig;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        // Создание контекста
        GenericApplicationContext context = new StaticApplicationContext();

        // Регистрация DataSource
        context.getBeanFactory().registerSingleton("dataSource", DataSourceConfig.createDataSource());

        // Регистрация UserDao
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        UserDao userDao = new UserDao(dataSource);
        context.getBeanFactory().registerSingleton("userDao", userDao);

        // Регистрация UserService
        UserService userService = new UserService(userDao);
        context.getBeanFactory().registerSingleton("userService", userService);

        UserService service = context.getBean("userService", UserService.class);

        service.save("NIKITKA");
        List<User> users = service.findAll();
        service.findById(UUID.fromString(""));
        service.delete(UUID.fromString(""));

        for (User user : users) {
            System.out.println(user);
        }
    }
}