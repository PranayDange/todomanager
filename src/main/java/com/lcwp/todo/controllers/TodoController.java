package com.lcwp.todo.controllers;

import com.lcwp.todo.models.Todo;
import com.lcwp.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/todos")
public class TodoController {

    Logger logger = LoggerFactory.getLogger(TodoController.class);
    @Autowired
    private TodoService todoService;

    Random random = new Random();

    //create
    @PostMapping("/create")
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo) {
        logger.info("Create Todo");
        int id = random.nextInt(9999999);
        todo.setId(id);
        Todo todo1 = todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);
    }

    //get all todo method
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodo() {
        List<Todo> getAllTodos = todoService.getAllTodos();
        return new ResponseEntity<>(getAllTodos, HttpStatus.OK);
    }

    //get single todo
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId) {
        Todo todo = todoService.getTodo(todoId);
        return ResponseEntity.ok(todo);
    }

    //update todo
    //for updating data we use put
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoWithNewDetails, @PathVariable int todoId) {
        Todo todo = todoService.updateTodo(todoId, todoWithNewDetails);
        return ResponseEntity.ok(todo);
    }

    //delete
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId){
        todoService.deleteTodo(todoId);
        return  ResponseEntity.ok("TODO SUCCESSFULLY DELETED");

    }
}
