package com.user.example.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.example.entity.Comment;
import com.user.example.entity.CommentReply;
import com.user.example.entity.Product;
import com.user.example.entity.User;
import com.user.example.repo.ProductInterface;
import com.user.example.userService.CommentService;
import com.user.example.userService.UserService;
    
@RestController
@RequestMapping("/comment")
public class CommentController {
    
	@Autowired
	private CommentService commentService;
	//Showing all Comment
	@GetMapping("/getComment")
	public List<Comment> getComment() {
		return commentService.showComment();
	}
	//Showing All replies to comment
	@GetMapping("/getReplyComment")
	public List<CommentReply> getReplyComment() {
		return commentService.showReplyComment();
	}
	
//	//Getting comment to Product  by nitesh
//	@GetMapping("/getPostComment/{product_id}")
//	public List<Comment> getPostComment(@RequestBody Comment comment, @PathVariable("product_id") String product_id){
//		return commentService.showPostComment(comment, product_id);
//	}
	//Getting comment to Product by deepak
	@GetMapping("/getPostComment/{product_id}")
	public List<Comment> getPostComment(@PathVariable("product_id") String product_id){
		return commentService.showPostComment( product_id);
	}
	
	//Getting comment replies 
	@GetMapping("/getReplyComment/{id}")
	public List<CommentReply> getPostReplyComment(@RequestBody CommentReply commentReply, @PathVariable("id") String id){
		return commentService.showPostReplyComment(commentReply, id);
	}
	
//	//Comment to product by nitesh
//	@PostMapping("/comment/{product_id}/{emp_id}")
//	public Comment createComment(@RequestBody Comment comment, @PathVariable("product_id") String product_id, @PathVariable("emp_id") String emp_id ) {
//		return commentService.addComment(comment, product_id , emp_id );
//	}
	//Comment to product by deepak
	@PostMapping("/comment")
	public Comment createComment(@RequestBody Comment comment ) {
		return commentService.addComment(comment);
	}
	
	//reply to comment
	@PostMapping("/replyComment/{id}/{emp_id}")
	public CommentReply replyComment(@RequestBody CommentReply commentReply, @PathVariable("id") String id, @PathVariable("emp_id") String emp_id) {
		return commentService.addReplyComment(commentReply, id, emp_id);
	}
	
	//delete comment by Author of comment by nitesh
//	@DeleteMapping("/deleteComment/{id}/{emp_id}")
//    public String deleteComment(@RequestBody Comment comment, @PathVariable("id") String id , @PathVariable("emp_id") String emp_id) {
//    	return commentService.eraseComment(comment,id, emp_id);
//    }
	//delete comment by deepak
	@DeleteMapping("/deleteComent/{id}/{eid}")
    public String deleteComent(@PathVariable("id") String id, @PathVariable("eid") String cid) {
    	return commentService.eraseComent(id,cid);
    }
	@DeleteMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable("id") String id) {
    	return commentService.eraseComment(id);
    }
	
	//delete reply to comment by Author of comment
	@DeleteMapping("/deleteReplyComment/{id}/{emp_id}")
	public String deleteReplyComment(@RequestBody CommentReply commentReply , @PathVariable("id") String id, @PathVariable("emp_id") String emp_id) {
		return commentService.eraseReplyComment(commentReply, id, emp_id);
	}
	
	//update comment by Author of comment
	@PutMapping("/updateComment/{id}/{emp_id}")
	public Comment updateComment(@RequestBody Comment comment, @PathVariable("id") String id , @PathVariable("emp_id") String emp_id) {
	    return commentService.changeComment(comment,id, emp_id);
	}
		
	//update comment's reply by Author of comment
	@PutMapping("/updateReplyComment/{id}/{emp_id}")
	public CommentReply updateReplyComment(@RequestBody CommentReply commentReply, @PathVariable("id") String id , @PathVariable("emp_id") String emp_id) {
		return commentService.changeReplyComment(commentReply,id, emp_id);
			    }
	
	//add emoji
//	@PostMapping("/addEmoji/{id}/{emoji}")
//	public Comment addEmoji(@PathVariable("id") String id , @PathVariable("emoji") String emoji) {
//		Comment cmt=commentService.appendemoji(id,emoji);
//		return cmt;
//	}
	@PostMapping("/addEmoji/{eid}/{id}/{emoji}")
	public Comment addEmoji(@PathVariable("eid") String eid ,@PathVariable("id") String id , @PathVariable("emoji") String emoji) {
		Comment cmt=commentService.appendemoji(eid,id,emoji);
		return cmt;
	}
	@PostMapping("/addReply/{id}/{reply}/{eid}")
	public Comment addReply(@PathVariable("id") String id , @PathVariable("reply") String reply, @PathVariable("eid") String eid) {
		Comment cmt=commentService.appendreply(id,reply,eid);
		return cmt;
	}
	//deepak logic for delete reply
//	@DeleteMapping("/deleteReply/{id}/{eid}/{idx}")
//    public String deleteReply(@PathVariable("id") String id, @PathVariable("eid") String cid,@PathVariable("idx") String idx) {
//    	return commentService.eraseReply(id,cid,idx);
//    }
//	@DeleteMapping("/deleteEmoji/{id}/{eid}/{idx}")
//    public String deleteEmoji(@PathVariable("id") String id, @PathVariable("eid") String cid,@PathVariable("idx") String idx) {
//    	return commentService.eraseReply(id,cid,idx);
//    }
}
