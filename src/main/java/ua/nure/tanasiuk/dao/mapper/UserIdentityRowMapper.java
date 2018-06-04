package ua.nure.tanasiuk.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ua.nure.tanasiuk.model.UserIdentity;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserIdentityRowMapper implements RowMapper<UserIdentity> {

    @Override
    public UserIdentity mapRow(ResultSet resultSet, int i) throws SQLException {
        return UserIdentity.builder()
            .id(resultSet.getLong("id"))
            .username(resultSet.getString("username"))
            .email(resultSet.getString("email"))
            .password(resultSet.getString("password"))
            .build();
    }
}
