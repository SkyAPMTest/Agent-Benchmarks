package org.skywalking.apm.benchmark.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.skywalking.apm.benchmark.example.entity.User;
import org.skywalking.apm.toolkit.trace.ActiveSpan;
import org.skywalking.apm.toolkit.trace.annotation.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDao {

    @Autowired
    private DataSource dataSource;

    @Trace
    public User queryUserById(String id) throws SQLException {
        ActiveSpan.tag("queryUserById", id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT name FROM test WHERE test.a = ?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
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

    @Trace
    public void updateUserById(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE test set name=?, age=?, value1=?, value2=?, vaue3=? WHERE id=?");
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getId());
            preparedStatement.setString(4, user.getId());
            preparedStatement.setString(5, user.getId());
            preparedStatement.setString(6, user.getId());
            boolean resultSet = preparedStatement.execute();
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    @Trace
    public void updateUserInfo1(User user) throws SQLException {
        ActiveSpan.tag("updateUserInfo1", user.getId());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE userInfo1 set name=?, age=?, value1=?, value2=?, vaue3=? WHERE id=?");
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getId());
            preparedStatement.setString(4, user.getId());
            preparedStatement.setString(5, user.getId());
            preparedStatement.setString(6, user.getId());
            boolean resultSet = preparedStatement.execute();
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    @Trace
    public void updateUserInfo2(User user) throws SQLException {
        ActiveSpan.tag("updateUserInfo2", user.getId());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE userInfo2 set name=?, age=?, value1=?, value2=?, vaue3=? WHERE id=?");
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getId());
            preparedStatement.setString(4, user.getId());
            preparedStatement.setString(5, user.getId());
            preparedStatement.setString(6, user.getId());
            boolean resultSet = preparedStatement.execute();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    @Trace
    public void updateUserInfo3(User user) throws SQLException {
        ActiveSpan.tag("updateUserInfo3", user.getId());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE userInfo3 set name=?, age=? WHERE id=?");
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getId());
            boolean resultSet = preparedStatement.execute();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    @Trace
    public void updateUserInfo4(User user) throws SQLException {
        ActiveSpan.tag("updateUserInfo4", user.getId());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE userInfo4 set name=?, age=? WHERE id=?");
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getId());
            boolean resultSet = preparedStatement.execute();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    @Trace
    public void updateUserInfo5(User user) throws SQLException {
        ActiveSpan.tag("updateUserInfo5", user.getId());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE userInfo5 set name=?, age=? WHERE id=?");
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getId());
            boolean resultSet = preparedStatement.execute();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    @Trace
    public void updateUserInfo6(User user) throws SQLException {
        ActiveSpan.tag("updateUserInfo6", user.getId());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE userInfo6 set name=?, age=? WHERE id=?");
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getId());
            boolean resultSet = preparedStatement.execute();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }
}
