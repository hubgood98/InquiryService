package org.study.customerservicecenter.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.study.customerservicecenter.domain.Inquiry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InquiryRepository {

    private final JdbcTemplate jdbcTemplate;

    public InquiryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Inquiry> findAll() { //최신 글 순서부터 보기위해서
        String sql = "SELECT * FROM inquiries ORDER BY date DESC";
        return jdbcTemplate.query(sql, new InquiryRowMapper());
    }

    public List<Inquiry> findByCategory(String category) {
        String sql = "SELECT * FROM inquiries WHERE category = ? ORDER BY date DESC";
        return jdbcTemplate.query(sql, new Object[]{category}, new InquiryRowMapper());
    }
    public void save(Inquiry inquiry) {
        String sql = "INSERT INTO inquiries (category, title, content, author_id, answered, date) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, inquiry.getCategory(), inquiry.getTitle(),inquiry.getContent(), inquiry.getAuthorId(), inquiry.isAnswered(), inquiry.getDate());
    }

    public void remove(Inquiry inquiry) {
     String sql = "DELETE FROM inquiries WHERE id = ?";
    jdbcTemplate.update(sql, inquiry.getId());
    }

    public void update(Inquiry inquiry) {
        String sql = "UPDATE inquiries SET category = ?, title = ?, reply = ?, replyauthor = ?, content = ?, answered = ?, date = ? WHERE id = ?";
        jdbcTemplate.update(sql, inquiry.getCategory(), inquiry.getTitle(), inquiry.getReply(), inquiry.getReplyAuthor(), inquiry.getContent(), inquiry.isAnswered(), inquiry.getDate(), inquiry.getId());
    }

    public List<Inquiry> findByAuthor(String authorId) {
        String sql = "SELECT * FROM inquiries WHERE author_id = ? ORDER BY date DESC";
        return jdbcTemplate.query(sql, new Object[]{authorId}, new InquiryRowMapper());
    }

    public Inquiry findById(Long id) {
        String sql = "SELECT * FROM inquiries WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new InquiryRowMapper());
    }

    private static class InquiryRowMapper implements RowMapper<Inquiry> {
        @Override
        public Inquiry mapRow(ResultSet rs, int rowNum) throws SQLException {
            Inquiry inquiry = new Inquiry();
            inquiry.setId(rs.getLong("id"));
            inquiry.setCategory(rs.getString("category"));
            inquiry.setTitle(rs.getString("title"));
            inquiry.setContent(rs.getString("content"));
            inquiry.setAuthorId(rs.getString("author_id"));
            inquiry.setAnswered(rs.getBoolean("answered"));
            inquiry.setReply(rs.getString("reply"));
            inquiry.setReplyAuthor(rs.getString("replyauthor"));
            inquiry.setDate(rs.getTimestamp("date").toLocalDateTime());
            return inquiry;
        }
    }

}