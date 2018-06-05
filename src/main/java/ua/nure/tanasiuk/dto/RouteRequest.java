package ua.nure.tanasiuk.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.nure.tanasiuk.resource.deserializer.UnixDateDeserializer;
import ua.nure.tanasiuk.resource.serializer.UnixDateSerializer;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteRequest {
    @JsonSerialize(using = UnixDateSerializer.class)
    @JsonDeserialize(using = UnixDateDeserializer.class)
    private Date startDate;
    private Integer startStation;
    private List<StationInRoute> stationsToVisit;
    private List<Integer> transportTypes;
    private Double factor; // 1 - greed, 0 - fast
    private String name;

    private List<Ticket> tickets;
}
