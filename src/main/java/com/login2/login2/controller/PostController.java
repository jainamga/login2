package com.login2.login2.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.login2.login2.config.AppConstants;
import com.login2.login2.models.PostDto;
import com.login2.login2.models.PostResponse;
import com.login2.login2.services.FileService;
import com.login2.login2.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	PostService postservice;
	
	@Autowired
	FileService fileservice;
	
	@Value("${project.image}")
	String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Long userId,@PathVariable Integer categoryId) throws Exception
	{
		PostDto createPost = this.postservice.createPost(postDto, userId, categoryId);
		
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/posts")
	public List<PostDto> getPostsByUser(@PathVariable Long userId) throws Exception
	{
		List<PostDto> pos =this.postservice.getPostsByUser(userId);
		return pos;
		
	}
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCat(@PathVariable Integer categoryId) throws Exception
	{
		List<PostDto> pos =this.postservice.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(pos,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

		PostResponse postResponse = this.postservice.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	  
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostByID(@PathVariable Integer postId) throws Exception
	{
		PostDto allPost = this.postservice.getPostById(postId);
		
		return new ResponseEntity<PostDto>(allPost,HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId) throws Exception
	{
		PostDto uppostDto = this.postservice.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(uppostDto,HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
		List<PostDto> posts = this.postservice.searchPostByKeyword(keywords);
		return new ResponseEntity<List<PostDto>>(posts ,HttpStatus.OK);
	}
	
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@PathVariable Integer postId, @RequestParam("image") MultipartFile image) throws Exception
	{
		String fileName=this.fileservice.UploadImage(path, image);
		PostDto postDto=this.postservice.getPostById( postId);
		postDto.setImageName(fileName);
		PostDto updatedPostDto=	this.postservice.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPostDto,HttpStatus.OK);
	
	}
	
	@GetMapping(value="post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException
	{
		InputStream resourse = this.fileservice.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resourse,response.getOutputStream());
		
		
	}
}
