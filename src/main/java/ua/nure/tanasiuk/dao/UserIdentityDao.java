package ua.nure.tanasiuk.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.nure.tanasiuk.dao.mapper.UserIdentityRowMapper;
import ua.nure.tanasiuk.dto.ChangePasswordDto;
import ua.nure.tanasiuk.model.UserIdentity;

@Repository
@Slf4j
@PropertySource("classpath:query/userIdentityQuery.xml")
public class UserIdentityDao extends GenericDaoImpl<UserIdentity> {
    @Value("${createUser}")
    private String createUserQuery;
    @Value("${findUserById}")
    private String findUserByIdQuery;
    @Value("${changePassword}")
    private String changePassword;
    @Value("${editProfile}")
    private String editProfile;
    @Value("${deleteProfile}")
    private String deleteProfile;

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

    public boolean changePassword(ChangePasswordDto changePasswordDto, Long requestInitiatorId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", requestInitiatorId);
        params.addValue("newPas", changePasswordDto.getNewPassword());
        params.addValue("oldPas", changePasswordDto.getOldPassword());
        return update(changePassword, params);
    }

    public void editProfile(UserIdentity userData, Long requestInitiatorId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", requestInitiatorId);
        params.addValue("username", userData.getUsername());
        params.addValue("email", userData.getEmail());
        update(editProfile, params);
    }

    public void deleteProfile(Long requestInitiatorId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", requestInitiatorId);
        update(deleteProfile, params);
    }
}
