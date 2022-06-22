package com.example.chuyendeweb.DTO;

import com.example.chuyendeweb.entities.location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
    int id;
    String distric;
    String ward;
    String detail;
    public static LocationDTO trantoDTO(location location) {
        return new LocationDTO(location.getId(),location.getDistric().getPrefix()+" "+location.getDistric().getName(),
                location.getWard().getPrefix()+" "+location.getWard().getName(), location.getDetail());
    }
}
