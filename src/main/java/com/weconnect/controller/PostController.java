package com.weconnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.weconnect.models.Post;
import com.weconnect.models.User;
import com.weconnect.response.ApiResponse;
import com.weconnect.service.PostService;
import com.weconnect.service.UserService;



//List<>

@CrossOrigin(origins = "*")
@RestController
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;

	@GetMapping("/api/posts") 
	public ResponseEntity<List<Post>> findAllPosts(){
		List<Post> posts=postService.findAllPost();
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@PostMapping("/api/post/user")
	public ResponseEntity<Post> createPost(
			@RequestHeader("Authorization") String jwt,
			@RequestBody Post post) throws Exception {
		
		User reqUser=userService.findUserByJwt(jwt);
		
		
		Post createdPost=postService.createNewPost(post, reqUser.getId());
		return new ResponseEntity<Post>(createdPost,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/api/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(
			@PathVariable Integer postId,
			@RequestHeader("Authorization") String jwt)throws Exception{
		
		User reqUser=userService.findUserByJwt(jwt);
		String msg=postService.deletePost(postId, reqUser.getId());
		ApiResponse res=new ApiResponse(msg,true);
		return new ResponseEntity<>(res,HttpStatus.OK);

	}
	
	@GetMapping("/api/post/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId)throws Exception{
		Post post=postService.findPostById(postId);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/posts/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId) throws Exception{
		List<Post> posts=postService.findPostByUserId(userId);
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}

	@PutMapping("/api/savedPost/{postId}/user")
	public ResponseEntity<Post> savePost(@PathVariable Integer postId,
			@RequestHeader("Authorization") String jwt)throws Exception{
		
		User reqUser=userService.findUserByJwt(jwt);
		Post post=postService.savedpost(postId, reqUser.getId());
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/api/post/like/{postId}/user")
	public ResponseEntity<Post> likePost(@PathVariable Integer postId,
			@RequestHeader("Authorization") String jwt)throws Exception{
		
		User reqUser=userService.findUserByJwt(jwt);
		Post post=postService.likePost(postId, reqUser.getId());
		return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
	}
}
