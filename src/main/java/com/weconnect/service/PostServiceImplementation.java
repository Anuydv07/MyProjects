package com.weconnect.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weconnect.models.Post;
import com.weconnect.models.User;
import com.weconnect.repositories.PostRepository;
import com.weconnect.repositories.UserRepository;


@Service
public class PostServiceImplementation implements PostService{
	
	@Autowired
	PostRepository postRepo;
	
	@Autowired 
	UserRepository userRepo;
	
	@Autowired 
	UserService userService;

	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		Post newPost=new Post();
		User user=userService.findUserById(userId);
		
		newPost.setCaption(post.getCaption());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setImage(post.getImage());
		newPost.setUser(user);
		newPost.setVideo(post.getVideo());
		return postRepo.save(newPost);
	}
	
	

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		Post post=findPostById(postId);
		User user=userService.findUserById(userId);
		if(post.getUser().getId()!=user.getId()) {
			throw new Exception("you can't delete anothern users post");
		}
		postRepo.delete(post);
        return "post deleted succesfully";
	}
	
	

	@Override
	public List<Post> findPostByUserId(Integer userId) {
		return  postRepo.findPostByUserId(userId);
		
	}
	
	
	

	@Override
	public Post findPostById(Integer postId) throws Exception {
		Optional<Post> otp=postRepo.findById(postId);
		if(otp.isEmpty()) {
			throw new Exception ("Post not found with id"+postId);
		}
		return otp.get();
		
	}
	
	
	

	@Override
	public List<Post> findAllPost() {
		return postRepo.findAll()	;
	}

	
	
	
	@Override
	public Post savedpost(Integer postId, Integer userId) throws Exception {
		User user=userService.findUserById(userId);
		Post post=findPostById(postId);
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}
		else
			user.getSavedPost().add(post);
		userRepo.save(user);
		return post;
		
	}
	
	
	
	

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		User user=userService.findUserById(userId);
		Post post=findPostById(postId);
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}
		else {	
		post.getLiked().add(user);
		}
		return postRepo.save(post);
		
	}
	
	

}
