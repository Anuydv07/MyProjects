package com.weconnect.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weconnect.models.Comment;
import com.weconnect.models.Post;
import com.weconnect.models.User;
import com.weconnect.repositories.CommentRepository;
import com.weconnect.repositories.PostRepository;

@Service
public class CommentServiceImplementation implements CommentService {
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService; 
	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private PostRepository postRepo;

	@Override
	public Comment createComment(Comment comment, Integer userId, Integer postId) throws Exception {
	
		User user=userService.findUserById(userId);
		Post post=postService.findPostById(postId);
		
		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreatedAt(LocalDateTime.now());
		Comment savedComment=commentRepo.save(comment);
		post.getComments().add(savedComment);
		postRepo.save(post);
		
		return savedComment;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		Optional<Comment> opt=commentRepo.findById(commentId);
		if(opt.isEmpty()) {
			throw new Exception("Comment not exist");
		}
		return  opt.get();
		
	}

	@Override
	public Comment likeComment(Integer CommentId, Integer userId) throws Exception {
		Comment comment=findCommentById(CommentId);
		User user=userService.findUserById(userId);
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		}
		else {
			comment.getLiked().remove(user);
		}
		return commentRepo.save(comment);
	}

}
