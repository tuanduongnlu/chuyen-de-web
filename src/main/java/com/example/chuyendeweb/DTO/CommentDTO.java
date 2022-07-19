package com.example.chuyendeweb.DTO;

import com.example.chuyendeweb.DTO.user.UserWriteDTO;
import com.example.chuyendeweb.entities.Comment;
import com.example.chuyendeweb.entities.FindPost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    int id ;
    String timePost;
    String comment;
    FindPost findPost;
    UserWriteDTO user ;
    public static CommentDTO trantoDTO (Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        commentDTO.setTimePost(formatter.format(comment.getTimePost()));
        commentDTO.setFindPost(comment.getFind_post());
        commentDTO.setComment(comment.getComment());
        commentDTO.setUser(UserWriteDTO.transtoDTO(comment.getUser()));
        return commentDTO;
    }
    public static  Comment trantToEntity(CommentDTO commentDTO){
        Comment comment = new Comment();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            comment.setTimePost(formatter.parse(commentDTO.getTimePost()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        comment.setComment(commentDTO.getComment());
        comment.setFind_post(commentDTO.getFindPost());
        comment.setUser(commentDTO.getUser().transUser());
        return  comment;

    }
}
