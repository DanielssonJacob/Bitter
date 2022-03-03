package com.bitter.backendapi.CommentFolder;

import com.bitter.backendapi.BeetFolder.Beet;
import com.bitter.backendapi.BeetFolder.BeetRepository;
import com.bitter.backendapi.UserFolder.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BeetRepository beetRepository;
    @Autowired
    CommentRepository commentRepository;

    public Comment addComment(Long beetId, String text, Long userId) {
        Comment comment = new Comment();
        if(userRepository.findById(userId) != null && beetRepository.findById(beetId) != null) {
        Beet beet = beetRepository.findById(beetId).get();
        comment = new Comment(null, text, userRepository.findById(userId).get(),
                beetRepository.findById(beetId).get());
           beet.addComment(comment);
       }
       return comment;
    }

    public void editComment(Long commentId, String text, Long userId, Long beetId) {
        if(userRepository.findById(userId).get() != null && commentRepository.findById(commentId).get() != null){
        beetRepository.findById(beetId).get();
        Comment comment = commentRepository.findById(commentId).get();
        comment.setText(text);
        commentRepository.save(comment);
        }
    }
}
