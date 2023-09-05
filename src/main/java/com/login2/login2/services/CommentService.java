package com.login2.login2.services;

import com.login2.login2.models.CommentDto;

public interface CommentService {

	
	CommentDto createComment(CommentDto commentDto,Integer postId) throws Exception;
	void deleteComment(Integer commentId) throws Exception;
}
