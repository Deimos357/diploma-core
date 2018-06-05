package ua.nure.tanasiuk.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.nure.tanasiuk.dto.Ticket;
import ua.nure.tanasiuk.resource.deserializer.UnixDateDeserializer;
import ua.nure.tanasiuk.resource.serializer.UnixDateSerializer;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketIdentity {
    private long id;
    private String name;
    private long originalId;
    private double cost;
    private int duration;
    @JsonSerialize(using = UnixDateSerializer.class)
    @JsonDeserialize(using = UnixDateDeserializer.class)
    private Date departureTime;
    private int transportTypeId;
    private int fromId;
    private int toId;
    private int companyId;
    private boolean isAvailable;

    public static TicketIdentity fromTicket(Ticket ticket) {
        return TicketIdentity.builder()
            .id(ticket.getId())
            .name(ticket.getName())
            .cost(ticket.getCost())
            .duration(ticket.getDuration())
            .departureTime(ticket.getDepartureTime())
            .transportTypeId(ticket.getTransport())
            .fromId(ticket.getFrom().getId())
            .toId(ticket.getTo().getId())
            .companyId(ticket.getCompany().getId())
            .isAvailable(ticket.isAvailable())
            .build();
    }
}
