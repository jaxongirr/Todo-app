package com.example.todoapp.repository;

import com.example.todoapp.domain.ToDo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends PagingAndSortingRepository<ToDo, Long> {

}
