package com.login2.login2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login2.login2.entity.Category;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
