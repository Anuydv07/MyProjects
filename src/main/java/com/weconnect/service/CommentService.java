package com.weconnect.service;

import com.weconnect.models.Comment;

public interface CommentService {

	public Comment createComment(Comment comment,
			Integer userId,
			Integer postId)throws Exception;
	
	public Comment findCommentById(Integer commentId)throws Exception;
	
	public Comment likeComment(Integer CommentId,
			Integer userId) throws Exception;
}
