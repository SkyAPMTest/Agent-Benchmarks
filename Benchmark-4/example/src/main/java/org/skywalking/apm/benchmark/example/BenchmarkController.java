package org.skywalking.apm.benchmark.example;

import java.sql.SQLException;
import org.skywalking.apm.benchmark.example.entity.User;
import org.skywalking.apm.benchmark.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

@Controller
public class BenchmarkController {

    @Autowired
    private Jedis jedis;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/user/{id}"})
    @ResponseBody
    public User selectUser(@PathVariable String id) throws SQLException {
        User user = userService.queryAndUpdateUser(id);
        jedis.set(user.getId(), user.getName());
        return user;
    }
}
