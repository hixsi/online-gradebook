package com.example.onlinegradebook.repositories;


import com.example.onlinegradebook.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
  List<School> findAllByName(String name);
}
