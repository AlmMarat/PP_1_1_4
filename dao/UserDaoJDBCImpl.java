package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection con = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement st = con.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(30) NOT NULL, " +
                    "lastName VARCHAR(30) NOT NULL, " +
                    "age TINYINT NOT NULL, " +
                    "PRIMARY KEY (id))");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement st = con.createStatement()) {
            st.executeUpdate("drop table if exists users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement pr = con.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            pr.setString(1, name);
            pr.setString(2, lastName);
            pr.setByte(3, age);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Statement st = con.createStatement()) {
            st.executeUpdate("delete from users where id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement st = con.createStatement()) {
            st.executeUpdate("delete from users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
