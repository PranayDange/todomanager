package com.lcwp.todo;

import com.lcwp.todo.dao.TodoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        /*System.out.println("Application started:");
        JdbcTemplate template = todoDao.getTemplate();
        logger.info("Template object : {}",template);*/
        /*Todo todo = new Todo();
        todo.setId(5);
        todo.setTitle("afternoon List");
        todo.setContent("coding");
        todo.setStatus("pending");
        todo.setAddedDate(new Date());
        todo.setTodoDate(new Date());

        todoDao.saveTodo(todo);*/

      /*  Todo todo = todoDao.getTodo(4);
        logger.info("TODO : {}", todo);*/


       /* todo.setTitle("learn spring course");
        todo.setContent("jdbc,boot,jpa");
        todo.setStatus("done");
        todo.setAddedDate(new Date());
        todo.setTodoDate(new Date());
        todoDao.updateTodo(1,todo);*/
   /*    List<Todo> todos=todoDao.getAllTodos();
        logger.info("ALL TODO LIST : {}", todos);*/

        // todoDao.deleteTodo(1 );

       // todoDao.deleteMultiple(new int[]{2, 3});
    }


}
