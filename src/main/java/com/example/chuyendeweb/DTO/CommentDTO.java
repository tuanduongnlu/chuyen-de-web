package com.example.chuyendeweb.DTO;

import com.example.chuyendeweb.DTO.user.UserWriteDTO;
import com.example.chuyendeweb.entities.Comment;
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
    UserWriteDTO user ;
    public static CommentDTO trantoDTO (Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        commentDTO.setTimePost(formatter.format(comment.getTimePost()));
        commentDTO.setComment(comment.getComment());
        commentDTO.setUser(UserWriteDTO.transtoDTO(comment.getUser()));
        return commentDTO;
    }
    public static  Comment trantToEntity(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            comment.setTimePost(formatter.parse(commentDTO.getTimePost()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        comment.setComment(commentDTO.getComment());
        comment.setUser(commentDTO.getUser().transUser());
        return  comment;

    }
}
