package com.bitter.backendapi.CommentFolder;

import com.bitter.backendapi.BeetFolder.Beet;
import com.bitter.backendapi.UserFolder.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1, max = 140)
    private String text;
    @ManyToOne
    private User user;
    @ManyToOne
    private Beet beet;


}
