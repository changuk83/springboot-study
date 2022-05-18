package com.app.messages;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Slf4j
public class MessageRepositoryForJDBC {
    private DataSource dataSource;

    public MessageRepositoryForJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Message saveMessage(Message message) {
        log.info("Saved message : {}", message.getText());

        Connection c = DataSourceUtils.getConnection(dataSource);
        try {
            String insertQuery = "insert into messages(id, text, created_date) value (null, ?, ?)";
            PreparedStatement ps = c.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, message.getText());
            ps.setTimestamp(2, new Timestamp(message.getCreatedDate().getTime()));

            int rowAffected = ps.executeUpdate();

            if(rowAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    int id = rs.getInt(1);
                    return new Message(id, message.getText(), message.getCreatedDate());
                } else {
                    log.error("Failed to retrieve id. No row in result set");
                    return null;
                }
            } else {
                return null;
            }
        } catch(SQLException e) {
            log.error("Failed to save message", e);
            try {
                c.close();
            } catch (SQLException ex) {
                log.error("Failed to close connection", ex);
            }
        } finally {
            DataSourceUtils.releaseConnection(c, dataSource);
        }
        return null;
    }
}
