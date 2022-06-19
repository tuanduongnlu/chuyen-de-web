package com.example.chuyendeweb.DTO;

import com.example.chuyendeweb.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    int id ;
    String link ;
    public static ImageDTO transtoDTO(Image image) {
        return new ImageDTO(image.getId(),image.getLink());
    }
}
