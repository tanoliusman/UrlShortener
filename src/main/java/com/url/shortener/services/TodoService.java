package com.url.shortener.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.shortener.entities.Todo;
import com.url.shortener.repositories.TodoRepository;


@Service
public class TodoService {

	
	@Autowired
	private TodoRepository todoRepository;
	
	public List<Todo> getAllTodos(){
		try {
		return todoRepository.findAll();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<Todo>();
	}

    public Todo saveTodo(Todo todo) {
		try{
			return todoRepository.save(todo);
		}catch(Exception ex){
			return null;
		}
    }

	public Boolean markCompleted(String[] ids) {
		try {
			for(String id: ids) {
				Todo todo = todoRepository.findOne(Long.parseLong(id));
				if(todo !=null) {
					todo.setIsCompleted(true);
					todoRepository.save(todo);
				}
			}
		} catch(Exception ex) {
			return false;
		}
		return true;
	}

	public Boolean deleteTodo(String todoId) {
		
		try {
			todoRepository.delete(Long.parseLong(todoId));
		}catch(Exception ex) {
			return false;
		}
		return true;
	}


}
