package ua.nure.tanasiuk.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ua.nure.tanasiuk.dao.RouteDao;
import ua.nure.tanasiuk.dto.RouteDto;
import ua.nure.tanasiuk.dto.RouteRequest;
import ua.nure.tanasiuk.dto.Ticket;
import ua.nure.tanasiuk.model.Station;

import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
public class RouteService {
    private final RouteDao routeDao;
    private final RestTemplate restTemplate;

    @Value("${route-creator.host}${route-creator.route}")
    private String routeCreatorUrl;

    public RouteService(RouteDao routeDao, RestTemplate restTemplate) {
        this.routeDao = routeDao;
        this.restTemplate = restTemplate;
    }

    @Transactional(readOnly = true)
    public RouteDto getById(Long id, Long requestInitiatorId) {
        return routeDao.getById(id);
    }

    @Transactional
    public void switchFavorite(Long id, Long requestInitiatorId) {
        routeDao.switchFavorite(id);
    }

    @Transactional(readOnly = true)
    public List<RouteDto> getHistory(Long requestInitiatorId) {
        return routeDao.getHistory(requestInitiatorId);
    }

    @Transactional(readOnly = true)
    public List<RouteDto> getFavorites(Long requestInitiatorId) {
        return routeDao.getFavorites(requestInitiatorId);
    }

    @Transactional(readOnly = true)
    public List<Ticket> getAlternatives(int id) {
        return routeDao.getAlternative(id);
    }

    @Transactional(readOnly = true)
    public List<Station> getStations() {
        return routeDao.getStations();
    }

    @Transactional
    public RouteDto create(RouteRequest routeRequest, Long requestInitiatorId) {
        List<Ticket> tickets = createRoute(routeRequest);

        AtomicBoolean isEmpty = new AtomicBoolean(false);
        tickets.forEach(t -> isEmpty.set(t.getId() == -1));
        if (isEmpty.get()) {
            throw new RuntimeException();
        }

        Long id = routeDao.create(routeRequest.getName(), requestInitiatorId);
        addTicketsToRoute(tickets, id);

        return getById(id, requestInitiatorId);
    }

    @Transactional
    public RouteDto edit(RouteRequest routeRequest, Long routeId, Long requestInitiatorId) {
        List<Ticket> tickets = editRoute(routeRequest);

        AtomicBoolean isEmpty = new AtomicBoolean(false);
        tickets.forEach(t -> isEmpty.set(t.getId() == -1));
        if (isEmpty.get()) {
            throw new RuntimeException();
        }

        routeDao.clearRoute(routeId);
        addTicketsToRoute(tickets, routeId);

        return getById(routeId, requestInitiatorId);
    }

    @Transactional
    public void replaceTicket(RouteDto route, Long requestInitiatorId) {
        routeDao.clearRoute(route.getId());
        addTicketsToRoute(route.getTickets(), route.getId());
    }

    private void addTicketsToRoute(List<Ticket> tickets, Long routeId) {
        tickets.forEach(t -> routeDao.addTicket(routeId, t.getId()));
    }

    private List<Ticket> createRoute(RouteRequest routeRequest) {
        ParameterizedTypeReference<List<Ticket>> typeReference =
            new ParameterizedTypeReference<List<Ticket>>() { };
        RequestEntity<RouteRequest> request = RequestEntity.post(URI.create(routeCreatorUrl)).body(routeRequest);
        ResponseEntity<List<Ticket>> response = restTemplate.exchange(request, typeReference);

        return response.getBody();
    }

    private List<Ticket> editRoute(RouteRequest routeRequest) {
        ParameterizedTypeReference<List<Ticket>> typeReference =
            new ParameterizedTypeReference<List<Ticket>>() { };
        RequestEntity<RouteRequest> request = RequestEntity.put(URI.create(routeCreatorUrl)).body(routeRequest);
        ResponseEntity<List<Ticket>> response = restTemplate.exchange(request, typeReference);

        return response.getBody();
    }
}
