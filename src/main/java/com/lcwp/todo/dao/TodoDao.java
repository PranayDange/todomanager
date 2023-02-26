package com.lcwp.todo.dao;

import com.lcwp.todo.helper.DateHelper;
import com.lcwp.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

//@Component
@Repository
public class TodoDao {

    Logger logger = LoggerFactory.getLogger(TodoDao.class);


    private JdbcTemplate template;

    public TodoDao(@Autowired JdbcTemplate template) {
        this.template = template;

        //create table if does not exists
        String createTable = "create table if not exists todos(id int primary key,title varchar(100) not null,content varchar(500),status varchar(10) not null,addedDate datetime,todoDate datetime)";
        int update = template.update(createTable);
        logger.info("QUERY CREATED TODO {}", update);
//return update;

    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    //save todo in database
    public Todo saveTodo(Todo todo) {
        String insertQuery = "insert into todos(id,title,content,status,addedDate,todoDate) values(?,?,?,?,?,?)";
        int rows = template.update(insertQuery, todo.getId(), todo.getTitle(), todo.getContent(), todo.getStatus(), todo.getAddedDate(), todo.getTodoDate());


        logger.info("JDBC OPERAION : {} inserted", rows);

        return todo;

    }

    //get single todos from database
    /*public void getTodo(int id) {
        String query = "select * from todos where id=?";
        Map<String, Object> todoData = template.queryForMap(query, id);

        logger.info("TODO : {}", todoData);
    }*/
  /*  public Todo getTodo(int id) throws ParseException {
        String query = "select * from todos where id=?";
        Map<String, Object> todoData = template.queryForMap(query, id);
        //with the use of row mapper
        //Map<String, Object> todoData = template.queryForObject(query,)

        logger.info("TODOooooooooooooooo : {}", todoData);

        Todo todo = new Todo();
        todo.setId((int) todoData.get("id"));
        todo.setTitle((String) todoData.get("title"));
        todo.setContent((String) todoData.get("content"));
        todo.setStatus((String) todoData.get("status"));
        todo.setAddedDate(DateHelper.parseDate((LocalDateTime) todoData.get("addedDate")));
        todo.setTodoDate(DateHelper.parseDate((LocalDateTime) todoData.get("todoDate")));
        return todo;
    }*/

    //by using row mapper
    /*public Todo getTodo(int id) throws ParseException {
        String query = "select * from todos where id=?";
       // Map<String, Object> todoData = template.queryForMap(query, id);
        //with the use of row mapper
        Todo todo = template.queryForObject(query,new TodoRowMapper(),id);

        logger.info("TODOooooooooooooooo : {}", todo);


        return todo;
    }*/

    //by using row mapper with anonymous class
    public Todo getTodo(int id) throws ParseException {
        String query = "select * from todos where id=?";
        // Map<String, Object> todoData = template.queryForMap(query, id);
        //with the use of row mapper
        Todo todo = template.queryForObject(query, new RowMapper<Todo>() {
            @Override
            public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
                Todo todo = new Todo();
                todo.setId(rs.getInt("id"));
                todo.setTitle(rs.getString("title"));
                todo.setContent(rs.getString("content"));
                todo.setStatus(rs.getString("status"));
                try {
                    todo.setAddedDate(DateHelper.parseDate((LocalDateTime) rs.getObject("addedDate")));
                    todo.setTodoDate(DateHelper.parseDate((LocalDateTime) rs.getObject("todoDate")));

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                return todo;
            }
        }, id);

        logger.info("TODOooooooooooooooo : {}", todo);


        return todo;
    }


    //get all todos

 /*   public List<Todo> getAllTodos() {
        String query = "select * from todos";
        List<Map<String, Object>> maps = template.queryForList(query);

        List<Todo> todos = maps.stream().map((map) -> {

            Todo todo = new Todo();
            todo.setId((int) map.get("id"));
            todo.setTitle((String) map.get("title"));
            todo.setContent((String) map.get("content"));
            todo.setStatus((String) map.get("status"));
            try {
                todo.setAddedDate(DateHelper.parseDate((LocalDateTime) map.get("addedDate")));
                todo.setTodoDate(DateHelper.parseDate((LocalDateTime) map.get("todoDate")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


            return todo;

        }).collect(Collectors.toList());
        return todos;
    }*/

    //by using row mapper
   /* public List<Todo> getAllTodos() {
        String query = "select * from todos";
        List<Todo> todo = template.query(query, new TodoRowMapper());
      *//*  List<Map<String, Object>> maps = template.queryForList(query);

        List<Todo> todos = maps.stream().map((map) -> {

            Todo todo = new Todo();
            todo.setId((int) map.get("id"));
            todo.setTitle((String) map.get("title"));
            todo.setContent((String) map.get("content"));
            todo.setStatus((String) map.get("status"));
            try {
                todo.setAddedDate(DateHelper.parseDate((LocalDateTime) map.get("addedDate")));
                todo.setTodoDate(DateHelper.parseDate((LocalDateTime) map.get("todoDate")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


            return todo;

        }).collect(Collectors.toList());*//*
        return todo;
    }*/
    //by using lambda expression
    public List<Todo> getAllTodos() {
        String query = "select * from todos";
        List<Todo> todos = template.query(query, ((rs, rowNum) -> {
            Todo todo1 = new Todo();
            todo1.setId(rs.getInt("id"));
            todo1.setTitle(rs.getString("title"));
            todo1.setContent(rs.getString("content"));
            todo1.setStatus(rs.getString("status"));
            try {
                todo1.setAddedDate(DateHelper.parseDate((LocalDateTime) rs.getObject("addedDate")));
                todo1.setTodoDate(DateHelper.parseDate((LocalDateTime) rs.getObject("todoDate")));

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return todo1;
        }));
      /*  List<Map<String, Object>> maps = template.queryForList(query);

        List<Todo> todos = maps.stream().map((map) -> {

            Todo todo = new Todo();
            todo.setId((int) map.get("id"));
            todo.setTitle((String) map.get("title"));
            todo.setContent((String) map.get("content"));
            todo.setStatus((String) map.get("status"));
            try {
                todo.setAddedDate(DateHelper.parseDate((LocalDateTime) map.get("addedDate")));
                todo.setTodoDate(DateHelper.parseDate((LocalDateTime) map.get("todoDate")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


            return todo;

        }).collect(Collectors.toList());*/
        //  return todo;
        return todos;
    }

    //update todo
    public Todo updateTodo(int id, Todo newTodo) {
        String query = "update todos set title=?,content=?,status=?,addedDate=?,todoDate=? where id =?";
        int update = template.update(query, newTodo.getTitle(), newTodo.getContent(), newTodo.getStatus(), newTodo.getAddedDate(), newTodo.getTodoDate(), id);
        logger.info("updated todo : {}", update);
        newTodo.setId(id);
        return newTodo;
    }

    //delete todo
    public void deleteTodo(int id) {
        String query = "delete from todos where id=?";
        int update = template.update(query, id);
        logger.info("Todos DEleted : {}", update);
    }

    //delete multiple
    public void deleteMultiple(int ids[]) {
        String query = "delete from todos where id=?";
        //
        int[] ints = template.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                int id = ids[i];
                ps.setInt(1, id);
            }

            @Override
            public int getBatchSize() {
                return ids.length;
            }
        });
        for (int i : ints) {
            logger.info("deleted : {}", i);
        }
    }
}
