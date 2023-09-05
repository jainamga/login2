package com.login2.login2.models;




public class CategoryDto {

	
	private Integer categoryId;
	private String cateogoryTitle;
	public CategoryDto() {
		super();
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCateogoryTitle() {
		return cateogoryTitle;
	}
	public void setCateogoryTitle(String cateogoryTitle) {
		this.cateogoryTitle = cateogoryTitle;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	private String categoryDescription;

}
