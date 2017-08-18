package org.skywalking.apm.benchmark.example.service;

import java.sql.SQLException;
import org.skywalking.apm.benchmark.example.dao.UserDao;
import org.skywalking.apm.benchmark.example.entity.User;
import org.skywalking.apm.toolkit.trace.ActiveSpan;
import org.skywalking.apm.toolkit.trace.annotation.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private Jedis jedis;

    @Trace
    public User queryAndUpdateUser(String id) throws SQLException {
        ActiveSpan.tag("user.id", id);
        String value = jedis.get(id);
        User user = null;
        if (value == null) {
            user = userDao.queryUserById(id);
            userDao.updateUserById(user);
        } else {
            user = new User(id, value);
        }

        userDao.updateUserInfo1(user);
        userDao.updateUserInfo2(user);
        userDao.updateUserInfo3(user);
        userDao.updateUserInfo4(user);
        userDao.updateUserInfo5(user);
        userDao.updateUserInfo6(user);
        return user;
    }
}
