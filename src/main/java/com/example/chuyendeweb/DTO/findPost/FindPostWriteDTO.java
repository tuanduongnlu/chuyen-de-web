package com.example.chuyendeweb.DTO.findPost;

import com.example.chuyendeweb.DTO.CommentDTO;
import com.example.chuyendeweb.DTO.RoomTypeDTO;
import com.example.chuyendeweb.DTO.user.UserWriteDTO;
import com.example.chuyendeweb.entities.Comment;
import com.example.chuyendeweb.entities.FindPost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindPostWriteDTO {
    int roomType;
    String title;
    String detail;
    public  static FindPost trantToPiFindPost(FindPostWriteDTO findPostWriteDTO){
        List<Comment> listComment= new ArrayList<>();
        FindPost findPost = new FindPost();
        findPost.setTimePost(new Date());
        findPost.setTitle(findPostWriteDTO.getTitle());
        findPost.setDetail(findPostWriteDTO.getDetail());
        findPost.setComments(listComment);

        return  findPost;
    }
}
