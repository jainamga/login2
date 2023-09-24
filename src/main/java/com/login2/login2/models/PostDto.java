package com.login2.login2.models;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


public class PostDto {
private Integer postId;
private String title;
private String content;
private String imageName;
private Date dateAdded;

public PostDto() {
	super();
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public String getImageName() {
	return imageName;
}

public void setImageName(String imageName) {
	this.imageName = imageName;
}

public Date getDateAdded() {
	return dateAdded;
}

public void setDateAdded(Date dateAdded) {
	this.dateAdded = dateAdded;
}


public CategoryDto getCategory() {
	return category;
}

public void setCategory(CategoryDto category) {
	this.category = category;
}

public User getUser() {
	return user;
}

public Set<CommentDto> getComments() {
	return comments;
}

public void setComments(Set<CommentDto> comments) {
	this.comments = comments;
}

public void setUser(User user) {
	this.user = user;
}


public Integer getPostId() {
	return postId;
}

public void setPostId(Integer postId) {
	this.postId = postId;
}


private CategoryDto category;

private User user;

private Set<CommentDto> comments = new HashSet<>();


}
