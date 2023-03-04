package com.lcwp.todo.dao;

import com.lcwp.todo.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;


//JPA


public interface TodoRepository extends JpaRepository<Todo, Integer> {


}