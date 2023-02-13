package com.lcwp.todo.services;

import com.lcwp.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TodoService {

    Logger logger = LoggerFactory.getLogger(TodoService.class);
    List<Todo> todos = new ArrayList<>();

    public Todo createTodo(Todo todo) {
        todos.add(todo);
        logger.info("TODOS {} ", this.todos);
        return todo;
    }

    public List<Todo> getAllTodos() {
        return todos;
    }

    public Todo getTodo(int todoId) {
        Todo todo = todos.stream().filter(todo2 -> todoId == todo2.getId()).findAny().get();
        logger.info("TODO : {} ", todo);
        return  todo;
    }
}
