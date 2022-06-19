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
    int distric;
    int ward;
    String detail;
    public static LocationDTO trantoDTO(location location) {
        return new LocationDTO(location.getId(),location.getDistric(), location.getWard(), location.getDetail());
    }
}
