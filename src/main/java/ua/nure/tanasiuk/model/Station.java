package ua.nure.tanasiuk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Station {
    private int id;
    private String name;
    private double latitude;
    private double longitude;
}
