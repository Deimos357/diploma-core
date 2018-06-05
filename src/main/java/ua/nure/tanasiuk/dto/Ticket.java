package ua.nure.tanasiuk.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.nure.tanasiuk.model.Company;
import ua.nure.tanasiuk.model.Station;
import ua.nure.tanasiuk.resource.deserializer.UnixDateDeserializer;
import ua.nure.tanasiuk.resource.serializer.UnixDateSerializer;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    private long id;
    private String name;
    private double cost;
    private int duration;
    @JsonSerialize(using = UnixDateSerializer.class)
    @JsonDeserialize(using = UnixDateDeserializer.class)
    private Date departureTime;
    private int transport;
    private Station from;
    private Station to;
    private Company company;
    private boolean isAvailable;

    @JsonSetter("departuretime")
    public void setDeparturetime(Date d) {
        this.departureTime = d;
    }
}
