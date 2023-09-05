package com.login2.login2.services;

import java.util.List;

import com.login2.login2.models.CategoryDto;



public interface CategoryService {
  CategoryDto createCategory(CategoryDto categorydto);
  CategoryDto updateCategory(CategoryDto categorydto);
  void deleteCategory(Integer categoryId) throws Exception;
  CategoryDto getCategory(Integer categoryId);
  List<CategoryDto> getCategories();
  CategoryDto updateCategory(CategoryDto categorydto, Integer categoryId) throws Exception;
  
}
