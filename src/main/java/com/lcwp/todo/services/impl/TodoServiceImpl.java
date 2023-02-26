package com.lcwp.todo.services.impl;

import com.lcwp.todo.exceptions.ResourceNotFoundException;
import com.lcwp.todo.models.Todo;
import com.lcwp.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Component
@Service
public class TodoServiceImpl implements TodoService {

    Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
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
        //   Todo todo = todos.stream().filter(todo2 -> todoId == todo2.getId()).findAny().get();
        Todo todo = todos.stream().filter(todo2 -> todoId == todo2.getId()).findAny().orElseThrow(() -> new ResourceNotFoundException("Todo not found with given Id", HttpStatus.NOT_FOUND));
        logger.info("TODO : {} ", todo);
        return todo;
    }

    public Todo updateTodo(int todoId, Todo todo) {
        List<Todo> newUpdatedList = todos.stream().map(todo2 -> {
            if (todo2.getId() == todoId) {
                //perfrom update
                todo2.setContent(todo.getContent());
                todo2.setTitle(todo.getTitle());
                todo2.setStatus(todo.getStatus());
                return todo2;
            } else {
                return todo2;
            }
        }).collect(Collectors.toList());
        todos = newUpdatedList;
        todo.setId(todoId);
        return todo;

    }

    public void deleteTodo(int todoId) {
        logger.info("DELETING TODO ");
        List<Todo> newList = todos.stream().filter(todo3 -> todo3.getId() != todoId).collect(Collectors.toList());
        todos = newList;
    }
}
