package _42.spring.service.repositories;

import _42.spring.service.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public  UsersRepositoryJdbcTemplateImpl(@Qualifier("dataSourceHikari") DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    "SELECT * FROM users WHERE email = ?",
                    (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("email"), rs.getString("password")),
                    email
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
                    (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("email"), rs.getString("password")),
                    id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
       return jdbcTemplate.query("SELECT id, email FROM users",((rs, rowNum) -> new User(rs.getLong("id"), rs.getString("email"))));

    }

    @Override
    public void save(User entity) {

        jdbcTemplate.update("insert into users(email, password) values (?,?)", entity.getEmail(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("update users SET  email = ?, password = ? WHERE id = ?",  entity.getEmail(), entity.getPassword(),entity.getIdentifier());
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
