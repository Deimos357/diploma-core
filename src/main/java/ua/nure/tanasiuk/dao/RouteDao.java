package ua.nure.tanasiuk.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.nure.tanasiuk.dao.mapper.RouteDtoRowMapper;
import ua.nure.tanasiuk.dao.mapper.TicketListRowMapper;
import ua.nure.tanasiuk.dto.RouteDto;
import ua.nure.tanasiuk.dto.Ticket;
import ua.nure.tanasiuk.model.Station;

import java.util.List;

@Repository
@Slf4j
@PropertySource("classpath:query/routeQuery.xml")
public class RouteDao extends GenericDaoImpl<RouteDto> {
    private final RouteDtoRowMapper routeDtoRowMapper;
    private final TicketListRowMapper ticketListRowMapper;

    @Value("${getRouteById}")
    private String getById;
    @Value("${getHistory}")
    private String getHistory;
    @Value("${getFavorites}")
    private String getFavorites;
    @Value("${switchFavorite}")
    private String switchFavorite;
    @Value("${getStations}")
    private String getStations;
    @Value("${getAlternative}")
    private String getAlternative;
    @Value("${createRoute}")
    private String create;
    @Value("${clearRoute}")
    private String clearRoute;
    @Value("${addTicket}")
    private String addTicket;

    public RouteDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                    RouteDtoRowMapper routeDtoRowMapper,
                    TicketListRowMapper ticketListRowMapper) {
        super(namedParameterJdbcTemplate);
        this.routeDtoRowMapper = routeDtoRowMapper;
        this.ticketListRowMapper = ticketListRowMapper;
    }

    public List<Station> getStations() {
        return query(getStations, new BeanPropertyRowMapper(Station.class));
    }

    public List<Ticket> getAlternative(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return query(getAlternative, params, ticketListRowMapper);
    }

    public List<RouteDto> getFavorites(Long requestInitiatorId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", requestInitiatorId);
        return query(getFavorites, params, routeDtoRowMapper);
    }

    public List<RouteDto> getHistory(Long requestInitiatorId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", requestInitiatorId);
        return query(getHistory, params, routeDtoRowMapper);
    }

    public void switchFavorite(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        update(switchFavorite, params);
    }

    public RouteDto getById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return get(getById, params, routeDtoRowMapper);
    }

    public Long create(String name, Long requestInitiatorId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("userId", requestInitiatorId);
        KeyHolder holder = new GeneratedKeyHolder();
        getNamedParameterJdbcTemplate().update(create, params, holder, new String[]{"id"});
        return holder.getKey().longValue();
    }

    public void clearRoute(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        update(clearRoute, params);
    }

    public void addTicket(Long routeId, Long ticketId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("routeId", routeId);
        params.addValue("ticketId", ticketId);
        update(addTicket, params);
    }
}
