package fr._42.sockets.repositories;

import fr._42.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public  UsersRepositoryImpl(@Qualifier("dataSourceHikari") DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    "SELECT * FROM users WHERE username = ?",
                    (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username"), rs.getString("password")),
                    username
            ));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public User findById(long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM users WHERE id = ?",
                    (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username"), rs.getString("password")),
                    id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT id, username, password FROM users",((rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"))));

    }

    @Override
    public void save(User entity) {

        jdbcTemplate.update("insert into users(username, password) values (?,?)", entity.getUsername(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("update users SET  username = ?, password = ? WHERE id = ?",  entity.getUsername(), entity.getPassword(),entity.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("delete from users where id = ?", id);
    }

    @Override
    public void cleanAll()
    {
        jdbcTemplate.update("delete from users");
    }
}
