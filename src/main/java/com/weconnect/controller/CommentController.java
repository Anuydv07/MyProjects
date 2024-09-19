package com.weconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.weconnect.models.Comment;
import com.weconnect.models.User;
import com.weconnect.service.CommentService;
import com.weconnect.service.UserService;


@CrossOrigin(origins = "*")
@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/comments/post/{postId}")
	public Comment createComment(@RequestBody Comment comment,
			@RequestHeader("Authorization")String jwt,
			@PathVariable("postId") Integer postId)throws Exception {
		
		User user=userService.findUserByJwt(jwt);
		Comment createdComment=commentService.createComment(comment,
				user.getId(),postId);
		return createdComment;
	}
	
	@PutMapping("/api/comment/like/post/{commentId}")
	public Comment LikeComment(@RequestHeader("Authorization")String jwt,
			@PathVariable Integer commentId)throws Exception {
		
		User user=userService.findUserByJwt(jwt);
		Comment likedComment=commentService.likeComment(commentId,
				user.getId());
		return likedComment;
	}
}
