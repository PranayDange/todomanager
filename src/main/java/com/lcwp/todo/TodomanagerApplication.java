package com.lcwp.todo;

import com.lcwp.todo.dao.TodoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class TodomanagerApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(TodomanagerApplication.class);
    @Autowired
    //non static
    private TodoDao todoDao;


    //static method
    public static void main(String[] args) {
        SpringApplication.run(TodomanagerApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started:");
        JdbcTemplate template = todoDao.getTemplate();
        logger.info("Template object : {}",template);
    }
}
