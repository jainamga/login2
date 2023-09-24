package com.login2.login2.services;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.login2.login2.models.PostDto;
import com.login2.login2.models.PostResponse;
import com.login2.login2.models.User;
import com.login2.login2.entity.Post;
import com.login2.login2.entity.Category;

import com.login2.login2.repository.CategoryRepo;
import com.login2.login2.repository.PostRepo;
import com.login2.login2.repository.UserRepository;


@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Override
	public PostDto createPost(PostDto postDto,Long userId,Integer categoryId) throws Exception {
		
		User user = this.userrepo.findById(userId).orElseThrow(()->new Exception("ds"));
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new Exception("fa"));
		Post post = this.modelMapper.map(postDto,Post.class);
		post.setImageName("default.png");
		post.setAddDate(new Date(0));
		post.setUser(user);
		post.setCategory(cat);
		Post newPost= this.postrepo.save(post);
		
		return this.modelMapper.map(newPost,PostDto.class);
		
		
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) throws Exception {
		Post post = this.postrepo.findById(postId).orElseThrow(()-> new Exception("ds"));
		
		post.setTitle(postDto.getTitle());
		post.setImageName(postDto.getImageName());
		post.setContent(postDto.getContent());
		Post updatedPost = this.postrepo.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	 @Override
	    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

	        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

	        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

	        Page<Post> pagePost = this.postrepo.findAll(p);

	        List<Post> allPosts = pagePost.getContent();

	        List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
	                .collect(Collectors.toList());

	        PostResponse postResponse = new PostResponse();

	        postResponse.setContent(postDtos);
	        postResponse.setPageNumber(pagePost.getNumber());
	        postResponse.setPageSize(pagePost.getSize());
	        postResponse.setTotalElements(pagePost.getTotalElements());

	        postResponse.setTotalPages(pagePost.getTotalPages());
	        postResponse.setLastPage(pagePost.isLast());

	        return postResponse;
	    }

	@Override
	public PostDto getPostById(Integer postId) throws Exception {
			Post pos =this.postrepo.findById(postId).orElseThrow(()->new Exception("ds"));
			
		return this.modelMapper.map(pos, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) throws Exception {
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new Exception("dada"));
		List<Post> posts=this.postrepo.findByCategory(cat);
		List<PostDto> postDto =  posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return postDto;
	}

	@Override
	public List<PostDto> getPostsByUser(Long userId) throws Exception {
		User user = this.userrepo.findById(userId).orElseThrow(()->new Exception("dsad"));
	List<Post> posts =  	this.postrepo.findByUser(user);
	List<PostDto> postDto =  posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
return postDto;
	
	
	}

	@Override
	public List<PostDto> searchPostByKeyword(String keyword) {
		 List<Post> posts = this.postrepo.findByTitleContaining("%"+keyword+"%");
			List<PostDto> postDto =  posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDto;
	}
	

}
