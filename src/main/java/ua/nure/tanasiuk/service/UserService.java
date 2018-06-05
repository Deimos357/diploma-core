package ua.nure.tanasiuk.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.tanasiuk.dao.UserIdentityDao;
import ua.nure.tanasiuk.dto.ChangePasswordDto;
import ua.nure.tanasiuk.model.UserIdentity;

import java.util.Date;

@Service("userDetailsService")
@Slf4j
public class UserService {
    private final UserIdentityDao userIdentityDao;

    public UserService(UserIdentityDao userIdentityDao) {
        this.userIdentityDao = userIdentityDao;
    }

    @Transactional
    public UserIdentity create(UserIdentity user) {
        user.setCreationDate(new Date());
        user = userIdentityDao.create(user);
        return user;
    }

    @Transactional(readOnly = true)
    public UserIdentity getCurrentUserProfile(long id) {
        UserIdentity user = userIdentityDao.findById(id);
        user.setCreationDate(null);
        user.setPassword(null);
        return user;
    }

    @Transactional
    public void changePassword(ChangePasswordDto changePasswordDto, Long requestInitiatorId) {
        // TODO
    }

    @Transactional
    public void editProfile(UserIdentity userData, Long requestInitiatorId) {
        // TODO
    }
}
