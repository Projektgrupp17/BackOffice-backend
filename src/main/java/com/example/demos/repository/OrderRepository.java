package com.example.demos.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.example.demos.model.*;

public interface OrderRepository extends CrudRepository<Order,String> {
    
    List<Order> findByuser(String user);
}