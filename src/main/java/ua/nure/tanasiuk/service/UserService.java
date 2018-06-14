package ua.nure.tanasiuk.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public UserService(UserIdentityDao userIdentityDao, PasswordEncoder passwordEncoder) {
        this.userIdentityDao = userIdentityDao;
        this.passwordEncoder = passwordEncoder;
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
        changePasswordDto.setNewPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));

        userIdentityDao.changePassword(changePasswordDto, requestInitiatorId);
    }

    @Transactional
    public void editProfile(UserIdentity userData, Long requestInitiatorId) {
        userIdentityDao.editProfile(userData, requestInitiatorId);
    }

    @Transactional
    public void deleteProfile(Long requestInitiatorId) {
        userIdentityDao.deleteProfile(requestInitiatorId);
    }
}
