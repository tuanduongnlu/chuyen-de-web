package com.example.chuyendeweb.DTO;

import com.example.chuyendeweb.entities.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomTypeDTO {
    int id;
    String name;
    public static RoomTypeDTO trantoDTO(RoomType roomType) {
        return new RoomTypeDTO(roomType.getId(),roomType.getName());
    }
    public static  RoomType trantToEntity (RoomTypeDTO roomTypeDTO){
        RoomType roomType = new RoomType();
        roomType.setId(roomTypeDTO.getId());
        roomType.setName(roomTypeDTO.getName());
        return roomType;
    }
}
