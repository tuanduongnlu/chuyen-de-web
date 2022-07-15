package com.example.chuyendeweb.DTO.findPost;

import com.example.chuyendeweb.DTO.CommentDTO;
import com.example.chuyendeweb.DTO.RoomTypeDTO;
import com.example.chuyendeweb.DTO.user.UserWriteDTO;
import com.example.chuyendeweb.entities.Comment;
import com.example.chuyendeweb.entities.FindPost;
import com.example.chuyendeweb.entities.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindPostReadDTO {
    int id ;
    String timePost;
    RoomTypeDTO roomType;
    String title;
    String detail;
    List<CommentDTO> commentDTOList;
    UserWriteDTO user ;
    public static FindPostReadDTO trantoDTO(FindPost findPost){
        FindPostReadDTO fP = new FindPostReadDTO();
        fP.setId(findPost.getId());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(findPost.getTimePost());
        fP.setTimePost(strDate);
        RoomTypeDTO roomTypeDTO = RoomTypeDTO.trantoDTO(findPost.getRoomType());
        fP.setRoomType(roomTypeDTO);
        fP.setTitle(findPost.getTitle());
        fP.setDetail(findPost.getDetail());
        fP.setUser(UserWriteDTO.transtoDTO(findPost.getUser()));
        ArrayList<CommentDTO> commentDTOS = new ArrayList<>();
        for(Comment c : findPost.getComments()){
            commentDTOS.add(CommentDTO.trantoDTO(c));
        }
        fP.setCommentDTOList(commentDTOS);
        return  fP;
    }

}
