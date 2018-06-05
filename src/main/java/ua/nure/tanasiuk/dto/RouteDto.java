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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteDto {
    private long id;
    @JsonSerialize(using = UnixDateSerializer.class)
    @JsonDeserialize(using = UnixDateDeserializer.class)
    private Date creationDate;
    private List<Ticket> tickets;
    private boolean isFavorite;
    private String name;
}
