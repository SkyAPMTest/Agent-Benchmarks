package org.skywalking.apm.benchmark.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;
import javax.sql.DataSource;
import org.skywalking.apm.benchmark.example.entity.User;
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
    private DataSource dataSource;

    @RequestMapping(value = {"/user/{id}"})
    @ResponseBody
    public User selectUser(@PathVariable String id) throws SQLException {
        String value = jedis.get(id);
        if (value == null) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                connection = dataSource.getConnection();
                preparedStatement = connection.prepareStatement("SELECT name FROM test WHERE test.a = ?");
                preparedStatement.setString(1, "1");
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                jedis.set(id + ThreadLocalRandom.current().nextDouble(), resultSet.getString("name"));
                return new User(id, resultSet.getString("name"));
            } finally {
                
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            }
        }

        return new User(id, value);
    }
}
