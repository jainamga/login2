package com.login2.login2.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login2.login2.entity.Category;
import com.login2.login2.models.CategoryDto;
import com.login2.login2.repository.CategoryRepo;

@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	CategoryRepo categoryrepo;
	
	@Autowired
	private ModelMapper modelmapper;	
	
	@Override
	public CategoryDto createCategory(CategoryDto categorydto) {
     Category cat = this.modelmapper.map(categorydto, Category.class);
      Category addedCat = this.categoryrepo.save(cat);
      return this.modelmapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categorydto, Integer categoryId) throws Exception {
		Category cat = this.categoryrepo.findById(categoryId).orElseThrow(()->new Exception("dsa"));
		cat.setCateogoryTitle(categorydto.getCateogoryTitle());
		cat.setCategoryDescription(categorydto.getCategoryDescription());
		
		Category upcat= this.categoryrepo.save(cat);
		  	 	
		
		
		return this.modelmapper.map(upcat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) throws Exception {
			
		Category cat = this.categoryrepo.findById(categoryId).orElseThrow(()->new Exception("dsa"));
		categoryrepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
	
		return null;
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> l = this.categoryrepo.findAll();
		List<CategoryDto> lo= l.stream().map((cat)->this.modelmapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return lo;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categorydto) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
