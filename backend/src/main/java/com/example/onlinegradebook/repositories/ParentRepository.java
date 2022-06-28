package com.example.onlinegradebook.repositories;

import com.example.onlinegradebook.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Integer> {
   List<Parent> findAllByName(String name);
}
