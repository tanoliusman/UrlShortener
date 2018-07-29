package com.url.shortener.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortener.entities.Todo;
import com.url.shortener.services.TodoService;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;


    @GetMapping
    public List<Todo> getAllTodos(){
        return todoService.getAllTodos();
    }

    @PostMapping
    public Todo saveTodo(@RequestBody Todo todo){
        return todoService.saveTodo(todo);
    }

    @PutMapping
    public Boolean markCompleted(@RequestBody String[] ids) {
    	return todoService.markCompleted(ids);
    }
    
    @DeleteMapping
    public Boolean deleteTodo(@RequestBody String todoId){
       return todoService.deleteTodo(todoId);
    	
    }

}
