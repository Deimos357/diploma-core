package ua.nure.tanasiuk.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.nure.tanasiuk.dao.mapper.UserIdentityRowMapper;
import ua.nure.tanasiuk.model.UserIdentity;

@Repository
@Slf4j
@PropertySource("classpath:query/userIdentityQuery.xml")
public class UserIdentityDao extends GenericDaoImpl<UserIdentity> {
    @Value("${createUser}")
    private String createUserQuery;
    @Value("${findUserById}")
    private String findUserByIdQuery;

    private final UserIdentityRowMapper userRowMapper;

    public UserIdentityDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                           UserIdentityRowMapper userRowMapper) {
        super(namedParameterJdbcTemplate);
        this.userRowMapper = userRowMapper;
    }

    public UserIdentity create(UserIdentity user) {
        Long id = create(createUserQuery, user);
        user.setId(id);

        return user;
    }

    public UserIdentity findById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return get(findUserByIdQuery, params, userRowMapper);
    }
}
