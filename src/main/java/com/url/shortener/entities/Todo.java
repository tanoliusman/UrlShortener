package com.url.shortener.entities;

import javax.persistence.*;

@Entity
@Table(name = "tbl_todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String todoText;
    
    private Boolean isCompleted;

    public Todo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

    public String getTodoText() {
		return todoText;
	}

	public void setTodoText(String todoText) {
		this.todoText = todoText;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	@Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", text='" + todoText + '\'' +
                ", completed=" + isCompleted +
                '}';
    }
}
