package com.lcwp.todo.services.impl;

import com.lcwp.todo.dao.TodoRepository;
import com.lcwp.todo.exceptions.ResourceNotFoundException;
import com.lcwp.todo.models.Todo;
import com.lcwp.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
//@Primary
public class TodoJpaServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getTodo(int todoId) throws ParseException {
        return todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("Todo not found with given id", HttpStatus.NOT_FOUND));
    }

    @Override
    public Todo updateTodo(int todoId, Todo todo) {
        Todo todo1 = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("Todo not found with given id", HttpStatus.NOT_FOUND));
        todo1.setTitle(todo.getTitle());
        todo1.setContent(todo.getContent());
        todo1.setStatus(todo.getStatus());
        todo1.setTodoDate(todo.getTodoDate());
        return todoRepository.save(todo1);
    }

    @Override
    public void deleteTodo(int todoId) {
        Todo todo1 = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("Todo not found with given id", HttpStatus.NOT_FOUND));
        todoRepository.delete(todo1);
    }
}