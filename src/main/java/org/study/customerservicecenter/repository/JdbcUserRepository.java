package org.study.customerservicecenter.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.study.customerservicecenter.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserRepository  {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
    public User save(User user) {
        String sql = "INSERT INTO user (id, password, name, admin) VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE password = VALUES(password), name = VALUES(name), admin = VALUES(admin)";
        jdbcTemplate.update(sql, user.getId(), user.getPassword(), user.getName(), user.isAdmin());
        return user;
    }


    public Optional<User> findById(String id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{id}, new UserRowMapper());
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }


    public Optional<User> findByName(String name) {
        String sql = "SELECT * FROM user WHERE name = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{name}, new UserRowMapper());
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }


    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }


    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setAdmin(rs.getBoolean("admin"));
            return user;
        }
    }
}