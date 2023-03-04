package com.lcwp.todo.services.impl;

import com.lcwp.todo.dao.TodoDao;
import com.lcwp.todo.models.Todo;
import com.lcwp.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;


//JDBC
@Service
@Primary
public class DaoTodoServiceImpl implements TodoService {
    @Autowired
    private TodoDao todoDao;

    @Override
    public Todo createTodo(Todo todo) {
        return todoDao.saveTodo(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoDao.getAllTodos();
    }

    @Override
    public Todo getTodo(int todoId) throws ParseException {
        return todoDao.getTodo(todoId);
    }

    @Override
    public Todo updateTodo(int todoId, Todo todo) {
        return todoDao.updateTodo(todoId, todo);
    }

    @Override
    public void deleteTodo(int todoId) {
        todoDao.deleteTodo(todoId);

    }
}
