package _42.spring.service.repositories;

import _42.spring.service.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryJdbcImpl implements UsersRepository {



    private final DataSource dataSource;


    @Autowired
    public UsersRepositoryJdbcImpl(
            @Qualifier("dataSourceSimple") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // ─── RowMapper helper — avoids repeating rs → User mapping ──────────────
    private User mapRow(ResultSet rs) throws SQLException {
        return new User(rs.getLong("id"), rs.getString("email"), rs.getString("password"));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT id, email, password FROM users WHERE email = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("findByEmail failed", e);
        }
    }

    @Override
    public User findById(long id) {
        String sql = "SELECT id, email, password FROM users WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("findById failed", e);
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT id, email, password FROM users";
        List<User> users = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                users.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("findAll failed", e);
        }
        return users;
    }

    @Override
    public void save(User entity) {
        String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getPassword());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("save failed", e);
        }
    }

    @Override
    public void update(User entity) {
        String sql = "UPDATE users SET email = ?, passowrd = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getPassword());
            ps.setLong(3, entity.getIdentifier());

            int rows = ps.executeUpdate();
            if (rows < 1) {
                throw new RuntimeException("update failed: no user with id " + entity.getIdentifier());
            }
        } catch (SQLException e) {
            throw new RuntimeException("update failed", e);
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            int rows = ps.executeUpdate();
            if (rows < 1) {
                throw new RuntimeException("delete failed: no user with id " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("delete failed", e);
        }
    }

    @Override
    public void cleanAll()
    {
        String sql = "DELETE FROM users";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            int rows = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("delete failed", e);
        }

    }

}