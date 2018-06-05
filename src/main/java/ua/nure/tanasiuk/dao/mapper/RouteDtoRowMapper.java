package ua.nure.tanasiuk.dao.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ua.nure.tanasiuk.dto.RouteDto;
import ua.nure.tanasiuk.dto.Ticket;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class RouteDtoRowMapper implements RowMapper<RouteDto> {
    private final ObjectMapper objectMapper;

    public RouteDtoRowMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public RouteDto mapRow(ResultSet resultSet, int i) throws SQLException {
        try {
            String ticketsString = resultSet.getString("tickets");
            List<Ticket> tickets = objectMapper.readValue(ticketsString, new TypeReference<List<Ticket>>() { });

            return RouteDto.builder()
                .id(resultSet.getLong("id"))
                .creationDate(resultSet.getTimestamp("creation_date"))
                .isFavorite(resultSet.getBoolean("is_favorite"))
                .name(resultSet.getString("name"))
                .tickets(tickets)
                .build();
        } catch (IOException e) {
            return null;
        }
    }
}
