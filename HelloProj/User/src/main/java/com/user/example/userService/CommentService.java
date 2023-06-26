package com.user.example.userService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.example.entity.Comment;
import com.user.example.entity.CommentReply;
import com.user.example.entity.Product;
import com.user.example.entity.User;
import com.user.example.repo.CommentInterface;
import com.user.example.repo.CommentReplyInterface;
import com.user.example.repo.ProductInterface;
import com.user.example.repo.UserInterface;

@Service
public class CommentService {

	
	@Autowired
	private ProductInterface productInterface;
	
	@Autowired
	private CommentInterface commentInterface;
	
	@Autowired
	private CommentReplyInterface commentReplyInterface;
	
	@Autowired
	private UserInterface userInterface;
	
	//by nitesh
//	public Comment addComment(Comment comment , String product_id, String emp_id) {
//	//	Optional<Product> tempProduct = productInterface.findById(product_id);
//	//	Optional<User> tempUser = userInterface.findById(emp_id);
//		//if(tempProduct.isPresent()) {
//			//Comment comments = new Comment();
////			User user= new User();
////			user = tempUser.get();
////			String userName= user.getName() + " " + emp_id;
////		
//			//comments.setContent(content);
//		//	comments.setId(comment.getId());
//			comment.setProduct_id(product_id);
//			comment.setEmp_id(emp_id);
//			//comments.setContent(comment.getContent());
//			commentInterface.save(comment);
//			return comment ;
//		//}
//	//	return "wrong product id";
//	}
	
	//by deepak
	public Comment addComment(Comment comment) {
			commentInterface.save(comment);
			return comment ;
	}

	
	
	
	
	public Comment addComment(Comment comment , String product_id, String emp_id) {
	//	Optional<Product> tempProduct = productInterface.findById(product_id);
	//	Optional<User> tempUser = userInterface.findById(emp_id);
		//if(tempProduct.isPresent()) {
			//Comment comments = new Comment();
//			User user= new User();
//			user = tempUser.get();
//			String userName= user.getName() + " " + emp_id;
//		
			//comments.setContent(content);
		//	comments.setId(comment.getId());
			comment.setProduct_id(product_id);
			comment.setEmp_id(emp_id);
			//comments.setContent(comment.getContent());
			commentInterface.save(comment);
			return comment ;
		//}
	//	return "wrong product id";
	}

	public CommentReply addReplyComment(CommentReply commentReply, String id, String emp_id) {
//		Optional<Comment> tempComment = commentInterface.findById(id);
//		Optional<User> tempUser= userInterface.findById(emp_id);
//		if(tempComment.isPresent()) {
		//	CommentReply commentReplys = new CommentReply();
	//		User user = new User();
	//		user=tempUser.get();
	//		String userName = user.getName() + " " + emp_id;
	//		commentReply.setContent(content);
			commentReply.setParentId(id);
			commentReply.setUserId(emp_id);
			commentReplyInterface.save(commentReply);
			return commentReply;
		//}
		//return null;
	}
//by nitesh
//	public String eraseComment(Comment comment, String id, String emp_id) {
//		Optional<Comment> tempComment = commentInterface.findById(id);
//		if(tempComment.isPresent()) {
//			Comment comments = new Comment();
//			comments = tempComment.get();
//			if(comments.getEmp_id().equals(emp_id)) {
//				commentInterface.deleteById(id);
//				List<CommentReply> tempCommentReply = commentReplyInterface.findAll();
//				for(int i=0;i<tempCommentReply.size();i++) {
//					if(tempCommentReply.get(i).getParentId().equals(id))
//				    commentReplyInterface.deleteById(tempCommentReply.get(i).getId());
//				}
//				return "deleted";
//			}
//			else
//				return "Sorry .. you can't delete another user's comment";
//		}
//		return "wrong comment id";
//	}
	
	//by deepak
	public String eraseComent(String id,String cid) {
		Comment cmt=commentInterface.findById(id).orElse(null);
		if(cid.equals(cmt.getEmp_id()) && cmt!=null) {
			commentInterface.deleteById(id);
			return "done";
		}
		return "not done";
	}
	public String eraseComment(String id) {
	    commentInterface.deleteById(id);
		return "done";
	}

