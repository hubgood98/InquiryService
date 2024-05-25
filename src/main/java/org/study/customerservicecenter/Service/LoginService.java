package org.study.customerservicecenter.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.study.customerservicecenter.domain.User;
import org.study.customerservicecenter.repository.JdbcUserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class LoginService {

    private final JdbcTemplate jdbcTemplate;

    public LoginService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User login(String id, String pwd) {
        String sql = "select * from user where id = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserRowMapper());
            if (user != null && user.getPassword().equals(pwd)) {
                return user;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    //ResultSet의 각 행을 User객체로 변환하기 위해 사용
    private static class UserRowMapper implements RowMapper<User>{
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
