package com.url.shortener.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.url.shortener.entities.Todo;


@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{

}
