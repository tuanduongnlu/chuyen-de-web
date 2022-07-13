package com.example.chuyendeweb.DTO;

import com.example.chuyendeweb.DTO.user.UserWriteDTO;
import com.example.chuyendeweb.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
