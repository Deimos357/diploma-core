package ua.nure.tanasiuk.dao.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ua.nure.tanasiuk.dto.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class TicketListRowMapper implements RowMapper<List<Ticket>> {
    private final ObjectMapper objectMapper;

    public TicketListRowMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Ticket> mapRow(ResultSet resultSet, int i) throws SQLException {
        try {
            String ticketsString = resultSet.getString("tickets");
            return objectMapper.readValue(ticketsString, new TypeReference<List<Ticket>>() { });
        } catch (Exception e) {
            return null;
        }
    }
}
