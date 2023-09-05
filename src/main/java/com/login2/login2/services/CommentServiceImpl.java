package com.login2.login2.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login2.login2.entity.Comment;
import com.login2.login2.entity.Post;
import com.login2.login2.models.CommentDto;
import com.login2.login2.repository.CommentRepo;
import com.login2.login2.repository.PostRepo;


@Service
public class CommentServiceImpl implements CommentService{

	
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) throws Exception {
		Post post= this.postRepo.findById(postId).orElseThrow(()->new Exception());
		Comment comment= this.modelMapper.map(commentDto,Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) throws Exception {
		Comment com= this.commentRepo.findById(commentId).orElseThrow(()->new Exception());
		this.commentRepo.delete(com);
		
		
	}

	
	
	
}
