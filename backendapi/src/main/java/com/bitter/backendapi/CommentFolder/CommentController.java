package com.bitter.backendapi.CommentFolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    CommentRepository repo;

    @Autowired
    CommentService service;

    @PutMapping("/comment/{beetId}")
    Comment addComment(@PathVariable Long beetId, String text, @RequestParam Long userId){
        return service.addComment(beetId, text, userId);
    }

    @DeleteMapping("/comment/{commentId}")
    void deleteComment(@PathVariable Long commentId){
        repo.deleteById(commentId);
    }

    @PutMapping("/comment/{commentId}")
    void editComment(@PathVariable Long commentId, String text, @RequestParam Long userId, @RequestParam Long beetId) {
        service.editComment(commentId, text, userId, beetId);
    }

}
