package com.bitter.backendapi.BeetFolder;

import com.bitter.backendapi.CommentFolder.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Beet {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Size (min= 1, max = 140)
        private String message;
        private LocalDateTime createdAt;
        private String createdByUsername;
        @OneToMany(fetch= FetchType.EAGER)
        private List<Comment> comments = new ArrayList<>();

        public Beet(Long id, String message, LocalDateTime createdAt, String createdByUsername) {
                this.id = id;
                this.message = message;
                this.createdAt = createdAt;
                this.createdByUsername = createdByUsername;
        }

        public void addComment(Comment comment) {
                comments.add(comment);
        }

        public void editComment(String text, Long id) {
                for (Comment comment : comments) {
                        if(Objects.equals(comment.getId(), id)){
                        comment.setText(text);
                        }
                }
        }

}