	public List<Comment> showComment() {
		return commentInterface.findAll();
	}

	public List<CommentReply> showReplyComment() {
		return commentReplyInterface.findAll();
	}
//   // by nitesh
//	public List<Comment> showPostComment(Comment comment, String product_id) {
//	    List<Comment> allComment = commentInterface.findAll();
//	    List<Comment> ansComment = new ArrayList<Comment>();
//	    for(int i=0;i<allComment.size();i++) {
//	    	if(allComment.get(i).getProduct_id().equals(product_id)) {
//	    		ansComment.add(allComment.get(i));
//	    	}
//	    }
//		return ansComment;
//	}
	//by deepak
	public List<Comment> showPostComment(String product_id) {
	    List<Comment> allComment = commentInterface.findAll();
	    List<Comment> ansComment = new ArrayList<Comment>();
	    for(int i=0;i<allComment.size();i++) {
	    	if(allComment.get(i).getProduct_id().equals(product_id)) {
	    		ansComment.add(allComment.get(i));
	    	}
	    }
		return ansComment;
	}

	public List<CommentReply> showPostReplyComment(CommentReply commentReply, String id) {
		List<CommentReply> allComment = commentReplyInterface.findAll();
		List<CommentReply> ansComment = new ArrayList<CommentReply>();
		for(int i=0;i<allComment.size();i++) {
			if(allComment.get(i).getParentId().equals(id)) {
				ansComment.add(allComment.get(i));
			}
		}
		return ansComment;
	}

	public String eraseReplyComment(CommentReply commentReply, String id, String emp_id) {
		Optional<CommentReply> tempComment = commentReplyInterface.findById(id);
		if(tempComment.isPresent()) {
			CommentReply commentReplys = new CommentReply();
			commentReplys = tempComment.get();
			if(commentReplys.getUserId().equals(emp_id)) {
				commentReplyInterface.deleteById(id);
				return "deleted";
			}
			else
				return "Sorry .. you can't delete another user's comment";
		}
		return "wrong comment id";
	}

	public Comment changeComment(Comment comment, String id, String emp_id) {
		Optional<Comment> tempComment = commentInterface.findById(id);
		if(tempComment.isPresent()) {
		Comment comments = new Comment();
		comments = tempComment.get();
		if(comments.getEmp_id().equals(emp_id)) {
		comment.setEmp_id(emp_id);
		comment.setId(id);
		comment.setProduct_id(comments.getProduct_id());
		commentInterface.save(comment);
		return comment;
		}
		else
			return null;
		}
		
		return null;
	}
	
	
	public CommentReply changeReplyComment(CommentReply commentReply, String id, String emp_id) {
		Optional<CommentReply> tempComment = commentReplyInterface.findById(id);
		if(tempComment.isPresent()) {
		CommentReply comments = new CommentReply();
		comments = tempComment.get();
		if(comments.getUserId().equals(emp_id)) {
		commentReply.setUserId(emp_id);
		commentReply.setId(id);
		commentReply.setParentId(comments.getParentId());
		commentReplyInterface.save(commentReply);
		return commentReply;
		}
		else
			return null;
		}
		
		return null;
	}
	
	//added by deepak for emoji
	public Comment appendemoji(String eid,String id,String emoji) {
		Comment cmt=commentInterface.findById(id).orElse(null);
		List<String> emojilist=cmt.getEmoji();
		//Map<String, String> emojimap=cmt.getEmojimap();
		 Map<String, String> emojimap= cmt.getEmojimap();
		 emojimap.put(eid, emoji);
		emojilist.add(eid+emoji);
		cmt.setEmoji(emojilist);
		commentInterface.save(cmt);
		return cmt;
		
	}
	//reply by deepak 
	public Comment appendreply(String id,String reply,String eid) {
		Comment cmt=commentInterface.findById(id).orElse(null);
		//List<String> emojilist=cmt.getEmoji();
		List<String> replylist=cmt.getReply();
		//emojilist.add(emoji);
		replylist.add(eid+" "+reply);
		//cmt.setEmoji(emojilist);
		cmt.setReply(replylist);
		commentInterface.save(cmt);
		return cmt;
		
	}
	
	
}
